package org.hung.service;

import java.time.LocalDate;
import java.util.Optional;

import org.hung.pojo.Book;
import org.hung.pojo.Borrower;
import org.hung.pojo.BorrowingRecord;
import org.hung.repo.BookRepository;
import org.hung.repo.BorrowerRepository;
import org.hung.repo.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {

	@Autowired
	private BorrowerRepository borrowerRepo;
	
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BorrowingRecordRepository borrowRecRepo;
	
	public BorrowingRecord borrowBook(String borrowUid, String bookNo) {
		Optional<Borrower> borrower = borrowerRepo.findById(bookNo);
		
		Optional<Book> book = bookRepo.findById(bookNo);
		
		BorrowingRecord record = new BorrowingRecord();
		
		record.setBorrowerUid(borrower.get().getUid());
		record.setBookNo(book.get().getBookNo());
		record.setBorrowDate(LocalDate.now());
		record.setDueDate(LocalDate.now().plusDays(14));
		
		borrowRecRepo.save(record);
		
		return record;
	}
	
	public void returnBook(BorrowingRecord record) {
		
	}

}
