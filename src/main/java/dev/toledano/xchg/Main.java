package dev.toledano.xchg;

import dev.toledano.xchg.services.AppLauncher;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String[] currencies = {"USD", "EUR", "MXN"};
        AppLauncher appLauncher = new AppLauncher();
        String selectedCurrency = appLauncher.start(currencies);
        System.out.println("Moneda seleccionada: " + selectedCurrency);
    }
}
