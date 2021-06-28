# hackerrankspringbootapp
hackerrankspringbootapp

```console
curl -X 'POST' \
  'http://localhost:8000/app/item' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "itemId":2,
      "itemName":"item_x2",
      "itemEnteredByUser":"user_x",
      "itemEnteredDate":"2020-05-10T13:00:41.499",
      "itemBuyingPrice":56.0,
      "itemSellingPrice":59.0,
      "itemLastModifiedDate":"2020-05-10T13:00:41.498",
      "itemLastModifiedByUser":"user_y2",
      "itemStatus":"AVAILABLE"
}'
```
![alt text](https://raw.githubusercontent.com/imdadareeph/hackerrankspringbootapp/main/screenshots/1insert.png "preview1")

```console
curl -X 'GET' \
  'http://localhost:8000/app/item/1' \
  -H 'accept: */*'
```

![alt text](https://raw.githubusercontent.com/imdadareeph/hackerrankspringbootapp/main/screenshots/5ItemsById.png "preview3")


```console
curl -X 'GET' \
  'http://localhost:8000/app/itemstatus?itemStatus=AVAILABLE&enteredBy=user_x' \
  -H 'accept: */*'
```
![alt text](https://raw.githubusercontent.com/imdadareeph/hackerrankspringbootapp/main/screenshots/7itemstatus.png "preview7")


```console
curl -X 'GET' \
  'http://localhost:8000/app/item?pageSize=1&page=0&sortByField=itemEnteredByUser' \
  -H 'accept: */*'
```

![alt text](https://raw.githubusercontent.com/imdadareeph/hackerrankspringbootapp/main/screenshots/8pagination.png "preview8")

