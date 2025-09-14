// Main.java
// Exemplo de uso interativo (linha de comando). Copie, compile e execute.
// java Main
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PasswordOptions opts = new PasswordOptions();

        System.out.println("=== Gerador de Senhas (simples) ===");

        System.out.print("Comprimento da senha (padrão 12): ");
        String sLen = sc.nextLine().trim();
        if (!sLen.isEmpty()) {
            try {
                opts.setLength(Integer.parseInt(sLen));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido, usando padrão 12.");
            }
        }

        System.out.print("Incluir maiúsculas? (S/n) [padrão S]: ");
        String in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("n")) opts.setIncludeUppercase(false);

        System.out.print("Incluir minúsculas? (S/n) [padrão S]: ");
        in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("n")) opts.setIncludeLowercase(false);

        System.out.print("Incluir dígitos? (S/n) [padrão S]: ");
        in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("n")) opts.setIncludeDigits(false);

        System.out.print("Incluir símbolos? (s/N) [padrão N]: ");
        in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("s")) opts.setIncludeSymbols(true);

        System.out.print("Evitar caracteres semelhantes (1 l I O 0)? (s/N) [padrão N]: ");
        in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("s")) opts.setAvoidSimilar(true);

        System.out.print("Garantir pelo menos 1 de cada tipo selecionado? (S/n) [padrão S]: ");
        in = sc.nextLine().trim();
        if (in.equalsIgnoreCase("n")) opts.setRequireEachType(false);

        System.out.print("Quantas senhas gerar? (padrão 5): ");
        int count = 5;
        in = sc.nextLine().trim();
        if (!in.isEmpty()) {
            try { count = Integer.parseInt(in); } catch (NumberFormatException e) { System.out.println("Valor inválido, usando 5."); }
        }

        // gera e mostra
        PasswordGenerator gen = new PasswordGenerator();
        try {
            List<String> passwords = gen.generateMultiple(opts, count);
            System.out.println("\nSenhas geradas:");
            for (int i = 0; i < passwords.size(); i++) {
                System.out.printf("%2d: %s%n", i + 1, passwords.get(i));
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Erro nas opções: " + ex.getMessage());
        }

        sc.close();
    }
}
