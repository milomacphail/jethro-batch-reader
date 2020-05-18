package com.bigDataFileReader.dao;

import java.util.List;

import com.bigDataFileReader.models.TransactionRecord;

public interface TransactionRecordDao {
    public void insert(List<? extends TransactionRecord> transactionRecords);

    List<TransactionRecord> loadAllTransactionRecords();
}
