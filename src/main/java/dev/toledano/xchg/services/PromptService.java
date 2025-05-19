package dev.toledano.xchg.services;

import org.jline.reader.*;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class PromptService {

    public String selectCurrency(String[] currencies) {
        try {
            // Crear el terminal
            Terminal terminal = TerminalBuilder.builder().system(true).build();

            // Configurar el LineReader con un Completer para las opciones
            LineReader reader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .completer(new StringsCompleter(currencies))
                    .build();

            // Mostrar el prompt interactivo
            String promptMessage = "Seleccione una moneda (" + String.join(", ", currencies) + "): ";
            String selectedCurrency = reader.readLine(promptMessage);

            // Validar entrada
            for (String currency : currencies) {
                if (currency.equalsIgnoreCase(selectedCurrency.trim())) {
                    return currency;
                }
            }

            // Si la moneda no es válida, muestra un mensaje de error
            System.out.println("Moneda no válida. Intente de nuevo.");
            return selectCurrency(currencies); // Llamada recursiva
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el terminal interactivo.", e);
        }
    }
}
