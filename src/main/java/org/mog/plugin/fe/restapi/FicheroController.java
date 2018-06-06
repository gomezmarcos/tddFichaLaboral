package org.mog.plugin.fe.restapi;

import org.mog.conf.Utilitario;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FicheroController {
    FrontEndFicheroAdapter ficheroAdapter = (FrontEndFicheroAdapter) Utilitario.myContainer().find("ficheroAdapter");

    @CrossOrigin(origins = "*")
    @GetMapping("list")
    public List<FichaFE> getFichas() {
        return ficheroAdapter.findAll().stream().map(f -> FichaFE.map(f)).collect(Collectors.toCollection(ArrayList::new));
    }

    @PostMapping("ingreso")
    public FichaFE ficharIngreso(@RequestBody FichaFE ficha) {
        return ficheroAdapter.ficharIngreso(ficha.sid, ficha.date, ficha.time);
    }

    @PostMapping("egreso")
    public FichaFE ficharEgreso(@RequestBody FichaFE ficha) {
        return ficheroAdapter.ficharEgreso(ficha.sid, ficha.date, ficha.time);
    }
}
