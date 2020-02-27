
package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Telefone;

public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void,Void,Telefone> {
    private final TelefoneDAO telefoneDAO;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListner listener;

    public interface PrimeiroTelefoneEncontradoListner{
        void quandoEncontrado(Telefone primeiroTelefone);
    }

    public BuscaPrimeiroTelefoneDoAlunoTask(TelefoneDAO telefoneDAO, int alunoId, PrimeiroTelefoneEncontradoListner listener) {
        this.telefoneDAO = telefoneDAO;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefoneDoAluno(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        if(listener != null){
            listener.quandoEncontrado(primeiroTelefone);
        }
    }
}
