package com.example.assignment1.reporitory;


import com.example.assignment1.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
