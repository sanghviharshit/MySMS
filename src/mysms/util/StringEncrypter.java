/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mysms.util;

// -----------------------------------------------------------------------------
// StringEncrypter.java
// -----------------------------------------------------------------------------

/*
 * =============================================================================
 * Copyright (c) 1998-2008 Jeffrey M. Hunter. All rights reserved.
 *
 * All source code and material located at the Internet address of
 * http://www.idevelopment.info is the copyright of Jeffrey M. Hunter and
 * is protected under copyright laws of the United States. This source code may
 * not be hosted on any other site without my express, prior, written
 * permission. Application to host any of the material elsewhere can be made by
 * contacting me at jhunter@idevelopment.info.
 *
 * I have made every effort and taken great care in making sure that the source
 * code and other content included on my web site is technically accurate, but I
 * disclaim any and all responsibility for any loss, damage or destruction of
 * data or any other property which may arise from relying on it. I will in no
 * case be liable for any monetary damages arising from such loss, damage or
 * destruction.
 *
 * As with any code, ensure to test this code in a development environment
 * before attempting to run it in production.
 * =============================================================================
 */

// CIPHER / GENERATORS
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

// KEY SPECIFICATIONS
import java.security.spec.KeySpec;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEParameterSpec;

// EXCEPTIONS
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import org.apache.commons.codec.binary.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * -----------------------------------------------------------------------------
 * The following example implements a class for encrypting and decrypting
 * strings using several Cipher algorithms. The class is created with a key and
 * can be used repeatedly to encrypt and decrypt strings using that key.
 * Some of the more popular algorithms are:
 *      Blowfish
 *      DES
 *      DESede
 *      PBEWithMD5AndDES
 *      PBEWithMD5AndTripleDES
 *      TripleDES
 *
 * @version 1.0
 * @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
 * @author  http://www.idevelopment.info
 * -----------------------------------------------------------------------------
 */
public class StringEncrypter
{
    Cipher ecipher;
    Cipher dcipher;

    /**
     * Constructor used to create this object.  Responsible for setting
     * and initializing this object's encrypter and decrypter Chipher instances
     * given a Pass Phrase and algorithm.
     * @param passPhrase Pass Phrase used to initialize both the encrypter and
     *                   decrypter instances.
     */
    public StringEncrypter()
    {
        String passPhrase = "#t%U4af^m8A";

        // Create encrypter/decrypter class
        //StringEncrypter desEncrypter = new StringEncrypter(passPhrase);
        generateKey(passPhrase);

    }

    public void generateKey(String passPhrase)
    {
        // 8-bytes Salt
        byte[] salt =
        {
            (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32,
            (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03
        };

        // Iteration count
        int iterationCount = 19;

        try
        {

            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            ecipher = Cipher.getInstance(key.getAlgorithm());
            dcipher = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);            
        } catch (InvalidKeySpecException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchPaddingException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (NoSuchAlgorithmException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (InvalidKeyException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Takes a single String as an argument and returns an Encrypted version
     * of that String.
     * @param str String to be encrypted
     * @return <code>String</code> Encrypted version of the provided String
     */
    public String encrypt(String str)
    {
        try
        {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt
            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            return new String(new Base64().encodeBase64(enc), "UTF8");

        } catch (BadPaddingException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (IllegalBlockSizeException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (UnsupportedEncodingException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e)
        {
            Logger.getLogger(StringEncrypter.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    /**
     * Takes a encrypted String as an argument, decrypts and returns the
     * decrypted String.
     * @param str Encrypted String to be decrypted
     * @return <code>String</code> Decrypted version of the provided String
     */
    public String decrypt(String str)
    {

        try
        {
            // Decode base64 to get bytes
            byte[] dec = new Base64().decodeBase64(str.getBytes("UTF8"));

            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            return new String(utf8, "UTF8");

        } catch (BadPaddingException e)
        {
        } catch (IllegalBlockSizeException e)
        {
        } catch (UnsupportedEncodingException e)
        {
        } catch (IOException e)
        {
        }
        return null;
    }    
}