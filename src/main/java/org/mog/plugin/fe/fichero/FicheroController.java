package org.mog.plugin.fe.fichero;

import org.mog.bo.Ficha;
import org.mog.bo.Fichero;
import org.mog.conf.Utilitario;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FicheroController {

    private FrontEndFicheroAdapter tareasRegistrable = new FrontEndFicheroAdapter((Fichero)Utilitario.myContainer().find("fichero"));

    @GetMapping("/version")
    public String version() {
        return "1.0.0";
    }

    @GetMapping("/sid/{sid}/horasTrabajadas/desde/{desde}/hasta/{hasta}")
    public String horasTrabajadasEnPeriodo(@PathVariable("sid") String sid, @PathVariable("desde") String desde, @PathVariable("hasta") String hasta) {
        return tareasRegistrable.horasTrabajadas(sid, desde, hasta);
    }

    @GetMapping("/sid/{sid}/fichas/desde/{desde}/hasta/{hasta}")
    @ResponseBody
    public List<Ficha> fichasRealizadasEnPeriodo(@PathVariable("sid") String sid, @PathVariable("desde") String desde, @PathVariable("hasta") String hasta) {
        return tareasRegistrable.fichasRealizadas(sid, desde, hasta);
    }

    @GetMapping("/sid/{sid}/fichas/")
    @ResponseBody
    public List<Ficha> fichasRealizadas(@PathVariable("sid") String sid) {
        return tareasRegistrable.fichasRealizadas(sid);
    }

    @PostMapping("/sid/{sid}/ingreso/dia/{dia}/hora/{time}")
    @ResponseBody
    public void ficharIngreso(@PathVariable("sid") String sid, @PathVariable("dia") String dia, @PathVariable("time") String hora) {
        tareasRegistrable.ficharIngreso(sid, dia, hora);
    }

    @PostMapping("/sid/{sid}/egreso/dia/{dia}/hora/{time}")
    @ResponseBody
    public void ficharEgreso(@PathVariable("sid") String sid, @PathVariable("dia") String dia, @PathVariable("time") String hora) {
        tareasRegistrable.ficharEgreso(sid, dia, hora);
    }
}
