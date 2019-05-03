/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;
import java.io.*; 
import java.util.*; 

/**
 *
 * @author Kris
 */
public class Automata {
    
    public Automata (){
        
        // inicializar el conjunto de estados
        System.out.println("Introduce el numero de los estados del Automata:");
        this.numeroEstados = sc.nextInt();
        this.estados = new String [numeroEstados];
        for(int i = 0; i < numeroEstados; i++){
            this.estados[i] = 'q' + Integer.toString(i);            
        }
        
        // inicializar el Alfabeto del Automata
        System.out.println("Introduce la longitud del Alfabeto de entrada que reconoce el Automata: ");
        this.longitudAlfabeto = sc.nextInt();
        this.alfabeto = new char[longitudAlfabeto];
        System.out.println("Introduce el alfabeto del Automata, uno por uno: ");
        for (int i = 0; i < longitudAlfabeto; i++){
            this.alfabeto[i] = sc.next().charAt(0);
        }
        
        // inicializar transiciones del automata
        System.out.println("Introduce el numero de las funciones de transicion del Automata:");
        this.numeroTransiciones = sc.nextInt();
        this.transiciones = new transicionesAutomata[numeroTransiciones];
        for (int i = 0; i < numeroTransiciones; i++)
        {
            this.transiciones[i] = new transicionesAutomata();
        }
    }
    
    //Scanner para leer las entradas
    Scanner sc = new Scanner(System.in);
    
    //Conj estados del Automata Q
    public int numeroEstados;
    public String[] estados;
    
    //Conj estados finales F
    public int numeroEstadosFinales;
    public String[] estadosFinales = new String [numeroEstadosFinales];
    
    //Alfabeto de entrada Sigma
    public int longitudAlfabeto = 0;
    public char[] alfabeto;
    
    // Pila Gamma y apilamiento del simbolo inicial de la pila z0=#
    public char simboloInicial = '#';
    public Stack<Character> pila = new Stack<>();
    public void incializarPila(){
        this.pila.push(this.simboloInicial);
    };
    
    // funcion de transiciones
    public int numeroTransiciones;
    public transicionesAutomata[] transiciones;

    @Override
    public String toString() {
        String conjEstados;
        conjEstados = "{ q0";
        for (int i = 1; i < numeroEstados; i++)
        {
            conjEstados = conjEstados + ", " + estados[i];
        }
        conjEstados = conjEstados + "}";
        
        String conjAlfabeto;
        conjAlfabeto= "{ ";
        for (int i = 0; i < longitudAlfabeto; i++)
        {
            conjAlfabeto = conjAlfabeto + alfabeto[i] + " ";
        }
        conjAlfabeto = conjAlfabeto + "}";
        
        String conjTransiciones = "";
        for (int i = 0; i < this.numeroTransiciones; i++)
        {
            conjTransiciones = conjTransiciones + "T" + i + this.transiciones[i].toString();
        }
        
        return "Automata{" + "numeroEstados=" + numeroEstados + ", estados=" + conjEstados + ", longitudAlfabeto=" + longitudAlfabeto + ", alfabeto=" + conjAlfabeto
                + ", numeroTransiciones= " + numeroTransiciones + ", Transiciones:\n" + conjTransiciones + "}";
    }

    
    
    // getters y setters
    public int getNumeroEstados() {
        return numeroEstados;
    }

    public void setNumeroEstados(int numeroEstados) {
        this.numeroEstados = numeroEstados;
    }

    public String[] getEstados() {
        return estados;
    }

    public void setEstados(String[] estados) {
        this.estados = estados;
    }

    public int getNumeroEstadosFinales() {
        return numeroEstadosFinales;
    }

    public void setNumeroEstadosFinales(int numeroEstadosFinales) {
        this.numeroEstadosFinales = numeroEstadosFinales;
    }

    public String[] getEstadosFinales() {
        return estadosFinales;
    }

    public void setEstadosFinales(String[] estadosFinales) {
        this.estadosFinales = estadosFinales;
    }

    public int getLongitudAlfabeto() {
        return longitudAlfabeto;
    }

    public void setLongitudAlfabeto(int longitudAlfabeto) {
        this.longitudAlfabeto = longitudAlfabeto;
    }

    public char[] getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(char[] alfabeto) {
        this.alfabeto = alfabeto;
    }

    public char getSimboloInicial() {
        return simboloInicial;
    }

    public void setSimboloInicial(char simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    public Stack<Character> getPila() {
        return pila;
    }

    public void setPila(Stack<Character> pila) {
        this.pila = pila;
    }

    public int getNumeroTransiciones() {
        return numeroTransiciones;
    }

    public void setNumeroTransiciones(int numeroTransiciones) {
        this.numeroTransiciones = numeroTransiciones;
    }

    public transicionesAutomata[] getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(transicionesAutomata[] transiciones) {
        this.transiciones = transiciones;
    }
    
    
    
}
