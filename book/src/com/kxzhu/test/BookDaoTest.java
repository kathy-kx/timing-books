package com.kxzhu.test;

import com.kxzhu.dao.BookDao;
import com.kxzhu.dao.impl.BookDaoImpl;
import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ClassName BookDaoTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-04 16:31
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"鲁迅全集", "鲁迅", new BigDecimal(33.8), 1003, 98, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(13);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(13,"鲁迅全集", "鲁迅", new BigDecimal(39.8), 1003, 98, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(13);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,20));
    }

    @Test
    public void queryForPageItems() {
        for (Book book: bookDao.queryForPageItems(8, Page.PAGE_SIZE)){
            System.out.println(book);
        }
    }



    @Test
    public void queryForPageItemsByPrice() {
        for (Book book: bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE,10,20)){
            System.out.println(book);
        }
    }
}