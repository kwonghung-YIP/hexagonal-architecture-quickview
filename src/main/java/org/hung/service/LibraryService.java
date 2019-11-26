package org.hung.service;

import org.hung.pojo.Book;
import org.hung.pojo.Borrower;
import org.hung.pojo.BorrowingRecord;
import org.hung.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LibraryService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public void borrowBook(Borrower borrower,Book book) {
	}
	
	public void returnBook(BorrowingRecord record) {
		
	}

}
