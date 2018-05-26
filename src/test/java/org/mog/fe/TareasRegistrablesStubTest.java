package org.mog.fe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mog.bo.Fichero;
import org.mog.conf.Utilitario;
import org.mog.plugin.fe.fichero.FrontEndFicheroAdapter;
import org.mog.plugin.repo.FicheroRepoStub;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class TareasRegistrablesStubTest {
    private FrontEndFicheroAdapter api;
    private String marcos = "30321188";
    private String nuria = "33397167";

    private LocalDate dia = LocalDate.of(2018, 6, 24);
    private LocalDate diaPosterior = LocalDate.of(2018, 6, 25);
    private LocalDate mesPosterior = LocalDate.of(2018, 7, 25);

    private LocalDate diaDesde = dia.minusDays(1);
    private LocalDate diaHasta = mesPosterior.plusDays(1);

    @Before
    public void setUp(){
        FicheroRepoStub ficherRepoStub = new FicheroRepoStub();
        Utilitario.myContainer().save("ficheroRepo", ficherRepoStub);
        Utilitario.myContainer().save("fichero", new Fichero(ficherRepoStub));
        api = new FrontEndFicheroAdapter(new Fichero(new FicheroRepoStub()));
    }

    @Test
    public void alCargarFichaCompletaPuedeObtenerseParaEseDia() {
        LocalTime horaEntrada = LocalTime.of(8, 0);
        api.ficharIngreso(marcos, dia.toString(), horaEntrada.toString());
        api.ficharEgreso(marcos, dia.toString(), horaEntrada.plusHours(2).toString());
        Assert.assertEquals("02:00", api.horasTrabajadas(marcos, dia.toString()));
    }

    @Test
    public void alCargarVariasFichasPuedenObtenerseEnRangos() {
        LocalTime horaEntrada = LocalTime.of(8, 0);
        api.ficharIngreso(marcos, dia.toString(), horaEntrada.toString());
        api.ficharEgreso(marcos, dia.toString(), horaEntrada.plusHours(2).toString());
        api.ficharIngreso(marcos, diaPosterior.toString(), horaEntrada.toString());
        api.ficharEgreso(marcos, diaPosterior.toString(), horaEntrada.plusHours(2).toString());
        Assert.assertEquals("04:00", api.horasTrabajadas(marcos, diaDesde.toString(), diaHasta.toString()));
    }

    @Test
    public void puedenObtenerseLasFichasDeTodosLosUsuarios() {
        LocalTime horaEntrada = LocalTime.of(8, 0);
        api.ficharIngreso(marcos, dia.toString(), horaEntrada.toString());
        api.ficharEgreso(marcos, dia.toString(), horaEntrada.plusHours(2).toString());
        api.ficharIngreso(marcos, diaPosterior.toString(), horaEntrada.toString());
        api.ficharEgreso(marcos, diaPosterior.toString(), horaEntrada.plusHours(2).toString());
        api.ficharIngreso(nuria, diaPosterior.toString(), horaEntrada.toString());
        api.ficharEgreso(nuria, diaPosterior.toString(), horaEntrada.plusHours(2).toString());

        Map<String, String> map = api.horasTrabajadasPorPersona(diaDesde.toString(), diaHasta.toString());
        Assert.assertEquals("04:00", map.get(marcos));
        Assert.assertEquals("02:00", map.get(nuria));
    }
}
