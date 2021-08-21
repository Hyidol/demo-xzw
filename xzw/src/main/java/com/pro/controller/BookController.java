package com.pro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pro.domain.*;
import com.pro.service.BookService;
import com.pro.service.CategoryService;
import com.pro.service.EvaluationService;
import com.pro.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Yuhua
 * @since 21.7.30 16:28
 */
@Controller
public class BookController {

    @Autowired private CategoryService categoryService;
    @Autowired private BookService bookService;
    @Autowired private EvaluationService evaluationService;
    @Autowired private MemberService memberService;

    @GetMapping("/index.html")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("/index");
        List<Category> categoryList = categoryService.selectAll();
        mav.addObject("categoryList", categoryList);
        return mav;
    }

    @GetMapping("/books")
    @ResponseBody
    public IPage<Book> selectBooks(Long categoryId, String order, Integer nextPage) {
        if (nextPage == null) {
            nextPage = 1;
        }
        IPage<Book> pageObject = bookService.pageing(categoryId, order, nextPage, 2);
        return pageObject;
    }

    //这里是跳转，不是接口
    @GetMapping("/book/{bookId}")
    public ModelAndView displayBookDetail(@PathVariable("bookId")Long bookId, HttpSession session){// 3. Model model return 返回页面名
        //1 书本信息
        Book book = bookService.getBookById(bookId);
        //1. MOdelAndView
        //2. ModeMap mm = new ModelMap(); return String 页面名
        //   mm.pu
        //3. model.addAttribute("book",book);
        ModelAndView mav = new ModelAndView("/detail");
        mav.addObject(book);//相当于mav.addObject("book",book);

        //2 评论列表+会员名
        List<Evaluation> evaluationList = evaluationService.selectEvaluationList(bookId);
        List<Member> memberList = new ArrayList<>();
        SimpleDateFormat f = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH : mm : ss");
        for (Evaluation evaluation : evaluationList){

            Member member = memberService.selectMemberById(evaluation.getMemberId());
            memberList.add(member);
        }
        mav.addObject(evaluationList);
        mav.addObject(memberList);

        Member member = (Member) session.getAttribute("loginMember");
        if(member!=null){
            MemberReadState memberReadState = memberService.selectMemberReadState(member.getMemberId(), bookId);
            mav.addObject(memberReadState);
        }
        //此时，书，评价列表，阅读状态，都会被传到页面中
        return mav;
    }
}