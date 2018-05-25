package com.myService;


import com.po.Paging;
import com.po.lxrUsers;
import com.po.lxrsInf;
import com.po.myLxrs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.springframework.data.mongodb.core.query.Criteria.where;


@Service("lxrUserService")
public class lxrUserService {
    @Autowired
    private JdbcTemplate jt;
    @Autowired
    private MongoTemplate mop;
    @Autowired
    public Paging pg;

    public boolean addUsers(lxrUsers lu){
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = dateFormat.formatToDate(df.format(System.currentTimeMillis()));
        int i = jt.update("insert into users(uname,upwd,truename) values(?,?,?)",lu.getUname(), lu.getUpwd(),lu.getTruename());
        if (i>0)
            return  true;
        return false;
    }
    public lxrUsers findByusername(String username){
        List<Map<String,Object>> data = (List<Map<String,Object>>)jt.queryForList("select * from users where uname= ?",username);
        if(!data.isEmpty()) {
            Map<String, Object> row = data.get(0);
            lxrUsers lu = new lxrUsers();
            lu.setUid(Integer.parseInt(row.get("uid").toString()));
            lu.setUname(row.get("uname").toString());
            lu.setUpwd(row.get("upwd").toString());
            lu.setTruename(row.get("truename").toString());
            lu.setAddtime(row.get("addtime").toString());
            return lu;
        }
        return null;
        /*List<lxrUsers> data = jt.queryForList("select * from users where uname= ?",lxrUsers.class,username);
        return  data;*/
    }

    public List<myLxrs> findLxrByusername(String username){
            Query query = new Query();
            query.addCriteria(where("username").is(username));
            List<myLxrs> data = mop.find(query,myLxrs.class);
            return data;
    }
    public List<myLxrs> findBylxrname(String username,String lxrname){
        Query query = new Query();
        Pattern pattern = Pattern.compile(lxrname,Pattern.CASE_INSENSITIVE);
        query.addCriteria(where("username").is(username).and("lxrinformation").
                elemMatch(where("lxrname").regex(pattern)));
        List<myLxrs> data = mop.find(query,myLxrs.class);
        return data;
    }

}


