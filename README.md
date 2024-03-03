# junior-crud-service
#### This is my home CRUD project with many modern and defferent technologies



#### for local start 
- active profile "default"
- run docker-compose.yml, button services


#### Get Order example curl 
````
curl location  'http://localhost:8080/api/v1/orders/1' \
--header 'Content-Type: aplication/json' \
--data '{
    "id": 1,
    "date": "2111-01-13",
    "cost": 11.5,
    "product": [
        {
            "id": 5,
            "name": "toy",
            "cost": 1.0
        }
    ]
}'
````
#### Create Order example curl 
````
curl location 'http://localhost:8080/api/v1/orders' \
--header 'Content-Type: aplication/json' \
--data insert '{
    "date": "2122-01-13",
    "cost": 21.5,
    "product": [
        {
            "name": "toy",
            "cost": 12.0
        }
    ]
}'
````
