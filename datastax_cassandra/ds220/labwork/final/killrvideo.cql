DROP KEYSPACE IF EXISTS killr_video;

CREATE KEYSPACE killr_video WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE killr_video;

CREATE TABLE users_by_email (
   email TEXT,
   password TEXT,
   user_id UUID,
   PRIMARY KEY ((email))
);

CREATE TABLE users (
   user_id UUID,
   email TEXT,
   first_name TEXT,
   last_name TEXT,
   registration_date TIMESTAMP,
   PRIMARY KEY((user_id))
);

CREATE TABLE videos_by_user (
   user_id UUID,
   video_id TIMEUUID,
   title TEXT,
   type TEXT,
   tags SET<TEXT>,
   preview_thumbnails MAP<int, BLOB>,
   PRIMARY KEY ((user_id), video_id))
WITH CLUSTERING ORDER BY (video_id DESC);

CREATE TABLE comments_by_user (
   user_id UUID,
   posted_timestamp TIMEUUID,
   video_id TIMEUUID,
   comment TEXT,
   title TEXT,
   type TEXT,
   tags SET<TEXT>,
   preview_thumbnails MAP<INT, BLOB>,
   PRIMARY KEY ((user_id), posted_timestamp, video_id))
WITH CLUSTERING ORDER BY (posted_timestamp DESC, video_id ASC);

CREATE TABLE comments_by_video (
   video_id TIMEUUID,
   posted_timestamp TIMESTAMP,
   user_id UUID,
   comment TEXT,
   title TEXT STATIC,
   type TEXT STATIC,
   tags SET<TEXT> STATIC,
   preview_thumbnails MAP<INT,BLOB> STATIC,
   PRIMARY KEY ((video_id), posted_timestamp, user_id))
WITH CLUSTERING ORDER BY (posted_timestamp DESC, user_id ASC);

CREATE TABLE latest_videos (
   video_bucket INT,
   video_id TIMEUUID,
   title TEXT,
   type TEXT,
   tags SET<TEXT>,
   preview_thumbnails MAP<INT,BLOB>,
   PRIMARY KEY ((video_bucket), video_id))
WITH CLUSTERING ORDER BY (video_id DESC);

CREATE TABLE ratings_by_video (
   video_id TIMEUUID,
   num_ratings COUNTER,
   sum_ratings COUNTER,
   PRIMARY KEY ((video_id))
);

CREATE TYPE encoding_type (
   encoding TEXT,
   height INT,
   width INT,
   bit_rates SET<TEXT>
);

CREATE TABLE videos (
   video_id TIMEUUID,
   user_id UUID,
   title TEXT,
   description TEXT,
   type TEXT,
   url TEXT,
   release_date TIMESTAMP,
   avg_rating FLOAT,
   mpaa_rating TEXT,
   encoding FROZEN<encoding_type>,
   tags SET<TEXT>,
   preview_thumbnails MAP<INT,BLOB>,
   genres SET<TEXT>,
   PRIMARY KEY ((video_id))
);

CREATE TABLE trailers_by_video (
   video_id TIMEUUID,
   title TEXT,
   trailer_id TIMEUUID,
   type TEXT,
   tags SET<TEXT>,
   preview_thumbnails MAP<INT,BLOB>,
   PRIMARY KEY ((video_id), title, trailer_id)
);

CREATE TABLE actors_by_video (
   video_id TIMEUUID,
   actor_name TEXT,
   character_name TEXT,
   PRIMARY KEY ((video_id), actor_name, character_name)
);

CREATE TABLE video_interactions_by_user_video (
   user_id UUID,
   video_id TIMEUUID,
   event_timestamp TIMESTAMP,
   event_type TEXT,
   video_timestamp TIMESTAMP,
   PRIMARY KEY ((user_id, video_id), event_timestamp))
WITH CLUSTERING ORDER BY (event_timestamp DESC);
