/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pushdowautomatatocfg;

/**
 *
 * @author Kris
 */
public class PushdowAutomataToCFG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Automata AP = new Automata(); 
        System.out.println(AP.toString());
    }
  
    /* para probar si el toString de cfg funciona correctamente
    public void cfgTest()
    {
        cfgtest gramatica = new cfgtest();
        
        gramatica.longitudAlfabeto = 2;
        gramatica.alfabeto[0] = 'a';
        gramatica.alfabeto[1] = 'b';
        
        gramatica.numeroEstadosNoTerminales = 2;
        gramatica.estadosGramatica[0] = "S";
        gramatica.estadosGramatica [1] = "A";
        
        gramatica.transiciones[0].estado = 'S';
        gramatica.transiciones[0].numeroTransiciones = 2;
        gramatica.transiciones[0].transiciones[0] = "aAb";
        gramatica.transiciones[0].transiciones[1] = "ab";
        
        gramatica.transiciones[1].estado = 'A';
        gramatica.transiciones[1].numeroTransiciones = 2;
        gramatica.transiciones[1].transiciones[0] = "aS";
        gramatica.transiciones[1].transiciones[1] = "b";
        
        System.out.println(gramatica.toString());
    }
    */ 
}
