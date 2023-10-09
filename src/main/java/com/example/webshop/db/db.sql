-- Drop the database if it exists
DROP DATABASE IF EXISTS webshop;

-- Create the database
CREATE DATABASE webshop;

-- Use the database
USE webshop;

CREATE TABLE T_User (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'customer', 'warehouse_staff') NOT NULL,
    token VARCHAR(255) NOT NULL unique,
    isActive BOOLEAN NOT NULL DEFAULT 1
);

INSERT INTO T_User (email, password, role, token) VALUES
('Balder@kth.se','password1', 'admin', UUID()),
('Tim@kth.se','password2', 'customer', UUID()),
('Alex@kth.se','password3', 'warehouse_staff', UUID());


CREATE TABLE T_Item (
    itemID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    price INT NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    category VARCHAR(255) NOT NULL,
    status ENUM('IN_STOCK', 'OUT_OF_STOCK') NOT NULL,
    active BOOLEAN NOT NULL DEFAULT 1
);

INSERT INTO T_Item (name, price, description, quantity, category, status) VALUES
('Item1', 10, 'Description1', 10, "mug", 'IN_STOCK'),
('Item2', 20, 'Description2', 0, "pen", 'OUT_OF_STOCK'),
('Item3', 30, 'Description3', 5, "table",'IN_STOCK');



CREATE TABLE T_Category (
    itemID INT,
    category VARCHAR(50),
    PRIMARY KEY (itemID, category),
    FOREIGN KEY (itemID) REFERENCES T_Item(itemID)
);

INSERT INTO T_Category (itemID, category) VALUES
(1, 'Electronics'),
(2, 'Books'),
(3, 'Clothing');

CREATE TABLE T_Order (
    orderID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    date DATE NOT NULL,
    status ENUM('active', 'sent', 'cancelled') NOT NULL,
    FOREIGN KEY (userID) REFERENCES T_User(userID)
);
INSERT INTO T_Order (userID, date, status) VALUES
(1, '2023-10-01', 'active'),
(2, '2023-10-02', 'sent'),
(3, '2023-10-03', 'cancelled');

CREATE TABLE T_PurchaseItems (
    orderID INT,
    itemID INT,
    quantity INT NOT NULL,
    PRIMARY KEY (orderID, itemID),
    FOREIGN KEY (orderID) REFERENCES T_Order(orderID),
    FOREIGN KEY (itemID) REFERENCES T_Item(itemID)
);

INSERT INTO T_PurchaseItems (orderID, itemID, quantity) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 1);










SELECT * from T_User;
SELECT * from T_Item;
SELECT * from T_Order;
SELECT * from T_PurchaseItems;
SELECT * FROM T_Item WHERE active = 1;