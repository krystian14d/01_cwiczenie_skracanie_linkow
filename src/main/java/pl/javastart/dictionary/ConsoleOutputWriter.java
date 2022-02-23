package pl.javastart.dictionary;

import org.springframework.stereotype.Component;
import pl.javastart.dictionary.formatter.TextFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ConsoleOutputWriter {
    private static final Logger logger = Logger.getLogger(ConsoleOutputWriter.class.getName());

    private final TextFormatter textFormatter;

    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    void logInfo(String text) {
        String formattedText = textFormatter.format(text);
        logger.log(Level.INFO, formattedText);
    }
}
