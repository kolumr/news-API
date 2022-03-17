# NEWS API
### Description
A REST API for querying and retrieving scoped news and information.

1. As a user I can add departments.
2. As a user I can add Users associated with a particular department.
3. As a user I can add news, retrieve all news or retrieve news belonging to a particular department.

### Concepts

1. PostgresSQL db
2. Java (Object Oriented Programming)
3. Spark Web Framework
4. GSON
5. Handlebars

### setup
Download Java Development kit 11. Previous or later versions may
have some minimal compatibility issue. Gradle is the tool used to
build and run the code. Preferred IDE is IntelliJ IDEA.

In PSQL:

CREATE DATABASE news_api;
\c news_api
CREATE TABLE news (id SERIAL PRIMARY KEY, headline VARCHAR, content VARCHAR, department_id INTEGER);
CREATE TABLE departments (id SERIAL PRIMARY KEY, name varchar, description VARCHAR);
CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR, position varchar, role VARCHAR, department_id INTEGER);

CREATE DATABASE news_api_test WITH TEMPLATE news_api;
## API ENDPOINTS AND PARAMETERS

Base URL : * Run IpConfig : get IP4 address.
http://YOURIP:3000/endpoint


| Endpoint|             Parameters(JSON)             |       Parameters(form-data)       | method | Status |
| ------- |:----------------------------------------:|:---------------------------------:|:------:|-------:|
|/departments/new |                                          |   (departmentName, description)   |  post  |    201 |
|/departments |    (id, departmentName, description)     |                                   |  get   |        |
|/news/new |                                          | (headline, content,departmentID)  |  post  |    201 |
|/users/new |                                          | (name,postion,role, departmentID) |  post  |    201 |
|users/:userId |                                          | (name,postion,role, departmentID) |  get  |        |
|departments/:departmentId/users | (userID,name,postion,role, departmentID) |  |  get   |        |

dependencies

implementation 'junit:junit:4.12'

testImplementation 'org.junit.jupiter:junit-jupiter-api:5.6.0'

testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine'

implementation "com.sparkjava:spark-core:2.6.0" //spark framework : java/kotlin based framework for creating web applications

implementation "com.sparkjava:spark-template-handlebars:2.5.5" //spark's template for creating layouts

implementation 'org.slf4j:slf4j-simple:1.7.21' //spark error formatting

implementation group: 'org.postgresql', name: 'postgresql', version: '9.4-1201-jdbc41+' //postgresql

implementation group: 'org.sql2o', name: 'sql2o', version: '1.5.4+' //library used to execute sql statements against your JDBC compliant database(postgres)

implementation group: 'org.postgresql', name: 'postgresql', version: '42.2.13' // ADD ME FOR POSTGRES VERSION 10.x

implementation 'com.google.code.gson:gson:2.5' //serialize and deserialize Java objects to JSON and vice versa

