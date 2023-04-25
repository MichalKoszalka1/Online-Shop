package com.Shop.Shop.Service.repository;

import com.Shop.Shop.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@SuppressWarnings( "ALL" )
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE " + "CONCAT (p.id, ' ' ,p.name, ' ' ,p.category, ' ' ,'p.brand')" + "LIKE %?1%")
    public Page<Product> findAll(String keyword, Pageable pageable);

}
