package org.hung;

import org.hung.exception.RecordNotFoundException;
import org.hung.pojo.BorrowingRecord;
import org.hung.service.LibraryUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JUnit5TestAdapter {

	@Autowired
	private LibraryUseCase useCase;
	
	@Test
	void whenBorrowSuccess_thenReturnBorrowingRecord() throws RecordNotFoundException {
		String uid = "U0001";
		String bookNo = "B0001";
		BorrowingRecord record = useCase.borrowBook(uid, bookNo);
		//assert
	}
	
	@Test
	void whenOverDue_thenAhebOverdueFines() throws RecordNotFoundException {
		long recNo = 1;
		BorrowingRecord record = useCase.returnBook(recNo);
	}
	
}
