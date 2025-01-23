# timing-books

Timing Books is a Java Web project based on the Shang Silicon Valley bookshop tutorial. 
Regular users can browse and purchase books, while administrators can manage inventory through a backend management system.

### 1.1 Frontend Features

The frontend module provides essential features for **users**, including:
- User Login and Registration
- Browsing and Shopping
    - Paginated display of books on the homepage
    - Browsing books
    - Filtering books by price
- Shopping Cart
    - Adding items to the cart
    - Removing items from the cart
    - Clearing the shopping cart

![login](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E7%99%BB%E5%BD%95.png)

![browing books on homepage](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E9%A6%96%E9%A1%B5%E6%B5%8F%E8%A7%88%E5%9B%BE%E4%B9%A6.png)

![shopping cart](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E8%B4%AD%E7%89%A9%E8%BD%A6.png)


### 1.2 Backend Features

The backend module provides management functionalities for administrators, including:
- Book Management
    - Adding books
    - Modifying book information and inventory
    - Deleting books
- Order Management (Under development)

## 2. Technologies Used
- Java as the backend development language
- Servlets for handling requests from the web frontend and returning responses
- JSP for creating dynamic frontend pages
- MySQL database for storing user and book information
- Filters for backend access control

![Java EE 3-Tier Architecture](https://github.com/kathy-kx/timing-books/blob/main/book_images/JavaEE%E4%B8%89%E5%B1%82%E7%BB%93%E6%9E%84.jpg)

## 3. Project Setup
1.  Download the code and the SQL files.
2.  Update the jdbc.properties file in the src directory with your database username and password.
3.  Launch Navicat and connect to the database:
    - Right-click on the local connection (localhost) and select “Execute SQL File.”
    - Sequentially execute the following files: 创建数据库和表.sql, 创建图书模块库和表.sql, and 创建订单模块表.sql.
    
    ![database](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E6%95%B0%E6%8D%AE%E5%BA%93.png)
    
4.  Run the project in IntelliJ IDEA and access it via http://localhost:8080/book/.
