import dev.toledano.xchg.services.PromptService;
import org.jline.terminal.Terminal;
import org.jline.terminal.impl.DumbTerminal;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class PromptServiceTest {

    @ParameterizedTest
    @CsvSource({"USD", "EUR", "MXN"})
    void testSelectCurrency(String currency) throws Exception {
        // Simula la entrada del usuario: elige "EUR" y presiona Enter
        String simulatedInput = currency+"\n";
        ByteArrayInputStream input = new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Terminal terminal = new DumbTerminal(input, output);
        PromptService promptService = new PromptService(terminal);
        String[] currencies = {"USD", "EUR", "MXN"};
        String selectedCurrency = promptService.selectCurrency(currencies);
        assertEquals(currency, selectedCurrency, "La moneda seleccionada debe ser EUR (simulada)");
    }

    @ParameterizedTest
    @CsvSource({"COP", "ARS", "0"})
    void testSelectCurrencyInvalidInput(String input) throws Exception {
        String simulatedInput = input + "\nUSD\n"; // Ingresa valor inválido y luego uno válido
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Terminal terminal = new DumbTerminal(in, out);
        PromptService promptService = new PromptService(terminal);
        String[] currencies = {"USD", "EUR", "MXN"};
        String selectedCurrency = promptService.selectCurrency(currencies);
        assertEquals("USD", selectedCurrency, "Debe seleccionar USD tras un intento inválido");
    }
}
