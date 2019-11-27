package org.hung.web;

import org.hung.pojo.BorrowingRecord;
import org.hung.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
public class LibraryRestController {

	@Autowired
	private LibraryService service;
	
	@GetMapping("/borrow")
	public BorrowingRecord borrowBook(@RequestParam String uid, @RequestParam String bookNo) {
		service.borrowBook(uid, bookNo);
		return null;
	}
	
	@GetMapping("/return")
	public BorrowingRecord returnBook(@RequestParam String recordId) {
		service.borrowBook(null, null);
		return null;
	}
	
}
