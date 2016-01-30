package jmetal.problems.tesis;

import java.util.logging.Level;
import jmetal.core.Problem;
import jmetal.core.Solution;
import jmetal.core.Variable;
import jmetal.encodings.solutionType.IntRealSolutionType;
import jmetal.metaheuristics.smpso.SMPSOTesis_main;
//import static jmetal.problems.tesis.TM.proxy;
import jmetal.util.JMException;
//import matlabcontrol.MatlabInvocationException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Marcos Brizuela
 */
public class Tesis extends Problem {

    int cantidadVarInt;
    int cantidadVarReal;
    static int filas;
    static int columnas;
    int[][] imagen;
    //double mediaGlobalOriginal;
    //double psnr;
    String nombreImagen;
    double ssim;
    static Logger log;

    static {
        PropertyConfigurator.configure("logger.properties");
        log = Logger.getLogger(Tesis.class.getName());
//        setDimensionesImagen();

    }

    public Tesis(int cantidadFilas, int cantidadColumnas) {
//        getImagenOriginal();
        cantidadVarInt = 2;
        cantidadVarReal = 1;
        numeroDeVariables = 3;
        numeroDeObjetivos = 2;
        //numeroDeObjetivos = 1;
        nombreProblema = "Tesis";
        upperLimit_ = new double[numeroDeVariables];
        lowerLimit_ = new double[numeroDeVariables];
        lowerLimit_[0] = 2;
        //upperLimit_[0] = filas;
        upperLimit_ [0] = Math.round(cantidadFilas/2);
        lowerLimit_[1] = 2;
        //upperLimit_[1] = columnas;
        upperLimit_[1] = Math.round(cantidadColumnas/2);
        lowerLimit_[2] = 0;
        //upperLimit_[2] = 256;
        upperLimit_[2] = 1;
        tipoSolucion = new IntRealSolutionType(this, 2, 1);
        //mediaGlobalOriginal = getMediaGlobal(imagen,filas,columnas);
    }
//
//    public static void setDimensionesImagen() {
//        try {asfasdfadsfadsfadsf
//
//            String nombreImagen = SMPSOTesis_main.nombreImagen;
//            StringBuilder parametros = new StringBuilder();
//
//            parametros.append(nombreImagen).append(",");
//            //comando.append("[e , c] = getDimensionesImagen(")
//            //        .append("'").append(nombreImagen).append("')");
//            //SMPSOTesis_main.proxy.setVariable("e", 0);
//            //SMPSOTesis_main.proxy.setVariable("c", 0);
//
//            log.info("ejecucion: " + parametros.toString());
//            SMPSOTesis_main.proxy.eval(comando.toString());
//
//            double dimensionx = ((double[]) SMPSOTesis_main.proxy.getVariable("e"))[0];
//            double dimensiony = ((double[]) SMPSOTesis_main.proxy.getVariable("c"))[0];
//
//            log.info("dim x: " + dimensionx);
//            log.info("dim y: " + dimensiony);
//            
//            filas = (int) dimensionx;
//            columnas = (int) dimensiony;
//
//        } catch (MatlabInvocationException ex) {
//            java.util.logging.Logger.getLogger(Problem.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//
//    }

