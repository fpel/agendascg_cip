package br.com.getnet.agenda.scg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class teste {

    public static Boolean validaStr2Integer(String s){
    	boolean d = true; 
    	if(!s.equals("")) {
    		for ( int i = 0; i < s.length(); i++ ) { 
    			// verifica se o char não é um dígito  
    			if ( !Character.isDigit( s.charAt(i) ) ) {  
    				d = false;  
    				break;  
    			}  
    		} 
    	} else {
    		return null;
    	}
    	return d;
	}

	public static void main(String[] args) {
		
		
		System.out.println("PARC.LOJISTA IATA 24X".substring(0,8));
		System.out.println("PARC.LOJISTA IATA 24X".substring(13));
		

//		String s1 = "1234";
//		String s2 = "123c34";
//		String s3 = "a12345";
//		String s4 = "1234x";
//		String s5 = "12345O";
//		String s6 = "1234-1";
//		
//		System.out.println("s1 = " + validaStr2Integer(s1));
//		System.out.println("s2 = " + validaStr2Integer(s2));
//		System.out.println("s3 = " + validaStr2Integer(s3));
//		System.out.println("s4 = " + validaStr2Integer(s4));
//		System.out.println("s5 = " + validaStr2Integer(s5));
//		System.out.println("s6 = " + validaStr2Integer(s6));
		
	}

}
