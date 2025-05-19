package dev.toledano.xchg.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageProvider {

    private final ResourceBundle resourceBundle;

    // Constructor que acepta un Locale
    public MessageProvider(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    // Método para obtener un mensaje por clave
    public String getMessage(String key) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            return "Missing message for key: " + key; // Fallback si falta el mensaje
        }
    }

    // Método para obtener un mensaje con argumentos
    public String getMessage(String key, Object... args) {
        try {
            String message = resourceBundle.getString(key);
            return MessageFormat.format(message, args);
        } catch (Exception e) {
            return "Missing message for key: " + key; // Fallback si falta el mensaje
        }
    }

    // Sobrecarga con fallback a otro Locale
    public String getMessage(String key, Locale fallbackLocale) {
        try {
            return resourceBundle.getString(key);
        } catch (Exception e) {
            ResourceBundle fallbackBundle = ResourceBundle.getBundle("messages", fallbackLocale);
            try {
                return fallbackBundle.getString(key);
            } catch (Exception ex) {
                return "Missing message for key: " + key;
            }
        }
    }
}
