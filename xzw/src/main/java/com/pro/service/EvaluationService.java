package com.pro.service;


import com.pro.domain.Evaluation;

import java.util.List;

/**
 * @author Yuhua
 * @since 21.8.4 11:23
 */
public interface EvaluationService {
    List<Evaluation> selectEvaluationList(Long bookId);
}
