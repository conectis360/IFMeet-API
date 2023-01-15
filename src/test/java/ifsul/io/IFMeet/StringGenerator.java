package ifsul.io.IFMeet;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringGenerator {
    @Test
    public void givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {

        int tamanho = 40;
        boolean usarLetras = true;
        boolean usarNumeros = false;
        String generatedString = RandomStringUtils.random(tamanho, usarLetras, usarNumeros);

        System.out.println(generatedString);
    }
}
