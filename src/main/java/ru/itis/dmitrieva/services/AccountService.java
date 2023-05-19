package ru.itis.dmitrieva.services;

import ru.itis.dmitrieva.dto.AccountDto;
import ru.itis.dmitrieva.dto.SignUpForm;
import ru.itis.dmitrieva.models.Account;
import java.util.List;
import java.util.Optional;

//представляет набор методов для работы с аккаунтами.

public interface AccountService {
    void signUp(SignUpForm form);
    void updateState(String confirmCode);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long accountId);
    void update(AccountDto accountDto, String email);
    Optional<Account> getAccountByEmail(String email);
}
