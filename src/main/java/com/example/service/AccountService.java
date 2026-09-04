package com.example.service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

public class AccountService{
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }
  
  public Account registerAccount(Account account){
    if(account.getUsername().isBlank() || account.getPassword().length() < 4)
      throw new IllegalArgumentException();
    if(accountRepository.accountExists(account.getUsername()) == false)
      throw new IllegalStateException();
    return accountRepository.save(account);
  }
}
