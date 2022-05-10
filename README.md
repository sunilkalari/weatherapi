## Environment:
- Java version: 17
- Maven version: 3.*
- Spring Boot version: 2.6.8

## Read-Only Files:
- src/test/*

## Data:
Sample request example of JSON data object:
```json
{
   "country": "uk",
   "city": "London",
   "apiKey": "KEY1"
}
```

Sample response example of JSON data object:
```json
{
    "description": "light intensity drizzle"
}
```

## Requirements:
The `REST` service to fetch weather data


`GET` request to `/weather`:
* the response code is 200
* the response body is description for records



The project by default supports the use of the H2 database.
