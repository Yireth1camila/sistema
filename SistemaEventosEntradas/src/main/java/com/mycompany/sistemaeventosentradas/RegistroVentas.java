/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;


import java.util.ArrayList;

public class RegistroVentas {

    private ArrayList<Entrada> ventas = new ArrayList<>();

    public void registrar(Entrada entrada) {
        ventas.add(entrada);
    }

    public ArrayList<Entrada> getVentas() {
        return ventas;
    }

    public void mostrarAuditoria() {
        System.out.println("===== AUDITORÍA DE VENTAS =====");

        for (Entrada e : ventas) {
            System.out.println(
                "\nVendedor: " + e.getVendedor().getNombre() +
                "\nCliente: " + e.getCliente().getNombre() +
                "\nPrecio: " + e.getPrecio() +
                "\nNivel: " + e.getNivel() +
                "\nGrupo del vendedor: " + e.getVendedor().getGrupo()
            );
        }
    }
}
