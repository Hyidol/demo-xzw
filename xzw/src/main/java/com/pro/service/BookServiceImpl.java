package com.pro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pro.dao.BookDao;
import com.pro.dao.EvaluationDao;
import com.pro.domain.Book;
import com.pro.domain.Evaluation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.8.2 9:36
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;
    @Autowired private EvaluationDao evaluationDao;

    /**
     * 分页
     * @param categoryId 分类
     * @param order 排序
     * @param page 页码
     * @param rows 页显示行数
     * @return 分页对象
     */
    public IPage<Book> pageing(Long categoryId, String order, Integer page, Integer rows) {
        Page<Book> p = new Page<>(page,rows);
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        //类别已选，可以加入sql中
        if(categoryId!=null && categoryId!=-1){
            queryWrapper.eq("categoryId",categoryId);
        }
        //按条件加入排序的sql
        if(order!=null){
            if("quantity".equals(order)){
                queryWrapper.orderByDesc("evaluationQuantity");
            }else if("score".equals(order)){
                queryWrapper.orderByDesc("evaluationScore");
            }
        }
        Page<Book> bookPage = bookDao.selectPage(p, queryWrapper);
        return bookPage;
    }

    @Override
    public Book getBookById(Long bookId) {
        Book book = bookDao.selectById(bookId);
        return book;
    }

    @Override
    public List<Evaluation> getEvaluationList() {
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        List<Evaluation> evaluationList = evaluationDao.selectList(queryWrapper);
        return evaluationList;
    }
}