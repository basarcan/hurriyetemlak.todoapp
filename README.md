# hurriyetemlak.todoapp.userapi

This is a todo app task for Hurriyet Emlak and it is a user api 

## Installation

First you need to pull docker image

```bash
docker pull cavitcanbasar/hurriyetemlak.userapi:latest
```

and than run couchbase

```bash
docker run --rm -d -p 8091-8094:8091-8094 -p 11210:11210 couchbase:community-6.0.0
```

create cluster

```bash
docker exec -it he-couchbase /opt/couchbase/bin/couchbase-cli cluster-init -c 127.0.0.1 \
--cluster-username=admin \
--cluster-password=123456 \
--cluster-port=8091 \
--cluster-ramsize=256 \
--services=data,query,index
```

create bucket

```bash
docker exec -it he-couchbase /opt/couchbase/bin/couchbase-cli bucket-create -c 127.0.0.1:8091 \
--bucket=user-bucket \
--bucket-type=couchbase \
--bucket-ramsize=128 \
--bucket-replica=1 \
-u user-bucket -p user-bucket
```

and run the docker image

```bash
docker run -i -t -p 8080:81 cavitcanbasar/hurriyetemlak.userapi
```


## Usage

You can view all the controllers from swagger
