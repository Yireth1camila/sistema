/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;



public class Vendedor extends Usuario {

    private String grupo;  // A o B
    private int totalVentas;
    private String horaEntrada;
    private String horaSalida;

    public Vendedor(String nombre, String correo, String password) {
        super(nombre, correo, password);
        this.totalVentas = 0;
        this.grupo = "Sin asignar";
    }

    public void asignarGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getGrupo() { return grupo; }

    public void registrarVenta() {
        totalVentas++;
    }

    public int getTotalVentas() { return totalVentas; }

    public void setHoraEntrada(String horaEntrada) { this.horaEntrada = horaEntrada; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }

    public String getHoraEntrada() { return horaEntrada; }
    public String getHoraSalida() { return horaSalida; }
}
