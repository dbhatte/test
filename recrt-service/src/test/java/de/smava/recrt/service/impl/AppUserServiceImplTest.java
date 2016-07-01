package de.smava.recrt.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.smava.recrt.model.AppUser;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.service.AppUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestApiConfig.class })
public class AppUserServiceImplTest {

	private AppUserService appUserService;

	@Before
	public void before(){
		appUserService = EasyMock.mock(AppUserService.class);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetAll() throws Exception {
		List data = new ArrayList();

		AppUser user1 = new AppUserEntity();
		user1.setUsername("user1");
		data.add(user1);

		AppUser user2 = new AppUserEntity();
		user1.setUsername("user2");
		data.add(user2);

		EasyMock.expect(appUserService.getAll()).andReturn(data);
		EasyMock.replay(appUserService);

		List result = appUserService.getAll();
		assertNotNull(result);
		assertEquals(result, data);
		EasyMock.verify(appUserService);

	}

	@Test
	public void testGet() throws Exception {
		AppUser data = new AppUserEntity();

		data.setUsername("user1");

		EasyMock.expect(appUserService.get("user1")).andReturn(data);
		EasyMock.replay(appUserService);

		AppUser result = appUserService.get("user1");
		assertNotNull(result);
		assertEquals(result, data);
		EasyMock.verify(appUserService);
	}
}