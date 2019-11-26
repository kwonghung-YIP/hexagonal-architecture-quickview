package org.hung.pojo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class BorrowingRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long recNo;
	
	private String borrowUid;
	
	private String bookNo;
	
	private LocalDate borrowDate;
	
	private LocalDate dueDate;
	
	private LocalDate returnDate;
	
}
