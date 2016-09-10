## About

This is an example project that illustrates creating a RESTful API in Spring Boot.

## Runnning this project

```
mvn spring-boot:run
```

## Example commands

Getting all people from the API:
```
curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:8000/gigy/people
```

Posting new person to the API:
```
curl -H "Content-Type: application/json" -X POST -d '{"name":"Mark","age":40}' http://localhost:8000/gigy/people
{"id":4,"name":"Mark","age":40}
```

Delete person based on ID:
```
curl -X DELETE http://localhost:8000/gigy/people/2
```
