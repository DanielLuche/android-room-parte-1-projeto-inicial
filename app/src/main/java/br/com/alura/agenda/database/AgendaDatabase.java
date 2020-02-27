package br.com.alura.agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.converter.ConversorTipoTelefone;
import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.database.dao.TelefoneDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6,exportSchema = false)
//Notacao que indica as classes de conversoes de tipos do Java para o Sqlite
@TypeConverters({ConversorCalendar.class, ConversorTipoTelefone.class})
public abstract class AgendaDatabase extends RoomDatabase {
    /**
     * Padrão Singleton
     */
    //    public static final String DATABASE_NAME = "agenda.db";
//    private static AgendaDatabase instance;
//    public abstract AlunoDAO getRoomAlunoDAO();
//
//    public static synchronized AgendaDatabase getInstance(Context context){
//        if(instance == null) {
//            instance = Room.databaseBuilder(context, AgendaDatabase.class, AgendaDatabase.DATABASE_NAME)
//                    .allowMainThreadQueries()
//                    .build();
//        }
//        return instance;
//
//    }

    public static final String DATABASE_NAME = "agenda.db";
    public abstract AlunoDAO getAlunoDAO();
    public abstract TelefoneDAO getTelefoneDAO();

    public static synchronized AgendaDatabase getInstance(Context context){
        return Room.databaseBuilder(context, AgendaDatabase.class, AgendaDatabase.DATABASE_NAME)
                //.allowMainThreadQueries() //
                //.fallbackToDestructiveMigration()//Drop o banco ao trocar versão
                .addMigrations(AgendaMigrations.TODAS_MIGRATIONS)
                .build();

    }
}
