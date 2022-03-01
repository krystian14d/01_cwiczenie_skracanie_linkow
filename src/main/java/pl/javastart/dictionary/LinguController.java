package pl.javastart.dictionary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

@Controller
class LinguController {

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
        outputWriter.logInfo("Witaj w aplikacji LinguApp");
        Options option;
        printMenu();
        option = chooseOption();
        executeOption(option);

    }

    private void executeOption(Options option) {
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
                outputWriter.logInfo("Opcja niezdefiniowana");
        }
    }

    private void test() {
        if (entryRepository.isEmpty()) {
            outputWriter.logInfo("Dodaj przynajmniej jedną frazę do bazy.");
            return;
        }
        final int testSize = entryRepository.size() > 10 ? 10 : entryRepository.size();
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for (Entry entry : randomEntries) {
            outputWriter.logInfo(String.format("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal()));
            String translation = scanner.nextLine();
            if (entry.getTranslation().equalsIgnoreCase(translation)) {
                outputWriter.logInfo("Odpowiedź poprawna");
                score++;
            } else {
                outputWriter.logInfo("Odpowiedź niepoprawna - " + entry.getTranslation());
            }
        }
        outputWriter.logInfo(String.format("Twój wynik: %d/%d\n", score, testSize));
    }

    private void addEntry() {
        outputWriter.logInfo("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        outputWriter.logInfo("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            outputWriter.logInfo("Zapisano stan aplikacji");
        } catch (IOException e) {
            outputWriter.logInfo("Nie udało się zapisać zmian");
        }
        outputWriter.logInfo("Do zobaczenia!");
    }

    private void printMenu() {
        outputWriter.logInfo("Wybierz opcję:");
        Arrays.stream(Options.values()).forEach(options -> outputWriter.logInfo(options.toString()));
    }

    private Options chooseOption() {
        boolean correctOptionSelected = false;
        Options option = null;
        while (!correctOptionSelected) {
            outputWriter.logInfo("Wybierz opcję:");
            int optionId = scanner.nextInt();
            scanner.nextLine();
            try {
                option = Options.numberToCategory(optionId);
                correctOptionSelected = true;
            } catch (InvalidOptionException e) {
                outputWriter.logInfo(e.getMessage());
            }
        }
        return option;
    }
}
