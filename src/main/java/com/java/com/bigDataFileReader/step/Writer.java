package com.bigDataFileReader.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.bigDataFileReader.dao.TransactionRecordDao;
import com.bigDataFileReader.models.TransactionRecord;

public class Writer implements ItemWriter<TransactionRecord> {

    private final TransactionRecordDao transactionRecordDao;

    public Writer(TransactionRecordDao transactionRecordDao) {
        this.transactionRecordDao = transactionRecordDao;
    }

    @Override
    public void write(List<? extends TransactionRecord> transactionRecords) throws Exception {
        transactionRecordDao.insert(transactionRecords);
    }

}
