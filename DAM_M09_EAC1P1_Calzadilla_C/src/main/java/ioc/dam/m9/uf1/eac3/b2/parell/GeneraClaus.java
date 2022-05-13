package ioc.dam.m9.uf1.eac3.b2.parell;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneraClaus {
	private final KeyPairGenerator generador;
	private final KeyPair parell;
	private final PrivateKey privada;
	private final PublicKey publica;

	public GeneraClaus(int longitud) throws NoSuchAlgorithmException, NoSuchProviderException {
		generador = KeyPairGenerator.getInstance("RSA");
		generador.initialize(longitud);
                parell = generador.generateKeyPair();
		privada = parell.getPrivate();
		publica = parell.getPublic();
	}

	public PrivateKey getPrivada() {
		return privada;
	}

	public PublicKey getPublica() {
		return publica;
	}

	private void escriuAFitxer(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(key);
                fos.flush();
            }
	}

	public static void main(String[] args)  {
		GeneraClaus gc_Meritxell;
		GeneraClaus gc_Nuria;
		
            try {
                gc_Meritxell = new GeneraClaus(1024);
                gc_Meritxell.escriuAFitxer("ParellClaus/publica_Meritxell", gc_Meritxell.getPublica().getEncoded());
		gc_Meritxell.escriuAFitxer("ParellClaus/privada_Meritxell", gc_Meritxell.getPrivada().getEncoded());
		gc_Nuria = new GeneraClaus(1024);
		gc_Nuria.escriuAFitxer("ParellClaus/publica_Nuria", gc_Nuria.getPublica().getEncoded());
		gc_Nuria.escriuAFitxer("ParellClaus/privada_Nuria", gc_Nuria.getPrivada().getEncoded());
                System.out.println("Claus asimètriques generades!");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(GeneraClaus.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchProviderException ex) {
                Logger.getLogger(GeneraClaus.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GeneraClaus.class.getName()).log(Level.SEVERE, null, ex);
            }
		
                
                
	}
}
