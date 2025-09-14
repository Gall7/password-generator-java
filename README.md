🔐 Gerador de Senhas em Java

Um gerador de senhas feito em **Java puro**, com foco em segurança e flexibilidade.  
Permite configurar o comprimento da senha, uso de maiúsculas, minúsculas, dígitos, símbolos, além da opção de evitar caracteres semelhantes e garantir pelo menos um de cada tipo escolhido.  

📌 Estrutura do Projeto
O projeto está organizado em três classes principais:
- **`PasswordOptions`** → Define as opções de geração (tamanho, tipos de caracteres, etc.).
- **`PasswordGenerator`** → Lógica principal de geração das senhas.
- **`Main`** → Interface simples de linha de comando para interagir com o usuário.

⚡ Funcionalidades
✅ Definir comprimento da senha  
✅ Incluir ou excluir letras maiúsculas, minúsculas, dígitos e símbolos  
✅ Opção para evitar caracteres semelhantes (1, l, I, O, 0)  
✅ Garantir pelo menos um caractere de cada tipo selecionado  
✅ Gerar múltiplas senhas de uma vez  
