package com.myController;

import com.myService.lxrUserService;
import com.po.lxrUsers;
import com.po.myLxrs;
import com.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LxrUserController {
    @Autowired
    private lxrUserService lus;
    @Autowired
    public lxrUsers lu;
    @Autowired
    public myLxrs ml;

    @RequestMapping(value= "/register.do",method = RequestMethod.POST)
    public String userRegister(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("truename") String truename){
        lu.setUname(username);
        //获取MD5码并加密
        String upwd = MD5Util.convertMD5(MD5Util.getMD5(password));
        lu.setUpwd(upwd);
        lu.setTruename(truename);
        if (lus.addUsers(lu))
            return "/login";
        return "/error";
    }




    @RequestMapping(value = "/login.do",method = RequestMethod.GET)
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password, HttpSession hs){
        lu = lus.findByusername(username);
        //获取密码的MD5码
        String getMD5Upwd = MD5Util.getMD5(password);
        //密码MD5解码
        String covertUpwd = MD5Util.convertMD5(lu.getUpwd());
//        System.out.println(lu.getTruename());

//        List<myLxrs> ulist = lus.findLxrByusername(lu.getUname(),1,3);
        if (lu!=null){
            if (covertUpwd.equals(getMD5Upwd)){
                hs.setAttribute("lu",lu);
/*                hs.setAttribute("pd",1);
                hs.setAttribute("ulist",ulist);*/
                return "/lxr/search";
            }
        }
        return "/error";
    }

  /*  @RequestMapping(value = "/listLxr.do",method = RequestMethod.GET)
    public String listLxr(@RequestParam("page") String pn, HttpSession hs){
        int page=1;     //当前第几页
        long rows;    //数据总数（可能比较长，就定义为了long）
        int size=3;    //每一页显示的记录条数
        int pageCount;//一共有多少页

        // 通过参数pn获取page（当前是第几页）



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
//        System.out.println(lu.getUname());
//        System.out.println(lu);
        List<myLxrs> ulist = lus.findLxrByusername(lu.getUname(),page,size);
//        System.out.println(ulist.size());
        ml = ulist.get(0);
        List<lxrsInf> lrf = ml.getLxrinformation();
        rows = ml.getCountLxr();
//        System.out.println(rows);
        //计算pageCount（总页数）
        if(rows%size==0)
        {
            pageCount = (int)(rows/size);

        }else{
            pageCount = (int)(rows/size)+1;
        }

        System.out.println(pageCount);


        //将数据发送给JSP页面，并实现转发
        hs.setAttribute("lu",lu);
        hs.setAttribute("ulist",ulist);
        hs.setAttribute("pageCount",pageCount);
        hs.setAttribute("page",page);
        return "/listLxr";
    }*/


    @RequestMapping(value = "/lxr/search.do" , method = RequestMethod.GET)
    public String listLxrBylxrname(@RequestParam("keyword") String keyword,HttpSession hs){
        List<myLxrs> mylist =  lus.findBylxrname(lu.getUname(),keyword);
        if (!mylist.isEmpty()){
            hs.setAttribute("ulist",mylist);
            return "/lxr/list";
        }
//        hs.setAttribute("information","没有联系人信息！");
        return "/error";
    }

    @RequestMapping(value = "/lxr/searchAll.do",method = RequestMethod.GET)
    public String listAllLxr(HttpSession hs){
        List<myLxrs> data = lus.findLxrByusername(lu.getUname());
        if (!data.isEmpty()){
            hs.setAttribute("ulist",data);
            return "/lxr/list";
        }
        return "/error";
    }

}
