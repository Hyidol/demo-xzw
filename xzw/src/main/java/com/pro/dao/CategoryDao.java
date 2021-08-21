package com.pro.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pro.domain.Category;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.7.30 16:57
 */
public interface CategoryDao extends BaseMapper<Category> {
    //List<Category> selectList(QueryWrapper<Object> queryWrapper);
}