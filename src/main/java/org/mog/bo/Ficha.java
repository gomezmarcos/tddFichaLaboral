package org.mog.bo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Ficha {
    private LocalTime time;
    private LocalDate date;
    private String sid;
    private String type;
    private Long oid;

    public Ficha(String type, String sid, LocalDate date, LocalTime time) {
        this.date = date;
        this.time = time;
        this.sid = sid;
        this.type = type;
    }

    public String prettyHora() {
        return this.time.format(DateTimeFormatter.ofPattern("hh:mm"));
    }
    public String getSid() {
        return this.sid;
    }
    public LocalDate getDate() {
        return date;
    }
    public LocalTime getTime() {
        return this.time;
    }
    public String getType() {
        return this.type;
    }

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
