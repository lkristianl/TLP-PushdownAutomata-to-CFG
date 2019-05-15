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
        this.transiciones = new transicionesAutomata[numeroTransiciones+1];
        for (int i = 0; i < numeroTransiciones; i++)
        {
            this.transiciones[i] = new transicionesAutomata();
        }
        
        // inicializar la clase cfg
        this.gramatica  = new cfg(numeroEstados*(numeroTransiciones), longitudAlfabeto, numeroEstados);
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
        gramatica.estadosGramatica.add("S");
        gramatica.numeroTransicionesS = numeroEstados;
                
        for (int i = 0; i < numeroEstados; i++)
        {
            // Para cada estado  creamos una transicion [q0,#,q']
            auxEstado = "[q0," + "#," + estados[i] + "]";
            gramatica.transicionesS[i] = auxEstado;
            gramatica.estadosGramatica.add(auxEstado);
        }
        // utilizamos este indice para saber donde intruducir cada tranformacion de una transicion
        int indiceAux = 0;
        for (int i = 0; i < numeroTransiciones; i++)
        {        
            // Comprobamos si entra algun elemento en la pila, en caso de que sea $(vacia) entramos en el paso 3 y en otro caso en el paso 2
            if ( "$".equals(transiciones[i].entradaPila) )
            {
                
                //Paso 3 Transformacion de las transiciones con entrada pila '$'/vacia
                // Creamos el nuevo estado y ponemos como transicion la entrada al automata
                auxEstado = "[" + transiciones[i].estadoActual + "," + transiciones[i].salidaPila +"," + transiciones[i].estadoResultante + "]";                
                if (!gramatica.estadosGramatica.contains(auxEstado))
                    gramatica.estadosGramatica.add(auxEstado); 
                gramatica.transiciones[indiceAux].estado = auxEstado;
                gramatica.transiciones[indiceAux].transiciones[0] = transiciones[i].entrada;
                indiceAux++;
            }  else 
            {  
               //Paso 2 Transformacion del la transiciones de AP a GLC
               for (int j = 0; j < numeroEstados; j++)
               {
                   // creamos el nuevo estado no terminal
                   auxEstado = "[" + transiciones[i].estadoActual + "," + transiciones[i].salidaPila + "," + estados[j] + "]";
                   if (!gramatica.estadosGramatica.contains(auxEstado))
                        gramatica.estadosGramatica.add(auxEstado); 
                   gramatica.transiciones[indiceAux].estado = auxEstado ;
                   
                   // Vector auxiliar
                   int[] auxVector = new int [transiciones[i].entradaPila.length()+1];
                   // inicializamos el vector auxiliar y igualamos a 0 cada uno de sus elementos, utilizamos el ultimo elemento del vector para saber si se ha llegado al fin fel recorrido
                   for (int k = 0; k < transiciones[i].entradaPila.length(); k++)
                   {
                       auxVector[k] = 0;
                   }
                   // utilizamos este int auxiliar como indice para saber en cual traniscion estamos
                   int aux = 0;
                   // si el ultimo elemento del vector es 0 este bucle se seguira ejecutando
                   while (auxVector[transiciones[i].entradaPila.length()-1] != 1)
                   {
                       // ejecucion del paso2
                       auxTransicion = transiciones[i].entrada;
                       auxEstado = "[" + transiciones[i].estadoResultante + "," + transiciones[i].entradaPila.charAt(0) + ",";
                      
                       for (int l = 0; l < transiciones[i].entradaPila.length()-1; l++)
                       {
                           auxEstado =  auxEstado + estados[auxVector[l]] + "]"; 
                           if (!gramatica.estadosGramatica.contains(auxEstado))
                                gramatica.estadosGramatica.add(auxEstado); 
                           auxTransicion = auxTransicion + auxEstado;
                           
                           auxEstado = "[" + estados[auxVector[l]] + "," + transiciones[i].entradaPila.charAt(l+1) + ",";
                       }
                       
                       auxEstado = auxEstado + estados[j] + "]";
                       if (!gramatica.estadosGramatica.contains(auxEstado))
                            gramatica.estadosGramatica.add(auxEstado);
                       auxTransicion = auxTransicion + auxEstado;
                       gramatica.transiciones[indiceAux].transiciones[aux] = auxTransicion;
                       aux++;
                      
                       
                       // actualizamos el vector auxiliar, si un elemento se iguala al numero de estados lo igualamos otra vez a 0 y subimos por 1 el valor del elemento con peso mas grande
                       auxVector[0]++; 
                       for (int k = 1; k < transiciones[i].entradaPila.length()+1; k++)
                       {
                           if (auxVector[k-1] == numeroEstados)
                           {
                               auxVector[k-1] = 0;
                               auxVector[k]++;
                           }
                           
                       }
                   }
                   // actualizamos el numero de transiciones que contiene el estado en cuestion de la gramatica
                   gramatica.transiciones[indiceAux].numeroTransiciones = aux;
                   indiceAux++; 
               }
            }  
        }
    }
    
    
    

    
    
    
}
