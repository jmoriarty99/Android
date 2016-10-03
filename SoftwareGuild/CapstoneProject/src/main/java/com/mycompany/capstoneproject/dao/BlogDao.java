/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dao;

import com.mycompany.capstoneproject.dto.Blog;
import com.mycompany.capstoneproject.dto.Category;
import com.mycompany.capstoneproject.dto.HashTag;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class BlogDao {

    private JdbcTemplate template;
    private CategoryDao catDao;

    private static final String SQL_INSERT_BLOG = "insert into blog set \n"
            + "creation_date  = ?,\n"
            + "category_id = ?,\n"
            + "pending_status = ?,\n"
            + "blog_content = ?,\n"
            + "expirable = ?,\n"
            + "title = ?,\n"
            + "post_date = ?,\n"
            + "expire_date = ?,\n"
            + "post_status = ?;";
    private static final String SQL_GET_BLOG = "select * from blog where id = ?;";
    private static final String SQL_GET_THREE_MOST_RECENT = "select * from blog order by creation_date desc limit 3";
    private static final String SQL_GET_BLOG_BY_CATEGORY = "select * from blog where category_id = ?";

    public BlogDao(JdbcTemplate temp, CategoryDao catDao) {
        this.template = temp;
        this.catDao = catDao;
    }

    public Blog get(Integer id) {

        Blog blog = template.queryForObject(SQL_GET_BLOG, new BlogMapper(), id);

        return blog;

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Blog create(Blog blog) {
        
        LocalDate loc = blog.getCreationDate();
        Date date = Date.valueOf(loc);
        
        LocalDate loc1 = blog.getPostDate();
        Date date1 = Date.valueOf(loc1);
        
        template.update(SQL_INSERT_BLOG,
                date,
                blog.getCategory().getId(),
                blog.getPendingStatus(),
                blog.getBlogContent(),
                blog.getExpirableStatus(),
                blog.getTitle(),
                date1,
                blog.getExpireDate(),
                blog.getPostStatus());

        int newId = template.queryForObject("select last_insert_id()", Integer.class);
        blog.setId(newId);

        return blog;

    }

    public void delete(Integer id) {

    }

    public Blog update(Blog blog) {
        return null;
    }

    public List<Blog> list() {

        List<Blog> list = template.query(SQL_GET_THREE_MOST_RECENT, new BlogMapper());
        return list;

    }

    public Blog searchByHashTag(HashTag hashTag) {
        return null;
    }

    public List<Blog> searchByCategory(Integer id) {

        List<Blog> list = template.query(SQL_GET_BLOG_BY_CATEGORY, new BlogMapper(), id);

        return list;
    }

    private class BlogMapper implements RowMapper<Blog> {

        public BlogMapper() {
        }

        @Override
        public Blog mapRow(ResultSet rs, int i) throws SQLException {
            //Parse crreation date
            Blog blog = new Blog();
            Date date = rs.getDate("creation_date");
            LocalDate ld = date.toLocalDate();

            blog.setId(rs.getInt("id"));
            blog.setCreationDate(ld);
            //SET category object
            int categoryId = rs.getInt("category_id");
            blog.setCategory(catDao.get(categoryId));

            blog.setPendingStatus(rs.getBoolean("pending_status"));
            blog.setBlogContent(rs.getString("blog_content"));
            blog.setExpirableStatus(rs.getBoolean("expirable"));
            blog.setTitle(rs.getString("title"));
            //PARSE post date
            date = rs.getDate("post_date");
            ld = date.toLocalDate();
            blog.setPostDate(ld);
            //PARSE expire date
            if (rs.getDate("expire_date") != null) {
                date = rs.getDate("expire_date");
                ld = date.toLocalDate();
                blog.setExpireDate(ld);
            }

            blog.setPostStatus(rs.getBoolean("post_status"));
            blog.setShowDate(ld.toString());
            
            return blog;
        }
    }

}
