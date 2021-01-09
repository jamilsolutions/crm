DROP TABLE IF EXISTS CUSTOMER;
  
CREATE TABLE CUSTOMER (
  id INT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  address VARCHAR(250) NOT NULL,
  dateOfBirth DATE DEFAULT NULL,
  PRIMARY KEY (id)
);