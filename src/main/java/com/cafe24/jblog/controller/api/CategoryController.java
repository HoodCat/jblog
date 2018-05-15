package com.cafe24.jblog.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.jblog.repository.CategoryDao;
import com.cafe24.jblog.vo.CategoryVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/api/category")
@ResponseBody
public class CategoryController {
    
    @Autowired
    private CategoryDao categoryDao;
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public List<Map<String, Object>> addCategory(
            @ModelAttribute CategoryVo vo,
            @AuthUser UserVo authUser) {
        categoryDao.insert(vo);
        return categoryDao.getCategoryList(authUser.getNo());
    }
    
    @RequestMapping(value="/remove", method=RequestMethod.POST)
    public List<Map<String, Object>> removeCategory(
            @ModelAttribute CategoryVo vo,
            @AuthUser UserVo authUser){
        categoryDao.delete(vo);
        return categoryDao.getCategoryList(authUser.getNo());
    }
}
