# Examen Final

Antes de ejecutar, crear un archivo .env en la raíz del proyecto con la siguiente información:

```
DATABASE_URL=jdbc:mysql://localhost:3306/app
DATABASE_USERNAME=root
DATABASE_PASSWORD=root

SQL_INIT_MODE=always
JPA_HIBERNATE_DDL_AUTO=create
JPA_HIBERNATE_DIALECT=org.hibernate.dialect.MySQLDialect

CORS_ALLOWED_ORIGINS=http://localhost:3000,https://example.com
```
