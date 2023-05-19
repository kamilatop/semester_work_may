package ru.itis.dmitrieva.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.dmitrieva.repositories.AccountRepository;

//реализация AccountUserDetails и
// обеспечивает загрузку учетной записи пользователя по его email из репозитория AccountRepository

@RequiredArgsConstructor
@Service
public class AccountUserDetailsService implements UserDetailsService {

    private final AccountRepository accountsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AccountUserDetails(
                accountsRepository.findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User not found")));

    }
}
