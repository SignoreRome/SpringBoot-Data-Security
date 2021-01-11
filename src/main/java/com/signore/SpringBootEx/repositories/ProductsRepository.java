package com.signore.SpringBootEx.repositories;

import com.signore.SpringBootEx.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {

//    @Modifying
//    @Query("update User u set u.firstname = ?1 where u.lastname = ?2")
//    int setFixedFirstnameFor(String firstname, String lastname);

    @Modifying
    @Query("update Product p set p.title = ?2, p.price =?3 where p.id=?1")
    void editProduct(Long id, String title, int price);
}
