# Spring Data Cassandra

## Requirements
- JDK 8
- Datastax Cassandra 6.0.0 running on localhost

## Sample curls

### Get
```bash
curl -kv GET http://localhost:8080/people
curl -kv GET http://localhost:8080/people?first=Iron
curl -kv GET http://localhost:8080/people?last=Man
curl -kv GET http://localhost:8080/people?first_name_query=Captain
```
### Insert
```bash
curl -kv -X POST -H "Content-Type: application/json" -d '{
  "firstName": "Black",
  "dateOfBirth": "1986-10-13T00:00:00",
  "id": null,
  "lastName": "Panther",
  "salary": 200000.0
}' http://localhost:8080/people

curl -kv -X POST -H "Content-Type: application/json" -d '{
  "firstName": "Captain",
  "dateOfBirth": "1990-11-13T00:00:00",
  "id": null,
  "lastName": "Marvel",
  "salary": 250000.0
}' http://localhost:8080/people

```