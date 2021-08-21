package com.pro.service;

import com.pro.dao.EvaluationDao;
import com.pro.domain.Evaluation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yuhua
 * @since 21.8.4 11:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EvaluationServiceImplTest {

    @Autowired private EvaluationService evaluationService;

    @Test
    public void selectEvaluationList() {
        List<Evaluation> evaluationList = evaluationService.selectEvaluationList(10l);
        for (Evaluation e :evaluationList){
            System.out.println(e.toString());
        }
    }
}