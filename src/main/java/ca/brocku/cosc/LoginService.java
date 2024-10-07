package ca.brocku.cosc;

import java.util.HashMap;
import java.util.Map;

public class LoginService {

    private final Map<String, String> DB = new HashMap<>() {{
        put("cc21lz@brocku.ca", "12345");
    }};
    private final Map<String, Integer> LOGIN_ATTEMPTS = new HashMap<>();



    public LoginResult login(String email, String password) {
        // Invalid credentials
        if (email == null || email.isEmpty()) return LoginResult.INVALID_CREDENTIALS;
        if (password == null || password.isEmpty()) return LoginResult.INVALID_CREDENTIALS;

        // Too many attempts
        if (LOGIN_ATTEMPTS.containsKey(email.toLowerCase()) && LOGIN_ATTEMPTS.get(email.toLowerCase()) >= 3) {
            return LoginResult.TOO_MANY_ATTEMPTS;
        }

        // Correct email and password
        if (DB.containsKey(email.toLowerCase()) && DB.get(email.toLowerCase()).equals(password)) {
            // Reset login attempts after successful login
            LOGIN_ATTEMPTS.put(email.toLowerCase(), 0);
            return LoginResult.SUCCESS;
        }

        // Increase login attempts on failure
        LOGIN_ATTEMPTS.put(email.toLowerCase(), LOGIN_ATTEMPTS.getOrDefault(email.toLowerCase(), 0) + 1);
        return LoginResult.FAIL;
    }

    public enum LoginResult {
        SUCCESS,
        FAIL,
        INVALID_CREDENTIALS,
        TOO_MANY_ATTEMPTS,
    }
}
