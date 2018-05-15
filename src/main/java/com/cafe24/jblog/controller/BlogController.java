package com.cafe24.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.jblog.service.BlogService;
import com.cafe24.jblog.service.CategoryService;
import com.cafe24.jblog.service.FileUploadService;
import com.cafe24.jblog.vo.BlogVo;
import com.cafe24.jblog.vo.PostVo;
import com.cafe24.jblog.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("/blog/{ownerID}")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public String blog(
            @PathVariable("ownerID") String ownerID, Model model,
            @RequestParam(value="categoryNo", required=false, defaultValue="0") Long categoryNo,
            @RequestParam(value="postNo", required=false, defaultValue="0") Long postNo) {
        // System.out.println(blogService.getBlog(id));
        BlogVo blogVo = blogService.getBlog(ownerID);
        if (blogVo == null) {
            return "redirect:/main";
        }
        
        PostVo postVo = new PostVo();
        postVo.setNo(postNo);
        postVo.setCategoryNo(categoryNo);
        
        
        model.addAttribute("blogVo", blogVo);
        model.addAttribute("ownerID", ownerID);
        model.addAttribute("categoryList", categoryService.getCategoryList(blogVo.getNo()));
        model.addAttribute("postList", blogService.getPostList(categoryNo));
//        model.addAttribute("viewPost", blogService.getPost(postNo));
        model.addAttribute("viewPost", blogService.getPost(postVo));
        return "blog/blog-main";
    }

    @Auth
    @RequestMapping("/admin/{menu}")
    public String adminPage(
            @PathVariable("ownerID") String ownerID,
            @PathVariable("menu") String menu,
            @AuthUser UserVo authUser, Model model) {
        // 블로그 주인 확인
        if (authUser.getId().equals(ownerID) == false) {
            return "redirect:/main";
        }

        BlogVo blogVo = blogService.getBlog(ownerID);
        model.addAttribute("blogVo", blogVo);
        model.addAttribute("menu", menu);
        
        if("basic".equals(menu)) {
            return "blog/blog-admin-basic";
        } else if("category".equals(menu)) {
            model.addAttribute("categoryList", categoryService.getCategoryList(authUser.getNo()));
            return "blog/blog-admin-category";
        } else if("write".equals(menu)) {
            model.addAttribute("categoryList", categoryService.getCategoryList(authUser.getNo()));
            return "blog/blog-admin-write";
        }
        
        return "redirect:/blog/" + ownerID;
    }

    @Auth
    @RequestMapping("/admin/modify")
    public String modify(
            @PathVariable("ownerID") String ownerID,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "logo-file", required = false) MultipartFile logoFile) {

        BlogVo vo = blogService.getBlog(ownerID);
        vo.setTitle(title);
        
        if(!logoFile.isEmpty()) {
            vo.setLogo(fileUploadService.restore(logoFile));
        }
        System.out.println(vo);
        blogService.modify(vo);

        return "redirect:/blog/" + ownerID;
    }
    
    @Auth
    @RequestMapping(value = "/admin/post", method=RequestMethod.POST)
    public String post(
            @PathVariable("ownerID") String ownerID,
            @ModelAttribute PostVo postVo) {
        
        blogService.post(postVo);
        return "redirect:/blog/" + ownerID;
    }
}
