package com.kxzhu.service.impl;

import com.kxzhu.dao.BookDao;
import com.kxzhu.dao.impl.BookDaoImpl;
import com.kxzhu.pojo.Book;
import com.kxzhu.pojo.Page;
import com.kxzhu.service.BookService;

import java.util.List;

/**
 * @ClassName BookServiceImpl
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-04 16:58
 */
public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }



    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //造Page对象
        Page<Book> page = new Page<>();

        //传入参数，设置每页显示的数量
        page.setPageSize(pageSize);     //每页显示的数量

        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);//传入page对象

        //总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //传入参数，设置当前页码
        //如有页码越界，会在Page类的setPageNo方法中重新设置pageNo为pageTotal
        page.setPageNo(pageNo);         //当前页码

        //求begin：此页码第一条数据的索引。第1条数据的索引从0开始，即第n条数据的索引=n-1
        int begin = (page.getPageNo() - 1) * pageSize;

        //当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }

    /**
     * 处理价格搜索功能
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //造Page对象
        Page<Book> page = new Page<>();

        //传入参数，设置每页显示的数量
        page.setPageSize(pageSize);     //每页显示的数量

        //总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);//传入page对象

        //总页码
        Integer pageTotal = pageTotalCount/pageSize;
        if(pageTotalCount % pageSize > 0){
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);

        //传入参数，设置当前页码
        //如有页码越界，会在Page类的setPageNo方法中重新设置pageNo为pageTotal
        page.setPageNo(pageNo);         //当前页码

        //求begin：此页码第一条数据的索引。第1条数据的索引从0开始，即第n条数据的索引=n-1
        int begin = (page.getPageNo() - 1) * pageSize;

        //当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
