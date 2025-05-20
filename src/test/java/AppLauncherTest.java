import dev.toledano.xchg.services.AppLauncher;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppLauncherTest {

    @Test
    void testAppLauncherInitialization() {
        AppLauncher app = new AppLauncher();

        // Simula un flujo básico con monedas
        String[] currencies = {"USD", "EUR", "MXN"};
        String selectedCurrency = app.start(currencies);

        // Esperamos que seleccione una moneda válida
        assertTrue(
                selectedCurrency.equals("USD") ||
                        selectedCurrency.equals("EUR") ||
                        selectedCurrency.equals("MXN"),
                "La moneda seleccionada debe ser válida."
        );
    }
}
