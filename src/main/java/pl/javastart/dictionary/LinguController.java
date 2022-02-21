package pl.javastart.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
class LinguController {
    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    private final EntryRepository entryRepository;
    private final FileService fileService;
    private final ConsoleOutputWriter outputWriter;
    private final Scanner scanner;

    @Autowired
    public LinguController(EntryRepository entryRepository, FileService fileService, ConsoleOutputWriter outputWriter, Scanner scanner) {
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.outputWriter = outputWriter;
        this.scanner = scanner;
    }

    void mainLoop() {
        outputWriter.println("Witaj w aplikacji LinguApp");
        int option = UNDEFINED;
        while(option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            executeOption(option);
        }
    }

    private void executeOption(int option) {
        switch (option) {
            case ADD_ENTRY:
                addEntry();
                break;
            case TEST:
                test();
                break;
            case CLOSE_APP:
                close();
                break;
            default:
                outputWriter.println("Opcja niezdefiniowana");
        }
    }

    private void test() {
        if(entryRepository.isEmpty()) {
            outputWriter.println("Dodaj przynajmniej jedną frazę do bazy.");
            return;
        }
        final int testSize = entryRepository.size() > 10? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            outputWriter.println(String.format("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal()));
            String translation = scanner.nextLine();
            if(entry.getTranslation().equalsIgnoreCase(translation)) {
                outputWriter.println("Odpowiedź poprawna");
                score++;
            } else {
                outputWriter.println("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        outputWriter.println(String.format("Twój wynik: %d/%d\n", score, testSize));
    }

    private void addEntry() {
        outputWriter.println("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        outputWriter.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            outputWriter.println("Zapisano stan aplikacji");
        } catch (IOException e) {
            outputWriter.println("Nie udało się zapisać zmian");
        }
        outputWriter.println("Do zobaczenia!");
    }

    private void printMenu() {
        outputWriter.println("Wybierz opcję:");
        outputWriter.println("0 - Dodaj frazę");
        outputWriter.println("1 - Test");
        outputWriter.println("2 - Koniec programu");
    }

    private int chooseOption() {
        int option;
        try {
            option = scanner.nextInt();
        } catch(InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if(option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }
}
