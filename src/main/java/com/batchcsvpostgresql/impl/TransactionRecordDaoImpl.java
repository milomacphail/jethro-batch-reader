package com.batchcsvpostgresql.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.batchcsvpostgresql.dao.TransactionRecordDao;
import com.batchcsvpostgresql.models.TransactionRecord;

@Repository
public class TransactionRecordDaoImpl extends JdbcDaoSupport implements TransactionRecordDao {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public void insert(List<? extends TransactionRecord> TransactionRecords) {
        String sqlConnection = "INSERT INTO transaction_records " + "(step, type, amount, name_orig, old_balance_orig, new_balance_orig, name_dest, old_balance_dest, new_balance_dest, is_fraud, is_flagged_fraud) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        getJdbcTemplate().batchUpdate(sqlConnection, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                TransactionRecord transactionRecord = TransactionRecords.get(i);
                preparedStatement.setInt(1, transactionRecord.getStep());
                preparedStatement.setString(2, transactionRecord.getType());
                preparedStatement.setFloat(3, transactionRecord.getAmount());
                preparedStatement.setString(4, transactionRecord.getNameOrig());
                preparedStatement.setFloat(5, transactionRecord.getOldBalanceOrig());
                preparedStatement.setFloat(6, transactionRecord.getNewBalanceOrig());
                preparedStatement.setString(7, transactionRecord.getNameDest());
                preparedStatement.setFloat(8, transactionRecord.getOldBalanceDest());
                preparedStatement.setFloat(9, transactionRecord.getNewBalanceDest());
                preparedStatement.setInt(10, transactionRecord.getIsFraud());
                preparedStatement.setInt(11, transactionRecord.getIsFlaggedFraud());
            }

            public int getBatchSize() {
                return TransactionRecords.size();
            }
        });
    }


    public List<TransactionRecord> loadAllTransactionRecords() {
        String sqlConnection = "SELECT * FROM transactionrecords";
        List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sqlConnection);

        List<TransactionRecord> result = new ArrayList<TransactionRecord>();

        for (Map<String, Object> row : rows) {
            TransactionRecord transactionRecord = new TransactionRecord();
            transactionRecord.setStep((Integer) row.get("step"));
            transactionRecord.setType((String) row.get("type"));
            transactionRecord.setAmount((Float) row.get("amount"));
            transactionRecord.setNameOrig((String) row.get("name_orig"));
            transactionRecord.setOldBalanceOrig((Float) row.get("old_balance_orig"));
            transactionRecord.setNewBalanceOrig((Float) row.get("new_balance_orig"));
            transactionRecord.setNameDest((String) row.get("name_dest"));
            transactionRecord.setOldBalanceDest((Float) row.get("old_balance_dest"));
            transactionRecord.setNewBalanceDest((Float) row.get("new_balance_dest"));
            transactionRecord.setIsFraud((Integer) row.get("is_fraud"));
            transactionRecord.setIsFlaggedFraud((Integer) row.get("is_flagged_fraud"));
            result.add(transactionRecord);
        }
        return result;
    }
}
