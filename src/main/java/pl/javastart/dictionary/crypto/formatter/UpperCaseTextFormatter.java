package pl.javastart.dictionary.crypto.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class UpperCaseTextFormatter implements TextFormatter {
    @Override
    public String format(String originalText) {
        return originalText.toUpperCase();
    }
}
