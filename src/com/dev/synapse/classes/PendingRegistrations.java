/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.synapse.classes;

/**
 *
 * @author sanukadev
 */
public class PendingRegistrations {

    private final String insName;
    private final String insType;
    private final String insEmail;

    public PendingRegistrations(String insName, String insType, String insEmail) {
        this.insName = insName;
        this.insType = insType;
        this.insEmail = insEmail;
    }

    public String getInsName() {
        return insName;
    }

    public String getInsType() {
        return insType;
    }

    public String getInsEmail() {
        return insEmail;
    }

}
