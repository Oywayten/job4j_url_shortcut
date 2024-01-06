# UrlShortCut
`RestFull API architecture application is url shortcut service`

### RestFul API architecture
> \- is a client-server application architecture based on 6 principles.
1. Uniform Interface
2. Stateless
3. Cacheable
4. Client-Server Orientation.
5. Layered System
6. Code on Demand

### Technology stack:
+ Java 17,
+ Maven 3.9.2,
+ Hibernate 5,
+ Spring boot 2,
+ PostgreSql 14,
+ Liquibase,
+ SLF4J,
+ Lombok,
+ Springdoc-OpenAPI

## Environment requirements:
+ Java 17,
+ Maven 3.8,
+ PostgreSQL 14

### Application launch

1. Install PostgreSQL: login - postgres, password - password;
2. Create shortcut database;
    ```postgres-sql
    CREATE DATABASE shortcut;
    ```
3. Build the project
    ```shell
    docker-compose build
   ```
4. Run the project
    ```shell
    docker-compose up
   ```

### Use application

1. Site registration.  

   The service can be used by different websites. Each site is given a pair of password and login.
   To register a site in the system, you need to send a request.
   
   URL `POST /api/v1/shortcut/registration`  
   With the body of a JSON object. `{site : "job4j.ru"}`
   ```shell
   curl --location 'localhost:8080/api/v1/shortcut/registration' \
   --header 'Content-Type: application/json' \
   --data '{
   "site":"example.com"
   }'
   ```
   Response from the server. `{registration : true/false, login: UNIQUE CODE, password : UNIQUE CODE}`  
   ```json
   {
       "registration": true,
       "login": "RsIegqwa7LxrGdTk",
       "password": "gp3M5Cozg0FCjvqd"
   }
   ```
   The registration flag indicates whether registration has been completed or not, that is, the site is already in the system.  

2. Authorization.  

   Authorization works through JWT. The user sends a POST request with login and password and receives a key.  
   ```shell
   curl --location 'localhost:8080/login' \
   --header 'Content-Type: application/json' \
   --data '{
   "login": "RsIegqwa7LxrGdTk",
   "password": "gp3M5Cozg0FCjvqd"
   }'
   ```

   This key is sent in the request in the HEAD block
   ```text
   Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSc0llZ3F3YTdMeHJHZFRrIiwiZXhwIjoxNjg2Nzg1MDI4fQ.sbAW7RNp0PdZ1vYicvXTg7EpU8ma7Te-l27vL3TqfvxRTChwEvL9a7U4WHHQOeMlkY0JxyXwdJUWlptcbHW_0w
   ```
   
3. Registration URL. 

   After the user has registered his site, he can send links to the site and receive converted links.

   Call `POST /api/v1/shortcut/convert`  
   With the body of a JSON object `{url: "https://job4j.ru/profile/exercise/106/task-view/532"}`
   ```shell
   curl --location 'localhost:8080/api/v1/shortcut/convert' \
   --header 'Content-Type: application/json' \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSc0llZ3F3YTdMeHJHZFRrIiwiZXhwIjoxNjg2Nzg1MDI4fQ.sbAW7RNp0PdZ1vYicvXTg7EpU8ma7Te-l27vL3TqfvxRTChwEvL9a7U4WHHQOeMlkY0JxyXwdJUWlptcbHW_0w' \
   --data '{"url": "https://example.com/profile/exercise/106/task-view/532"}'
   ```

   Response from the server `{code: UNIQUE CODE}`  
   ```json
   {
       "code": "Vap5hd5B"
   }
   ```
   The key `Vap5hd5B` is associated with the URL `https://job4j.ru/profile/exercise/106/task-view/532`.

4. Forwarding. Executed without authorization.

   When a site sends a link with a code, the associated address and a 302 status are returned in response.
   
   Call `GET /api/v1/shortcut/redirect/UNIQUE CODE`
   ```shell
   curl --location 'localhost:8080/api/v1/shortcut/redirect/Vap5hd5B'
   ```
   
   Response from server in header `HTTP CODE - 302 REDIRECT URL`
   ```text
   HTTP CODE - 302 s.com/profile/exercise/106/task-view/521321 
   ```

5. Statistics.

   The service counts the number of calls to each address.
   On the site you can get the statistics of all addresses and the number of calls to this address.  
   Call `GET /api/v1/shortcut/statistic`  
      ```shell
   curl --location 'localhost:8080/api/v1/shortcut/statistic' \
   --header 'Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSc0llZ3F3YTdMeHJHZFRrIiwiZXhwIjoxNjg2Nzg1MDI4fQ.sbAW7RNp0PdZ1vYicvXTg7EpU8ma7Te-l27vL3TqfvxRTChwEvL9a7U4WHHQOeMlkY0JxyXwdJUWlptcbHW_0w'
   ```  
   Response from the server
   ```json
   [
       {
           "url": "example.com/profile/exercise/106/task-view/532",
           "total": 0
       }
   ]
   ```

### Contacts
+ email: [oywayten+git@gmail.com](mailto:oywayten+git@gmail.com)
+ telegram: [@VitaliyJVM](https://t.me/VitaliyJVM/ "go to t.me/VitaliyJVM")
