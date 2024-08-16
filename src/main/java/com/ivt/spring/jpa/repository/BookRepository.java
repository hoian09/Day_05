package com.ivt.spring.jpa.repository;

import com.ivt.spring.jpa.entity.BookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {
    List<BookEntity> findByAuthor(String author);
    List<BookEntity> findByNameAndAuthor(String name, String author);


    BookEntity findByBookDetailsIsbn(String isbn);

    List<BookEntity> findByBookDetailsPriceLessThan(int price);
    List<BookEntity> findByBookDetailsLessThanEqual(int price);
    List<BookEntity> findByBookDetailsPriceGreaterThanEqual (int price);

    List<BookEntity> findByNameContaining(String name);
    @Query("select b from BookEntity b where b.name like ?1%")
    List<BookEntity> getBookNameStartWith(String name);

    @Query("select b from BookEntity b where b.bookDetails.price > ?1")
    List<BookEntity> getBookPriceGreaterThan (int price);
    @Query(value = "select * from book", nativeQuery = true)
    List<BookEntity> findAll();
}
