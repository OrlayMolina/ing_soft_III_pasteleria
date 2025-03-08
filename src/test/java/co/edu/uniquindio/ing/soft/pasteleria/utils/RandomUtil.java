package co.edu.uniquindio.ing.soft.pasteleria.utils;

import java.util.Random;

public class RandomUtil {

    public static String generateRandomNumericId(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 1; i < length; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
