package com.myService;


import com.po.Paging;
import com.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static sun.misc.MessageUtils.where;

@Service("userService")
public class userService {
    @Autowired
    private MongoTemplate mop;
    @Autowired
    public Paging pg;

    public List<Users> userPaging(int page,int size){
        Query query = new Query();
        pg.setRows(mop.count(query,Users.class));
        List<Users> data = mop.find(query.limit(size).skip((page-1)*size),Users.class);
        return data;
    }

    public boolean addUser(){
        Query query = new Query();
        return true;
    }




}
