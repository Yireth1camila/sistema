/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;



public class PDFGenerator {

    public void generarPDFEntrada(Entrada entrada) {
        System.out.println("\n===== GENERANDO PDF - ENTRADA =====");
        System.out.println("Cliente: " + entrada.getCliente().getNombre());
        System.out.println("Vendedor: " + entrada.getVendedor().getNombre());
        System.out.println("Nivel: " + entrada.getNivel() + " (" + entrada.getNivel().getColor() + ")");
        System.out.println("Pago: " + entrada.getMetodoPago());
        System.out.println("Cuenta: " + entrada.getCuentaOrigen());
        System.out.println("Evento: " + entrada.getEvento().getNombre());
        System.out.println("Artista: " + entrada.getEvento().getArtista());
        System.out.println("Lugar: " + entrada.getEvento().getLugar());
        System.out.println("Fecha: " + entrada.getEvento().getFecha());
        System.out.println("Frase: " + entrada.getFraseCancion());
        System.out.println("QR generado: [SIMULADO]");
    }

    public void generarPDFRegistro(RegistroVentas registro) {
        System.out.println("\n===== GENERANDO PDF - REGISTRO COMPLETO =====");
        registro.mostrarAuditoria();
    }
}

