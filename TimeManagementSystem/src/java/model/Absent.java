/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Do Duc Duong
 */
public class Absent {
    Request request;
    String date;
    String reason;
    int acceptType;
    String username;
    String accepter;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(int isAccepted) {
        this.acceptType = isAccepted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccepter() {
        return accepter;
    }

    public void setAccepter(String accepter) {
        this.accepter = accepter;
    }
    
}
