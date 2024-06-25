/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ticket_manager.util;

import java.util.Random;

/**
 *
 * @author chien
 */
public class StringUtil {

    public static int getAlphabetPosition(char character) {
        character = Character.toUpperCase(character);
        if (character >= 'A' && character <= 'Z') {
            return character - 'A' + 1;
        }

        return -1;
    }

    public static String genString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }
}
