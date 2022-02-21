package pl.javastart.dictionary.crypto;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CaesarCipherService implements CipherService{
    private static final int SHIFT = 3;

    @Override
    public String encrypt(String text) {
        return null;
    }

    @Override
    public String decrypt(String cipher) {
        return null;
    }

    private static int shift(int character){
        return character + SHIFT;
    }

    private static int shiftBack (int character){
        return character - SHIFT;
    }
}
