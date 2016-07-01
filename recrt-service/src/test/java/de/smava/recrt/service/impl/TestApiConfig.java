package de.smava.recrt.service.impl;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.smava.recrt.persistence.repository.AppUserRoleRepository;
import de.smava.recrt.service.AppUserRoleService;
import de.smava.recrt.service.AppUserService;
import de.smava.recrt.service.BankAccountService;

@Configuration
public class TestApiConfig {

    @Bean(name = "appUserService")
    public AppUserService getAppUserService() {
        return EasyMock.mock(AppUserService.class);
    }

    @Bean(name = "appUserRoleService")
    public AppUserRoleService getAppUserRoleService() {
        return EasyMock.mock(AppUserRoleService.class);
    }

    @Bean(name = "bankAccountPersistenceService")
    public BankAccountService getBankAccountService() {
        return EasyMock.mock(BankAccountService.class);
    }
    
    @Bean(name = "appUserRoleRepository")
    public AppUserRoleRepository getAppUserRoleRepository() {
        return EasyMock.mock(AppUserRoleRepository.class);
    }
    
}
