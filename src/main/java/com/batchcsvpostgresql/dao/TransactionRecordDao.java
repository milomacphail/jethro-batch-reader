package com.batchcsvpostgresql.dao;

import java.util.List;

import com.batchcsvpostgresql.models.TransactionRecord;

public interface TransactionRecordDao {
    public void insert(List<? extends TransactionRecord> transactionRecords);

    List<TransactionRecord> loadAllTransactionRecords();
}
