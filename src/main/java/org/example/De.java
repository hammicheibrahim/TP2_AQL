package org.example;

import java.util.Random;

public class De {
    private Random random;

    public De() {



        random = new Random();
    }

    public int lancer() {
        return random.nextInt(6) + 1;
    }
}
