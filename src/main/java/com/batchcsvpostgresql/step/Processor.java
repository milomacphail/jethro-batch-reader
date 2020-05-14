package com.batchcsvpostgresql.step;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.batchcsvpostgresql.models.TransactionRecord;
import org.springframework.transaction.TransactionSuspensionNotSupportedException;

public class Processor implements ItemProcessor<TransactionRecord, TransactionRecord> {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    @Override
    public TransactionRecord process(TransactionRecord transactionRecord) throws Exception {
        Random r = new Random();
        final int step = transactionRecord.getStep();
        final String type = transactionRecord.getType().toUpperCase();
        final float amount = transactionRecord.getAmount();
        final String nameOrig = transactionRecord.getNameOrig().toUpperCase();
        final float oldBalanceOrig = transactionRecord.getOldBalanceOrig();
        final float newBalanceOrig = transactionRecord.getNewBalanceOrig();
        final String nameDest = transactionRecord.getNameDest().toUpperCase();
        final float oldBalanceDest = transactionRecord.getOldBalanceDest();
        final float newBalanceDest = transactionRecord.getNewBalanceDest();
        final int isFraud = transactionRecord.getIsFraud();
        final int isFlaggedFraud = transactionRecord.getIsFlaggedFraud();

        final TransactionRecord fixedTransactionRecord = new TransactionRecord(step, type, amount, nameOrig, oldBalanceOrig, newBalanceOrig, nameDest, oldBalanceDest, newBalanceDest, isFraud, isFlaggedFraud);

        log.info("Converting " + transactionRecord + " into " + fixedTransactionRecord + ".");
        return fixedTransactionRecord;
    }

}
