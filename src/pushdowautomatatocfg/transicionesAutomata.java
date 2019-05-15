/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;

import java.util.Scanner;

/**
 *
 * @author Kris
 */
public class transicionesAutomata {
    public transicionesAutomata(){
        System.out.println("Introduce estado actual del Automata: ");
        this.estadoActual = sc.next();
        System.out.println("Introduce la entrada que acepta el Automata: ");
        this.entrada = sc.next();
        System.out.println("Introduce el elemento en la cima de la pila del Automata: ");
        this.salidaPila = sc.next().charAt(0);
        
        System.out.println("Introduce el estado resultante despues de la transicion: ");
        this.estadoResultante = sc.next();
        
        System.out.println("Introduce el string que entrara en la pila despues de la transicion: ");
        this.entradaPila = sc.next();
    }
    
    //Scanner
    Scanner sc = new Scanner(System.in);
    
    // entradas para la transicion
    public String estadoActual;
    public String entrada;
    public char salidaPila;
    
    // salidas de la transicion
    public String estadoResultante;
    public String entradaPila;

    @Override
    public String toString() {
        return "(" + estadoActual + ", " + entrada + ", " + salidaPila + ") = (" + estadoResultante + ", " + entradaPila + ")\n";
    };
    
    

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public char getSalidaPila() {
        return salidaPila;
    }

    public void setSalidaPila(char salidaPila) {
        this.salidaPila = salidaPila;
    }

    public String getEstadoResultante() {
        return estadoResultante;
    }

    public void setEstadoResultante(String estadoResultante) {
        this.estadoResultante = estadoResultante;
    }

    public String getEntradaPila() {
        return entradaPila;
    }

    public void setEntradaPila(String entradaPila) {
        this.entradaPila = entradaPila;
    }
    
    
}
