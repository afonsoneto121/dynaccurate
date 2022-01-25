# Documentation 

The documentation generate through Postman https://documenter.getpostman.com/view/16544315/UVe9SVdK

## Environment Variables

* APP_PORT
* DB_USER
* DB_PASS
* DB_HOST
* DB_PORT
* RABBIT_HOST
* RABBIT_PORT
* RABBIT_USER
* RABBIT_PASS



## Endpoints

### Save user

```
POST :8085/api/v1/users
```

**Body**

```
{
    "nickname": "afonso"
}
```

**Response**

- 201 CREATED
- 400 BAD REQUEST



### Update User

```
PUT /api/v1/users/{idUser}
```

**Body**

```
{
    "nickname": "afonso neto"
}
```

**Response**

- 200 OK
- 400 Bad Request



### Delete User

```
DELETE /api/v1/users/{idUser}
```

**Header**

```
AUTHORIZATION Bearer token
```

**Response**

- 204 NO CONTENT



### Find user by ID

```
GET /api/v1/users/{idUser}
```



**Response**

- 200 OK
- 404 NOT FOUND



### Find all user

```
GET /api/v1/users/
```



**Response**

- 200 OK



### Find user events

```
GET /api/v1/events/user/{nicknameUser}
```

**Params**

<table>
    <tr>
    	<th>toDate</th>
        <th>yyyy-mm-ddThh:mm:ss</th>
    </tr>
    <tr>
        <th>fromDate</th>
        <th>yyyy-mm-ddThh:mm:ss</th>
    </tr>
    <tr>
        <th>sort</th>
        <th>attribute,[desc | asc]</th>
    </tr>
    <tr>
        <th>page</th>
        <th>int</th>
    </tr>
    <tr>
        <th>size</th>
        <th>int</th>
    </tr>
</table>



**Response**

- 200 OK

## 
