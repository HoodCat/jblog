package com.cafe24.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {
    @Autowired
    private SqlSession sqlSession;
    
    public boolean insert(CategoryVo vo) {
        return sqlSession.insert("category.insert", vo) == 1;
    }
    
    public List<Map<String, Object>> getCategoryList() {
        return sqlSession.selectList("category.getCategoryList");
    }
    
    public List<Map<String, Object>> getCategoryList(Long blogNo) {
        return sqlSession.selectList("category.getCategoryList", blogNo);
    }
    
    public boolean delete(CategoryVo vo) {
        return sqlSession.delete("category.delete", vo)==1;
    }
}
