package com.hongxu.ripple.gateway;


import java.util.UUID;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AbstractTest extends AbstractJUnit4SpringContextTests {
	
	public String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
}