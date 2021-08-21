package com.pro.service;

import com.pro.domain.Evaluation;
import com.pro.domain.Member;
import com.pro.domain.MemberReadState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Yuhua
 * @since 21.8.4 12:45
 */
public interface MemberService {
    Member selectMemberById(Long memberId);
    Member createMember(String username, String password, String nickname);

    Member checkLogin(String username, String password);
    //获取阅读状态
    MemberReadState selectMemberReadState(Long memberId,Long bookId);
    //更新阅读状态，返回会员的阅读状态
    MemberReadState updateMemberReadState(Long memberId,Long bookId,Integer readState);
    //添加评论
    Evaluation evaluation(Long memberId,Long bookId,Integer score,String content);
    //点赞
    Evaluation enjoy(Long evaluetionId);
}
