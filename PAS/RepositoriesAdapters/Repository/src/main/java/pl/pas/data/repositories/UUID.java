package pl.pas.data.repositories;

import java.util.Random;

public class UUID {
    public static long randomUUID() {
        Random random = new Random();
        long uuid;

        do {
            uuid = random.nextLong() / 4096;
        } while (uuid <= 0);

        return uuid;
    }
}
