package com.jatismobile.unittest.repositories.unittest;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jatismobile.unittest.models.unittest.ItemModel;

public interface ItemRepository extends JpaRepository<ItemModel, Integer> {

}