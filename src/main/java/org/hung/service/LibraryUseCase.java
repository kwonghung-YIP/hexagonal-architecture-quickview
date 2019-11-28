package org.hung.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import org.hung.exception.BookNotFoundException;
import org.hung.exception.BorrowerNotFoundException;
import org.hung.exception.BorrowingRecordNotFoundException;
import org.hung.exception.RecordNotFoundException;
import org.hung.pojo.Book;
import org.hung.pojo.Borrower;
import org.hung.pojo.BorrowingRecord;
import org.hung.repo.BookRepository;
import org.hung.repo.BorrowerRepository;
import org.hung.repo.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryUseCase {

	@Autowired
	private BorrowerRepository borrowerRepo;
	
	@Autowired
	private BookRepository bookRepo;

	@Autowired
	private BorrowingRecordRepository recordRepo;
	
	public BorrowingRecord borrowBook(String borrowerUid, String bookNo) throws RecordNotFoundException {
		Optional<Borrower> borrowerOpt = borrowerRepo.findById(borrowerUid);
		Borrower borrower = borrowerOpt.orElseThrow(BorrowerNotFoundException::new);
		
		Optional<Book> bookOpt = bookRepo.findById(bookNo);
		Book book = bookOpt.orElseThrow(BookNotFoundException::new);
		
		BorrowingRecord record = new BorrowingRecord();
		
		LocalDate borrowDate = LocalDate.now();
		
		LocalDate dueDate = borrowDate.plusDays(14);
		LocalDate adjustedDueDate = adjustDueDateForHolidays(dueDate);
		
		record.setBorrowerUid(borrower.getUid());
		record.setBookNo(book.getBookNo());
		record.setBorrowDate(borrowDate);
		record.setDueDate(adjustedDueDate);
		
		record = recordRepo.save(record);
		
		return record;
	}
	
	public BorrowingRecord returnBook(long recNo) throws RecordNotFoundException {
		Optional<BorrowingRecord> recordOpt = recordRepo.findById(recNo);
		BorrowingRecord record = recordOpt.orElseThrow(BorrowingRecordNotFoundException::new);
		
		LocalDate dueDate = record.getDueDate();
		LocalDate returnDate = LocalDate.now();
		
		record.setReturnDate(returnDate);

		if (returnDate.isAfter(dueDate)) {
			long overDueDays = ChronoUnit.DAYS.between(record.getDueDate(), returnDate);
			
			BigDecimal overDueFines = BigDecimal.valueOf(overDueDays * 1.2d);
			
			record.setOverdueFines(overDueFines);
		}
		
		record = recordRepo.save(record);		
		return record;
	}
	
	private LocalDate adjustDueDateForHolidays(LocalDate date) {
		return date;
	}

}
