/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject;

import com.mycompany.capstoneproject.dao.BlogDao;
import com.mycompany.capstoneproject.dao.CategoryDao;
import com.mycompany.capstoneproject.dao.StaticDao;
import com.mycompany.capstoneproject.dto.Blog;
import com.mycompany.capstoneproject.dto.Category;
import com.mycompany.capstoneproject.dto.CommandObject;
import com.mycompany.capstoneproject.dto.Static;
import java.sql.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping({"/blog"})
public class BlogController {

    private BlogDao blogDao;
    private CategoryDao catDao;
    private StaticDao statDao;

    @Inject
    public BlogController(BlogDao blogDao, CategoryDao catDao, StaticDao statDao) {

        this.blogDao = blogDao;
        this.catDao = catDao;
        this.statDao = statDao;

    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Blog createBlog(@RequestBody CommandObject input) {

        Blog blog = new Blog();
        Category cat = catDao.get(input.getCategory());

        Date date = Date.valueOf(input.getCreationDate());
        Date postDate = Date.valueOf(input.getPostDate());

        blog.setTitle(input.getTitle());
        blog.setBlogContent(input.getBlogContent());
        blog.setCreationDate(date.toLocalDate());
        blog.setPostDate(postDate.toLocalDate());

        blog.setCategory(cat);

        blogDao.create(blog);

        return blog;

    }

    @RequestMapping(value = "/testAdmin", method = RequestMethod.GET)
    public String blogHome() {

        return "testAdmin";
    }
    
    @RequestMapping(value = "/testAdmin2", method = RequestMethod.GET)
    public String PostHome() {

        return "testAdmin2";
    }     

    public void deleteBlog() {

    }

    @RequestMapping(value = "/viewblog/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable Integer id) {

        List<Category> nameList = catDao.listNames();

        Blog blog = blogDao.get(id);

        model.addAttribute("nameList", nameList);
        model.addAttribute("blog", blog);

        return "viewblog";
    }

//    public Blog show() {
//        return null;
//    }
    public List<Blog> view() {
        return null;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewRecentThree(Model model) {

        List<Blog> list = blogDao.list();
        List<Category> nameList = catDao.listNames();
        List<Static> stat = statDao.list();

        model.addAttribute("list", list);
        model.addAttribute("nameList", nameList);
        model.addAttribute("stat", stat);

        return "blog";

    }

    public List<Blog> searchByHashTag() {
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/categorysearch/{id}", method = RequestMethod.GET)
    public List<Blog> searchByCategory(@PathVariable("id") Integer id) {

        List<Blog> list = blogDao.searchByCategory(id);

        return list;
    }

    @ResponseBody
    @RequestMapping(value = "viewblog/{blog}/categorysearch/{id}", method = RequestMethod.GET)
    public List<Blog> searchByCategory(@PathVariable("id") Integer id, @PathVariable("blog") Integer blog) {

        List<Blog> list = blogDao.searchByCategory(id);

        return list;
    }

}
