package org.hung.repo;

import org.hung.pojo.BorrowingRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends CrudRepository<BorrowingRecord,Long> {

}
