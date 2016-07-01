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

import de.smava.recrt.model.UserRole;
import de.smava.recrt.persistence.config.PersistenceConfig;
import de.smava.recrt.persistence.model.AppUserEntity;
import de.smava.recrt.persistence.model.AppUserRoleEntity;
import de.smava.recrt.persistence.model.AppUserRoleKey;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfig.class)
public class AppUserRoleRepositoryTest {

	@Autowired
	AppUserRoleRepository appUserRoleRepository;
	
    @Test
    public void testFindByKeyAppUser() throws Exception {
    	AppUserEntity user = new AppUserEntity("user1");

        List<AppUserRoleEntity> result = appUserRoleRepository.findByKeyAppUser(user);
        assertEquals(1, result.size());
        assertEquals(UserRole.ROLE_ADMIN, result.get(0).getRole());

        appUserRoleRepository.delete(result);
    	AppUserRoleKey key = new AppUserRoleKey(user, UserRole.ROLE_ADMIN);
        AppUserRoleEntity deleted = appUserRoleRepository.findOne(key);
        assertNull(deleted);
    }
}