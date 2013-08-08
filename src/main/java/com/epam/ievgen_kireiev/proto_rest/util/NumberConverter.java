/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.ievgen_kireiev.proto_rest.util;

/**
 *
 * @author GIN
 */
public class NumberConverter {
    
    public int AlienToDecimal(String srcNumber, String srcABC){
        int result=0;
        int multiplier=1;
        for(int i=srcNumber.length()-1; i>=0; --i){
            result+=srcABC.indexOf(srcNumber.charAt(i))*multiplier;
            multiplier*=srcABC.length();
        }
        return result;
    }
    
    public String DecimalToAlien(int srcNumber, String srcABC){
        StringBuilder result=new StringBuilder();
        do{
            result.insert(0,srcABC.charAt(srcNumber%srcABC.length()));
            srcNumber/=srcABC.length();
        }while(srcNumber>0);
        return result.toString();
    }
    
    public String AlienToAlien (String srcNumber, String srcABC, String resultABC){
        return DecimalToAlien(AlienToDecimal(srcNumber, srcABC),resultABC);
    }
    
    
}
