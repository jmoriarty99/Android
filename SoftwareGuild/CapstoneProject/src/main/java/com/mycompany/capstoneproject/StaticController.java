/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject;

import com.mycompany.capstoneproject.dao.CategoryDao;
import com.mycompany.capstoneproject.dao.StaticDao;
import com.mycompany.capstoneproject.dto.Category;
import com.mycompany.capstoneproject.dto.CommandStaticObject;
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

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping({"/stat"})
public class StaticController {
    
    private StaticDao staticDao;
    private CategoryDao catDao;
 
    @Inject
    public StaticController(StaticDao staticDao, CategoryDao catDao){
        this.staticDao = staticDao;
        this.catDao = catDao;
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Static createStatic(@RequestBody CommandStaticObject input){
        
        Static staticPost = new Static();
//        Category cat = new Category();
        
        Date date = Date.valueOf(input.getCreationDate());
        
        staticPost.setTitle(input.getTitle());
        staticPost.setStaticContent(input.getStaticContent());
        staticPost.setCreationDate(date.toLocalDate());
        
//        cat.setName(input.getCategory());
//        staticPost.setCategory(cat);        
        
        Static createdStatic = staticDao.create(staticPost);
        
        return createdStatic;
    }
    
    @RequestMapping(value = "/testAdmin2", method = RequestMethod.GET)
    public String PostHome() {

        return "testAdmin2";
    }    
    
    public void deleteStatic(Integer staticId){
        
        staticDao.delete(staticId);
        
    }
    
    public Static show(Integer staticId){
        
        Static shownStatic = staticDao.get(staticId);
        
        return shownStatic;
    }
    
    public Static edit(Integer staticId){
    
        Static staticPost = show(staticId);
        
        staticDao.update(staticPost);
        
        return staticPost;
    }
    
    public List<Static> view(){
        return null;
    }
    
    public List<Static> searchByHashTag(String search){
        
//        List<Static> results = staticDao.searchByHashTag(search);
        
        return null;
    }
    
    public List<Static> searchByCategory(String search){
        
//        List<Static> results = staticDao.searchByCategory(category);
        
        return null;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewRecentThree(@PathVariable("id") Integer id, Model model) {

        List<Static> list = staticDao.listById(id);
        List<Category> nameList = catDao.listNames();
//        List<Title> title = 

        model.addAttribute("list", list);
        model.addAttribute("nameList", nameList);

        return "viewpost";

    } 
}

