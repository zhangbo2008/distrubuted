package com.hc.hcbasic.demo;

import org.mindrot.jbcrypt.BCrypt;

/**
 * bcrypt加密demo
 *
 * @author HC
 * @create 2019-05-11 0:33
 */
public class BCryptDemo {
    public static void main(String[] args) {
        // Hash a password for the first time
        String password = "testpassword";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(hashed);
        // gensalt's log_rounds parameter determines the complexity
        // the work factor is 2**log_rounds, and the default is 10
        String hashed2 = BCrypt.hashpw(password, BCrypt.gensalt(12));

        // Check that an unencrypted password matches one that has previously been hashed
        //String candidate = "wrongtestpassword";
        if (BCrypt.checkpw(password, hashed2))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }
}
