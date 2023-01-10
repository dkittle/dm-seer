# Dm Seer
A place to integrate all the gaming knowledge

## Development Setup

### Local Setup

Install Docker

Install Postgres
```shell
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=some_password -d postgres:15-alpine
docker start -a postgres 
```

## Set up Elastic Beanstalk environment

`eb create b4b-dev --timeout 20 -i t2.micro --cname b4b-dev --elb-type application --database.engine postgres --database.instance db.t3.micro --database.username postgres --database.password *any password*`

`sudo yum install postgresql-devel.x86_64`

`psql -h [hostname] -U postgres -d dmseer -f dmseer.tar`

Edit Load Balancer config to set monitoring process endpoint to `/api/health`


## Manual curls



```shell
// Authenticate
curl -v -X POST http://localhost:3000/proxy/auth -H"Content-Type: application/json" -d '{"cobalt":"'$COBALT_TOKEN'"}'
// Campaigns
curl -v -X POST http://localhost:3000/proxy/campaigns -H"Content-Type: application/json" -d '{"cobalt":"'$COBALT_TOKEN'"}'
// Search Monsters
curl -v -X POST http://localhost:3000/proxy/monsters -H"Content-Type: application/json" -d '{"cobalt":"'$COBALT_TOKEN'"}'

```


```shell
curl -v http://localhost:8000/api/creature/1 -H"Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzIzNzM2NDcsInVzZXJuYW1lIjoiRG9uRGFETSJ9.dOq53ZaqXuc80ccrICkc46qJbUCuOQGw1KjSp6v8Um8" -H"Content-Type: application/json" -H"user_session: eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzIzNzM2NDcsInVzZXJuYW1lIjoiRG9uRGFETSJ9.dOq53ZaqXuc80ccrICkc46qJbUCuOQGw1KjSp6v8Um8"   

```
