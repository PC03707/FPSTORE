package com.poly.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.entity.Book;
import com.poly.entity.Category;

public interface BookDAO extends JpaRepository<Book, Integer>{	
	
	@Query("SELECT b FROM Book b ORDER BY createdate DESC")
	List<Book> getAllProduct();
	
	@Query("SELECT COUNT(o) FROM Book o")
	Integer countProduct();
	
	List<Book> findByCategory(Category category);
	
	@Query("SELECT b FROM Book b WHERE b.category.id=?1")
	public List<Book> findByCategoryId (Integer cid);
	
	
	@Query("SELECT p FROM Book p WHERE p.name LIKE %:keyword%")
	List<Book> searchProductsByKeyword(String keyword);
	
}
