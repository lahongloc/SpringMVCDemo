/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lhl.repositories.impl;

import com.lhl.pojo.Product;
import com.lhl.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Product> getProducts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder c = s.getCriteriaBuilder();
        CriteriaQuery<Product> q = c.createQuery(Product.class);
        Root r = q.from(Product.class);
        q.select(r);
        List<Predicate> predicates = new ArrayList<>();
        
        String kw = params.get("kw");
        if(kw != null && !kw.isEmpty()) {
            predicates.add(c.like(r.get("name"), String.format("%%%s%%", kw)));
        }
        
        String fromPrice = params.get("fromPrice");
        String toPrice = params.get("toPrice");
        
        if(fromPrice != null && !fromPrice.isEmpty()) {
            predicates.add(c.greaterThanOrEqualTo(r.get("price"), Double.parseDouble(fromPrice)));
        }
        
        if(toPrice != null && !toPrice.isEmpty()) {
            predicates.add(c.lessThanOrEqualTo(r.get("price"), Double.parseDouble(toPrice)));
        }
        
        q.where(predicates.toArray(Predicate[]::new));
        
        return s.createQuery(q).getResultList();
    }
    
}
