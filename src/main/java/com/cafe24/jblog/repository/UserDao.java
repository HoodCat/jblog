package com.cafe24.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.UserVo;

@Repository
public class UserDao {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean insert(UserVo vo) {
        return sqlSession.insert("user.insert", vo)==1;
    }
    
    public UserVo getUser(String id, String password) {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("id", id);
        parameter.put("password", password);
        
        return sqlSession.selectOne("user.getUserByIdAndPassword", parameter);
    }
}
