package pl.javastart.dictionary;

public enum Options {
    ADD_ENTRY(0, "Dodaj frazę"),
    TEST(1, "Test"),
    CLOSE_APP(2, "Koniec programu");

    private int number;
    private String description;

    Options(int number, String description) {
        this.number = number;
        this.description = description;
    }

    @Override
    public String toString() {
        return number + " - " + description;
    }

    static Options numberToCategory(int number) {
        if (number < 1 || number > Options.values().length) {
            throw new InvalidOptionException();
        }
        return values()[number];
    }
}
