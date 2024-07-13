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

SHOW TABLES

CREATE TABLE comments
(
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(100),
  comment TEXT,
  PRIMARY KEY (id)
) ENGINE = InnoDB

SELECT * FROM comments

CREATE Table sample_time
(
  id INT not null AUTO_INCREMENT,
  sample_date DATE,
  sample_time TIME,
  sample_timestamp TIMESTAMP,
  PRIMARY KEY (id)
) engine = InnoDB

SELECT * from sample_time