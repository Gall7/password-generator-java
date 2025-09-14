// PasswordOptions.java
// Classe que guarda as opções de geração de senha (fácil de ajustar)
public class PasswordOptions {
    // comprimento desejado da senha
    private int length;

    // incluir letras maiúsculas (A-Z)
    private boolean includeUppercase;

    // incluir letras minúsculas (a-z)
    private boolean includeLowercase;

    // incluir dígitos (0-9)
    private boolean includeDigits;

    // incluir símbolos (!@#... )
    private boolean includeSymbols;

    // impedir caracteres visualmente parecidos (1 l I O 0)
    private boolean avoidSimilar;

    // garantir que cada tipo selecionado apareça ao menos uma vez
    private boolean requireEachType;

    public PasswordOptions() {
        // valores padrão razoáveis
        this.length = 12;
        this.includeUppercase = true;
        this.includeLowercase = true;
        this.includeDigits = true;
        this.includeSymbols = false;
        this.avoidSimilar = false;
        this.requireEachType = true;
    }

    // getters e setters
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public boolean isIncludeUppercase() { return includeUppercase; }
    public void setIncludeUppercase(boolean includeUppercase) { this.includeUppercase = includeUppercase; }

    public boolean isIncludeLowercase() { return includeLowercase; }
    public void setIncludeLowercase(boolean includeLowercase) { this.includeLowercase = includeLowercase; }

    public boolean isIncludeDigits() { return includeDigits; }
    public void setIncludeDigits(boolean includeDigits) { this.includeDigits = includeDigits; }

    public boolean isIncludeSymbols() { return includeSymbols; }
    public void setIncludeSymbols(boolean includeSymbols) { this.includeSymbols = includeSymbols; }

    public boolean isAvoidSimilar() { return avoidSimilar; }
    public void setAvoidSimilar(boolean avoidSimilar) { this.avoidSimilar = avoidSimilar; }

    public boolean isRequireEachType() { return requireEachType; }
    public void setRequireEachType(boolean requireEachType) { this.requireEachType = requireEachType; }

    // validação simples das opções (lança IllegalArgumentException se inválido)
    public void validate() {
        if (length <= 0) {
            throw new IllegalArgumentException("O comprimento da senha deve ser positivo.");
        }
        if (!includeUppercase && !includeLowercase && !includeDigits && !includeSymbols) {
            throw new IllegalArgumentException("Pelo menos um conjunto de caracteres deve ser selecionado.");
        }
        int typesSelected = 0;
        if (includeUppercase) typesSelected++;
        if (includeLowercase) typesSelected++;
        if (includeDigits) typesSelected++;
        if (includeSymbols) typesSelected++;
        if (requireEachType && length < typesSelected) {
            throw new IllegalArgumentException("Comprimento insuficiente para garantir pelo menos um caractere de cada tipo selecionado.");
        }
    }
}
