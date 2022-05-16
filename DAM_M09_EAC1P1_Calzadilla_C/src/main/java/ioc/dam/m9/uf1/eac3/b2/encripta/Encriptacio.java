package ioc.dam.m9.uf1.eac3.b2.encripta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;

public class Encriptacio {

    private Cipher cipher;

    public PublicKey getPublica(String fitxer, String algorisme) throws Exception {
        
    	byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytesClau);
        KeyFactory kf = KeyFactory.getInstance(algorisme);
        
        return kf.generatePublic(spec);
    }

    public SecretKeySpec getClauSecreta(String fitxer, String algorisme) throws IOException {
        byte[] bytesClau = Files.readAllBytes(new File(fitxer).toPath());
        
        return new SecretKeySpec(bytesClau, algorisme);
    }

    //----------------------------------------------------------------
    
    public void encriptaDades(File original, File encriptat, SecretKeySpec clauSecreta, String algorismeXifrat) throws IOException, GeneralSecurityException {
        
        //IMPLEMENTAR
    	cipher = Cipher.getInstance(algorismeXifrat); // CIPHER RECIBE EL ALGORITMO YA CIFRADO
    	cipher.init(Cipher.ENCRYPT_MODE, clauSecreta); // CON EL MÉTODO INIT, INICIAMOS LA ENCRIPTACIÓN DE LA CLAVE // solo recibe la clave privada
    	escriuAFitxer(encriptat, cipher.doFinal(fitxerEnBytes(original))); // ENVIAMOS POR BYSTES EL FICHERO
    }

    //--------------------------------------------------------------
    public void encriptaClau(PublicKey clau, File fitxerClauOriginal, File fitxerClauEncriptada, String algorismeXifrat) throws IOException, GeneralSecurityException {
       
        //IMPLEMENTAR
    	
    	cipher = Cipher.getInstance(algorismeXifrat);
    	cipher.init(Cipher.ENCRYPT_MODE, clau);// SOLO RECIBE LA CLAVE PÚBLICA
    	escriuAFitxer(fitxerClauEncriptada, cipher.doFinal(fitxerEnBytes(fitxerClauOriginal)));
    }

    
      
    
    public static void main(String[] args) throws IOException, Exception {
       
    	Encriptacio iniEnc = new Encriptacio();

    	// ENCRIPTACIÓN DE CLAVES
    	
        File fichaOriginal = new File("UnaClau/clauSecreta"); // CREACIÓN D ELA CARPETA Y ARCHIVO
        //Encripta la carta
        File fichaEncriptado = new File("FitxersEncriptats/clauSecreta");
        
        iniEnc.encriptaClau(iniEnc.getPublica("ParellClaus/publica_Meritxell", "RSA"), fichaOriginal, fichaEncriptado, "RSA");// ENCRIPTA CON LOS PARÁMETRO LA CLAVE EN EL FICHERO
        
     // ENCRIPTACIÓN DE ARCHIVOS
        File archivoOriginal = new File("C:\\Users\\isabe\\Documents\\GitHub\\IOC_JAVA_CRIPTOGRAPHY\\DAM_M09_EAC1P1_Calzadilla_C\\confidential.txt");
        File archivoEncriptado = new File("FitxersEncriptats/fitxerEncriptat");
        
        iniEnc.encriptaDades(archivoOriginal, archivoEncriptado, iniEnc.getClauSecreta("UnaClau/clauSecreta", "AES"), "AES");//LUEGO DE INDICAR LOS ARCHIVOS DONDE ALMACENAR LAS CLAVES Y FICHEROS A ENCRIPTAR, LLAMAMOS AL MÉTODO DE CLASE PARA QUE LOS RECIBA POR PARÁMETROS Y GENERE LOS ARCHIVOS Y CLAVES
    }

          // Mètodes auxiliars 
    private void escriuAFitxer(File out, byte[] aEscriure) throws  IOException {
        out.getParentFile().mkdirs();
        try (FileOutputStream fos = new FileOutputStream(out)) {
            fos.write(aEscriure);
            fos.flush();
        }
        System.out.println("El fitxer ha estat guardat a:  " + out.getPath());
    }

    private byte[] fitxerEnBytes(File f) throws IOException {
        byte[] fbytes;
        try (FileInputStream fis = new FileInputStream(f)) {
            fbytes = new byte[(int) f.length()];
            fis.read(fbytes);
        }
        return fbytes;
    }

}
