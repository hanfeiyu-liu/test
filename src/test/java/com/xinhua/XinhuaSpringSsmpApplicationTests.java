package com.xinhua;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xinhua.dao.BookDao;
import com.xinhua.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class XinhuaSpringSsmpApplicationTests {

    @Autowired
    private BookDao bookDao;

    //查询全部
    @Test
    void contextLoads() {
        List<Book> books = bookDao.selectList(null);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    //查询单个
    @Test
    void selectById() {
        bookDao.selectById(1);
    }

    //添加
    @Test
    void testSave() {
        Book book = new Book();
        book.setType("小说");
        book.setName("雪中悍刀行");
        book.setDescription("江湖是一张珠帘。大人物小人物，是珠子，大故事小故事，是串线。情义二字，则是那些珠子的精气神。");
        bookDao.insert(book);
    }

    //修改
    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(23);
        book.setType("小说");
        book.setName("神墓");
        book.setDescription("一个死去万载岁月的平凡青年从远古神墓中复活而出……");
        bookDao.updateById(book);

    }

    //删除
    @Test
    void testDelete() {
        bookDao.deleteById(27);
    }

    //分页
    @Test
    void testGetPage() {
        IPage page = new Page(2, 2);
        bookDao.selectPage(page, null);
        System.out.println(page.getCurrent());  //当前页码值
        System.out.println(page.getSize());     //每页显示数
        System.out.println(page.getTotal());    //数据总量
        System.out.println(page.getPages());    //总页数
        System.out.println(page.getRecords());  //详细数据

    }

    //按条件查询
    @Test
    void testGetBy() {
        QueryWrapper<Book> QW = new QueryWrapper<>();
        QW.like("name", "神墓");
        bookDao.selectList(QW);
    }

    //按条件查询
    @Test
    void testGetBy2() {
        String name="圣王";
        String name1="";
        LambdaQueryWrapper<Book> QW = new LambdaQueryWrapper<Book>();

        //name不等于null时链接
        QW.like(name != null, Book::getName, name);
        QW.like(name1 != null, Book::getDescription, name1);

        bookDao.selectList(QW);
    }

}
