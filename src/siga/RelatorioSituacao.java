package siga;

/**
 * DESLIZE 1 (parte B) — a MESMA conversão, escrita de novo, em outro lugar.
 *
 * Aqui alguém percebeu que a situação às vezes vem em minúscula e corrigiu.
 * A correção não chegou ao ImportadorAlunos: as duas versões já divergem,
 * e o mesmo aluno aparece ativo em um lugar e inativo no outro.
 */
public class RelatorioSituacao {

    private final FonteDeAlunos fonte;

    public RelatorioSituacao(FonteDeAlunos fonte) {
        this.fonte = fonte;
    }

    public void imprimir() {
        for (Aluno aluno : fonte.listar()) {
            System.out.println("Situação - " + aluno.getMatricula() + ": " + (aluno.isAtivo() ? "ATIVA" : "INATIVA"));
        }
    }
}
