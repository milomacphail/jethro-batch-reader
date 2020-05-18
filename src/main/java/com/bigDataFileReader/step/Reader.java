package com.bigDataFileReader.step;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import com.bigDataFileReader.models.TransactionRecord;
import org.springframework.core.io.ClassPathResource;

public class Reader {
    public static FlatFileItemReader<TransactionRecord> reader(String path) {
        FlatFileItemReader<TransactionRecord> reader = new FlatFileItemReader<TransactionRecord>();

        reader.setResource(new ClassPathResource(path));
        reader.setLineMapper(new DefaultLineMapper<TransactionRecord>() {
            {
                setLineTokenizer(new DelimitedLineTokenizer() {
                    {
                        setNames(new String[]{"step", "type", "amount", "nameOrig", "oldBalanceOrig", "newBalanceOrig", "nameDest", "oldBalanceDest", "newBalanceDest", "isFraud", "isFlaggedFraud"});
                    }
                });
                setFieldSetMapper(new BeanWrapperFieldSetMapper<TransactionRecord>() {
                    {
                        setTargetType(TransactionRecord.class);
                    }
                });
            }
        });
        return reader;
    }
}
