package pl.javastart.dictionary.crypto;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class RawCipherService implements CipherService{
    @Override
    public String encrypt(String text) {
        return text;
    }

    @Override
    public String decrypt(String cipher) {
        return cipher;
    }
}
