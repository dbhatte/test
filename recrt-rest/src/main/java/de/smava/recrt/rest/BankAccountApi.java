package de.smava.recrt.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.smava.recrt.exception.RecrtServiceException;
import de.smava.recrt.model.BankAccount;
import de.smava.recrt.rest.model.BankAccountResource;
import de.smava.recrt.service.BankAccountService;

@RestController
@RequestMapping(value = "/rest/accounts", produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
public class BankAccountApi {

    @Autowired
    @Qualifier("bankAccountPersistenceService")
    private BankAccountService bankAccountService;

    @Qualifier("bankAccountJmsProducer")
    private BankAccountService bankAccountJMSService;


    @Secured({"ROLE_USER"})
    @RequestMapping(method = RequestMethod.GET)
    public List<BankAccountResource> getAll() throws RecrtServiceException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        List<BankAccountResource> result = new ArrayList<>();

        List<? extends BankAccount> bankAccountEntities = bankAccountService.getByAppUser(name);
        for (BankAccount bankAccountEntity : bankAccountEntities) {
            result.add(new BankAccountResource(bankAccountEntity));
        }
        return result;
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST)
    public BankAccountResource create(@RequestBody BankAccountResource account) throws RecrtServiceException {
        BankAccount saved = bankAccountJMSService.create(account);
        if (saved != null) {
            return new BankAccountResource(saved);
        }
        return null;
    }
}
