-- Drop the database if it exists
DROP DATABASE IF EXISTS webshop;

-- Create the database
CREATE DATABASE webshop;

-- Use the database
USE webshop;

-- Create the users table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
-- Insert sample users
INSERT INTO users (username, password) VALUES ('alice', 'password123');
INSERT INTO users (username, password) VALUES ('bob', 'bobsecure');
INSERT INTO users (username, password) VALUES ('carol', 'carolpass');

-- Create the 'item' table
CREATE TABLE item (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);
-- Insert sample items
INSERT INTO item (name, description) VALUES ('Laptop', 'A portable computer.');
INSERT INTO item (name, description) VALUES ('Smartphone', 'A mobile device with advanced features.');
INSERT INTO item (name, description) VALUES ('Chair', 'A piece of furniture for sitting.');

