package com.xinhua.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinhua.dao.BookDao;
import com.xinhua.domain.Book;
import com.xinhua.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IBookServiceImpl extends ServiceImpl<BookDao,Book> implements IBookService {

}
