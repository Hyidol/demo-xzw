package com.pro.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pro.dao.EvaluationDao;
import com.pro.dao.MemberDao;
import com.pro.dao.MemberReadStateDao;
import com.pro.domain.Evaluation;
import com.pro.domain.Member;
import com.pro.domain.MemberReadState;
import com.pro.service.exception.ServiceException;
import com.pro.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Yuhua
 * @since 21.8.4 12:45
 */
@Service
@Transactional
/*@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)*/
public class MemberServiceImpl implements MemberService {
    @Autowired private MemberDao memberDao;

    @Autowired private MemberReadStateDao memberReadStateDao;

    @Autowired private EvaluationDao evaluationDao;

    @Override
    public Member selectMemberById(Long memberId) {
        Member member = memberDao.selectById(memberId);
        return member;
    }

    @Override
    public Member createMember(String username, String password, String nickname){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username",username);

        List<Member> memberList = memberDao.selectList(queryWrapper);
        //根据username，如果查到了，说明已经被注册了
        if(memberList.size()>0){
            //不能注册
            throw new ServiceException("801","该用户名已被注册了！");
        }
        //
        Member member = new Member();
        member.setUsername(username);
        member.setNickName(nickname);
        //密码域
        int salt = new Random().nextInt(1000) + 1000;
        String md5 = MD5Util.md5Digest(password,salt);
        member.setPassword(md5);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        System.out.println(member.toString());
        //添加
        memberDao.insert(member);
        return member;
    }

    //登录，查用户及密码，是否正确
    @Override
    public Member checkLogin(String username, String password){
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);

        Member member = memberDao.selectOne(queryWrapper);
        if(member==null){
            throw new ServiceException("802","用户不存在");
        }
        //可以站到这里，说明表中有这个用户
        String md5 = MD5Util.md5Digest(password, member.getSalt());
        if(!md5.equalsIgnoreCase(member.getPassword())){
            throw new ServiceException("803", "输入密码不对");
        }
        //如果代码可以走到这里，说明都正确
        return member;
    }

    /**
     * 获取阅读状态
     * @param memberId 会员id
     * @param bookId 图书id
     * @return 阅读对象
     */
    @Override
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        //准备查询的条件
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper();
        queryWrapper.eq("memberId",memberId);
        queryWrapper.eq("bookId", bookId);
        MemberReadState memberReadState = memberReadStateDao.selectOne(queryWrapper);
        return memberReadState;
    }

    //修改状态，为空则创建，不空则修改
    //这里的事务搞不太清！！！！！！！！！！！！！！！！！！！！！！
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("memberId",memberId);
        queryWrapper.eq("bookId",bookId);

        MemberReadState memberReadState = memberReadStateDao.selectOne(queryWrapper);
        //如果为null，则添加一个新的阅读
        if(memberReadState==null){
            memberReadState = new MemberReadState();
            memberReadState.setMemberId(memberId);
            memberReadState.setBookId(bookId);
            memberReadState.setReadState(readState);
            memberReadState.setCreateTime(new Date());
            memberReadStateDao.insert(memberReadState);
        } else {
            memberReadState.setReadState(readState); //1,2
            memberReadStateDao.updateById(memberReadState);
        }
        return memberReadState;
    }

    /**
     *  添加短评
     * @param memberId
     * @param bookId
     * @param score
     * @param content
     * @return 返回短评对象
     */
    @Override
    public Evaluation evaluation(Long memberId, Long bookId, Integer score, String content) {
        Evaluation e = new Evaluation();
        e.setMemberId(memberId);
        e.setBookId(bookId);
        e.setScore(score);
        e.setContent(content);
        e.setCreateTime(new Date());
        e.setStatu("enable");
        e.setEnjoy(0);
        evaluationDao.insert(e);
        return e;
    }

    /**
     * 修改评论
     * @param evaluetionId
     * @return
     */
    @Override
    public Evaluation enjoy(Long evaluetionId) {
        Evaluation evaluation = evaluationDao.selectById(evaluetionId);
        evaluation.setEnjoy(evaluation.getEnjoy() + 1);
        evaluationDao.updateById(evaluation);
        return evaluation;
    }
}