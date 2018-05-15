package com.cafe24.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.UserDao;
import com.cafe24.jblog.vo.UserVo;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private BlogDao blogDao;
    
    public boolean join(UserVo vo) {
        return userDao.insert(vo) && blogDao.insert(vo);
    }
    
    public UserVo login(String id, String password) {
        return userDao.getUser(id, password);
    }
}
