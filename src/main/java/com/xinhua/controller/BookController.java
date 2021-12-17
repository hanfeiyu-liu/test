package com.xinhua.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinhua.domain.Book;
import com.xinhua.service.IBookService;
import com.xinhua.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService iBookService;
    //public static  final Logger logger= LoggerFactory.getLogger(BookController.class);


    //查找全部
    @RequestMapping("/list")
    public R list() {
        List<Book> list = iBookService.list();

        return new R(true,list);

    }

    //添加
    @RequestMapping(value = "/insert")
    public R insert(@RequestBody Book book) {

        boolean save = iBookService.save(book);
        return new R(save, save ? "添加成功" : "添加失败");
    }

    //删除
    @RequestMapping("/delete/{id}")
    public R delete(@PathVariable() Integer id) {

        boolean b = iBookService.removeById(id);
        return new R(b, b ? "删除成功" : "数据已被删除，自动刷新");
    }

    //查询单一数据
    @RequestMapping("/selectById/{id}")
    public R selectById(@PathVariable() Integer id) {
        return new R(true, iBookService.getById(id));
    }

    //修改
    @RequestMapping("/update")
    public R update(@RequestBody Book book) {
        boolean b = iBookService.updateById(book);
        return new R(b, b ? "修改成功" : "此数据已被删除，自动刷新");
    }

    //分页
    @RequestMapping("/page/{current}/{size}")
    public R page(@PathVariable Integer current, @PathVariable Integer size,Book book) {
        IPage page = new Page(current, size);
        LambdaQueryWrapper<Book> qw = new LambdaQueryWrapper<Book>();
        qw.like(Strings.isNotEmpty(book.getType()), Book::getType,book.getType());
        qw.like(Strings.isNotEmpty(book.getName()), Book::getName,book.getName());
        qw.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription,book.getDescription());

        iBookService.page(page, qw);

        log.info("执行了分页");

        //如果当前页码值大于总页码值，重新执行查询操作，并将总页码当成当前页码值
        if (current > page.getPages()) {
            page = new Page(page.getPages(), size);
            iBookService.page(page, qw);
        }


        return new R(true, page);

    }


}
