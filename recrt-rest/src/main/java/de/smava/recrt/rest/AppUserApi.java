package de.smava.recrt.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.smava.recrt.exception.RecrtServiceException;
import de.smava.recrt.model.AppUser;
import de.smava.recrt.model.BankAccount;
import de.smava.recrt.rest.model.AppUserResource;
import de.smava.recrt.rest.model.BankAccountResource;
import de.smava.recrt.service.AppUserService;
import de.smava.recrt.service.BankAccountService;

@RestController
@RequestMapping(value = "/rest/users", produces = {APPLICATION_JSON_VALUE})
public class AppUserApi {

    @Autowired
    @Qualifier("appUserService")
    private AppUserService appUserService;

    @Autowired
    @Qualifier("bankAccountPersistenceService")
    private BankAccountService bankAccountService;

    /*@InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new AdvisorValidator());
    }*/

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.GET)
    public List<AppUserResource> search() throws RecrtServiceException {

        List<AppUserResource> result = new ArrayList<>();

        List<? extends AppUser> userEntities = appUserService.getAll();
        for (AppUser userEntity : userEntities) {
            result.add(new AppUserResource(userEntity));
        }

        return result;
    }
    
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/{userName}/accounts", method = RequestMethod.GET)
    public List<BankAccountResource> getAll(@PathVariable String userName) throws RecrtServiceException {

        List<BankAccountResource> result = new ArrayList<>();

        List<? extends BankAccount> bankAccountEntities = bankAccountService.getByAppUser(userName);
        for (BankAccount bankAccountEntity : bankAccountEntities) {
            result.add(new BankAccountResource(bankAccountEntity));
        }
        return result;
    }

}
