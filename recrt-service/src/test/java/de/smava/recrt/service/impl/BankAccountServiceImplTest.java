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

import de.smava.recrt.model.BankAccount;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.BankAccountEntity;
import de.smava.recrt.service.BankAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestApiConfig.class})
public class BankAccountServiceImplTest {

    private BankAccountService bankAccountService;

    @Before
	public void before(){
    	bankAccountService = EasyMock.mock(BankAccountService.class);
	}
    
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
    public void testGetByAppUser() throws Exception {
        String name = "user1";
		List data = new ArrayList();
		
		BankAccountEntity bankAccountEntity = new BankAccountEntity();
		AppUserEntity appUser = new AppUserEntity();
		appUser.setUsername(name);
		appUser.setEmail("user1@smava.de");
		bankAccountEntity.setAppUser(appUser);
		
		data.add(bankAccountEntity);
		
		EasyMock.expect(bankAccountService.getByAppUser(name)).andReturn(data);
		EasyMock.replay(bankAccountService);
		
		List result = bankAccountService.getByAppUser(name);
		assertNotNull(result);
		EasyMock.verify(bankAccountService);
		assertEquals(data, result);
    }

    @SuppressWarnings("rawtypes")
	@Test
    public void testCreate() throws Exception {
    	BankAccount account = new BankAccountEntity();
    	
		EasyMock.expect(bankAccountService.create(account)).andReturn(account);
		EasyMock.replay(bankAccountService);
		
		BankAccount result = bankAccountService.create(account);
		assertNotNull(result);
		EasyMock.verify(bankAccountService);
		assertEquals(account, result);
		
    }
}