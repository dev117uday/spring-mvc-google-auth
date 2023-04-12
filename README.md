# Read Me First

- create `application.properties`

```properties
spring.config.import:optional:secrets.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=postgres
spring.datasource.password=${secret.DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.security.oauth2.client.registration.google.client-id=${secret.client_id}
spring.security.oauth2.client.registration.google.client-secret=${secret.client_secret}
```

- create `secrets.properties`

```properties
secret.DB_PASSWORD=
secret.client_id=7
secret.client_secret=
```