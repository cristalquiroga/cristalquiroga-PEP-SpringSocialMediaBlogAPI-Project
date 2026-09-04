package com.example.service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService{
  private final AccountRepository accountRepository;

  public AccountService(AccountRepository accountRepository){
    this.accountRepository = accountRepository;
  }
  
  public Account registerAccount(Account account){
    if(account.getUsername().isBlank() || account.getPassword().length() < 4)
      throw new IllegalArgumentException();
    if(accountRepository.existsByUsername(account.getUsername()) == true)
      throw new IllegalStateException();
    return accountRepository.save(account);
  }
}
