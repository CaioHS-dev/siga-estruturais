# Documentação siga-estruturais

## 1. Leitura duplicada e dependência (A integração com o Legado)

* O nosso sistema precisava ler dados de um sistema antigo da secretaria. O problema é que a regra que traduzia esses dados foi escrita duas vezes: uma vez no importador de alunos e outra no gerador de relatórios. 
Como o código estava duplicado, alguém corrigiu um erro do "A" minúsculo em um lugar, mas esqueceu do outro. Isso fez com que a mesma aluna aparecesse como inativa no importador e ativa no relatório. Além disso, o nosso importador estava "amarrado" diretamente à classe exata desse sistema antigo, o que dificultaria muito a troca desse sistema no futuro.

* Usei o Adapter. Primeiro, criei uma interface que dita como o nosso sistema gosta de receber a lista de alunos. Depois, criei uma classe adaptadora que pega os dados bagunçados do sistema antigo e os traduz para o nosso formato. Dessa forma, a regra de ler a letra "A" (maiúscula ou minúscula) ficou em um lugar só. O importador e o relatório agora usam apenas esse adaptador, resolvendo a divergência da aluna e cortando a união direta com o código antigo.

---

## 2. A complexidade da Matrícula

* Para matricular um aluno, a tela precisava conversar com seis ferramentas diferentes: validador de CPF, banco de dados de alunos, controle de vagas, calculadora de descontos, banco de matrículas e o enviador de e-mails. 
Se a secretaria mudasse a regra, eu teria que alterar o código da tela (visual).

* Criei uma classe nova chamada `FachadaMatricula`. Peguei toda aquela sequência complexa de validações, cálculos e salvamentos e coloquei dentro dela. 
A fachada passou a ser a gerente exclusiva desse processo. A tela, por sua vez, foi "limpa": agora ela conhece apenas a fachada. Quando o usuário clica em matricular, a tela simplesmente repassa o pedido para a fachada e avisa se deu certo ou se deu erro, sem fazer ideia de quantas ferramentas trabalharam nos bastidores para isso acontecer.

---
