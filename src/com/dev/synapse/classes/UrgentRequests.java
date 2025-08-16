/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.synapse.classes;

import java.time.LocalDate;

/**
 *
 * @author sanukadev
 */
public class UrgentRequests {

    private final String bloodType;
    private final String urgencyLevel;
    private final String insName;
    private final String requiredUnits;
    private final LocalDate requestDate;

    public UrgentRequests(String bloodType, String urgencyLevel, String insName, String requiredUnits, LocalDate requestDate) {
        this.bloodType = bloodType;
        this.urgencyLevel = urgencyLevel;
        this.insName = insName;
        this.requiredUnits = requiredUnits;
        this.requestDate = requestDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public String getInsName() {
        return insName;
    }

    public String getRequiredUnits() {
        return requiredUnits;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }
    
    
}
