/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jmetal.problems.tesis;

//import matlabcontrol.MatlabConnectionException;
//import matlabcontrol.MatlabInvocationException;
//import matlabcontrol.MatlabProxy;
//import matlabcontrol.MatlabProxyFactory;

/**
 *
 * @author monsemora
 */
public class TM {
    
//    public static void main(String[] args) {
//        try {
//            System.out.println("Ejecutando TestMetricas..");
//            String test = "[e , c , l] = testMetricas('1_1_IM-0001-4001.png',2,4,0.02)";
//            TM.run(test);
//            
//            System.out.println("Ejecutando TestMetricas..");
//            test = "[e , c , l] = testMetricas('1_1_IM-0001-4001.png',4,8,0.02)";
//            TM.run(test);
//            
//            System.out.println("Fin de ejecucion.");
//            proxy.disconnect();
//            
//        } catch (MatlabConnectionException mce) {
//            System.err.println("Error en la conexion con el MATLAB " + mce.getMessage());
//        } catch (MatlabInvocationException mie) {
//            System.err.println("Error en la invocacion de MATLAB " + mie.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error General " + e.getMessage());
//        }
//    }
//
//    public static Metricas run(String comando) throws MatlabInvocationException {
//        Metricas metricas = null;
//        proxy.setVariable("e", 0); 
//        proxy.setVariable("c", 0); 
//        proxy.setVariable("l", 0); 
//        proxy.eval(comando);
//        double entropiaOrig = ((double[]) proxy.getVariable("e"))[0];
//        double entropiaClahe = ((double[]) proxy.getVariable("c"))[0];
//        double ltg = ((double[]) proxy.getVariable("l"))[0];
//        metricas = new Metricas(entropiaOrig, entropiaClahe, ltg);
//        System.out.println(metricas.toString());
//        return metricas;
//    }
}
