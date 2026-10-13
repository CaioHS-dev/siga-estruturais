package siga;

public class Main {

    public static void main(String[] args) {
        SecretariaLegadoWS servicoLegado = new SecretariaLegadoWS();
        AlunoDAO alunoDAO = new AlunoDAOMemoria();

        // 1. Instanciando o Adapter passando o componente legado
        FonteDeAlunos fonteDeAlunos = new AdaptadorSecretariaLegado(servicoLegado);

        System.out.println("=== SIGA — Aula 10: Adapter e Facade (código refatorado) ===");

        System.out.println("\n1) Importação a partir do sistema legado");
        // O importador agora recebe a abstração
        ImportadorAlunos importador = new ImportadorAlunos(fonteDeAlunos, alunoDAO);
        System.out.println("   Alunos importados: " + importador.importar());
        System.out.println("   Situação segundo o importador:");
        for (Aluno aluno : alunoDAO.listarTodos()) {
            System.out.println("     " + aluno);
        }

        System.out.println("\n2) O mesmo dado, convertido por outro trecho do sistema");
        // O relatório passa a receber exatamente a mesma fonte adaptada
        new RelatorioSituacao(fonteDeAlunos).imprimir();
        System.out.println("   >> DESLIZE 1: Correção aplicada! A situação agora é igual em ambas as pontas.");
        System.out.println("   >> DESLIZE 2: Resolvido. O DIP foi respeitado no importador.");

        System.out.println("\n3) Matrícula pela tela");
        Aluno bolsista = alunoDAO.buscarPorMatricula("2024001");
        if (bolsista != null) {
            bolsista.setBolsista(true);
        }

        // 2. Instanciando a Facade com as regras de negócio
        FachadaMatricula fachada = new FachadaMatricula(
                new ValidadorCpf(),
                alunoDAO,
                new ControleVagas(),
                new CalculadoraDesconto(),
                new MatriculaDAOMemoria(),
                new NotificadorEmail()
        );

        // A tela agora conhece única e exclusivamente a fachada
        TelaMatricula tela = new TelaMatricula(fachada);

        tela.aoClicarEmMatricular("2024001", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024002", "111", "DSM-2A");
        tela.aoClicarEmMatricular("2024009", "123.456.789-09", "DSM-2A");
        tela.aoClicarEmMatricular("2024004", "123.456.789-09", "DSM-2B");
        System.out.println("   >> DESLIZE 3: A tela precisou receber apenas UM colaborador (a fachada) e o comportamento continua o mesmo.");
    }
}