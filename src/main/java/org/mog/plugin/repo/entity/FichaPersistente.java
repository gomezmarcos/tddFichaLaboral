package org.mog.plugin.repo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

//@Entity(name = "FICHA")
public class FichaPersistente {
    //@Id
    //@GeneratedValue
    public Long oid;
    public LocalTime time;
    public LocalDate date;
    public String sid;
    public String type;
}
