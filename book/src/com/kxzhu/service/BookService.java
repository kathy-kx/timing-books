package com.kxzhu.service;

import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;

import java.util.List;

/**
 * @ClassName BookService
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-04 16:56
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    /**
     * 处理分页业务
     * @param pageNo
     * @param pageSize
     * @return 当前需要显示的页面（内含数据）
     */
    public <T> Page<T> page(int pageNo, int pageSize);

    /**
     * 处理价格搜索功能
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);

}
