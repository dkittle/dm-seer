# Dm Seer
A place to integrate all the gaming knowledge


## Set up Elastic Beanstalk environment

`eb create dm-seer --timeout 20 -i t2.micro --cname dm-seer --elb-type application --database.engine postgres --database.instance db.t3.micro --database.username postgres --database.password *any password*`

`sudo yum install postgresql-devel.x86_64`

Edit Load Balancer config to set monitoring process endpoint to `/health`
