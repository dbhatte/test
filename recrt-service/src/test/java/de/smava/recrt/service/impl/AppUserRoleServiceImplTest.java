package de.smava.recrt.service.impl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.smava.recrt.model.AppUserRole;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.service.AppUserRoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApiConfig.class})
public class AppUserRoleServiceImplTest {

	@Autowired
	@Qualifier("appUserRoleService")
	private AppUserRoleService appUserRoleService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testGetByAppUser() throws Exception {
		List data = new ArrayList();
		
		AppUserEntity appUser = new AppUserEntity();
		appUser.setUsername("user1");
		
		AppUserRoleEntity appUserRoleEntity = new AppUserRoleEntity();
		data.add(appUserRoleEntity);
		
		EasyMock.expect(appUserRoleService.getByAppUser(appUser)).andReturn(data);
		EasyMock.replay(appUserRoleService);
		
		List<? extends AppUserRole> userRoles = appUserRoleService.getByAppUser(appUser);
		assertNotNull(userRoles);
		EasyMock.verify(appUserRoleService);
		

	}
}