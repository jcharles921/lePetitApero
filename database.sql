-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS coffee_ordering_system;

-- Use the newly created database
USE coffee_ordering_system;

-- Create the coffees table
CREATE TABLE IF NOT EXISTS coffees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    category VAR    CHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role INT DEFAULT 2
);

-- Create the orders table
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    coffee_id INT,
    user_id INT,
    quantity INT NOT NULL,
    status ENUM('completed', 'failed', 'pending') NOT NULL,
    FOREIGN KEY (coffee_id) REFERENCES coffees(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
