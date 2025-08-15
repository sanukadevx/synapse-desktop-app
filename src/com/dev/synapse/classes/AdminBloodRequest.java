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
public class AdminBloodRequest {

    private final String bloodType;
    private final String requestStatus;
    private final String insName;
    private final String requiredUnits;
    private final String urgencyLevel;
    private final LocalDate requestDate;

    public AdminBloodRequest(String bloodType, String requestStatus, String urgencyLevel, String insName, String requiredUnits, LocalDate requestDate) {
        this.bloodType = bloodType;
        this.requestStatus = requestStatus;
        this.insName = insName;
        this.requiredUnits = requiredUnits;
        this.urgencyLevel = urgencyLevel;
        this.requestDate = requestDate;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public String getInsName() {
        return insName;
    }

    public String getRequiredUnits() {
        return requiredUnits;
    }

    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }
}
