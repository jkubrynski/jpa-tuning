# Sandbox project for JPA

It helps you understand what's happening inside Hibernate engine. Build on tests that are:
- easy to write
- easy to debug
- easy to analize

```java
	@Test
	public void sampleTest() {
		doInTransaction(() -> {
			em.persist(new Car());
		});
	}
```

Detailed logging is available as well:
```
[2594 ms] SQL: drop table car if exists
[2598 ms] SQL: drop sequence if exists hibernate_sequence
[2600 ms] SQL: create sequence hibernate_sequence start with 1 increment by 1
[2602 ms] SQL: create table car (id bigint not null, uuid varchar(36) not null, version timestamp, primary key (id))
[2773 ms] SampleTest: Started SampleTest in 1.962 seconds (JVM running for 3.088)
[2895 ms] SQL: call next value for hibernate_sequence
[2914 ms] SQL: insert into car (uuid, version, id) values (?, ?, ?)
Caller+4	 at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3152)
Caller+5	 at org.hibernate.persister.entity.AbstractEntityPersister.insert(AbstractEntityPersister.java:3686)
Caller+6	 at org.hibernate.action.internal.EntityInsertAction.execute(EntityInsertAction.java:90)
Caller+7	 at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:604)
Caller+8	 at org.hibernate.engine.spi.ActionQueue.executeActions(ActionQueue.java:478)
Caller+9	 at org.hibernate.event.internal.AbstractFlushingEventListener.performExecutions(AbstractFlushingEventListener.java:356)
Caller+10	 at org.hibernate.event.internal.DefaultFlushEventListener.onFlush(DefaultFlushEventListener.java:39)
Caller+11	 at org.hibernate.internal.SessionImpl.doFlush(SessionImpl.java:1454)
Caller+12	 at org.hibernate.internal.SessionImpl.managedFlush(SessionImpl.java:511)
Caller+13	 at org.hibernate.internal.SessionImpl.flushBeforeTransactionCompletion(SessionImpl.java:3283)
Caller+14	 at org.hibernate.internal.SessionImpl.beforeTransactionCompletion(SessionImpl.java:2479)
Caller+15	 at org.hibernate.engine.jdbc.internal.JdbcCoordinatorImpl.beforeTransactionCompletion(JdbcCoordinatorImpl.java:473)
Caller+16	 at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.beforeCompletionCallback(JdbcResourceLocalTransactionCoordinatorImpl.java:178)
Caller+17	 at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl.access$300(JdbcResourceLocalTransactionCoordinatorImpl.java:39)
Caller+18	 at org.hibernate.resource.transaction.backend.jdbc.internal.JdbcResourceLocalTransactionCoordinatorImpl$TransactionDriverControlImpl.commit(JdbcResourceLocalTransactionCoordinatorImpl.java:271)
Caller+19	 at org.hibernate.engine.transaction.internal.TransactionImpl.commit(TransactionImpl.java:98)
Caller+20	 at org.springframework.orm.jpa.JpaTransactionManager.doCommit(JpaTransactionManager.java:532)
Caller+21	 at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:746)
Caller+22	 at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:714)
Caller+23	 at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:152)
Caller+24	 at com.kubrynski.jpa.BaseJpaTest.doInTransaction(BaseJpaTest.java:44)
[2927 ms] SQL: drop table car if exists
[2930 ms] SQL: drop sequence if exists hibernate_sequence
```
