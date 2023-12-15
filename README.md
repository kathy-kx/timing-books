# timing-books
有时书城是一个Java Web项目，基于尚硅谷书城搭建。普通用户可以在书城进行选购，管理员可以在后台管理系统中进行商品的库存管理。

### 1.1 前台功能

前台模块为**用户**提供了基础的功能，包含：

- 用户登录注册
- 选购
    - 在首页分页展示图书
    - 浏览商品
    - 根据价格筛选商品
- 购物车
    - 加入购物车
    - 删除商品
    - 清空购物车

![登录](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E7%99%BB%E5%BD%95.png)

![首页浏览图书](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E9%A6%96%E9%A1%B5%E6%B5%8F%E8%A7%88%E5%9B%BE%E4%B9%A6.png)

![购物车](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E8%B4%AD%E7%89%A9%E8%BD%A6.png)

### 1.2 后台功能

后台模块为管理员提供的管理系统。包含：

- 图书管理
    - 添加图书
    - 修改图书信息和库存
    - 删除图书
- 订单管理（开发中）

## 2. 使用技术

- 使用Java作为后端开发语言
- 使用Servlet接收来自Web前端的请求，处理这些请求，并返回响应
- 使用JSP创建动态前端页面。
- 使用MySQL数据库存储用户、图书信息
- 使用Filter过滤器实现后台权限检查

![Java EE三层结构](https://github.com/kathy-kx/timing-books/blob/main/book_images/JavaEE%E4%B8%89%E5%B1%82%E7%BB%93%E6%9E%84.jpg)

## 3. 项目启动

1. 下载代码和[sql文件](https://github.com/kathy-kx/timing-books/tree/main/book_db)。
2. 修改src/jdbc.properties文件中的数据库信息，即修改username和password
3. 启动navicat并连接数据库。
    - 右键本地连接localhost，选择execute SQL File
    - 依次选择“创建数据库和表.sql”、“创建图书模块库和表.sql”、“创建订单模块表.sql”
    
    ![数据库](https://github.com/kathy-kx/timing-books/blob/main/book_images/%E6%95%B0%E6%8D%AE%E5%BA%93.png)
    
4. 在IDEA中运行项目，访问地址：http://localhost:8080/book/
