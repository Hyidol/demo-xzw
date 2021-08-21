package com.pro.controller;


import com.pro.domain.Evaluation;
import com.pro.domain.Member;
import com.pro.domain.MemberReadState;
import com.pro.service.MemberService;
import com.pro.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yuhua
 * @since 21.8.5 9:30
 */

@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    /*这里不清楚！！！！！！下课问下老谈*/
    @GetMapping("/reg.html")
    public ModelAndView toReg() {
        ModelAndView mav = new ModelAndView("reg");
        return mav;
    }

    //去登录页
    @GetMapping("/login.html")
    public String login(){
        return "login";
    }

    @PostMapping("/regist")
    @ResponseBody//响应数据
    public Map regist(String vc, String username, String password,String nickname, HttpServletRequest request){
        Map result = new HashMap();
        //从会话session中拿到生成时的验证码
        String verifyCode = (String)request.getSession().getAttribute("verifyCode");
        if(vc==null || verifyCode==null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code","err_vc");
            result.put("msg","验证码错误！");
        }else {
            try {
                Member member = memberService.createMember(username,password,nickname);
                result.put("code","ok_vc");
                result.put("msg","成功！");
                result.put("member",member);
            } catch (ServiceException e){
                e.printStackTrace();
                result.put("code",e.getCode());
                result.put("result  ",e.getMsg());
            }

        }
        return result ;
    }

    //登录
    @PostMapping("/checkLogin")
    @ResponseBody
    public Map checkLogin(String username, String password, String vc, HttpSession session){
        String verifyCode = (String)session.getAttribute("verifyCode");
        Map result = new HashMap();
        if(vc==null || verifyCode==null || !vc.equalsIgnoreCase(verifyCode)){
            result.put("code","err_vc");
            result.put("msg","验证码错误");
        }else {
            try{
                Member member = memberService.checkLogin(username, password);
                session.setAttribute("loginMember",member);
                result.put("code","ok_vc");
                result.put("msg", "success");
                result.put("member",member);
            } catch (ServiceException e){
                result.put("code",e.getCode());
                result.put("msg",e.getMsg());
            }
        }
        return result;
    }

    /**
     * 更新会员阅读状态
     *  我们写的是接口，可能会有些扩展
     */
    @PostMapping("/updateReadState")
    @ResponseBody
    public Map updateReadState(Long memberId,Long bookId,Integer readState){
        Map result = new HashMap();
        try {
            MemberReadState memberReadState = memberService.updateMemberReadState(memberId, bookId, readState);
            result.put("code","ok_mrs");
            result.put("msg","成功！");
            result.put("memberReadState",memberReadState);
        } catch (ServiceException e) {
            result.put("code",e.getCode());
            result.put("msg",e.getMsg());
        }
        return result;
    }

    /**
     * 添加评论
     * @param memberId
     * @param bookId
     * @param score
     * @param content
     * @return
     */
    @PostMapping("/evaluate")
    @ResponseBody
    public Map evaluate(Long memberId,Long bookId,Integer score,String content){
        Map result = new HashMap();
        try {
            Evaluation evaluation = memberService.evaluation(memberId, bookId, score, content);
            result.put("code","ok_mrs");
            result.put("msg","成功！");
            result.put("evaluation",evaluation);
        } catch (ServiceException e) {
            result.put("code",e.getCode());
            result.put("msg",e.getMsg());
        }
        return result;
    }

    //点赞接口
    @PostMapping("/enjoy")
    @ResponseBody
    public Map enjoy(Long evaluationId){
        Map result = new HashMap();
        try {
            Evaluation enjoy = memberService.enjoy(evaluationId);
            result.put("code", "ok_en");
            result.put("msg","成功！");
            result.put("enjoy",enjoy);
        } catch (ServiceException e) {
            result.put("code",e.getCode());
            result.put("msg",e.getMsg());
        }
        return result;
    }
}