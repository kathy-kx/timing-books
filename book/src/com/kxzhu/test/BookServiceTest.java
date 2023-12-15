package com.kxzhu.test;

import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;
import com.kxzhu.service.BookService;
import com.kxzhu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @ClassName BookServiceTest
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-04 17:01
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"老舍全集", "老舍", new BigDecimal(28.8), 398, 38, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(14);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(14,"老舍全集", "舒庆春", new BigDecimal(28.8), 398, 38, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(14);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books){
            System.out.println(book);
        }
    }


    @Test
    public void page() {
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,20));
    }
}