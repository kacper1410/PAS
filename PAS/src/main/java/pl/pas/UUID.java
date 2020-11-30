package pl.pas;

import java.util.Random;

public class UUID {
    public static long randomUUID() {
        Random random = new Random();
        long uuid;

        do {
            uuid = random.nextLong();
        } while (uuid < 0);

        return uuid;
    }
}
