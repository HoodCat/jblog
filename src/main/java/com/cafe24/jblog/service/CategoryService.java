package com.cafe24.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.jblog.repository.CategoryDao;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    
    public List<Map<String, Object>> getCategoryList(Long blogNo) {
        return categoryDao.getCategoryList(blogNo);
    }
}
