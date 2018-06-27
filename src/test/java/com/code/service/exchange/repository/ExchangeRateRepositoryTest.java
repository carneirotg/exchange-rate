package com.code.service.exchange.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.code.service.exchange.entities.ExchangeRate;

@RunWith(SpringRunner.class)
public class ExchangeRateRepositoryTest {

	@MockBean
	private ExchangeRateRepository rRepo;
	
	private ExchangeRate createRate(BigDecimal value) {
		ExchangeRate r = new ExchangeRate();
		r.setCreated(LocalDate.now());
		r.setRate(value);
		return r;
	}
	
	@Test
	public void findFirstByOrderByCreatedDesc_LastInserted_WithSuccess(){
		ExchangeRate r1 = createRate(BigDecimal.valueOf(1.67));
		when(rRepo.findFirstByOrderByCreatedDesc()).thenReturn(r1);
		assertThat(rRepo.findFirstByOrderByCreatedDesc(), is(r1));
		
		ExchangeRate r2 = createRate(BigDecimal.valueOf(1.53));
		when(rRepo.findFirstByOrderByCreatedDesc()).thenReturn(r2);
		assertThat(rRepo.findFirstByOrderByCreatedDesc(), is(r2));
		
		
	}
	
	@Test
	public void findAllByCreatedBetween_WithSuccess(){
		LocalDate startDate = LocalDate.of(2018, 6, 24);
		LocalDate endDate = LocalDate.of(2018, 6, 25);
		
		List<ExchangeRate> rates = new ArrayList<ExchangeRate>();
		rates.add(new ExchangeRate(startDate, new BigDecimal(1.37)));
		rates.add(new ExchangeRate(startDate, new BigDecimal(1.56)));
		rates.add(new ExchangeRate(endDate, new BigDecimal(1.51)));
		rates.add(new ExchangeRate(endDate, new BigDecimal(1.65)));
		
		when(rRepo.findAllByCreatedBetweenOrderByCreatedAsc(startDate, endDate)).thenReturn(rates);
		
		assertThat(rates.size(), is(4));
		
		List<ExchangeRate> ratesPersisted = rRepo.findAllByCreatedBetweenOrderByCreatedAsc(startDate, endDate);
		
		assertThat(ratesPersisted.get(0).getCreated(), is(startDate));
		assertThat(ratesPersisted.get(0).getRate(), is(new BigDecimal(1.37)));
		assertThat(ratesPersisted.get(1).getCreated(), is(startDate));
		assertThat(ratesPersisted.get(1).getRate(), is(new BigDecimal(1.56)));
		assertThat(ratesPersisted.get(2).getCreated(), is(endDate));
		assertThat(ratesPersisted.get(2).getRate(), is(new BigDecimal(1.51)));
		assertThat(ratesPersisted.get(3).getCreated(), is(endDate));
		assertThat(ratesPersisted.get(3).getRate(), is(new BigDecimal(1.65)));
		
	}
	
}
