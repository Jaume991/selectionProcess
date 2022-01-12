package com.example.selection.selection.repository;

import java.util.List;

import com.example.selection.selection.repository.models.PriceEntity;

import org.springframework.data.repository.CrudRepository;


public interface PriceRepository extends CrudRepository<PriceEntity, Integer> {
    List<PriceEntity> findByProductIdAndBrandId(int productId, int brandId);
}
