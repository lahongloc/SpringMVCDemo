/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhl.controllers;

import com.lhl.services.CategoryService;
import com.lhl.services.ProductService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author admin
 */
@Controller
public class IndexController {
    @Autowired
    private CategoryService cateService;
    
    @Autowired
    private ProductService productService;
    
    
    
    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("categories", this.cateService.getCates());
        Map<String, String> params = new HashMap<>();
        params.put("kw", "iphone");
        model.addAttribute("products", this.productService.getProducts(params));
        return "index";
        
    }
}
