package br.com.alura.agenda.database.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Calendar;

/**
 * Classe que será usada pelo Room para converter o tipo "incompativel" calendar para INTEGER do Sqlite
 */
public class ConversorCalendar {
    //Necessario colocar a anottation para o Room saber que é um metodo de conversão
    @TypeConverter
    public Long paraLong(Calendar valor){
        if(valor != null) {
            return valor.getTimeInMillis();
        }
        return null;
    }
    //Necessario colocar a anottation para o Room saber que é um metodo de conversão
    @TypeConverter
    public Calendar paraCalendar(Long valor){
        Calendar momentoAtual = Calendar.getInstance();
        if(valor != null){
            momentoAtual.setTimeInMillis(valor);
        }
        return momentoAtual;
    }
}
