package com.xinhua.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xinhua.domain.Book;

import java.util.List;

public interface BookService {
    //添加
    Boolean save(Book book);

    //修改
    Boolean update(Book book);

    //删除
    Boolean delete(Integer id);

    //查询单个
    Book getById(Integer id);

    //查询全部
    List<Book> findAll();

    //分页
    IPage<Book> getPage(int current, int size);

}
