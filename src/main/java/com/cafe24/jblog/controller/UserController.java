package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.jblog.service.UserService;
import com.cafe24.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String login() {
        return "user/login";
    }
    
    @RequestMapping(value="/join", method=RequestMethod.GET)
    public String join() {
        return "user/join";
    }
    
    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(
            @ModelAttribute UserVo vo) {
        System.out.println(vo);
        if(userService.join(vo)) {
            return "redirect:/user/joinsuccess";            
        }
        
        return "redirect:/main";
    }
    
    @RequestMapping("joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }
}
