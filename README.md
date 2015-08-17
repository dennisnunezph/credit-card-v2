# Credit Card Service Boot

# Installation

1. Download and import database schema. 
 - clone or update credit card domain repository https://github.com/compareasiagroup/creditcard-service-domain

 - import database schema `creditcard-service-domain/data/schema.sql` 


2. Update `application.properties` with local data base credentials. 

    ```
     spring.datasource.url=jdbc:mysql://localhost:3306/creditcard
     # Replace with your credentials
     spring.datasource.username=user
     spring.datasource.password=pass
     spring.datasource.driverClassName=com.mysql.jdbc.Driver
     # Hibernate
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
     spring.jpa.properties.hibernate.show_sql=false
     spring.jpa.properties.hibernate.hbm2ddl.auto=update
    ```

# Run the application

``` 
$ mvn clean spring-boot:run
``` 

## Make a local request
This example uses `en-ID` locale

```
$ mvn clean spring-boot:run
``` 

``` 
$ curl -H "Content-Type: application/json" -d '{"locale": "en-ID"}' http://localhost:8080/compare
```

## Run the integration Tests
This tests check the JSON response for each country 

```
$ mvn clean install -P integration
``` 

Generate markdown results output
```
$ mvn clean install -P integration 2> results.md 
``` 
