/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaeventosentradas;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Vendedor> vendedores = new ArrayList<>();
    private Administrador admin = new Administrador("Admin", "admin@mail.com", "123");
    private RegistroVentas registro = new RegistroVentas();
    private Scanner sc = new Scanner(System.in);

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n==== SISTEMA DE VENTAS ====");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Vendedor");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> menuAdmin();
                case 2 -> menuCliente();
                case 3 -> menuVendedor();
            }
        } while (opcion != 0);
    }

    // ------------------ ADMIN -------------------
    private void menuAdmin() {
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Clave: ");
        String p = sc.nextLine();

        if (!admin.getCorreo().equals(c) || !admin.validarPassword(p)) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        int op;
        do {
            System.out.println("\n=== ADMINISTRADOR ===");
            System.out.println("1. Crear evento");
            System.out.println("2. Eliminar evento");
            System.out.println("3. Ver auditoría");
            System.out.println("4. Generar PDF registro");
            System.out.println("5. Asignar grupo vendedor");
            System.out.println("0. Regresar");
            System.out.print("Seleccione: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearEvento();
                case 2 -> eliminarEvento();
                case 3 -> registro.mostrarAuditoria();
                case 4 -> new PDFGenerator().generarPDFRegistro(registro);
                case 5 -> asignarGrupo();
            }
        } while (op != 0);
    }

    private void crearEvento() {
        if (eventos.size() >= 6) {
            System.out.println("Máximo 6 eventos permitidos.");
            return;
        }

        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Artista: ");
        String a = sc.nextLine();
        System.out.print("Lugar: ");
        String l = sc.nextLine();
        System.out.print("Fecha: ");
        String f = sc.nextLine();
        System.out.print("Precio VIP: ");
        double pv = sc.nextDouble();
        System.out.print("Precio Premium: ");
        double pp = sc.nextDouble();
        System.out.print("Precio General: ");
        double pg = sc.nextDouble();
        sc.nextLine();

        eventos.add(new Evento(n, a, l, f, pv, pp, pg));
        System.out.println("Evento creado.");
    }

    private void eliminarEvento() {
        listarEventos();
        System.out.print("Seleccione número evento: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();

        if (i >= 0 && i < eventos.size()) {
            eventos.remove(i);
            System.out.println("Evento eliminado.");
        } else {
            System.out.println("Inválido.");
        }
    }

    private void asignarGrupo() {
        listarVendedores();
        System.out.print("Vendedor #: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();

        if (i < 0 || i >= vendedores.size()) {
            System.out.println("Inválido.");
            return;
        }

        System.out.print("Grupo (A/B): ");
        String g = sc.nextLine();
        vendedores.get(i).asignarGrupo(g);

        System.out.println("Grupo asignado.");
    }

    // ------------------ CLIENTE -------------------

    private void menuCliente() {
        System.out.println("1. Registrarse");
        System.out.println("2. Comprar entrada");
        System.out.print("Seleccione: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> registrarCliente();
            case 2 -> comprarEntrada();
        }
    }

    private void registrarCliente() {
        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Clave: ");
        String p = sc.nextLine();

        clientes.add(new Cliente(n, c, p));
        System.out.println("Registro exitoso.");
    }

    private Cliente loginCliente() {
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Clave: ");
        String p = sc.nextLine();

        return clientes.stream()
                .filter(x -> x.getCorreo().equals(c) && x.validarPassword(p))
                .findFirst()
                .orElse(null);
    }

    private void comprarEntrada() {
        Cliente cliente = loginCliente();
        if (cliente == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        if (eventos.isEmpty()) {
            System.out.println("No hay eventos.");
            return;
        }

        listarEventos();
        System.out.print("Seleccione evento #: ");
        int i = sc.nextInt() - 1;
        sc.nextLine();

        Evento ev = eventos.get(i);

        System.out.println("1. VIP 2. Premium 3. General");
        int op = sc.nextInt();
        sc.nextLine();

        Nivel nivel;
        double precio;

        switch (op) {
            case 1 -> { nivel = Nivel.VIP; precio = ev.getPrecioVip(); }
            case 2 -> { nivel = Nivel.PREMIUM; precio = ev.getPrecioPremium(); }
            default -> { nivel = Nivel.GENERAL; precio = ev.getPrecioGeneral(); }
        }

        listarVendedores();
        System.out.print("Seleccione vendedor #: ");
        int v = sc.nextInt() - 1;
        sc.nextLine();
        Vendedor vendedor = vendedores.get(v);

        System.out.print("Método de pago: ");
        String mp = sc.nextLine();
        System.out.print("Cuenta origen: ");
        String co = sc.nextLine();

        String frase = "“La música es el alma de este evento”";

        Entrada entrada = new Entrada(cliente, vendedor, ev, nivel, precio, mp, co, frase);
        registro.registrar(entrada);
        vendedor.registrarVenta();

        new PDFGenerator().generarPDFEntrada(entrada);

        System.out.println("Entrada comprada.");
    }

    // ------------------ VENDEDOR -------------------
    private void menuVendedor() {
        System.out.println("1. Registrar vendedor");
        System.out.println("2. Iniciar sesión");
        System.out.print("Seleccione: ");
        int op = sc.nextInt();
        sc.nextLine();

        switch (op) {
            case 1 -> registrarVendedor();
            case 2 -> loginVendedor();
        }
    }

    private void registrarVendedor() {
        System.out.print("Nombre: ");
        String n = sc.nextLine();
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Clave: ");
        String p = sc.nextLine();

        vendedores.add(new Vendedor(n, c, p));
        System.out.println("Registro exitoso.");
    }

    private void loginVendedor() {
        System.out.print("Correo: ");
        String c = sc.nextLine();
        System.out.print("Clave: ");
        String p = sc.nextLine();

        Vendedor v = vendedores.stream()
                .filter(x -> x.getCorreo().equals(c) && x.validarPassword(p))
                .findFirst()
                .orElse(null);

        if (v == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        System.out.println("\nBienvenido " + v.getNombre());
        System.out.println("Grupo: " + v.getGrupo());
        System.out.println("Ventas realizadas: " + v.getTotalVentas());
    }

    // ------------------ UTILIDAD -------------------
    private void listarEventos() {
        System.out.println("\n=== EVENTOS ===");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i).getNombre());
        }
    }

    private void listarVendedores() {
        System.out.println("\n=== VENDEDORES ===");
        for (int i = 0; i < vendedores.size(); i++) {
            System.out.println((i + 1) + ". " + vendedores.get(i).getNombre());
        }
    }

   
}
