package com.pro.service;

import com.pro.controller.MemberController;
import com.pro.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author Yuhua
 * @since 21.8.5 11:40
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceImplTest {

    @Autowired private MemberService memberService;

    @Test
    public void createMember() {
        memberService.createMember("小甜甜", "1236", "xtt");
    }
}