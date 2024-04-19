package com.jatismobile.unittest.models.unittest;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "orders")
public class OrderModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "item_id")
    Integer itemId;
    
    @Column(name = "buyer_name")
    String buyerName;
    
    @Column(name = "date")
    Date date;
    
    @Column(name = "created_at")
    Date createdAt;
    
    @Column(name = "updated_at")
    Date updatedAt;
    
    // ORM relation
    @ManyToOne
    @JoinColumn(name = "item_id", insertable = false, updatable = false)
    ItemModel item;
}
