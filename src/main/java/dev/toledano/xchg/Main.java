package dev.toledano.xchg;

import org.jline.terminal.Attributes;
import org.jline.terminal.Attributes.LocalFlag;
import org.jline.terminal.Attributes.ControlChar;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();

        Attributes attributes = terminal.getAttributes();

        terminal.writer().println("Hola, Mundo!");

        // Display some attributes
        terminal.writer().println("Terminal attributes:");
        terminal.writer().printf("  ECHO: %b%n", attributes.getLocalFlag(LocalFlag.ECHO));
        terminal.writer().printf("  ICANON: %b%n", attributes.getLocalFlag(LocalFlag.ICANON));
        terminal.writer().printf("  INTR char: %c%n", (char) attributes.getControlChar(ControlChar.VINTR));
        terminal.writer().flush();
    }
}