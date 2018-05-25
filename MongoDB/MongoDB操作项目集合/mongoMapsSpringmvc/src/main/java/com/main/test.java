package com.main;

import com.po.lxrsInf;
import com.po.myLxrs;
import com.po.telsInf;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class test {
    public static void main(String[] args) throws ParseException {
/*        MongoTemplate mop = new MongoTemplate(new MongoClient("shang.hadoop.com"),"Nosql");
        Query query = new Query();

        query.addCriteria(where("age").gt(25).lt(30));
        List<Users> ulist = mop.find(query,Users.class);

        for(Users u:ulist){
            String[] books = u.getBooks();
            for(String book:books){
                System.out.println(book);
            }
        }*/

/*        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = sdf.parse("2018-05-11 08:44:06");
//        java.util.Date date = sdf.parse("2018-05-11");

        System.out.println(date);
        System.out.println(new java.sql.Date(date.getTime()));*/



        ApplicationContext app = new ClassPathXmlApplicationContext("classpath:mongodbContext.xml");
        MongoTemplate mop = (MongoTemplate) app.getBean("mongoTemplate");




        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        mop.createCollection(myLxrs.class);
//        Date date = dateFormat.formatToDate(df.format(System.currentTimeMillis()));

        /**
         *
         * 添加集合
         *
         */
       /* List<lxrsInf> li = new ArrayList();
        List<telsInf> tl = new ArrayList();
        for (int i=0;i<10;i++){
            lxrsInf lif = new lxrsInf();
            lif.setLxrname("wang"+i);
            if (i<2){
                telsInf tlf = new telsInf();
                tlf.setTel("1377898565"+i);
                tlf.setAddtime(df.format((System.currentTimeMillis())));
                tl.add(tlf);
                lif.setTelinformation(tl);
                lif.setNumber(tl.size());
                li.add(lif);
            }

            lif.setTelinformation(tl);
            lif.setNumber(tl.size());
            li.add(lif);


        }
        myLxrs my = new myLxrs("wang123",li,li.size());
        System.out.println(my.getCountLxr());
        mop.insert(my,"myLxrs");*/

//        List<lxrsInf> lrf =(List<lxrsInf>)
/*        lxrUserService ls = new lxrUserService();
        List<myLxrs> data = ls.findByuname2("wang123","wang0");
        System.out.println(data.size());*/
/*         Query query = new Query();
        query.addCriteria(where("username").is("wang123"));
        List<myLxrs> data = mop.find(query.limit(3).skip(0),myLxrs.class);
        System.out.println(data.size());*/
//
//Criteria ci = where("username").is("wang123").and("lxrinformation").elemMatch(where("lxrname").is("wang0"));

        /**
         *
         *模糊查询+子文档查询
         *
         */


/*        Query query = new Query();
        Pattern p = Pattern.compile("G0",Pattern.CASE_INSENSITIVE);
        query.addCriteria(where("username").is("wang123").and("lxrinformation").
                elemMatch(where("lxrname").regex(p)));

//        query.addCriteria(where("").regex());
        Query query1 = new Query();
        query1.addCriteria(where());
//        System.out.println();
        List<myLxrs> data = mop.find(query,myLxrs.class);
        List<lxrsInf> lrf = data.get(0).getLxrinformation();
        List<telsInf> tlf = lrf.get(0).getTelinformation();
        System.out.println(lrf.get(0).getLxrname());
        for (telsInf tl:tlf){
            System.out.println(tl.getTel());
        }*/


        /**
         *
         * 更改联系人信息。
         *
         */
   /*     Query query = new Query();
        query.addCriteria(where("username").is("wang123").and("lxrinformation").
            elemMatch(where("lxrname").is("wang0").and("telinformation").
            elemMatch(where("tel").is("13778985650"))));
        List<myLxrs> data = mop.find(query,myLxrs.class);
        List<lxrsInf> lif = data.get(0).getLxrinformation();
        List<telsInf> tl = lif.get(0).getTelinformation();
        List<telsInf> data1 = new ArrayList();
         for (telsInf tel:tl){
            telsInf ti = new telsInf();
            if(tel.getTel().equals("13778985651")){
                ti.setTel("17854168788");
                ti.setAddtime(df.format(System.currentTimeMillis()));
                if(tl.size()==1)
                    data1.add(ti);
                else
                    continue;
            }
            ti.setTel(tel.getTel());
            ti.setAddtime(tel.getAddtime());
            data1.add(ti);
        }
        Update update = new Update();
        Update update1 = new Update();
        update.unset("lxrinformation.$.telinformation");
        update.set("lxrinformation.$.telinformation",data1);
        mop.updateFirst(query,update,myLxrs.class);
        mop.updateFirst(query,update,myLxrs.class);*/
    }

}
