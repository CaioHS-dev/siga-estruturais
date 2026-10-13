package siga;

import java.util.ArrayList;
import java.util.List;

/**
 * DESLIZE 1 (parte A) — conversão do formato legado feita aqui dentro.
 * DESLIZE 2 — depende da CLASSE CONCRETA SecretariaLegadoWS, e não de uma abstração.
 *
 * Esta classe conhece o formato da matriz do legado: que a coluna 0 é a matrícula,
 * a 1 é o nome e a 2 é a situação em letra. Esse conhecimento deveria estar em um
 * único lugar do sistema — e não está (ver RelatorioSituacao).
 */
public class ImportadorAlunos {

    // Agora depende da interface, e não mais da classe concreta do legado
    private final FonteDeAlunos fonte;
    private final AlunoDAO dao;

    public ImportadorAlunos(FonteDeAlunos fonte, AlunoDAO dao) {
        this.fonte = fonte;
        this.dao = dao;
    }

    public int importar() {
        int importados = 0;
        // Puxa a lista de alunos já convertida e instanciada pelo Adaptador
        for (Aluno aluno : fonte.listar()) {
            dao.inserir(aluno);
            importados++;
        }
        return importados;
    }
}
