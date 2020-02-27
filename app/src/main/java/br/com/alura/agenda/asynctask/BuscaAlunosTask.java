package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunosTask extends AsyncTask<Void,Void,List<Aluno> > {
    private final AlunoDAO alunoDAO;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunosTask(AlunoDAO alunoDAO, ListaAlunosAdapter adapter) {
        this.alunoDAO = alunoDAO;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void... voids) {
        return alunoDAO.todos();
    }

    @Override
    protected void onPostExecute(List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
