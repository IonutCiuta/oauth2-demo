package com.sci.oauth2.service;

import com.sci.oauth2.dao.AccountDetailsRepo;
import com.sci.oauth2.dao.AccountRepo;
import com.sci.oauth2.model.Account;
import com.sci.oauth2.model.AccountDetails;
import dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ionutciuta24@gmail.com on 06.01.2018.
 */
@Component
public class AccountService {
    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountDetailsRepo accountDetailsRepo;

    public Account createAccount(User user) {
        return accountRepo.save(new Account(user.getUsername(), user.getPassword()));
    }

    public AccountDetails storeAccountDetails(Account account, User user) {
        return accountDetailsRepo.save(new AccountDetails(
                account.getId(), user.getFirstName(), user.getLastName(), user.getPhone()
        ));
    }

    public Account loadAccount(String username) {
        return accountRepo.findByUsername(username);
    }

    public AccountDetails loadDetails(String accountId) {
        return accountDetailsRepo.findOne(accountId);
    }
}
