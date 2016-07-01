package de.smava.recrt.persistence.repository;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.smava.recrt.persistence.config.PersistenceConfig;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.BankAccountEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfig.class)
public class AppUserRepositoryTest {

	@Autowired
	AppUserRepository appUserRepository;
	
    @Test
    public void testSave() throws Exception {
    	AppUserEntity user = new AppUserEntity("user4");
    	user.setPassword("4444");
    	user.setEmail("user4@smava.de");
    	appUserRepository.save(user);

        AppUserEntity result = appUserRepository.findOne(user.getUsername());
        assertEquals("user4", result.getUsername());
        
    }

    @Test
    public void testFindOne() throws Exception {
    	AppUserEntity result = appUserRepository.findOne("user1");
        assertEquals("user1", result.getUsername());
    }

    @Test
    public void testDelete() throws Exception {
    	AppUserEntity user = new AppUserEntity("user4");
    	user.setPassword("4444");
    	user.setEmail("user4@smava.de");
    	appUserRepository.save(user);

    	appUserRepository.delete(user);
    	AppUserEntity deleted = appUserRepository.findOne(user.getUsername());
        assertNull(deleted);
    }

}