package com.sdo.spring_boot_demo;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sdo.entity.ResmsEntity;
import com.sdo.mapper.global.ResmsMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisGlobalTest {
	@Autowired
	ResmsMapper resmsMapper;
	
	@Autowired
	ApplicationContext applicationContext;
	private final static Logger logger = LoggerFactory.getLogger(MybatisGlobalTest.class);
	@Test
	public void testListAll() throws Exception {
		List<ResmsEntity> list = resmsMapper.listAll();
		for (ResmsEntity resms : list) {
//			System.out.println(resms);
			logger.debug(resms.toString());
		}
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void testInsert() throws Exception {
		ResmsEntity resms = new ResmsEntity();
		resms.setResmsNumber(10690079);
		resms.setTelecomName("中国微信");
		resms.setTelecom("10001");
		resms.setTelecomStatus(1);
		try {
			resmsMapper.insert(resms);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertTrue(true);

	}
	
	@Test
	public void testInsert2() throws Exception {
		ResmsEntity resms = new ResmsEntity();
		resms.setResmsNumber(10690079);
		resms.setTelecomName("中国X信");
		resms.setTelecom("100011");
		resms.setTelecomStatus(1);
		try {
			resmsMapper.insert2(resms);
			System.out.println("auto inc id="+resms.getId());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertTrue(true);

	}

	@Test
	public void testUpdate() throws Exception {
		ResmsEntity resms = new ResmsEntity();
		resms.setId(101);
		resms.setResmsNumber(10690079);
		// resms.setTelecomName("中国微信1");
		resms.setTelecom("10001");
		try {
			resmsMapper.update(resms);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		Assert.assertTrue(true);

	}

	@Test
	public void test() throws Exception {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println(dataSource);
	}

}
