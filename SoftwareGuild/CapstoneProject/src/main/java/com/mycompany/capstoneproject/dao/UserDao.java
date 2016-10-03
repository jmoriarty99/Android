/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dao;

import com.mycompany.capstoneproject.dto.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class UserDao {
    
    private static final String SQL_GET_USER_NAME = "select * from user where username = ?;";
    private static final String SQL_GET_USER_ID = "select * from user where id = ?;";
    private static final String SQL_LIST_USER = "select * from user";
    
    private JdbcTemplate template;
    
    public UserDao(JdbcTemplate template){
        this.template = template;
        
    }
    
    public User get(String name) {

        User user = template.queryForObject(SQL_GET_USER_NAME, new UserMapper(), name);

        return user;

    }   
    
    public User get(Integer id){
        
        User user = template.queryForObject(SQL_GET_USER_ID, new UserMapper(), id);

        return user;  
                
    }
    
    public User create(User user){
        return null;
        
    }
    
    public void delete(Integer id){
        
    }
    
    public User update(User user){
        return null;
    }
    
    public List<User> list(){
        
        List<User> list = template.query(SQL_LIST_USER, new UserMapper());
        
        return list;
        
    }

    private static class UserMapper implements RowMapper<User> {

        public UserMapper() {
        }

        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            
            User user = new User();
            
            user.setId(rs.getInt("id"));
            user.setUserName(rs.getString("username"));
            user.setPassword(rs.getString("passwordd"));
            
            return user;
        }
    }
    
}
