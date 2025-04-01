package dani.m8.uf3.utils;

import java.util.Random;

public class Methods {
    public static float randomFloat(float min, float max){
        Random r = new Random();
        return r.nextFloat() * (max - min) + min;
    }
}
