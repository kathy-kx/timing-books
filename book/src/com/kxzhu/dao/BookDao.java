package com.kxzhu.dao;

import com.kxzhu.pojo.Book;

import java.util.List;

/**
 * @ClassName BookDao
 * @Description 标准的增删改查
 * @Author zhukexin
 * @Date 2022-10-04 15:52
 */
public interface BookDao {
    public int addBook(Book book);

    public int deleteBookById(Integer id);

    public int updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    /**
     * @return 总记录数
     */
    public Integer queryForPageTotalCount();

    /**
     * @param begin 页码第一条数据的索引。索引从0开始，即第n条数据的索引=n-1
     * @param pageSize 每页的数据数量
     * @return 当前页的数据
     */
    public List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min, int max);//由bookService的pageByPrice()调用，用于按照价格获取数据

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);//由bookService的pageByPrice()调用，用于按照价格获取数据
}
