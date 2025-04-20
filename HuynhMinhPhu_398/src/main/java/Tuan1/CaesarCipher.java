/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Tuan1;

/**
 *
 * @author THIS PC
 */
public class CaesarCipher {
    
    public static String encrypt (String plain, int key){
        return caesarCipher(plain, key, true);
    }
    
    public static String decrypt (String cipher, int key){
        return caesarCipher(cipher, key, false);
    }
    
    private static String caesarCipher (String P, int K, boolean mode){
        
        StringBuilder result = new StringBuilder();
        int shift = mode ? K : -K;
        
        for (char ch : P.toCharArray()){
            if (Character.isLetter(ch)) {
                
                char base = Character.isUpperCase(ch) ? 'A':'a';
                int offset = (ch - base + shift)% 26;
                if (offset < 0) 
                    offset += 26;
                result.append((char)(base + offset));
            }    
            else
                result.append(ch);
        }
        return result.toString();
    }
}

