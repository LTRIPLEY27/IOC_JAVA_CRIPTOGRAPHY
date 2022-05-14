/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b1;

/**
 *
 * @author Isabel Calzadilla
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;


public class ProgramaHash {

    /**
     * Donat un arxiu, aquesta funció calcula el seu hash usant
     * l'algorisme SHA-512. EL hash es transformarà a cadena
     * usant una representació en base 64.
     * 
     * @param f
     * @return La funció retorna el hash en forma de cadena base 64
     * @throws Exception 
     */
   /* public static String hashFile(File f) throws Exception {
                
        //IMPLEMENTA
    	
    }*/

    /**
     * El programa recorrerà tots els arxius de la carpeta "data/copydetector" i
     * per a cada arxiu. :
     * A) n'obtindrà el hash sha-256 en format base 64, cridant
     * al mètode hashFile().
     * B) Desarà una referència de l'arxiu a una estructura de dades
     * "arxiusPerHash", que és una taula de indexada, on per cada
     * clau s'associa una llista d'arxius. 
     *     HashMap<     String,                             ArrayList<File> >
     *             un hash sha-256                     -->  llista d'arxius que tenen aquest hash
     *  Per exemple:
     *   v7lcN1TC6Ae/7nn8uT9d2z9uckGMPWOVFhdAknB6lmA=  --> { a.txt, c.txt, h.txt, k.txt }
     * 
     * Quan es troba un arxiu amb una hash que no existeix a "arxiusPerHash"
     * cal crear una nova llista d'arxius, posar l'arxiu a dins, i desar la llista
     * a la taula arxiusPerHash amb el valor del hash correponent.
     * 
     * Quan es troba un arxiu amb una hash que ja existeix a "arxiusPerHash", 
     * s'agafarà la taula que hi ha a dins, i simplement s'afegirà a la llista 
     * el nou arxiu. NO cal tornar a posar la llista a arxiusPerHash, ja està a dins!
     * 
     * 
     * Finalment, quan haguem recorregut tots els arxius, usarem el mètode 
     * "informeDuplicats" per mostrar la informació dins de "arxiusPerHash"
     * 
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        //IMPLEMENTA
    	String algorithm = "SHA-512";
    	MessageDigest arxiu = MessageDigest.getInstance(algorithm);
    	
    	
    	//RECORRIDO DE LOS ARCHIVOS D ELA CARPETA
    	try {
    		InputStream archivosVerificar = new FileInputStream("C:\\Users\\isabe\\Documents\\GitHub\\IOC_JAVA_CRIPTOGRAPHY\\DAM_M09_EAC1P1_Calzadilla_C\\data");
    		byte[] passingFile = new byte[1024]; // parsing a bytes
    		int endingFile = -1;
    		
    		int verificaBytes = archivosVerificar.read(passingFile);
    		
    		while(verificaBytes != endingFile) {
    			arxiu.update(passingFile);
    			verificaBytes = archivosVerificar.read(passingFile);
    		}
    		
    		archivosVerificar.close();
    		byte[] traslate = arxiu.digest();
    		
    		String letras = "";
    		
    		for(byte x : traslate) {
    			letras += Integer.toHexString((x >> 4) & 0xf);
    			letras += Integer.toHexString(x  & 0xf);
    		}
    		
    		System.out.println("Resum : " + algorithm + " " + letras);
    	}catch(Exception ex) {
    		
    	}
    }
    
    /**
     * Es mostraran els arxius que són probablement iguals ( que tenen el mateix hash)
     * agrupats convenientment. 
     * Simplement cal recórrer "arxiusPerHash", i per cada entrada, veurem si la llista
     * conté un o més fitxers. Si només en té 1, vol dir que no està repetit i l'ignorarem.
     * Si en té més d'1, mostrarem un informe amb el codi hash i la llista d'arxius que 
     * tenen aquest hash.
     * @param arxiusPerHash 
     */
   /* private static void informeDuplicats(HashMap<String, ArrayList<File>> arxiusPerHash){
        for(String clau:arxiusPerHash.keySet()) {
            if(arxiusPerHash.get(clau).size()>1) {
                System.out.println(">------ Els següents arxius tenen hash idèntic ----------------------------------");
                System.out.println(" HASH:"+clau);
                for(File f:arxiusPerHash.get(clau)) {
                    System.out.println( "\t>" +f.getName() );
                }
            }
        }*/

        
    //}
}
