/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dao;

import com.mycompany.capstoneproject.dto.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class CategoryDao {

    private JdbcTemplate template;

    private static final String SQL_GET_CATEGORY_NAME = "select * from categories where category_name = ?;";
    private static final String SQL_GET_CATEGORY_ID = "select * from categories where id = ?;";
    private static final String SQL_GET_CATEGORY_NAME_LIST = "select * from categories limit 10";
    private static final String SQL_LIST_CATEGORY = "select * from categories";
    private static final String SQL_UPDATE_CATEGORY = "update categories set name = ? where id = ?";
    private static final String SQL_INSERT_CATEGORY = "insert into categories (name) VALUES(?)";
    


    public CategoryDao(JdbcTemplate temp) {
        this.template = temp;
    }

    public Category get(String name) {

        Category cat = template.queryForObject(SQL_GET_CATEGORY_NAME, new CategoryMapper(), name);

        return cat;

    }

    public Category get(int id) {

        Category cat = template.queryForObject(SQL_GET_CATEGORY_ID, new CategoryMapper(), id);

        return cat;

    }

    public Category create(Category category) {
        
       template.update(SQL_INSERT_CATEGORY,
                category.getName());
        
        int newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        category.setId(newId);
        
        return category;    

    }

    public void delete(Integer id) {

    }

    public void update(Category category) {
        
        template.update(SQL_UPDATE_CATEGORY, 
        category.getName(),
        category.getId());

    }

    public List<Category> list() {
        
        List<Category> list = template.query(SQL_LIST_CATEGORY, new CategoryMapper());
        
        return list;
    }

    public List<Category> listNames() {

        List<Category> nameList = template.query(SQL_GET_CATEGORY_NAME_LIST, new CategoryMapper());
        return nameList;

    }

    private class CategoryMapper implements RowMapper<Category> {

        public CategoryMapper() {
        }

        @Override
        public Category mapRow(ResultSet rs, int i) throws SQLException {

            Category cat = new Category();

            cat.setId(rs.getInt("id"));
            cat.setName(rs.getString("category_name"));

            return cat;

        }
    }

}
