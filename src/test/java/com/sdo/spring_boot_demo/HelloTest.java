package com.sdo.spring_boot_demo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sdo.controller.LoginController;
import com.sdo.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloTest {
	private MockMvc mvc;
	private ApplicationContext context;
	@Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new LoginController()).build();
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void getHello() throws Exception {
    	User u=context.getBean(User.class);
    	System.out.println(u.print());
        mvc.perform(MockMvcRequestBuilders.get("/user/login").param("userId", "1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"msg\":\"success\"}")));
    }
}
