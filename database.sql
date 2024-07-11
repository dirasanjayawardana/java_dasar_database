CREATE TABLE customers
(
  id VARCHAR(100) NOT NULL,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  CONSTRAINT email_unique UNIQUE (email),
  PRIMARY KEY (id)
) ENGINE = InnoDB

DESC customers

SELECT * FROM customers

CREATE TABLE admin
(
  username VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  PRIMARY key (username)
) ENGINE = InnoDB

SELECT * FROM admin

INSERT INTO admin (username, password) VALUES ('admin', 'admin')