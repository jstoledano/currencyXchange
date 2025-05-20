package dev.toledano.xchg.services;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import dev.toledano.xchg.services.PromptService;
import org.jline.terminal.Attributes;
import org.jline.terminal.Attributes.LocalFlag;
import org.jline.terminal.Attributes.ControlChar;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class AppLauncher {
    private final Terminal terminal;
    private final PromptService promptService;

    public AppLauncher() {
        try {
            this.terminal = TerminalBuilder.builder().system(true).build();
            this.promptService = new PromptService(terminal);
        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar el terminal.", e);
        }
    }

    public String start(String[] currencies) {
        try {
            return promptService.selectCurrency(currencies);
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            return null;
        }
    }

}
