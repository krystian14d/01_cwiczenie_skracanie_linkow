package pl.javastart.dictionary.crypto;

public interface CipherService {
    String encrypt(String text);

    String decrypt(String cipher);
}
