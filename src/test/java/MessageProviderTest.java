import dev.toledano.xchg.utils.MessageProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;

public class MessageProviderTest {

    private static MessageProvider providerUS;
    private static MessageProvider providerMX;

    @BeforeAll
    static void setupProviders() {
        providerUS = new MessageProvider(new Locale("en", "US"));
        providerMX = new MessageProvider(new Locale("es", "MX"));
    }

    @ParameterizedTest
    @CsvSource({
            "en, Welcome to the Currency Converter",
            "es, Bienvenidos al convertidor de monedas de Alura Challenge"
    })
    void testWelcomeMessages(String locale, String expectedMessage) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        assertEquals(expectedMessage, provider.getMessage("app.welcome"));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "en|Please follow the prompts to convert currencies",
            "es|Indique la moneda origen, la cantidad a convertir y la moneda destino"
    }, delimiter = '|')
    void testInstructionMessages(String locale, String expectedMessage) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        assertEquals(expectedMessage, provider.getMessage("app.instructions"));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "en|Invalid input. Please try again.",
            "es|Entrada inválida. Por favor, intenta de nuevo."
    }, delimiter = '|')
    void testInvalidInputMessages(String locale, String expectedMessage) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        assertEquals(expectedMessage, provider.getMessage("error.invalid_input"));
    }

    @ParameterizedTest
    @CsvSource({
            "en, Failed to fetch exchange rates. Try later.",
            "es, Error al obtener las tasas de cambio. Intenta más tarde."
    })
    void testApiFailureMessages(String locale, String expectedMessage) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        assertEquals(expectedMessage, provider.getMessage("error.api_failure"));
    }

    @ParameterizedTest
    @CsvSource({
            "en, 1, USD, 19.2, MXN, Conversion successful: 1 USD = 19.2 MXN",
            "es, 1, USD, 19.2, MXN, Conversión exitosa: 1 USD = 19.2 MXN"
    })
    void testConversionSuccessMessage(String locale, int amount, String fromCurrency, double rate, String toCurrency, String expectedMessage) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        Object[] args = {amount, fromCurrency, rate, toCurrency};
        assertEquals(expectedMessage, provider.getMessage("conversion.success", args));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "en|Select the source currency:",
            "es| Selecciona la moneda origen:"
    }, delimiter = '|')
    void testSelectOriginMessage(String locale, String expectedString) {
        MessageProvider provider = locale.equals("en") ? providerUS : providerMX;
        assertEquals(expectedString, provider.getMessage("menu.select_origin"));
    }

    @ParameterizedTest
    @CsvSource(value={
            "es|Selecciona la moneda destino:",
            "en|Select the target currency:"
    }, delimiter = '|')
    void testSelectTargetMessage(String locale, String expectedString) {
        MessageProvider provider = locale.equals("es") ? providerMX : providerUS;
        assertEquals(expectedString, provider.getMessage("menu.select_target"));
    }
}
