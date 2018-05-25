package com.myController;


import com.myService.userService;
import com.po.Paging;
import com.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class userController {
    @Autowired
    private userService us;
    @Autowired
    public Paging pg;


    @RequestMapping(value = "/ps.do", method = RequestMethod.GET)
    public String findAll(@RequestParam(value = "page",defaultValue = "1") String pag, HttpSession hs) {
        int page=1;     //当前第几页
        long rows;    //数据总数（可能比较长，就定义为了long）
        int size=10;    //每一页显示的记录条数
        int pageCount;//一共有多少页
        String pn=pag;//通过参数获取page（当前是第几页）



        //得到当前是第几页
        if(pn!=null && pn.length()>0)
        {
            try {
                page=Integer.parseInt(pn);      //进行类型转换（得到的是一个字符串）
            } catch (NumberFormatException e) {
                page=1;                         //如果又错，就返回第一页得到信息
                e.printStackTrace();
            }
        }

        List<Users> ulist = us.userPaging(page,size);
        rows = pg.getRows();

        //计算pageCount（总页数）
        if(rows%size==0)
        {
            pageCount = (int)(rows/size);

        }else{
            pageCount = (int)(rows/size)+1;
        }


        //将数据发送给JSP页面，并实现转发
        hs.setAttribute("ulist",ulist);
        hs.setAttribute("pageCount",pageCount);
        hs.setAttribute("page",page);
        return "/user/success";
    }

 /*   @RequestMapping(name = "/user/xx.do",method = RequestMethod.GET)
    public String AA(){
        return "/success";
    }*/
}
