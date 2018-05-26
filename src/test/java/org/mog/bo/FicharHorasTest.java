package org.mog.bo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mog.plugin.repo.FicheroRepoStub;
import org.mog.plugin.repo.FicheroRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class FicharHorasTest {
    private Fichero fichero;

    @Before
    public void sestUp() {
        FicheroRepo repo = new FicheroRepoStub();
        fichero = new Fichero(repo);
    }

    @Test
    public void sePuedeFicharIngresoCuandoElUsuarioExisteEnElSistema() {
        LocalDate hoy = LocalDate.now();
        String empleado = "30321188";

        fichero.ficharIngreso(empleado, hoy, LocalTime.of(8, 30));

        Assert.assertEquals("08:30", fichero.horaIngreso(empleado, hoy));
    }

    @Test
    public void noSePuedeFicharIngresosCuandoElUsuarioNoExiste() {
        try {
            Assert.assertEquals("08:30", fichero.horaIngreso("asdf", LocalDate.now()));
            Assert.fail();
        } catch (RuntimeException e) {
            Assert.assertEquals(Fichero.FICHA_INEXISTENTE, e.getMessage());
        }
    }

    @Test
    public void obtenerHorasTrabajasEsIgualALaDiferenciaEntreFichaIngresoYFichaSalida() {
        LocalDate hoy = LocalDate.now();
        String empleado = "30321188";

        fichero.ficharIngreso(empleado, hoy, LocalTime.of(8, 30));
        fichero.ficharEgreso(empleado, hoy, LocalTime.of(10, 30));

        Assert.assertEquals("02:00", fichero.horasTrabajadas(empleado, hoy).format(DateTimeFormatter.ofPattern("hh:mm")));
    }

    @Test
    public void casfdasdf() {
        Fichero fichero = new Fichero(new FicheroRepoStub());
        LocalDate hoy = LocalDate.now();
        LocalDate semanaPasada = LocalDate.now().minusWeeks(1);
        LocalDate ayer = LocalDate.now().minusDays(1);
        String marcos = "30321188";

        fichero.ficharIngreso(marcos, hoy, LocalTime.of(8, 30));
        fichero.ficharEgreso(marcos, hoy, LocalTime.of(10, 30));
        fichero.ficharIngreso(marcos, ayer, LocalTime.of(10, 30));
        fichero.ficharEgreso(marcos, ayer, LocalTime.of(20, 30));
        fichero.ficharIngreso(marcos, semanaPasada, LocalTime.of(8, 30));
        fichero.ficharEgreso(marcos, semanaPasada, LocalTime.of(10, 30));


        Assert.assertEquals(LocalTime.of(14, 0), fichero.horasTrabajadas(marcos, hoy.minusMonths(1), hoy.plusDays(2)));
    }

    @Test
    public void obtenerHorasTrabajadasParaTodosLosUsuariosEnUnPeriodo() {
        Fichero fichero = new Fichero(new FicheroRepoStub());
        LocalDate hoy = LocalDate.now();
        LocalDate semanaPasada = LocalDate.now().minusWeeks(1);
        LocalDate ayer = LocalDate.now().minusDays(1);
        String marcos = "30321188";
        String nubi = "33397167";

        fichero.ficharIngreso(marcos, hoy, LocalTime.of(8, 30));
        fichero.ficharEgreso(marcos, hoy, LocalTime.of(10, 30));
        fichero.ficharIngreso(marcos, ayer, LocalTime.of(10, 30));
        fichero.ficharEgreso(marcos, ayer, LocalTime.of(20, 30));
        fichero.ficharIngreso(marcos, semanaPasada, LocalTime.of(8, 30));
        fichero.ficharEgreso(marcos, semanaPasada, LocalTime.of(10, 30));

        fichero.ficharIngreso(nubi, hoy, LocalTime.of(8, 30));
        fichero.ficharEgreso(nubi, hoy, LocalTime.of(10, 30));
        fichero.ficharIngreso(nubi, ayer, LocalTime.of(10, 30));
        fichero.ficharEgreso(nubi, ayer, LocalTime.of(20, 30));
        Map<String, LocalTime> map = fichero.horasTrabajadas(hoy.minusMonths(1), hoy.plusDays(1));

        Assert.assertEquals(LocalTime.of(14, 0), map.get(marcos));
        Assert.assertEquals(LocalTime.of(12, 0), map.get(nubi));
    }
}
