/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dao;

import com.mycompany.capstoneproject.dto.HashTag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class HashTagDao {
    
    private static final String SQL_GET_HASHTAG_NAME = "select * from hashtags where hash_name = ?;";
    private static final String SQL_GET_HASHTAG_ID = "select * from hashtags where id = ?;"; 
    private static final String SQL_UPDATE_HASHTAG = "update hashtags set hash_name = ? where id = ?";
    private static final String SQL_INSERT_HASHTAG = "insert into hashtags (hash_name) VALUES(?)";
    private static final String SQL_LIST_HASHTAG = "select * from hashtags";
    private static final String SQL_REMOVE_HASHTAG = "DELETE FROM hashtags WHERE id = ?";
    
    private JdbcTemplate template;
    
    HashTagDao(JdbcTemplate template){
        this.template = template;
        
    }
    
    public HashTag get(String name) {

        HashTag hash = template.queryForObject(SQL_GET_HASHTAG_NAME, new HashTagMapper(), name);

        return hash;

    }   
    
    public HashTag get(Integer id){
        
        HashTag hash = template.queryForObject(SQL_GET_HASHTAG_ID, new HashTagMapper(), id);
        
        return hash;
        
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public HashTag create(HashTag hashTag){
        
        template.update(SQL_INSERT_HASHTAG,
                hashTag.getName());
        
        int newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        hashTag.setId(newId);
        
        return hashTag;
        
    }
    
    public void delete(Integer id){
        
        template.update(SQL_REMOVE_HASHTAG, id);
        
    }
    
    public void update(HashTag hashTag){
        
        template.update(SQL_UPDATE_HASHTAG, 
                hashTag.getName(),
                hashTag.getId());

    }
    
    public List<HashTag> list(){
        
        List<HashTag> list = template.query(SQL_LIST_HASHTAG, new HashTagMapper());
        
        return list;
        
    }  

    private class HashTagMapper implements RowMapper<HashTag> {

        public HashTagMapper() {
        }

        @Override
        public HashTag mapRow(ResultSet rs, int i) throws SQLException {
            
            HashTag hash = new HashTag();
            
            hash.setId(rs.getInt("id"));
            hash.setName(rs.getString("name"));
            
            return hash;


        }
    }
    
}
