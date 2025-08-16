/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dev.synapse.classes;

/**
 *
 * @author sanukadev
 */
public class BloodInventory {

    private final String bloodType;
    private final String unitsAvailable;

    public BloodInventory(String bloodType, String unitsAvailable) {
        this.bloodType = bloodType;
        this.unitsAvailable = unitsAvailable;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getUnitsAvailable() {
        return unitsAvailable;
    }

}
