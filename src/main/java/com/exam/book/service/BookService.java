package com.exam.book.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.exam.book.domain.Book;
import com.exam.book.domain.BookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	@Transactional // 서비스 함수 종료될 때 commit or rollback
	public Book 저장하기(Book book) {
		return bookRepository.save(book);
	}
	
	@Transactional (readOnly = true) // 변경감지 기능 막기(연산 감소), update 정합성 유지, insert 유령데이터 해결 x
	public Book 한건가져오기(Long id) {
		return bookRepository.findById(id).
				orElseThrow( ()-> new IllegalArgumentException("id를 확인해주세요"));
	}
	
	@Transactional (readOnly = true)
	public List<Book> 모두가져하기(Book book) {
		return bookRepository.findAll();
	}
	
	@Transactional
	public Book 수정하기(Long id, Book book) {
		// 더티체킹: bookEntity 영속화 시켜서 객체를 변경하면 함수 종료 후(트랜잭션 종료 후)  flush(비워내고 업데이트)
		Book bookEntity = bookRepository.findById(id).
				orElseThrow( ()-> new IllegalArgumentException("id를 확인해주세요"));
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		return bookEntity;
	}
	
	@Transactional
	public String 삭제하기(Long id) {
		bookRepository.deleteById(id);
		return "ok";
	}
	
}
