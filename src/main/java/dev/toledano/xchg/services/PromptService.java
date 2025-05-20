package dev.toledano.xchg.services;

import org.jline.reader.*;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;

public class PromptService {
    private final Terminal terminal;

    public PromptService(Terminal terminal) {
        this.terminal = terminal;
    }

    public String selectCurrency(String[] currencies) {
        try {
            // Usar el terminal proporcionado
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
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el terminal interactivo.", e);
        }
    }
}
