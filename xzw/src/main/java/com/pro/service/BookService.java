package com.pro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pro.domain.Book;
import com.pro.domain.Evaluation;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.8.2 9:21
 */
public interface BookService {
    /**
     * 分页
     * @param categoryId 分类
     * @param order 排序
     * @param page 页码
     * @param rows 页显示行数
     * @return 分页对象
     */
    public IPage<Book> pageing(Long categoryId,String order,Integer page,Integer rows);


    Book getBookById(Long bookId);

    List<Evaluation> getEvaluationList();
}