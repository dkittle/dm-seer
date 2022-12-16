package ca.kittle;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptTest {

    @Test
    public void simplePassword() {
        String salt = BCrypt.gensalt(12);
        String h1 = BCrypt.hashpw("password", salt);
    }
}
