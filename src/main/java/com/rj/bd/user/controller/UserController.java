package com.rj.bd.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rj.bd.appeal.entity.Appeal;
import com.rj.bd.user.entity.User;
import com.rj.bd.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userServiceImpl;

    @RequestMapping(value = "/register")
    public String register(User u){
        System.err.println("UserController--->register");
        System.err.println("user--->"+u);

        userServiceImpl.save(u);
        //return "redirect:/user/query.action";
        //return "user/user_edit";
        return "redirect:/index.jsp";
    }

    @RequestMapping(value = "/login")
    public String login(User u, HttpServletRequest request, HttpServletResponse response){
        System.err.println("UserController--->login");
        System.err.println("user--->"+u);

        Map userMap = userServiceImpl.queryUser(u);
        System.err.println("userMap--->"+userMap);
        request.getSession().setAttribute("userMap", userMap);

        if (userMap != null){
            System.err.println("进入到个人页面");
            //判断是否在黑名单内
            Map blackMap = userServiceImpl.queryByIdToBlackList(userMap);
            System.err.println("blackMap--->"+blackMap);

            if (blackMap != null){
                return "user/blacklist";
            }else{
                return "user/list";
            }

        }else{
            System.err.println("该用户不存在！！！");
            return "redirect:/index.jsp";
        }

    }


    /**
     * @desc 申诉
     * @return
     */
    @RequestMapping(value = "/appeal")
    public String appeal(){
        System.err.println("进行申诉。。。");

        //return "redirect:/index.jsp";
        return "appeal/shensu";
    }

    /**
     * @desc 用户提交申诉
     * @return
     */
    @RequestMapping(value = "/addappeal")
    public String addappeal(HttpServletRequest request, HttpServletResponse response){

        String info = request.getParameter("username");
        String uid = request.getParameter("uid");

        System.err.println("uid--->"+uid);
        System.err.println("info--->"+info);

        Appeal appeal = new Appeal();
        appeal.setUid((long) Integer.parseInt(uid));
        appeal.setAinfo(info);

        userServiceImpl.saveAppeal(appeal);

        return null;
        //return "appeal/success";
    }

}
