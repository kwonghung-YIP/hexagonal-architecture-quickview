package org.hung.web;

import org.hung.exception.RecordNotFoundException;
import org.hung.pojo.BorrowingRecord;
import org.hung.service.LibraryUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryRestAdaptor {

	@Autowired
	private LibraryUseCase useCase;
	
	@GetMapping("/borrow")
	public BorrowingRecord borrowBook(@RequestParam String uid, @RequestParam String bookNo) throws RecordNotFoundException {
		return useCase.borrowBook(uid, bookNo);
	}
	
	@GetMapping("/return")
	public BorrowingRecord returnBook(@RequestParam long recNo) throws RecordNotFoundException {
		return useCase.returnBook(recNo);
	}
	
}
