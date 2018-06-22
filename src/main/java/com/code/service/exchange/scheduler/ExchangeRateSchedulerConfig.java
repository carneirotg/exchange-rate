package com.code.service.exchange.scheduler;

import org.apache.tomcat.jni.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateSchedulerConfig implements InitializingBean{

	private static final Logger log = LoggerFactory.getLogger(ExchangeRateSchedulerConfig.class);
	
	@Value("${scheduler.fetchRatePeriod}")
	private long checkPeriod;
	
	@Value("${scheduler.forexUrl}")
	private String forexUrl;
	
	public long getFetchRatePeriod(){
		return checkPeriod;
	}
	
	public String getForexUrl(){
		return forexUrl;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("The check period is configured to: " + Time.sec(checkPeriod));
		
	}

}
