/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tuan1;

import java.util.Arrays;

/**
 *
 * @author THIS PC
 */
public class TranspositionCipher {
    public static String encrypt(String text, int[] key){
        int numRows = (int)Math.ceil((double)text.length() / key.length);
        char[][] grid = new char[numRows][key.length];
        for(char[] row : grid) Arrays.fill(row, ' ');
        for(int i = 0; i < text.length(); i++)  grid[i / key.length][i % key.length] = text.charAt(i);
        StringBuilder ciphertext = new StringBuilder();
        for(int k : key){
            for(int row = 0; row < numRows; row++){
                if(grid[row][k - 1] != ' ') ciphertext.append(grid[row][k - 1]);
            }
        }
        return ciphertext.toString();
    }
    public static String decrypt(String text, int[] key){
        int numRows = (int)Math.ceil((double)text.length() / key.length);
        char[][] grid = new char [numRows][key.length];
        for(char[] row : grid)  Arrays.fill(row, ' ');
        int textIndex = 0;
        for(int k : key){
            for(int row = 0; row < numRows; row++){
                if(textIndex < text.length())   grid[row][k - 1] = text.charAt(textIndex++);
            }
        }
        StringBuilder plaintext = new StringBuilder();
        for(int row = 0; row < numRows; row++){
            for(int col = 0; col < key.length; col++){
                if(grid[row][col] != ' ')   plaintext.append(grid[row][col]);
            }
        }
        return plaintext.toString();
    }
}

