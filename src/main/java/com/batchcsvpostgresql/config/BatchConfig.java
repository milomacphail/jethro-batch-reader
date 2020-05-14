package com.batchcsvpostgresql.config;

import com.batchcsvpostgresql.dao.TransactionRecordDao;
import com.batchcsvpostgresql.models.TransactionRecord;
import com.batchcsvpostgresql.step.Listener;
import com.batchcsvpostgresql.step.Writer;
import com.batchcsvpostgresql.step.Processor;
import com.batchcsvpostgresql.step.Reader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

    @Override
    protected JobRepository createJobRepository() throws Exception {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public TransactionRecordDao transactionRecordDao;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).listener(new Listener(transactionRecordDao))
                .flow(step1()).end().build();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor= new SimpleAsyncTaskExecutor("spring_batch");
        asyncTaskExecutor.setConcurrencyLimit(5);
        return asyncTaskExecutor;
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1").<TransactionRecord, TransactionRecord>chunk(200000)
                .reader(Reader.reader("transactionRecords.csv"))
                .processor(new Processor()).writer(new Writer(transactionRecordDao))
                .taskExecutor(taskExecutor()).build();
    }
}
