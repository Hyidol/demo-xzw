package com.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pro.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yuhua
 * @since 21.8.2 11:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BookServiceImplTest {

    @Autowired private BookService bookService;

    @Test
    public void pageing() {
        //IPage<Book> bookIPage = bookService.pageing(1, 2);
        IPage<Book> bookIPage = bookService.pageing(4l,"quantity",1,3);
        List<Book> bookList = bookIPage.getRecords();
        for (Book book: bookList){
            System.out.println(book.toString());
        }
        System.out.println("总页数："+bookIPage.getPages());
        System.out.println("总记录数："+bookIPage.getTotal());
        System.out.println("当前页："+bookIPage.getCurrent());
        System.out.println("页显示行数："+bookIPage.getSize());
    }
}