// PasswordGenerator.java
// Lógica principal de geração de senha
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {
    // conjuntos de caracteres possíveis
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*()-_=+[]{}<>?/.,:;" ;

    // caracteres que podem confundir visualmente
    private static final String SIMILAR = "Il1O0";

    private final SecureRandom random = new SecureRandom();

    // Gera uma única senha conforme as opções
    public String generate(PasswordOptions opts) {
        opts.validate();

        StringBuilder charset = new StringBuilder();
        List<String> requiredPools = new ArrayList<>(); // para garantir pelo menos 1 de cada tipo, se pedido

        if (opts.isIncludeUppercase()) {
            charset.append(UPPER);
            requiredPools.add(UPPER);
        }
        if (opts.isIncludeLowercase()) {
            charset.append(LOWER);
            requiredPools.add(LOWER);
        }
        if (opts.isIncludeDigits()) {
            charset.append(DIGITS);
            requiredPools.add(DIGITS);
        }
        if (opts.isIncludeSymbols()) {
            charset.append(SYMBOLS);
            requiredPools.add(SYMBOLS);
        }

        // remover caracteres semelhantes se solicitado
        String finalCharset = charset.toString();
        if (opts.isAvoidSimilar()) {
            StringBuilder sb = new StringBuilder();
            for (char c : finalCharset.toCharArray()) {
                if (SIMILAR.indexOf(c) == -1) {
                    sb.append(c);
                }
            }
            finalCharset = sb.toString();
        }

        if (finalCharset.isEmpty()) {
            throw new IllegalStateException("Conjunto de caracteres vazio após filtros.");
        }

        int length = opts.getLength();
        StringBuilder password = new StringBuilder(length);

        // se precisar garantir pelo menos um de cada tipo selecionado
        if (opts.isRequireEachType()) {
            // primeiro adiciona um caractere obrigatório de cada pool (após aplicar avoidSimilar)
            for (String pool : requiredPools) {
                String effectivePool = pool;
                if (opts.isAvoidSimilar()) {
                    StringBuilder sb = new StringBuilder();
                    for (char c : pool.toCharArray()) {
                        if (SIMILAR.indexOf(c) == -1) sb.append(c);
                    }
                    effectivePool = sb.toString();
                }
                if (effectivePool.isEmpty()) continue; // se removed all by avoidSimilar

                char chosen = effectivePool.charAt(random.nextInt(effectivePool.length()));
                password.append(chosen);
            }
        }

        // preenche o resto com caracteres aleatórios do charset final
        while (password.length() < length) {
            char c = finalCharset.charAt(random.nextInt(finalCharset.length()));
            password.append(c);
        }

        // embaralhar para que os caracteres obrigatórios não fiquem sempre no começo
        return shuffleString(password.toString());
    }

    // Gera várias senhas de uma vez
    public List<String> generateMultiple(PasswordOptions opts, int count) {
        List<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(generate(opts));
        }
        return list;
    }

    // Função simples para embaralhar os caracteres de uma string
    private String shuffleString(String input) {
        char[] a = input.toCharArray();
        for (int i = a.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }
        return new String(a);
    }
}
