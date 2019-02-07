package co.swapi;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();

        for(int i=1;i<12;i++){
            System.out.println(random.nextInt(61));
        }

    }
}
