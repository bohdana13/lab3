### GET All
GET http://localhost:8080/api/v1/items/

### GET first one
GET http://localhost:8080/api/v1/items/1

### DELETE third one
DELETE http://localhost:8080/api/v1/items/3

### Create one
POST http://localhost:8080/api/v1/items/
Content-Type: application/json

{
  "name": "Test Item",
  "code": "ABC123",
  "description": "This is a test item."
}

#### Update Existing Item
PUT http://localhost:8080/api/v1/items/
Content-Type: application/json

{
  "id": 1,
  "name": "Updated Item",
  "code": "XYZ789",
  "description": "This item has been updated."
}

