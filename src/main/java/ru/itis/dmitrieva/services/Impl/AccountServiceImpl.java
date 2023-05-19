package ru.itis.dmitrieva.services.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dmitrieva.dto.AccountDto;
import ru.itis.dmitrieva.dto.SignUpForm;
import ru.itis.dmitrieva.helper.exceptions.AccountNotExistsException;
import ru.itis.dmitrieva.models.Account;
import ru.itis.dmitrieva.repositories.AccountRepository;
import ru.itis.dmitrieva.services.AccountService;
import ru.itis.dmitrieva.util.EmailUtil;
import java.util.*;

import static ru.itis.dmitrieva.dto.AccountDto.from;

//в коде определен класс AccountServiceImpl, который реализует интерфейс AccountService.

@Service
@RequiredArgsConstructor
@Log4j2
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${server.port}")
    String accountDto;

    @Transactional
    @Override
    public void signUp(SignUpForm signUpForm) { //выполняет регистрацию нового пользователя
        Account newUser = Account.builder()
                .firstName(signUpForm.getFirstName())
                .lastName(signUpForm.getLastName())
                .email(signUpForm.getEmail().toLowerCase(Locale.ROOT))
                .password(passwordEncoder.encode(signUpForm.getPassword())) //хэширование пароля passwordEncoder
                .faculty_name(signUpForm.getFaculty_name())
                .confirmCode(UUID.randomUUID().toString())
                .state(Account.State.NOT_CONFIRMED)
                .role(Account.Role.USER)
                .build();
        accountRepository.save(newUser);

        HashMap<String, String> data = new HashMap<>();
        data.put("confirm_code", newUser.getConfirmCode());
        data.put("first_name", newUser.getFirstName());
        data.put("port", accountDto);
        emailUtil.sendMail(newUser.getEmail(), "confirm", "mails/confirm_mail.ftlh",
                data);
    }

    @Override
    public void updateState(String confirmCode) { //обновленяет состояние учетной записи пользователя по коду подтверждения
        Account account = accountRepository.findAllByConfirmCode(confirmCode);
        if (account == null) {
            throw new AccountNotExistsException();
        }
        account.setState(Account.State.CONFIRMED);
        accountRepository.save(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        return from(accountRepository.findAllByState(Account.State.CONFIRMED));
    }

    @Override
    public void deleteAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(AccountNotExistsException::new);
        account.setState(Account.State.DELETED);
        accountRepository.save(account);
    }

    @Override
    public void update(AccountDto accountDto, String email) {
        accountRepository.updateUser(accountDto.getFirstName(), accountDto.getLastName(), passwordEncoder.encode(accountDto.getPassword()), email);
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }
}


