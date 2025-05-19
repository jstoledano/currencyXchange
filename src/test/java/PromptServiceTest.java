import dev.toledano.xchg.services.PromptService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PromptServiceTest {
    @Test
    void testSelectCurrency() {
        PromptService promptService = new PromptService();

        // Simula un menú con monedas disponibles
        String[] currencies = {"USD", "EUR", "MXN"};
        String selectedCurrency = promptService.selectCurrency(currencies);

        assertTrue(
                selectedCurrency.equals("USD") ||
                        selectedCurrency.equals("EUR") ||
                        selectedCurrency.equals("MXN"),
                "La moneda seleccionada debe ser parte de la lista stub."
        );
    }
}
