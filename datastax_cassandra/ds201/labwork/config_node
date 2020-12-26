#!/bin/sh
dir="node$1"
#
# Modify the yaml file values - including:
#       num_tokens: 1
#       listen_address: 127.0.0.?
#       rep_address: 127.0.0.?
#       seeds: 127.0.0.1
#       data_file_directories: /Users/arvins-mac/Downloads/datastax_cassandra/node?/data/data
#       commitlog_directory: /Users/arvins-mac/Downloads/datastax_cassandra/node?/data/commit-log
#       hints_directory: /Users/arvins-mac/Downloads/datastax_cassandra/node?/data/hints
#       saved_caches_directory: /Users/arvins-mac/Downloads/datastax_cassandra/node?/data/saved-caches
#
if [ -z $1 ]
        then
        num="1"
else
        num="$1"
fi
echo "modifying /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra.yaml as follows:"
echo "\tnum_tokens: 1"
listen_address="127.0.0.$num"
echo "\tlisten_addresss: $listen_address"
rpc_address="127.0.0.$num"
echo "\trpc_address: $rpc_address"
echo "\tseeds: 127.0.0.1, 127.0.0.2, 127.0.0.3"
data_file_directories="/Users/arvins-mac/Downloads/datastax_cassandra/$dir/data/data"
echo "\tdata_file_directories: $data_file_directories"
commitlog_directory="/Users/arvins-mac/Downloads/datastax_cassandra/$dir/data/commit-log"
echo "\tcommitlog_directory: $commitlog_directory"
hints_directory="/Users/arvins-mac/Downloads/datastax_cassandra/$dir/data/hints"
echo "\thints_directory: $hints_directory"
saved_caches_directory="/Users/arvins-mac/Downloads/datastax_cassandra/$dir/data/saved-caches"
echo "\tsaved_caches_directory: $saved_caches_directory"
echo "\tintital_token: 0"
cdc_raw_directory="/Users/arvins-mac/Downloads/datastax_cassandra/$dir/data/cdc_raw"
echo "\tcdc_raw_directory: $cdc_raw_directory"
if [ -z $1 ]
        then
        native_transport_port="9042"
else
        native_transport_port="904$num"
fi
echo "\tnative_transport_port: $native_transport_port"
sed "
s/^\(#[ \t]*\)*num_tokens:[ \t]*[0-9][0-9]*/num_tokens: 1/
s/^listen_address:.*$/listen_address: $listen_address/
s/^rpc_address:.*$/rpc_address: $rpc_address/
s/seeds:.*$/seeds: \"127.0.0.1, 127.0.0.2, 127.0.0.3\"/      
s|[ \t]*-[ \t]*/var/lib/cassandra/data|   - $data_file_directories|
s|^commitlog_directory:.*$|commitlog_directory: $commitlog_directory|
s|^hints_directory:.*$|hints_directory: $hints_directory|
s|^saved_caches_directory:.*$|saved_caches_directory: $saved_caches_directory|
s|^cdc_raw_directory:.*$|cdc_raw_directory: $cdc_raw_directory|
s|^native_transport_port:.*$|native_transport_port: $native_transport_port|
s/^\(#[ \t]*\)*initial_token:[ \t]*[0-9]*/initial_token: 0/" \
 < /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra.yaml > /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra.yaml_new
#
# rename the new yaml file to the old name
#
mv /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra.yaml_new /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra.yaml
#
# modify the env.sh file
#       MAX_HEAP_SIZE="512M"
#       HEAP_NEWSIZE="128M"
#       JMX_PORT="7?99"
#
portend="99"
portno="7$num$portend"
echo "modifying /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra-env.sh as follows:"
echo "\tMAX_HEAP_SIZE=\"512M\""
echo "\tHEAP_NEWSIZE=\"128M\""
echo "\tJMX_PORT=\"$portno\""
sed "
s/^\(#[ \t]*\)*MAX_HEAP_SIZE=.*$/MAX_HEAP_SIZE=\"512M\"/
s/^\(#[ \t]*\)*HEAP_NEWSIZE=.*$/HEAP_NEWSIZE=\"128M\"/
s/^\(#[ \t]*\)*JMX_PORT=.*$/JMX_PORT=\"$portno\"/" \
 < /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra-env.sh > /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra-env.sh_new
#
# rename the new env.sh file to the old name
#
mv /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra-env.sh_new /Users/arvins-mac/Downloads/datastax_cassandra/$dir/resources/cassandra/conf/cassandra-env.sh
