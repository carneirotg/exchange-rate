package com.code.service.exchange.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.code.service.exchange.base.ExchangeRateConfig;
import com.code.service.exchange.entities.Rate;
import com.code.service.exchange.repository.ExchangeRateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ExchangeRateConfig.class, ExchangeRateController.class })
public class ExchangeControllerTest {

	private MockMvc mockMvc;
	
	@Autowired
	private ExchangeRateRepository rRepo;

	@Autowired
	@InjectMocks
	private ExchangeRateController exchangeRateController;

	@Before
	public void setUp() {
		final StaticApplicationContext applicationContext = new StaticApplicationContext();

		final WebMvcConfigurationSupport webMvcConfigurationSupport = new WebMvcConfigurationSupport();
		webMvcConfigurationSupport.setApplicationContext(applicationContext);

		mockMvc = MockMvcBuilders.standaloneSetup(exchangeRateController)
				.setHandlerExceptionResolvers(webMvcConfigurationSupport.handlerExceptionResolver()).build();

	}
	
	private Rate createRate() {
		Rate r = new Rate();
		r.setCreated(LocalDate.now());
		r.setRate(1.65);
		return r;
	}
	
	@Test
	public void get_latestRate_withSuccess() throws Exception{
		
		Rate rate = createRate();
		
		when(rRepo.findFirstByOrderByCreatedDesc()).thenReturn(rate);
		
		this.mockMvc.perform(get("/exchangerate/v1/rates/latest")
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.rate").value("1.65"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.created").exists());
	}
	
	@Test
	public void get_historicalRate_withSuccess() throws Exception{
		
		String startDate = "2018-06-21";
		String endDate   = "2018-06-22";
		
		this.mockMvc.perform(get("/exchangerate/v1/rates/history?start_date="+startDate+"&end_date="+endDate)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void get_historicalRate_failWithStartDateAfterEndDate() throws Exception{
		String startDate = "2018-06-25";
		String endDate   = "2018-06-22";
		
		this.mockMvc.perform(get("/exchangerate/v1/rates/history?start_date="+startDate+"&end_date="+endDate)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}
