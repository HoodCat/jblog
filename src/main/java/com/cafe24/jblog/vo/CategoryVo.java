package com.cafe24.jblog.vo;

public class CategoryVo {
    private Long no;
    private String name;
    private String description;
    private String regDate;
    private Long blogNo;
    
    public Long getNo() {
        return no;
    }
    public void setNo(Long no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public Long getBlogNo() {
        return blogNo;
    }
    public void setBlogNo(Long blogNo) {
        this.blogNo = blogNo;
    }
    @Override
    public String toString() {
        return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
                + ", blogNo=" + blogNo + "]";
    }
}
