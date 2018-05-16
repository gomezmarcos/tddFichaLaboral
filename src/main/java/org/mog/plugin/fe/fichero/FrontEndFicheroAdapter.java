package org.mog.plugin.fe.fichero;

import org.mog.bo.Ficha;
import org.mog.bo.Fichero;
import org.mog.conf.Utilitario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FrontEndFicheroAdapter {
    private Fichero fichero;

    public FrontEndFicheroAdapter(Fichero fichero){
        //fichero = (Fichero) Utilitario.myContainer().find("fichero");
        this.fichero = fichero;
    }

    public void ficharIngreso(String empleado, String hoy, String of) {
        String[] date = hoy.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        String[] time = of.split(":");
        Integer hour = new Integer(time[0]);
        Integer minutes = new Integer(time[1]);

        fichero.ficharIngreso(empleado, LocalDate.of(year,month,day), LocalTime.of(hour,minutes));
    }

    public void ficharEgreso(String empleado, String hoy, String of) {
        String[] date = hoy.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        String[] time = of.split(":");
        Integer hour = new Integer(time[0]);
        Integer minutes = new Integer(time[1]);
        fichero.ficharIngreso(empleado, LocalDate.of(year,month,day), LocalTime.of(hour,minutes));
    }

    public String horasTrabajadas(String empleado, String hoy) {
        String[] date = hoy.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        return fichero.horasTrabajadas(empleado, LocalDate.of(year,month,day)).format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public String horasTrabajadas(String empleado, String fromDate, String toDate) {
        String[] date = fromDate.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        String[] date2 = toDate.split("-");
        Integer day2 = new Integer( date2[2]);
        Integer month2 = new Integer( date2[1]);
        Integer year2 = new Integer( date2[0]);
        return fichero.horasTrabajadas(empleado, LocalDate.of(year,month,day), LocalDate.of(year2,month2,day2) ).format(DateTimeFormatter.ofPattern("hh:mm"));
    }

    public Map<String, String> horasTrabajadasPorPersona(String fromDate, String toDate) {
        String[] date = fromDate.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        String[] date2 = toDate.split("-");
        Integer day2 = new Integer( date2[2]);
        Integer month2 = new Integer( date2[1]);
        Integer year2 = new Integer( date2[0]);

        return fichero.horasTrabajadas(LocalDate.of(year, month, day), LocalDate.of(year2, month2, day2))
                .entrySet().stream()
                .collect(Collectors.toMap(x -> x.getKey(), y -> y.getValue().format(DateTimeFormatter.ofPattern("hh:mm"))));

    }

    public List<Ficha> fichasRealizadas(String sid, String fromDate, String toDate) {
        String[] date = fromDate.split("-");
        Integer day = new Integer( date[2]);
        Integer month = new Integer( date[1]);
        Integer year = new Integer( date[0]);

        String[] date2 = toDate.split("-");
        Integer day2 = new Integer( date2[2]);
        Integer month2 = new Integer( date2[1]);
        Integer year2 = new Integer( date2[0]);
        LocalDate d1 = LocalDate.of(year, month, day);
        LocalDate d2 = LocalDate.of(year2, month2, day2);
        return Arrays.asList(fichero.getFichass(d1, d2, sid));
    }

    public List<Ficha> fichasRealizadas(String sid) {
        return fichero.getAllFichas(sid);
    }
}
