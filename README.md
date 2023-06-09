bet-parser
=

Convert html data to java object. </br>
Contains data using in-memory H2 database </br>

Opportunities:
=

On main page (`http://localhost:3000`) can see statistics from `https://lite.betbonanza.com/` as
table

* can sort by `id` and `date start`
* by default enabled pagination with opportunity to chose page size
* `update statistics button` trigger re-fetch of all data from website, will remove all contained
  statistics and rewrite it
* `download as html button` trigger download all statistics as html page with table

Available modules
==

* `src-be` - backend entry point
* `src-fe` - frontend entry point

Request data
==

* GET -> `http://localhost:8080/statistics` to get page of statistics (by default 20 elements)
* PATCH -> `http://localhost:8080/statistics` re-fetch of all data from website, will remove all contained
  statistics and rewrite it
* GET -> `http://localhost:8080/statistics/download/html` download as html table

Building aps
==

By default:

* backend start on port 8080 
  --- to change in file `src-be/src/main/resources/application.properties` change value of `server.port=` 
* frontend start on port 3000

To change in file `docker-compose.yml` change to necessary ports

Building app using Docker
===

* from root folder run `docker-compose build` to build containers
* run application using `docker-compose up`

Start application for development
===

Project requirements:
* jdk-17+
* mvn-3.8+
* npm 6.14+

### Start as Spring Boot application from IDE

* open `src-be` as maven module
* run `main` method in `com.example.SiteParserApplication.java`

### Start as Maven project

* open `src-be` directory
* in command line run `mvn spring-boot:run`

### Start frontend

* open `src-fe` directory
* in file `src.setupProxy.js` change host from `be` to `localhost`
* run `npm install`
* run `npm start`