package com.xinhua.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinhua.dao.BookDao;
import com.xinhua.domain.Book;
import com.xinhua.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    //添加
    @Override
    public Boolean save(Book book) {
        return bookDao.insert(book) > 0;
    }

    //修改
    @Override
    public Boolean update(Book book) {
        return bookDao.updateById(book) > 0;
    }

    //删除
    @Override
    public Boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    //查询单个
    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    //查询全部
    @Override
    public List<Book> findAll() {
        return bookDao.selectList(null);
    }

    //分页
    @Override
    public IPage<Book> getPage(int current, int size) {
        IPage page = new Page(current,size);
        return bookDao.selectPage(page,null);
    }

}
