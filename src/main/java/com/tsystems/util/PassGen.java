package com.tsystems.util;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by nikita on 20.09.2020.
 */
public class PassGen {

    public String randomPass() {
        String password = new Random().ints(7, 48, 57)
                .mapToObj(i -> String.valueOf((char) i)).collect(Collectors.joining());
        return password;
    }
}
