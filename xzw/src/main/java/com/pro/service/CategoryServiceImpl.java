package com.pro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.pro.dao.CategoryDao;
import com.pro.domain.Category;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.7.30 17:01
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    @Autowired private CategoryDao categoryDao;

    @Override
    public List<Category> selectAll() {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        List<Category> categoryList = categoryDao.selectList(queryWrapper);
        return categoryList;
    }
}