import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.security.*;

public class Utils {
    /**
     * comprueba que los archivos existen
     * @return
     */
    public static boolean areKeysPresent(){
        //comprueba que los ficheros existen
        File privateKey = new File("private.key");
        File publicKey = new File("public.key");

        if(publicKey.exists() && privateKey.exists()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Crea un KeyPair y los archivos necesarios
     * @return KeyPair
     */
    public static KeyPair generateKey(){
        KeyPair clv = null;
        try {
            //generador de claves
            KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
            //numero de bytes que usara para generar la clave
            gen.initialize(2048);
            //genera las dos claves
            clv = gen.genKeyPair();

            //obtenemos las claves
            PublicKey publicKey = clv.getPublic();
            PrivateKey privateKey = clv.getPrivate();

            //creamos el archivo de clave publico y privado
            File publicKeyFile = new File("public.key");
            publicKeyFile.createNewFile();
            File privateKeyFile = new File("private.key");
            publicKeyFile.createNewFile();

            //Escribimos las claves
            ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            objOutStream.writeObject(publicKey);

            objOutStream = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            objOutStream.writeObject(privateKey);
            objOutStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clv;
    }

    /**
     * obtiene el hash de un archivo en md5
     * @param f
     * @param algoritmo
     * @return hash del archivo
     */
    public static byte[] digestiona(File f, String algoritmo) {
        //obtenemos los bytes del archivo
        byte[] fileBytes = new byte[(int) f.length()];
        byte[] res = null;
        try {
            //digestiona
            MessageDigest md = MessageDigest.getInstance("MD5");
             res = md.digest(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * cifra el hash
     * @param digest
     * @param pk
     * @return digest cifrat
     */
    public static byte[] signar(byte[] digest, PrivateKey pk){
        byte[] res = null;
        try {
            //Utiltiat de cifratge
            Cipher key = Cipher.getInstance("RSA");
            key.init(Cipher.ENCRYPT_MODE, pk);
            res = key.doFinal(digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static void write(){

    }
    public static void read(){

    }
    public static void concatenateByteArrays(){

    }


}
