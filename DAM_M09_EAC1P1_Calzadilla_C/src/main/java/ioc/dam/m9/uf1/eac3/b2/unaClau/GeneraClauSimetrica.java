package ioc.dam.m9.uf1.eac3.b2.unaClau;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class GeneraClauSimetrica {

    private final SecretKeySpec clauSecreta;

    public SecretKeySpec getClauSecreta() {
        return clauSecreta;
    }

    public GeneraClauSimetrica(int longitud, String algorisme) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
        SecureRandom rnd = new SecureRandom();
        byte[] clau = new byte[longitud];
        rnd.nextBytes(clau);
        clauSecreta = new SecretKeySpec(clau, algorisme);
    }



    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        GeneraClauSimetrica clauSimetrica = new GeneraClauSimetrica(16, "AES");
        clauSimetrica.escriuAFitxer("UnaClau/clauSecreta", clauSimetrica.getClauSecreta().getEncoded());
        System.out.println("Claus simètriques generades!");
    }
    
    
        private void escriuAFitxer(String ruta, byte[] clau) throws IOException {
        File f = new File(ruta);
        f.getParentFile().mkdirs();

        try (FileOutputStream fos = new FileOutputStream(f)) {
            fos.write(clau);
            fos.flush();
        }
    }
}
