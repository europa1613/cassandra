cqlsh:pluralsight> create table views_by_date (
               ... id varchar,
               ... viewed_date timestamp,
               ... primary key (id, viewed_date)
               ... ) with clustering order by (viewed_date desc);
cqlsh:pluralsight> desc table views_by_date;

CREATE TABLE pluralsight.views_by_date (
    id text,
    viewed_date timestamp,
    PRIMARY KEY (id, viewed_date)
) WITH CLUSTERING ORDER BY (viewed_date DESC)
    AND bloom_filter_fp_chance = 0.01
    AND caching = {'keys': 'ALL', 'rows_per_partition': 'NONE'}
    AND comment = ''
    AND compaction = {'class': 'org.apache.cassandra.db.compaction.SizeTieredCompactionStrategy', 'max_threshold': '32', 'min_threshold': '4'}
    AND compression = {'chunk_length_in_kb': '64', 'class': 'org.apache.cassandra.io.compress.LZ4Compressor'}
    AND crc_check_chance = 1.0
    AND dclocal_read_repair_chance = 0.1
    AND default_time_to_live = 0
    AND gc_grace_seconds = 864000
    AND max_index_interval = 2048
    AND memtable_flush_period_in_ms = 0
    AND min_index_interval = 128
    AND read_repair_chance = 0.0
    AND speculative_retry = '99PERCENTILE';

cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-1', '2020-12-05');
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-2', '2020-12-04');
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-3', '2020-12-03');
cqlsh:pluralsight> select * from views_by_date;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-3 | 2020-12-03 00:00:00.000000+0000
 page-2 | 2020-12-04 00:00:00.000000+0000

(3 rows)
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-1', '2020-12-03');
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-1', '2020-12-04');
cqlsh:pluralsight> select * from views_by_date;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-1 | 2020-12-04 00:00:00.000000+0000
 page-1 | 2020-12-03 00:00:00.000000+0000
 page-3 | 2020-12-03 00:00:00.000000+0000
 page-2 | 2020-12-04 00:00:00.000000+0000

(5 rows)
cqlsh:pluralsight> select * from views_by_date where viewed_date > '2020-12-04';
InvalidRequest: Error from server: code=2200 [Invalid query] message="Cannot execute this query as it might involve data filtering and thus may have unpredictable performance. If you want to execute this query despite the performance unpredictability, use ALLOW FILTERING"
cqlsh:pluralsight> select * from views_by_date where viewed_date > '2020-12-04' allow filtering;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000

(1 rows)
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-04' allow filtering;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-1 | 2020-12-04 00:00:00.000000+0000
 page-2 | 2020-12-04 00:00:00.000000+0000

(3 rows)
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-04';
InvalidRequest: Error from server: code=2200 [Invalid query] message="Cannot execute this query as it might involve data filtering and thus may have unpredictable performance. If you want to execute this query despite the performance unpredictability, use ALLOW FILTERING"
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-04' and id='page-1';

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-1 | 2020-12-04 00:00:00.000000+0000

(2 rows)
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-04' and id='page-1';
cqlsh:pluralsight> select * from views_by_date
               ... ;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-1 | 2020-12-04 00:00:00.000000+0000
 page-1 | 2020-12-03 00:00:00.000000+0000
 page-3 | 2020-12-03 00:00:00.000000+0000
 page-2 | 2020-12-04 00:00:00.000000+0000

(5 rows)
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-1', '2020-12-02');
cqlsh:pluralsight> insert into views_by_date (id, viewed_date) values('page-1', '2020-12-01');
cqlsh:pluralsight> select * from views_by_date ;

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-05 00:00:00.000000+0000
 page-1 | 2020-12-04 00:00:00.000000+0000
 page-1 | 2020-12-03 00:00:00.000000+0000
 page-1 | 2020-12-02 00:00:00.000000+0000
 page-1 | 2020-12-01 00:00:00.000000+0000
 page-3 | 2020-12-03 00:00:00.000000+0000
 page-2 | 2020-12-04 00:00:00.000000+0000

(7 rows)
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-02' viewed_date<'2020-12-05' and and id='page-1';
SyntaxException: line 1:62 mismatched input 'viewed_date' expecting EOF (...views_by_date where viewed_date >= '2020-12-02' [viewed_date]...)
cqlsh:pluralsight> select * from views_by_date where viewed_date >= '2020-12-02' and viewed_date<'2020-12-05' and id='page-1';

 id     | viewed_date
--------+---------------------------------
 page-1 | 2020-12-04 00:00:00.000000+0000
 page-1 | 2020-12-03 00:00:00.000000+0000
 page-1 | 2020-12-02 00:00:00.000000+0000

(3 rows)
cqlsh:pluralsight>