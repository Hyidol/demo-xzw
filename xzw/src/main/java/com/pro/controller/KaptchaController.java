package com.pro.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Yuhua
 * @since 21.8.4 15:50
 */
@Controller
public class KaptchaController {
    @Autowired
    protected Producer defaultKaptcha;

    @GetMapping("/verifyCode")
    public void createVerifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires",0);//响应立即过期
        //不缓存图片
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.setHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/png");

        //生成验证码文本
        String verifyCode = defaultKaptcha.createText();
        request.getSession().setAttribute("verifyCode", verifyCode);
        //这里打印出来，一会试验时候，看看对比一下，是否一致
        System.out.println(request.getSession().getAttribute("verifyCode"));

        //变魔术，变成图片
        BufferedImage image = defaultKaptcha.createImage(verifyCode);
        //输出流，从服务器到客户端，架了一个管道，将那个图片验证码，发送到你的页面中
        ServletOutputStream outputStream = response.getOutputStream();
        //写，把图通过管道，写到你的客户端
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();
        //io流。什么是流，有哪些流？
    }

    @GetMapping("/a")
    public String b(){
        return "reg";
    }
}