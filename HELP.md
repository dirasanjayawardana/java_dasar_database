# JDBC (Java Database Connection)
Merupakan API standard untuk mengakses database di java.
JDBC tidak bisa langsung digunakan, karena isinya hanya interface, sehingga perlu implementasi.
Implementasi JDBC disebut dengan Driver, contohnya MySQL Driver, Oracle Drive, dll.

- App -> JDBC -> Driver -> Database

## How to Connect
- Download Driver sesuai dengan database yang digunakan
- Melakukan registrasi driver, bisa menggunakan static method registerDriver milik class java.sql.DriverManager

## Learning
- test/RegisterDriverTest.java
- test/ConnectionTest.java
- test/ConnectionPoolTest.java