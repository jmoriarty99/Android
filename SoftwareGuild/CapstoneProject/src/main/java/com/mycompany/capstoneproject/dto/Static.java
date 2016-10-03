/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneproject.dto;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author apprentice
 */
public class Static {
    
    private Integer id;
    private LocalDate creationDate;
    private Boolean pendingStatus;
    private String staticContent;
    private String title;    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getPendingStatus() {
        return pendingStatus;
    }

    public void setPendingStatus(Boolean pendingStatus) {
        this.pendingStatus = pendingStatus;
    }

    public String getStaticContent() {
        return staticContent;
    }

    public void setStaticContent(String staticContent) {
        this.staticContent = staticContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
}
