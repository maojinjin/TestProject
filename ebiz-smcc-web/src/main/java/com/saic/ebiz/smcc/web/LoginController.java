package com.saic.ebiz.smcc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class LoginController {

    /**
     * 功能描述: <br>
     * 登录
     *
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/login")
    @ResponseBody
    private String login() {
        return "hello world" + "----cacheName:";
    }

}
