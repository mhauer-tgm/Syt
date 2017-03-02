package hauer.java;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;
import javax.xml.bind.DatatypeConverter;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Miriam on 16.10.2016.
 */
public class Client {
    public static String toHexString(byte[] array) {
        return DatatypeConverter.printHexBinary(array);
    }
    public static byte[] toByteArray(String s) {
        return DatatypeConverter.parseHexBinary(s);
    }
    public static Object getPublicKey( Attributes attrs ) {

        if (attrs == null) {
            return null;
        } else {
        /* Print each attribute */
            try {
                for (NamingEnumeration ae = attrs.getAll(); ae.hasMore(); ) {
                    Attribute attr = (Attribute)ae.next();
                  //  System.out.println( "attribute: " + attr.getID() );

        		/* print each value */
                    for (NamingEnumeration e = attr.getAll(); e.hasMore(); ) {
                        if ( attr.getID().equals( "description" ) )
                            return e.next();
                        e.next();
                    }
                }
            } catch (NamingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void getPublicKeyFromNamingService(){
        /* Read Public Key from Naming Directory */
        LDAPConnector ldapConnector = new LDAPConnector();
        String ldapKey = null;

        try {
            NamingEnumeration listName = ldapConnector.search("dc=nodomain,dc=com", "(&(objectclass=PosixGroup)(cn=group.service1))");
            while (listName.hasMore()) {
                SearchResult sr = (SearchResult) listName.next();
                ldapKey = getPublicKey(sr.getAttributes()).toString();
            }

            System.out.println("ladKey : "+ldapKey);

            byte[] key = toByteArray(ldapKey);
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

            //SYMETERISCHER SCHLÜSSEL OF DOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOM
            SecretKey skey =
                    KeyGenerator.getInstance("DES").generateKey();
            byte[] skeyen = skey.getEncoded();

            System.out.println(new String("skey : "+toHexString(skeyen)));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            byte[] encrypted = cipher.doFinal(skeyen);

            System.out.println(new String("encrypted : "+toHexString(encrypted)));

            CommunicationSocket cs = new CommunicationSocket("localhost",4444,toHexString(encrypted));
            cs.SocketSend();

            cs.SocketGet();
            boolean wait = true;
            while(wait) {
                if(cs.getMsg() == null){
                    System.out.println("hallo ich while cs.getMsg = false");
                    wait = true;
                } else {
                    //byte[] encrypted = css.getMsg().getBytes();
                    System.out.println("hallo ich while cs.getMsg = true");

                    byte[] Textencrypted = toByteArray(cs.getMsg());
                    Cipher Textcipher = Cipher.getInstance("DES");
                    Textcipher.init(Cipher.DECRYPT_MODE, skey);

                    System.out.println("Text ec : "+toHexString(Textencrypted));

                    byte[] decryptedText = Textcipher.doFinal(Textencrypted);

                    System.out.println("Text dc new String : "+new String(decryptedText));
                    System.out.println("Text dc : "+toHexString(decryptedText));
                    wait = false;
                }
            }

            cs.Close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void generateSymKey(){

    }

    public static void main(String[] args){
        getPublicKeyFromNamingService();
    }
}
