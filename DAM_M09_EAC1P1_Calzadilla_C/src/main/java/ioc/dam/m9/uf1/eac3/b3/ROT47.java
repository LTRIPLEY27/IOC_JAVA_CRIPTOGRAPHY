/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ioc.dam.m9.uf1.eac3.b3;

import java.util.Scanner;

/**
 *
 * @author Isabel Calzadilla
 */
public class ROT47 {
    private static Scanner in;
    public static void main(String[] args){
        in = new Scanner(System.in);
        System.out.print("1. Encriptació. Desencriptació \n Tria(1,2): ");
        int opcio = in.nextInt();
        in.nextLine();

        if(opcio == 1){
            System.out.println("---Encriptació---");
            cipherEncryption();

        } else if (opcio == 2){
            System.out.println("---Desencriptació---");
            cipherDecryption();

        } else {
            System.out.println("Tria incorrecta");
        }
    }

    private static void cipherDecryption() {
        System.out.println("Entra missatge: ");
        String missatge = in.nextLine();
        in.nextLine();

        int key = 47; // CONSTANTE DEL CIFRADO
        String decrypText = ""; // STRING A RECIBIR CARACTER A CARACTER
        
        for (int i = 0; i < missatge.length(); i++) {
            int temp = (int) missatge.charAt(i) - key; // UBICACIÓN DEL CHAR EN LA VARIABLE PARA VERIFICAR RESTANDO LA CONSTANTE
            if((int) missatge.charAt(i) == 32){ // VERIFICACIÓN
                decrypText += " ";
            } else if(temp < 32){
                temp += 94;
                decrypText += (char)temp;
            } else {
                decrypText += (char)temp;
            } // if-else
        } // for

        System.out.println("Text Desencriptat: " + decrypText);
    }

    private static void cipherEncryption() {
        
        //IMPLEMENTA
    	System.out.println("Entra missatge: ");
        String missatge = in.nextLine();
        in.nextLine();

        int key = 47;// CONSTANTE DEL CIFRADO
        String encrypText = "";// STRING A RECIBIR CARACTER A CARACTER
        for (int i = 0; i < missatge.length(); i++) {
            int temp = (int)missatge.charAt(i) + key;// UBICACIÓN DEL CHAR EN LA VARIABLE PARA VERIFICAR SUMANDO LA CONSTANTE PARA UBICAR EL CARACTER CIFRADO
            if((int)missatge.charAt(i) == 32){// VERIFICACIÓN
            	encrypText += " ";
            } else if(temp > 126){
                temp -= 94;
                encrypText += (char)temp;
            } else {
            	encrypText += (char)temp;
            } 
        }
        
        System.out.println("Text Encriptat: " + encrypText);
    }
}