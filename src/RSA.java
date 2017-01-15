import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.security.*;


public class RSA {

    public static final String PRIVATE_KEY_FILE = "private.key";

    public static final String FITXER_PLA = "SignarProva.txt";
    public static final String FITXER_SIGNAT = "SignatProva.txt";

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = null;
        PrivateKey prik = null;

        File f = new File(FITXER_PLA);


        /**
         * Utils areKeysPresents:
         * comprueba que existen los ficheros de la clave pública
         * y clave privada
         */

        if(!Utils.areKeysPresent()){

            /**
             * Si no existe, las general las guarda en el fichero
             * y devuelve el par. Luego nos quedamos con la pública
             */

            keyPair = Utils.generateKey();
            prik = keyPair.getPrivate();
        }else{

            /**
             * Si existen las claves, las leemos del fichero
             * y las guardamos en una variable
             */

            ObjectInputStream inputStream = null;
            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            prik = (PrivateKey) inputStream.readObject();
        }


        /**
         * Aquí llegamos conlas claves privadas.
         * Utils digestiona coge el fichero f,
         * y devuelve el array de bits correspondiente al
         * hash del fichero en MD5
         */
        byte[] digestionat = Utils.digestiona(f,"MD5");

        /**
         * en signar, cogemos la clave privada y el hash
         * y encriptamos el hash
         */
        byte[] encryptdigestionat = Utils.signar(digestionat,prik);

        /**
         * read: passa el fichero a bytes
         * concatenateByteArrays: añadimos al final del fichero los bytes de la firma
         * write: vuelve a guardar los bytes en fichero.
         * **/
        Utils.write(FITXER_SIGNAT,encryptdigestionat);
        System.out.println("Fitxer signat");
    }

}