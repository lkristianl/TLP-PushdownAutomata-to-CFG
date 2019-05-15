/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;
import java.io.*; 
import java.util.*; 
import java.util.ArrayList;

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
        
        // inicializar la clase cfg
        this.gramatica  = new cfg(numeroEstados*numeroTransiciones, longitudAlfabeto);
        // copiar el alfabeto de entrada al alfabeto de la GLC ya que es el mismo
        System.arraycopy(this.alfabeto, 0, gramatica.alfabeto, 0, longitudAlfabeto);
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
    
    // GLC resultante
    public cfg gramatica;
    
    
    

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

    // transformacion de Automata con pila
    public void transformAutomata ()
    {
        String auxEstado;
        String auxTransicion;
        // Paso 1 creacion del estado S de la GLC y sus transiciones
        gramatica.transiciones[0].estado = "S";
        gramatica.estadosGramatica.add("S");
        gramatica.transiciones[0].numeroTransiciones = numeroEstados;
        
        
        for (int i = 0; i < numeroEstados; i++)
        {
            
            auxEstado = "[q0," + "#," + estados[i] + "]";
            gramatica.transiciones[0].transiciones[i] = auxEstado;
            gramatica.estadosGramatica.add(auxEstado);
        }
       
        for (int i = 1; i < numeroTransiciones; i++)
        {             
            
            if ( "$".equals(transiciones[i-1].entradaPila) )
            {
                //Paso 3 Transformacion de las transiciones con entrada pila '$'/vacia
                // Creamos el nuevo estado 
                auxEstado = "[" + transiciones[i-1].estadoActual + "," + transiciones[i-1].salidaPila +"," + transiciones[i-1].estadoResultante + "]";                
                if (!gramatica.estadosGramatica.contains(auxEstado))
                    gramatica.estadosGramatica.add(auxEstado); 
                gramatica.transiciones[i].estado = auxEstado;
                gramatica.transiciones[i].transiciones[i-1] = transiciones[i-1].entrada ;
            }  else 
            {  
               //Paso 2 Transformacion del la transiciones de AP a GLC
               for (int j = 0; j < numeroEstados; j++)
               {
                   auxEstado = "[" + transiciones[i-1].estadoActual + "," + transiciones[i-1].salidaPila + "," + estados[j] + "]";
                   if (!gramatica.estadosGramatica.contains(auxEstado))
                        gramatica.estadosGramatica.add(auxEstado); 
                   gramatica.transiciones[i].estado = auxEstado ;
                   
                   // Vector auxiliar
                   int[] auxVector = new int [transiciones[i-1].entradaPila.length()+1];
                   // inicializamos el vector auxiliar y igualamos a 0 cada uno de sus elementos, utilizamos el ultimo elemento del vector para saber si se ha llegado al fin fel recorrido
                   for (int k = 0; k < transiciones[i-1].entradaPila.length(); k++)
                   {
                       auxVector[k] = 0;
                   }
                   int aux = 0;
                   while (auxVector[transiciones[i-1].entradaPila.length()-1] != 1)
                   {
                       // ejecucion del paso2
                       auxTransicion = transiciones[i-1].entrada;
                       auxEstado = "[" + transiciones[i].estadoResultante + "," + transiciones[i].entradaPila.charAt(0) + ",";
                       
                       for (int l = 0; l < transiciones[i-1].entradaPila.length()-1; l++)
                       {
                           auxEstado =  auxEstado + estados[auxVector[l]] + "]"; 
                           if (!gramatica.estadosGramatica.contains(auxEstado))
                                gramatica.estadosGramatica.add(auxEstado); 
                           auxTransicion = auxTransicion + auxEstado;
                           
                           auxEstado = "[" + estados[auxVector[l]] + "," + transiciones[i-1].entradaPila.charAt(l+1) + ",";
                       }
                       
                       auxEstado = auxEstado + estados[j] + "]";
                       if (!gramatica.estadosGramatica.contains(auxEstado))
                            gramatica.estadosGramatica.add(auxEstado);
                       auxTransicion = auxTransicion + auxEstado;
                       gramatica.transiciones[i].transiciones[aux] = auxTransicion;
                       aux++;
                      
                       
                       // actualizamos el vector auxiliar
                       auxVector[0]++; 
                       for (int k = 1; k < transiciones[i-1].entradaPila.length()+1; k++)
                       {
                           if (auxVector[k-1] == numeroEstados)
                           {
                               auxVector[k-1] = 0;
                               auxVector[k]++;
                           }
                           
                       }
                   }
                   gramatica.transiciones[i].numeroTransiciones = aux;
               }
            }
        }
    }
    
    
    

    
    
    
}
