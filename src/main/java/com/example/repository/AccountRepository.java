package com.example.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
  public boolean existsByUsername(String username);

  public boolean existsByUsernameAndPassword(String username, String password);

  public Account findByUsernameAndPassword(String username, String password);
}
