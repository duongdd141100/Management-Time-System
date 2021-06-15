/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author Do Duc Duong
 */
public class WorkTimeReport {
    Date date;
    LocalTime from;
    LocalTime to;
    Time total;
    int acceptType;
    String accepter;

    public int getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(int acceptType) {
        this.acceptType = acceptType;
    }

    public Time getTotal() {
        return total;
    }

    public void setTotal(Time total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }


    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }
    
}
