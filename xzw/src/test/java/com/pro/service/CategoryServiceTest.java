package com.pro.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.pro.domain.Category;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.7.30 17:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class CategoryServiceTest {

    @Autowired private CategoryService categoryService;

    @Test
    public void testSelectCastAll() {
        List<Category> categoryList = categoryService.selectAll();
        for (Category category : categoryList){
            System.out.println(category);
        }
    }
}