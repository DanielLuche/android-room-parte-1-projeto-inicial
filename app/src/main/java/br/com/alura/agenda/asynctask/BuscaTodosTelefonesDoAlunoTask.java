package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaTodosTelefonesDoAlunoTask extends AsyncTask<Void,Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final int alunoId;
    private final TelefoneDoAlunoEncontradoListener listener;

    public BuscaTodosTelefonesDoAlunoTask(TelefoneDAO telefoneDAO, int alunoId, TelefoneDoAlunoEncontradoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosTelefonesDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        if(listener != null){
            listener.quandoEncontrado(telefones);
        }
    }

    public interface TelefoneDoAlunoEncontradoListener{
        void quandoEncontrado(List<Telefone> telefones);
    }
}
