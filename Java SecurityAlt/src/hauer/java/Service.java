package hauer.java;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

/**
 * Created by Miriam on 16.10.2016.
 */
public class Service {

    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }

    public static void generatePublicKey() {
        byte[] text = "Das ist keine Nachricht!".getBytes();
        KeyPair keyPair = null;
        try {
            // create public key
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            generator.initialize(1024, random);
            keyPair = generator.generateKeyPair();

            byte[] key = keyPair.getPublic().getEncoded();


            FileOutputStream keyfos = new FileOutputStream("suepk");
            keyfos.write(key);
            keyfos.close();


            /* Store Public Key in Naming Directory */
            LDAPConnector ldapConnector = new LDAPConnector();
            ldapConnector.updateAttribute("cn=group.service1,dc=nodomain,dc=com", "description", toHexString(key));
            System.out.println("key : "+toHexString(key));


            CommunicationServerSocket css = new CommunicationServerSocket(4444);

            css.SocketGet();
            byte[] decryptedSymKey = new byte[1];

            boolean wait = true;
            while(wait) {
                if(css.getMsg() == null){
                    wait = true;
                } else {
                    //byte[] encrypted = css.getMsg().getBytes();
                    byte[] encrypted = toByteArray(css.getMsg());
                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
                    System.out.println("ec : "+toHexString(encrypted));

                    decryptedSymKey = cipher.doFinal(encrypted);

                    System.out.println("dc : "+toHexString(decryptedSymKey));
                    wait = false;
                }
            }

            SecretKeySpec skeySpec = new SecretKeySpec(decryptedSymKey,"DES");
            Cipher Textcipher = Cipher.getInstance("DES");
            Textcipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            System.out.println("Text: "+ new String(text));
            byte[] encrypted = Textcipher.doFinal(text);

            System.out.println("Textencrypted : "+toHexString(encrypted));

            css.SocketSend(toHexString(encrypted));

            // byte[] encrypted = toByteArray(new CommunicationServerSocket(4444).getMsg());

            //   Cipher cipher = Cipher.getInstance("RSA");
            //  cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
            // byte[] decrypted = cipher.doFinal(encrypted);

            //System.out.println(new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.print("whupsi dupsi fehler" + e);
        }
    }
    public static void main(String[] args){
        generatePublicKey();
    }


}