    @Override
    public void evaluate(Solution solution) throws JMException {
//        try {
//            Variable[] variables = solution.getDecisionVariables();
//            double [] fx = new double[3];
//            fx[0] = variables[0].getValue();
//            fx[1] = variables[1].getValue();
//            fx[2] = variables[2].getValue();


            nombreImagen = SMPSOTesis_main.nombreImagen;
            StringBuilder comando = new StringBuilder();

            comando.append(nombreImagen).append(",")
                    .append(solution.getDecisionVariables()[0]).append(",")
                    .append(solution.getDecisionVariables()[1]).append(",")
                    .append(solution.getDecisionVariables()[2]).append("\n")
                    ;
//            comando.append("[e , c , l] = testMetricas(")
//                    .append("'").append(nombreImagen).append("',")
//                    .append(solution.getDecisionVariables()[0]).append(",")
//                    .append(solution.getDecisionVariables()[1]).append(",")
//                    .append(solution.getDecisionVariables()[2]).append(")");
//            SMPSOTesis_main.proxy.setVariable("e", 0);
//            SMPSOTesis_main.proxy.setVariable("c", 0);
//            SMPSOTesis_main.proxy.setVariable("l", 0);
            log.info("ejecucion: " + comando.toString());
            //SMPSOTesis_main.proxy.eval(comando.toString());
            String resultadoSocket = SMPSOTesis_main.canalSocket.enviarMensaje(comando.toString());
            double[] resultados = doDecodeRespuesta(resultadoSocket);

            double entropiaOrig = resultados[0];//((double[]) SMPSOTesis_main.proxy.getVariable("e"))[0];
            double entropiaClahe = resultados[1];//((double[]) SMPSOTesis_main.proxy.getVariable("c"))[0];
            double ltg = resultados[2];//((double[]) SMPSOTesis_main.proxy.getVariable("l"))[0];
            log.info("entropia original: " + entropiaOrig);
            log.info("entropia clahe: " + entropiaClahe);
            log.info("ltg: " + ltg);

            double resul = entropiaClahe / entropiaOrig;
            if (resul <= 1) {
                entropiaClahe = 0;
            } else {
                entropiaClahe = resul;
            }
            solution.setNombreImagenResultado(nombreImagen);
            solution.setObjective(0, entropiaClahe);
            solution.setObjective(1, ltg);

            //        int[][] imagenClahe = getCLAHE((int)fx[0],(int)fx[1],fx[2]);
            //        double entropia = getEntropia(getHistograma(imagenClahe,filas,columnas),filas*columnas);
            //double intensidadMedia = getMediaGlobal(imagenClahe,filas,columnas);
            //double difMedia = Math.abs(mediaGlobalOriginal - intensidadMedia);
            //solution.setIntensidadMedia(intensidadMedia);
//            solution.setNombreImagenResultado(nombreImagen);
//            solution.setObjective(0, entropiaClahe);
            //solution.setObjective(1, difMedia);
//            solution.setObjective(1, ssim);
//        } catch (MatlabInvocationException ex) {
//            log.error(ex.getMessage());
//        }
    }
    /*@Override
     public void evaluate(Solution solution) throws JMException {
     Variable[] variables = solution.getDecisionVariables();
     double [] fx = new double[3];
     fx[0] = variables[0].getValue();
     fx[1] = variables[1].getValue();
     fx[2] = variables[2].getValue();
     int[][] imagenClahe = getCLAHE((int)fx[0],(int)fx[1],fx[2]);
     double entropia = getEntropia(getHistograma(imagenClahe,filas,columnas),filas*columnas);
     solution.setNombreImagenResultado(nombreImagen);
     solution.setObjective(0, entropia*ssim);
     }*/
//    private int[] getHistograma(int[][] imagen, int fila, int columna){
//        int[] histograma = new int[256];
//        for (int i=0; i<256; i++){
//            histograma[i] = 0;
//        }
//        for (int i=0; i<fila; i++){
//            for (int j=0; j<columna; j++){
//                histograma[imagen[i][j]] = histograma[imagen[i][j]]+1;
//            }
//        }
//        return histograma;
//    }
//    private double getEntropia(int[] histograma, int totalPixeles){
//        double entropia = 0;
//        for (int i=0; i<256; i++){
//            double probabilidad = (double) histograma[i]/totalPixeles;
//            if (probabilidad==0) entropia=0;
//            else entropia += -(probabilidad*(Math.log(probabilidad)/Math.log(2)));
//        }
//        return entropia;
//    }
//    private double getMediaGlobal(int[][] imagen, int fila, int columna){
//        double mediaGlobal = 0;
//        for (int i=0; i<fila; i++){
//            for (int j=0; j<columna; j++){
//                mediaGlobal += imagen[i][j];
//            }
//        }
//        return mediaGlobal/(fila*columna);
//    }
//    private void getImagenOriginal(){
//        try{
//            HttpClient client = new DefaultHttpClient();
//            HttpPost post = new HttpPost("http://localhost:8081/json");
//            Gson gson = new Gson();
//            HttpResponse response = client.execute(post);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            Response resp = gson.fromJson(rd, Response.class);
//            imagen = new int[resp.filas][resp.columnas];
//            int index=0;
//            for (int i=0; i<resp.filas; i++){
//                for (int j=0; j<resp.columnas; j++){
//                    imagen[i][j] = resp.valores[index];
//                    index++;
//                }
//            }
//            filas = resp.filas;
//            columnas = resp.columnas;
//        }catch(Exception e){}
//    }
//    private int[][] getCLAHE(int ventanaX, int ventanaY, double clipLimit){
//        try{
//            HttpClient client = new DefaultHttpClient();
//            Request solicitud = new Request(ventanaX, ventanaY, clipLimit);
//            HttpPost post = new HttpPost("http://localhost:8080/json");
//            Gson gson = new Gson();
//            String test = gson.toJson(solicitud, Request.class);
//            StringEntity input = new StringEntity(test);
//            input.setContentType("application/json");
//            post.setEntity(input);
//            HttpResponse response = client.execute(post);
//            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
//            Response resp = gson.fromJson(rd, Response.class);
//            int[][] matrizImagen = new int[resp.filas][resp.columnas];
//            int index=0;
//            for (int i=0; i<resp.filas; i++){
//                for (int j=0; j<resp.columnas; j++){
//                    matrizImagen[i][j] = resp.valores[index];
//                    index++;
//                }
//            }
//            ssim = resp.ssim;
//            nombreImagen = resp.nombreresultado;
//            return matrizImagen;
//        }catch(Exception e){}
//        return null;
//    }

    private double[] doDecodeRespuesta(String resultadoSocket) {
        double [] resultado = new double [3];
        String [] misplit = resultadoSocket.split(",");
        for (int i = 0; i<3; i++){
            resultado[i]= Double.valueOf(misplit[i]);
        }
        
        return resultado;
    }
}