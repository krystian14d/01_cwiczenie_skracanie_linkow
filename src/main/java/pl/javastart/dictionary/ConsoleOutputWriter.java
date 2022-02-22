package pl.javastart.dictionary;

import org.springframework.stereotype.Component;
import pl.javastart.dictionary.crypto.formatter.TextFormatter;

@Component
public class ConsoleOutputWriter {

    private final TextFormatter textFormatter;

    public ConsoleOutputWriter(TextFormatter textFormatter) {
        this.textFormatter = textFormatter;
    }

    void println(String text){
        String formattedText = textFormatter.format(text);
        System.out.println(formattedText);
    }
}
