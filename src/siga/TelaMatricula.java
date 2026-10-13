package siga;

/**
 * DESLIZE 3 — a camada de apresentação conhece as SEIS classes do subsistema
 * e, pior, conhece a ORDEM correta de chamá-las, que é regra de negócio.
 *
 * Quando a secretaria pedir matrícula em lote, estas linhas serão copiadas —
 * e, copiadas, divergirão.
 */
public class TelaMatricula {

    private final FachadaMatricula fachada;

    public TelaMatricula(FachadaMatricula fachada) {
        this.fachada = fachada;
    }

    public void aoClicarEmMatricular(String matricula, String cpf, String codigoTurma) {
        try {
            fachada.matricular(matricula, cpf, codigoTurma);
            System.out.println("   [Tela] Matrícula processada com sucesso: " + matricula + " em " + codigoTurma);
        } catch (Exception e) {
            System.out.println("   [Tela] Erro na matrícula de " + matricula + ": " + e.getMessage());
        }
    }
}
