package com.kubrynski.jpa;

import com.kubrynski.jpa.model.Car;
import org.junit.Test;

public class SampleTest extends BaseJpaTest {

	@Test
	public void name() {
		doInTransaction(() -> {
			em.persist(new Car());
		});
	}
}
