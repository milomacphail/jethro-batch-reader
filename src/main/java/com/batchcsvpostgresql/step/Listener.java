package com.batchcsvpostgresql.step;

import java.util.List;

import com.batchcsvpostgresql.dao.TransactionRecordDao;
import com.batchcsvpostgresql.models.TransactionRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.boot.autoconfigure.jms.JmsProperties;


public class Listener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(Listener.class);

    private final TransactionRecordDao transactionRecordDao;

    public Listener(TransactionRecordDao transactionRecordDao) {
        this.transactionRecordDao = transactionRecordDao;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job finished! Check window for results.");

            List<TransactionRecord> transactionRecordsList = transactionRecordDao.loadAllTransactionRecords();

            for (TransactionRecord transactionRecord : transactionRecordsList) {
                log.info("Found < " + transactionRecord + "> in the database.");
            }
        }
    }
}
