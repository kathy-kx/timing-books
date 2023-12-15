package com.kxzhu.dao.impl;

import com.kxzhu.dao.BookDao;
import com.kxzhu.pojo.Book;

import java.util.List;

/**
 * @ClassName BookDaoImpl
 * @Description TODO
 * @Author zhukexin
 * @Date 2022-10-04 15:56
 */
public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) VALUES(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "DELETE FROM t_book WHERE `id`=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE t_book SET `name`=?, `author`=?, `price`=?, `sales`=?, `stock`=?, `img_path`=? WHERE `id`=?";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) {
        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` FROM t_book WHERE `id`=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` FROM t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "SELECT COUNT(*) FROM t_book";
        Number count = (Number)queryForSingleValue(sql);//BaseDao中的方法public Object queryForSingleValue(String sql, Object...args)
        //使用此方法后查询后，可返回Number类型（Number是所有包装类的父类）
        return count.intValue();//Number -> Integer
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` FROM t_book limit ?,?";
        return queryForList(Book.class,sql, begin, pageSize);
    }

    //该价格区间的总记录数
    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "SELECT COUNT(*) FROM t_book where `price` between ? and ?";
        Number count = (Number)queryForSingleValue(sql, min, max);//BaseDao中的方法public Object queryForSingleValue(String sql, Object...args)
        //使用此方法后查询后，可返回Number类型（Number是所有包装类的父类）
        return count.intValue();//Number -> Integer
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "SELECT `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` FROM t_book where `price` between ? and ? order by `price` limit ?,?";
        return queryForList(Book.class,sql, min, max, begin, pageSize);
    }

}
