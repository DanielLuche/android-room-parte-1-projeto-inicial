package br.com.alura.agenda.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void,Void,Void> {

    protected  final FinalizadaListener listener;

    protected BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }


    protected void vinculaAlunoComTelefone(int alunoId, Telefone... telefones) {
        for (Telefone telefone : telefones) {
            telefone.setAlunoId(alunoId);
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {a
        super.onPostExecute(aVoid);
        if(listener!=null){
            listener.quandoFinalizada();
        }
    }

    public interface FinalizadaListener{
        void quandoFinalizada();
    }

}
