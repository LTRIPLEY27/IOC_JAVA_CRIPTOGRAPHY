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
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Enumeration;
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
     public static String hashFile(File f) throws Exception {
                
         //IMPLEMENTA
      	String algorithm = "SHA-512";
      	MessageDigest arxiu = MessageDigest.getInstance(algorithm);
     	String letras = "";
     	//RECORRIDO DE LOS ARCHIVOS D ELA CARPETA
     	try {
     		
     		InputStream archivosVerificar = new FileInputStream(f);//RECIBE CADA ARCHIVO Y LO PASA A STREAM PARA PARSEAR
     		byte[] passingFile = new byte[1]; // parsing a bytes

     		int endingFile = -1;
    		int verificaBytes = archivosVerificar.read(passingFile);//VERIFICA LOS BYTES
   
     		while(verificaBytes != endingFile) {//RECORRE TODO EL DOCUMENTO TRANSCRIBIENDOLO A BYTES
     			arxiu.update(passingFile);//DIGEST RECIBE LOS VALORES
     			verificaBytes = archivosVerificar.read(passingFile);
     		}
     		
     		archivosVerificar.close();
     		byte[] traslate = arxiu.digest();//CREAMOS UN ARRAY DE BYTES CON EL VALOR DEL DIGEST
     		letras = Base64.getEncoder().encodeToString(traslate);//TRASPONEMOS A LA BASE64

     	}catch(Exception ex) {
     		
     	}
     	
     	return letras;
    }

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
   
    	File ficha = new File("data");
    	HashMap <String, ArrayList <File>> arxiusPerHash = new HashMap <String, ArrayList<File>>();
    	String hashB64 = "";
 
    	for(File file : ficha.listFiles()) {
    		
    		hashB64 = hashFile(file); //STRING HASH, KEY
    		ArrayList <File> listaHash = new ArrayList <File>();
    		listaHash.add(file);
    		if(arxiusPerHash.get(hashB64) == null) {
    			arxiusPerHash.put(hashB64, listaHash);
    		} else {
    			arxiusPerHash.get(hashB64).add(file);
    		}
    		
    	}
    	
    	informeDuplicats(arxiusPerHash);
    }
    
    /**
     * Es mostraran els arxius que són probablement iguals ( que tenen el mateix hash)
     * agrupats convenientment. 
     * Simplement cal recórrer "arxiusPerHash", i per cada entrada, veurem si la llista
     * conté un o més fitxers. Si només en té 1, vol amb el codi hash i la llista d'arxius que 
     * tenen aquest hash.
     * @param arxiusPerHash 
     */
    
   private static void informeDuplicats(HashMap<String, ArrayList<File>> arxiusPerHash){
        for(String clau : arxiusPerHash.keySet()) {
            if(arxiusPerHash.get(clau).size()>1) {
                System.out.println(">------ Els següents arxius tenen hash idèntic ----------------------------------");
                System.out.println(" HASH:"+ clau);
                for(File f : arxiusPerHash.get(clau)) {
                    System.out.println( "\t>" +f.getName() );
                }
            }
        }

        
    }
}
