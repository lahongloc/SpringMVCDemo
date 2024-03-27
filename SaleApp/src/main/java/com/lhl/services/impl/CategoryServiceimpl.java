/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhl.services.impl;

import com.lhl.pojo.Category;
import com.lhl.repositories.CategoryRepository;
import com.lhl.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class CategoryServiceimpl implements CategoryService {
    @Autowired
    private CategoryRepository cateRepo;
    @Override
    public List<Category> getCates() {
        return this.cateRepo.getCates();
    }
    
}
