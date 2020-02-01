package br.com.alura.agenda.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1,exportSchema = false)
public abstract class AgendaDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "agenda.db";

    public abstract RoomAlunoDAO getRoomAlunoDAO();
}