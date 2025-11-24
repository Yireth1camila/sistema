/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;


public enum Nivel {
    VIP("Rojo"),
    PREMIUM("Azul"),
    GENERAL("Verde");

    private String color;

    Nivel(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
