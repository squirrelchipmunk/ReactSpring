package com.exam.book.domain;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository를 상속하여 @Repository 생략 가능
public interface BookRepository extends JpaRepository<Book, Integer>{

}
