package br.com.alura.agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

class AgendaMigrations {
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN sobrenome TEXT");
        }
    };
    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Criar nova table com os campos desejados
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");
            //Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id,nome,telefone,email) " +
                    " SELECT id,nome,telefone,email FROM Aluno");
            //Deleta tabela antiga
            database.execSQL("DROP TABLE Aluno");
            //Renomeia tabela nova
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //Criar nova table com os campos desejados
            database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                    "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT, " +
                    " `telefoneFixo` TEXT," +
                    " `telefoneCelular` TEXT," +
                    " `email` TEXT," +
                    " `momentoDeCadastro` INTEGER)"
            );
            //Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id,nome,telefoneFixo,telefoneCelular,email,momentoDeCadastro) " +
                    " SELECT id,nome,telefone,'',email,momentoDeCadastro FROM Aluno");
            //Deleta tabela antiga
            database.execSQL("DROP TABLE Aluno");
            //Renomeia tabela nova
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE  `Aluno_novo` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `nome` TEXT, " +
                    "`email` TEXT, " +
                    "`momentoDeCadastro` INTEGER)"
            );
            //Copiar dados da tabela antiga para a nova
            database.execSQL("INSERT INTO Aluno_novo (id,nome,email,momentoDeCadastro) " +
                    " SELECT id,nome,email,momentoDeCadastro FROM Aluno");
            //Cria tabela de telefone
            database.execSQL("CREATE TABLE  `Telefone` (" +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " `numero` TEXT, " +
                    "`tipo` TEXT," +
                    " `alunoId` INTEGER NOT NULL)"
            );
            //Insere dados do telefone vindo da tabela aluno
            database.execSQL("INSERT INTO Telefone (numero,alunoId) " +
                    " SELECT telefoneFixo,id FROM Aluno");
            //Atualiza tipo para fixo
            database.execSQL("UPDATE Telefone SET tipo = ? ", new TipoTelefone[] {FIXO});
            //Deleta tabela antiga
            database.execSQL("DROP TABLE Aluno");
            //Renomeia tabela nova
            database.execSQL("ALTER TABLE Aluno_novo RENAME TO Aluno");
        }
    };
    //
    static final Migration[] TODAS_MIGRATIONS = {
            MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6
    };
}
