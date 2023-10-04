-- Drop the database if it exists
DROP DATABASE IF EXISTS webshop;

-- Create the database
CREATE DATABASE webshop;

-- Use the database
USE webshop;

CREATE TABLE T_User (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'customer', 'warehouse_staff') NOT NULL,
    token VARCHAR(255) NOT NULL unique
);

CREATE TABLE T_Item (
    itemID INT PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    price INT NOT NULL,
    description TEXT,
    quantity INT NOT NULL,
    status ENUM('IN_STOCK', 'OUT_OF_STOCK') NOT NULL
);
INSERT INTO T_Item (name, price, description, quantity, status) VALUES
('Item1', 10, 'Description1', 10, 'IN_STOCK'),
('Item2', 20, 'Description2', 0, 'OUT_OF_STOCK'),
('Item3', 30, 'Description3', 5, 'IN_STOCK');



CREATE TABLE T_Category (
    itemID INT,
    category VARCHAR(50),
    PRIMARY KEY (itemID, category),
    FOREIGN KEY (itemID) REFERENCES T_Item(itemID)
);

CREATE TABLE T_Order (
    orderID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT,
    date DATE NOT NULL,
    status ENUM('pack', 'sent', 'cancelled') NOT NULL,
    FOREIGN KEY (userID) REFERENCES T_User(userID)
);

CREATE TABLE T_PurchaseItems (
    orderID INT,
    itemID INT,
    quantity INT NOT NULL,
    PRIMARY KEY (orderID, itemID),
    FOREIGN KEY (orderID) REFERENCES T_Order(orderID),
    FOREIGN KEY (itemID) REFERENCES T_Item(itemID)
);

INSERT INTO T_User (name, password, role, token) VALUES
('Balder','password1', 'admin', UUID()),
('Tim','password2', 'customer', UUID()),
('Alex','password3', 'warehouse_staff', UUID());


INSERT INTO T_Category (itemID, category) VALUES
(1, 'Electronics'),
(2, 'Books'),
(3, 'Clothing');

INSERT INTO T_Order (userID, date, status) VALUES
(1, '2023-10-01', 'pack'),
(2, '2023-10-02', 'sent');



INSERT INTO T_PurchaseItems (orderID, itemID, quantity) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 1);


SELECT * from T_Item;