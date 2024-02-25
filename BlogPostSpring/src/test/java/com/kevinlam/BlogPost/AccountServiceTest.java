package com.kevinlam.BlogPost;

import com.kevinlam.BlogPost.Exceptions.InvalidUserNameException;
import com.kevinlam.BlogPost.Exceptions.UserAlreadyExistsException;
import com.kevinlam.BlogPost.Users.Account;
import com.kevinlam.BlogPost.Users.AccountDB;
import com.kevinlam.BlogPost.Users.AccountService;
import com.kevinlam.BlogPost.Users.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    private AccountDB mockAccountDB;
    @InjectMocks
    private AccountService mockAccountService;
    @Mock
    private Account mockAccount;

    @Test
    public void testRegisterAccount() {
        when(mockAccount.getUsername()).thenReturn("Kevin");
        when(mockAccount.getPassword()).thenReturn("passs");

        when(mockAccountDB.findByUser(any())).thenReturn(null);

        assertDoesNotThrow(() ->mockAccountService.registerAccount(mockAccount));
        verify(mockAccount, times(1)).setUsername("kevin");
        verify(mockAccount, times(2)).setPassword(PasswordEncoder.hashPassword(mockAccount.getPassword()));
        verify(mockAccountDB, times(1)).save(mockAccount);
    }
    @Test
    public void testRegisterAccount_usernameLong() {
        lenient().when(mockAccount.getUsername()).thenReturn("KevinKevinKevinKevinKevin");
        lenient().when(mockAccount.getPassword()).thenReturn("passs");

        lenient().when(mockAccountDB.findByUser(any())).thenReturn(null);

        assertThrows(InvalidUserNameException.class, () ->mockAccountService.registerAccount(mockAccount));
        verify(mockAccount, times(0)).setUsername("kevinkevinkevinkevinkevin");
        verify(mockAccount, times(0)).setPassword(PasswordEncoder.hashPassword(mockAccount.getPassword()));
        verify(mockAccountDB, times(0)).save(mockAccount);
    }
    @Test
    public void testRegisterAccount_passwordShort() {
        lenient().when(mockAccount.getUsername()).thenReturn("KevinKevinKevinKevin");
        lenient().when(mockAccount.getPassword()).thenReturn("pass");

        lenient().when(mockAccountDB.findByUser(any())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () ->mockAccountService.registerAccount(mockAccount));
        verify(mockAccount, times(0)).setUsername("kevinkevinkevinkevin");
        verify(mockAccount, times(0)).setPassword(any());
        verify(mockAccountDB, times(0)).save(mockAccount);
    }
    @Test
    public void testRegisterAccount_userExists() {
        lenient().when(mockAccount.getUsername()).thenReturn("KevinKevinKevinKevin");
        lenient().when(mockAccount.getPassword()).thenReturn("passs");
        Account mockAccount2 = Mockito.mock(Account.class);
        lenient().when(mockAccountDB.findByUser(any())).thenReturn(mockAccount2);

        assertThrows(UserAlreadyExistsException.class, () ->mockAccountService.registerAccount(mockAccount));
        verify(mockAccount, times(1)).setUsername("kevinkevinkevinkevin");
        verify(mockAccount, times(0)).setPassword(any());
        verify(mockAccountDB, times(0)).save(mockAccount);
    }
}
