package ca.brocku.cosc.test;

import ca.brocku.cosc.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private LoginService loginService;

    @BeforeEach
    public void setup() {
        this.loginService = new LoginService();
    }

    @Test
    public void testInvalidCredentials() {
        // Test with empty username and password
        LoginService.LoginResult result = loginService.login("", ".");

        // Assert that the result is INVALID_CREDENTIALS
        assertEquals(LoginService.LoginResult.INVALID_CREDENTIALS, result);

        System.out.println("Invalid credentials test passed!");
    }

    @Test
    public void testSuccessfulLogin() {
        // Test with valid email and password
        LoginService.LoginResult result = loginService.login("cc21lz@brocku.ca", "12345");

        // Assert that the result is SUCCESS
        assertEquals(LoginService.LoginResult.SUCCESS, result);

        System.out.println("Successful login test passed!");
    }

    @Test
    public void testFailedLogin() {
        // Test with valid email but wrong password
        LoginService.LoginResult result = loginService.login("cc21lz@brocku.ca", "1234560");

        // Assert that the result is FAIL
        assertEquals(LoginService.LoginResult.FAIL, result);

        System.out.println("Failed login test passed!");
    }

    @Test
    public void testTooManyAttempts() {
        // Simulate 3 failed login attempts
        for (int i = 0; i < 3; i++) {
            loginService.login("cc21lz@brocku.ca", "0");
        }

        // Fourth attempt should return TOO_MANY_ATTEMPTS
        LoginService.LoginResult result = loginService.login("cc21lz@brocku.ca", "0");

        // Assert that the result is TOO_MANY_ATTEMPTS
        assertEquals(LoginService.LoginResult.TOO_MANY_ATTEMPTS, result);

        System.out.println("Too many attempts test passed!");
    }

}
