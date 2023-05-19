package ru.itis.dmitrieva.security.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.dmitrieva.models.Account;
import java.util.Collection;
import java.util.Collections;


//объект, содержащий информацию  о пользователе для аунт и автор
public class AccountUserDetails implements UserDetails {

    private final Account account;

    public AccountUserDetails(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //роли пользователей
        return Collections.singleton(new SimpleGrantedAuthority(account.getRole().name()));
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account.getState().equals(Account.State.BANNED);
    }
    //указывают на состояние учетной записи, если не забанена и не истекла

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getState().equals(Account.State.CONFIRMED);
    } //указыввает активирована ли учетна запись
}
