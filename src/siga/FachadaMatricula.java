package siga;

public class FachadaMatricula {

    private final ValidadorCpf validadorCpf;
    private final AlunoDAO alunoDAO;
    private final ControleVagas controleVagas;
    private final CalculadoraDesconto calculadoraDesconto;
    private final MatriculaDAO matriculaDAO;
    private final NotificadorEmail notificadorEmail;

    public FachadaMatricula(ValidadorCpf validadorCpf, AlunoDAO alunoDAO, ControleVagas controleVagas,
                            CalculadoraDesconto calculadoraDesconto, MatriculaDAO matriculaDAO, NotificadorEmail notificadorEmail) {
        this.validadorCpf = validadorCpf;
        this.alunoDAO = alunoDAO;
        this.controleVagas = controleVagas;
        this.calculadoraDesconto = calculadoraDesconto;
        this.matriculaDAO = matriculaDAO;
        this.notificadorEmail = notificadorEmail;
    }

    public void matricular(String matricula, String cpf, String codigoTurma) {
        // 1. Validar CPF chamando o método "valido" que retorna um boolean
        if (!validadorCpf.valido(cpf)) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        
        // 2. Buscar Aluno no DAO
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            throw new IllegalArgumentException("Aluno não encontrado para a matrícula: " + matricula);
        }
        
        // 3. Checar e reservar vaga no ControleVagas
        controleVagas.reservar(codigoTurma);
        
        // 4. Calcular desconto
        double desconto = calculadoraDesconto.calcular(aluno);
        
        // 5. Salvar na MatriculaDAO
        Matricula novaMatricula = new Matricula(aluno, codigoTurma, desconto);
        matriculaDAO.inserir(novaMatricula);
        
        // 6. Enviar notificação usando o método "enviarConfirmacao" que recebe Aluno e Matricula
        notificadorEmail.enviarConfirmacao(aluno, novaMatricula);
    }
}