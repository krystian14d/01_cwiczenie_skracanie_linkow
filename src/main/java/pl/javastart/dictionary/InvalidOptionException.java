package pl.javastart.dictionary;

public class InvalidOptionException extends RuntimeException {
    InvalidOptionException() {
        super("Podana opcja nie istnieje");
    }
}
