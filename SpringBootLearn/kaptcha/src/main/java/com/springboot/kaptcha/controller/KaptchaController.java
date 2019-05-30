/*
 * Copyright 2001-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springboot.kaptcha.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * <p> Title: </p>
 *
 * <p> Description: </p>
 *
 * @author: Guo.Weifeng
 * @version: 1.0
 * @create: 2019/5/15 16:24
 */
@Controller
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @GetMapping("/defaultkaptcha")
    public void defaultKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        byte[] kaptchaChallengAsJpeg = null;

        ByteArrayOutputStream jpegOutput = new ByteArrayOutputStream();
        String createText = defaultKaptcha.createText();
        request.getSession().setAttribute("rightCode", createText);

        // 使用生产的验证码字符返回
        BufferedImage challenge = defaultKaptcha.createImage(createText);

        try {
            ImageIO.write(challenge, "jpg", jpegOutput);
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 定义response输出类型为image/jpeg类型
        kaptchaChallengAsJpeg = jpegOutput.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(kaptchaChallengAsJpeg);
        servletOutputStream.flush();
        servletOutputStream.close();
    }

    /**
     * 校对
     * */
    @GetMapping("/check")
    public ModelAndView check(HttpServletRequest request, HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        String rightCode = (String)request.getSession().getAttribute("rightCode");
        String tryCode = request.getParameter("tryCode");
        System.out.println("rightCode:" + rightCode + "----- tryCode:" + tryCode);
        if (!rightCode.equals(tryCode)){
            modelAndView.addObject("info", "验证码错误");
            modelAndView.setViewName("index");
        } else {
            modelAndView.addObject("info", "登录成功");
            modelAndView.setViewName("success");
        }
        return modelAndView;
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}