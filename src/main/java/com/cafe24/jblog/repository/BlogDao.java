package com.cafe24.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.UserVo;

@Repository
public class BlogDao {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean insert(UserVo vo) {
        return sqlSession.insert("blog.insert", vo) == 1;
    }
    
    public BlogVo getBlog(String id) {
        return sqlSession.selectOne("blog.getBlogByID", id);
    }
    
    public boolean update(BlogVo vo) {
        return sqlSession.update("blog.update", vo) == 1;
    }
}
