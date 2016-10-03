/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dao;

import com.mycompany.capstoneproject.dto.Category;
import com.mycompany.capstoneproject.dto.HashTag;
import com.mycompany.capstoneproject.dto.Static;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class StaticDao {

    private static final String SQL_GET_STATIC_POST = "SELECT * FROM static_page WHERE id = ?";
    private static final String SQL_INSERT_STATIC_POST = "INSERT INTO static_page (static_content, title, pending, creation_date) VALUES (?, ?, ?, ?)";
    private static final String SQL_REMOVE_STATIC_POST = "DELETE FROM static_page WHERE id = ?";
    private static final String SQL_UPDATE_STATIC_POST = "UPDATE static_page SET static_content = ?, title = ?, pending = ?, creation_date = ?, WHERE id = ?";
    private static final String SQL_LIST_STATIC_POST = "select * from static_page";
    private static final String SQL_LIST_STATIC_POST_ID = "select * from static_page where id = ?";

    private JdbcTemplate template;

    @Inject
    public StaticDao(JdbcTemplate template) {
        this.template = template;

    }

    public Static get(Integer id) {

        Static staticPost = template.queryForObject(SQL_GET_STATIC_POST, new StaticMapper(), id);

        return staticPost;

    }

    public Static create(Static staticPost) {

        LocalDate loc = staticPost.getCreationDate();
        Date date = Date.valueOf(loc);

        template.update(SQL_INSERT_STATIC_POST,
                staticPost.getStaticContent(),
                staticPost.getTitle(),
                staticPost.getPendingStatus(),
                date);

        int newId = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        staticPost.setId(newId);

        return staticPost;

    }

    public void delete(Integer id) {

        template.update(SQL_REMOVE_STATIC_POST, id);

    }

    public void update(Static staticPost) {

        template.update(SQL_UPDATE_STATIC_POST,
                staticPost.getStaticContent(),
                staticPost.getTitle(),
                staticPost.getPendingStatus(),
                staticPost.getCreationDate(),
                staticPost.getId());

    }

    public List<Static> list() {

        List<Static> list = template.query(SQL_LIST_STATIC_POST, new StaticMapper());

        return list;

    }

    public List<Static> listById(Integer id) {

        List<Static> list = template.query(SQL_LIST_STATIC_POST_ID, new StaticMapper(), id);

        return list;

    }

    public Static searchByHashTag(HashTag hashTag) {
        return null;

    }

    public Static searchByCategory(Category category) {
        return null;

    }

    private class StaticMapper implements RowMapper<Static> {

        public StaticMapper() {
        }

        @Override
        public Static mapRow(ResultSet rs, int i) throws SQLException {

            Static staticPost = new Static();
            Date date = rs.getDate("creation_date");
            LocalDate ld = date.toLocalDate();

            staticPost.setId(rs.getInt("id"));
            staticPost.setTitle(rs.getString("title"));
            staticPost.setStaticContent(rs.getString("static_content"));
            staticPost.setPendingStatus(rs.getBoolean("pending"));
            staticPost.setCreationDate(ld);

            return staticPost;

        }
    }
}
