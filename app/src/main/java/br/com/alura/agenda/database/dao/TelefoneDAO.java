package br.com.alura.agenda.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

@Dao
public interface TelefoneDAO {
    @Query(" SELECT t.* " +
           " FROM Telefone t "+
           " WHERE t.alunoId = :alunoId "+
           " LIMIT 1"
    )
    Telefone buscaPrimeiroTelefoneDoAluno(int alunoId);
    @Insert
    void salva(Telefone... telefones);

    @Query(" SELECT t.* " +
            " FROM Telefone t "+
            " WHERE t.alunoId = :alunoId ")
    List<Telefone> buscaTodosTelefonesDoAluno(int alunoId);
    //Tenta o insert, se ja existir faz o update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone... telefones);
}
