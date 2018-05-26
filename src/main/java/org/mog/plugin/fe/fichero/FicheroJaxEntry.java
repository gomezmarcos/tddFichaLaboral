package org.mog.plugin.fe.fichero;

import org.mog.bo.Ficha;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalTime;

@Path("/jax")
public class FicheroJaxEntry {
    @GET
    @Path("version")
    @Produces(MediaType.APPLICATION_JSON)
    public FichaFE test(){
        Ficha ficha = new Ficha("ENTRADA", "30321188", LocalDate.now(), LocalTime.now());
        return FichaFE.map(ficha);
    }
}
