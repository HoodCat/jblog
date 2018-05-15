package com.cafe24.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.BlogDao;
import com.cafe24.jblog.repository.PostDao;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.PostVo;

@Service
public class BlogService {
    @Autowired
    private BlogDao blogDao;
    
    @Autowired
    private PostDao postDao;
    
    public BlogVo getBlog(String id) {
        return blogDao.getBlog(id);
    }
    
    public void modify(BlogVo vo) {
        blogDao.update(vo);
    }
    
    public void post(PostVo vo) {
        postDao.insert(vo);
    }
    
    public List<PostVo> getPostList(Long categoryNo) {
        return postDao.getList(categoryNo);
    }
    
    public PostVo getPost(Long postNo) {
        return postDao.getPost(postNo);
    }
    
    public PostVo getPost(PostVo postVo) {
        return postDao.getPost(postVo);
    }
}
