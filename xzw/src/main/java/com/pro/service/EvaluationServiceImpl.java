package com.pro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pro.dao.BookDao;
import com.pro.dao.EvaluationDao;
import com.pro.dao.MemberDao;
import com.pro.domain.Book;
import com.pro.domain.Evaluation;
import com.pro.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.8.4 11:23
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired EvaluationDao evaluationDao;
    //对会员和书下手，需要注入这两个
    @Autowired BookDao bookDao;
    @Autowired MemberDao memberDao;

    @Override
    public List<Evaluation> selectEvaluationList(Long bookId) {
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bookId",bookId);
        queryWrapper.eq("statu","enable");
        queryWrapper.orderByDesc("createTime");
        //查出所有评论，针对这本书的
        List<Evaluation> evList = evaluationDao.selectList(queryWrapper);
        //书有了
        Book book = bookDao.selectById(bookId);
        for(Evaluation evaluation : evList){
            //会员有了
            Member member = memberDao.selectById(evaluation.getMemberId());
            evaluation.setBook(book);
            evaluation.setMember(member);
        }
        return evList;
    }
}