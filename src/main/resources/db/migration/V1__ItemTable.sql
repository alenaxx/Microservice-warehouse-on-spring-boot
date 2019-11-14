CREATE TABLE item (
     id UUID NOT NULL PRIMARY KEY,
     name VARCHAR (100) NOT NULL,
     price FLOAT NOT NULL,
     actualAmount VARCHAR (100),
     availableAmount VARCHAR (100)
);