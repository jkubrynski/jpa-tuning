package com.kubrynski.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.invoke.MethodHandles;

import ch.qos.logback.classic.LoggerContext;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
abstract class BaseJpaTest {

	protected static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	static {
		LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		loggerContext.setMaxCallerDataDepth(30);
	}

	@PersistenceContext
	protected EntityManager em;

	@Autowired
	protected TransactionTemplate transactionTemplate;

	@Bean
	TransactionTemplate transactionTemplate(PlatformTransactionManager transactionManager) {
		return new TransactionTemplate(transactionManager);
	}

	protected void doInTransaction(final Runnable runnable) {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
				runnable.run();
			}
		});
	}
}
