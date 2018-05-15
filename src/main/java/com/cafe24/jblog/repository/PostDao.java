package com.cafe24.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.PostVo;

@Repository
public class PostDao {
    
    @Autowired
    private SqlSession sqlSession;
    
    public boolean insert(PostVo vo) {
        return sqlSession.insert("post.insert", vo)==1;
    }
    
    public List<PostVo> getList(Long categoryNo) {
        return sqlSession.selectList("post.getPostByCategoryNo", categoryNo);
    }
    
    public PostVo getPost(Long postNo) {
        return sqlSession.selectOne("post.getPostByNo", postNo);
    }
    
    public PostVo getPost(PostVo postVo) {
        return sqlSession.selectOne("post.getPost", postVo);
    }
}
