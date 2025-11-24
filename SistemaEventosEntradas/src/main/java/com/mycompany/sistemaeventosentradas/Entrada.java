/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;


public class Entrada {

    private Cliente cliente;
    private Vendedor vendedor;
    private Evento evento;
    private Nivel nivel;
    private double precio;
    private String metodoPago;
    private String cuentaOrigen;
    private String fraseCancion;

    public Entrada(Cliente cliente, Vendedor vendedor, Evento evento, Nivel nivel,
                   double precio, String metodoPago, String cuentaOrigen, String fraseCancion) {

        this.cliente = cliente;
        this.vendedor = vendedor;
        this.evento = evento;
        this.nivel = nivel;
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.cuentaOrigen = cuentaOrigen;
        this.fraseCancion = fraseCancion;
    }

    public Cliente getCliente() { return cliente; }
    public Vendedor getVendedor() { return vendedor; }
    public Evento getEvento() { return evento; }
    public Nivel getNivel() { return nivel; }
    public double getPrecio() { return precio; }
    public String getMetodoPago() { return metodoPago; }
    public String getCuentaOrigen() { return cuentaOrigen; }
    public String getFraseCancion() { return fraseCancion; }
}
