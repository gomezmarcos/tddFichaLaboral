package org.mog.plugin.fe.restapi;

import org.mog.bo.Ficha;

import java.time.format.DateTimeFormatter;

public class FichaFE  {
    public String time;
    public String date;
    public String sid;
    public String type;
    public Long oid;

    private FichaFE(){}

    public static FichaFE map(Ficha ficha) {
        FichaFE fichaFE = new FichaFE();
        fichaFE.date = ficha.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        fichaFE.time = ficha.getTime().format(DateTimeFormatter.ofPattern("hh:mm"));
        fichaFE.sid = ficha.getSid();
        fichaFE.type = ficha.getType();
        fichaFE.oid = ficha.getOid();
        return fichaFE;
    }
}
