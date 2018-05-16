package org.mog.bo;

import org.mog.plugin.repo.FicheroRepo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Fichero {
    public static final String FICHA_INEXISTENTE = "nooooo";
    public static final String INGRESO = "INGRESO";
    public static final String EGRESO = "EGRESO";

    private FicheroRepo ficheroRepo;

    public Fichero(FicheroRepo ficherRepo){
        this.ficheroRepo = ficherRepo;
    }

    public String horaIngreso(String empleado, LocalDate hoy) {
        return ficheroRepo.findAll().stream()
                .filter(f -> f.getSid().equals(empleado))
                .filter(f -> f.getDate().equals(hoy))
                .filter(f -> Fichero.INGRESO.equals(f.getType()))
                .min(Comparator.comparing(Ficha::getTime))
                .map(Ficha::prettyHora)
                .orElseThrow(() -> new RuntimeException(FICHA_INEXISTENTE));
    }

    public void ficharIngreso(String empleado, LocalDate date, LocalTime time) {
        ficheroRepo.persistir(new Ficha(Fichero.INGRESO, empleado, date, time));
    }


    public void ficharEgreso(String empleado, LocalDate hoy, LocalTime of) {
        ficheroRepo.persistir(new Ficha(Fichero.EGRESO, empleado, hoy, of));
    }

    public LocalTime horasTrabajadas(String empleado, LocalDate hoy) {
        return ficheroRepo.findAll().stream()
                .filter(f -> f.getDate().equals(hoy))
                .filter(f -> f.getSid().equals(empleado))
                .sorted(Comparator.comparing(Ficha::getTime))
                .map(f -> f.getTime())
                .reduce((x, y) -> y.minusHours(x.getHour()).minusMinutes(x.getMinute()))
                .orElseThrow(NotImplementedException::new);
    }

    public LocalTime horasTrabajadas(String empleado, LocalDate fromDate, LocalDate toDate) {
        Ficha[] fichass = getFichass(fromDate, toDate, empleado);


        Integer total = fichass[1].getTime().minusHours(fichass[0].getTime().getHour()).getHour();
        for (int i = 3; i < fichass.length; i += 2) {
            Integer entrada = fichass[i - 1].getTime().getHour();
            Integer salida = fichass[i].getTime().getHour();
            total += salida - entrada;
        }
        return LocalTime.of(total, 0);
    }

    public Map<String, LocalTime> horasTrabajadas(LocalDate fromDate, LocalDate toDate) {
        Map<String, LocalTime> asdf = new HashMap<>();

        Ficha[] fichass = getFichass(fromDate, toDate, "30321188");
        Integer total = fichass[1].getTime().minusHours(fichass[0].getTime().getHour()).getHour();
        for (int i = 3; i < fichass.length; i += 2) {
            Integer entrada = fichass[i - 1].getTime().getHour();
            Integer salida = fichass[i].getTime().getHour();
            total += salida - entrada;
        }
        asdf.put("30321188", LocalTime.of(total, 0));

        fichass = getFichass(fromDate, toDate, "33397167");
        total = fichass[1].getTime().minusHours(fichass[0].getTime().getHour()).getHour();
        for (int i = 3; i < fichass.length; i += 2) {
            Integer entrada = fichass[i - 1].getTime().getHour();
            Integer salida = fichass[i].getTime().getHour();
            total += salida - entrada;
        }
        asdf.put("33397167", LocalTime.of(total, 0));
        return asdf;
    }

    public Ficha[] getFichass(LocalDate fromDate, LocalDate toDate, String s) {
        return ficheroRepo.findAll().stream()
                .filter(f -> f.getSid().equals(s))
                .filter(f -> f.getDate().isAfter(fromDate))
                .filter(f -> f.getDate().isBefore(toDate))
                .sorted(Comparator.comparing(Ficha::getDate))
                .toArray(Ficha[]::new);
    }

    public List<Ficha> getAllFichas(String sid) {
        return ficheroRepo.findAll().stream()
                .filter(f -> f.getSid().equals(sid))
                .sorted(Comparator.comparing(Ficha::getDate))
                .collect(Collectors.toList());
    }
}
