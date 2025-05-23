# Cafe Management System (Java Swing)

A simple desktop-based Cafe Management System built with Java Swing for the GUI, backed by MySQL database access via JDBC.  
This application allows you to manage cafe menu items and orders with an easy-to-use graphical interface.

---

## Features

- Add, view, and manage cafe menu items (name and price)
- Place orders by selecting menu items and specifying quantity
- View current orders with the ability to cancel any order
- Uses MySQL as backend database
- Modular design separating data access, service layer, and Swing UI

---

## Technologies Used

- Java (JDK 8 or higher)
- Java Swing (for GUI)
- MySQL (database)
- JDBC (database connectivity)

---

## Prerequisites

- Java JDK installed (version 8 or higher recommended)
- MySQL installed and running
- MySQL user with proper privileges for the database
- IDE or command line environment to compile and run Java programs

---

## Setup Instructions

1. **Clone this repository**

   ```bash
   git clone https://github.com/yourusername/cafe-management-system.git
   cd cafe-management-system

## Project Structure

├── DBConnection.java         # Database connection utility
├── MenuItem.java             # Menu item model class
├── Order.java                # Order model class
├── MenuDAO.java              # DAO for menu table
├── OrdersDAO.java            # DAO for orders table
├── CafeService.java          # Service layer handling business logic
└── CafeSwingApp.java         # Java Swing GUI main application

