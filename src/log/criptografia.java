package log;
import java.util.Scanner;
public class criptografia {
	
	   public static String encriptar(String texto){

		  StringBuilder textoCifrado = new StringBuilder();
	      int tamanhoTexto = texto.length();
	      
	      for(int c=0; c < tamanhoTexto; c++){
	         int letraCifradaASCII = ((int) texto.charAt(c)) + 3;
	         
	         while(letraCifradaASCII > 126)
	            letraCifradaASCII -= 94;
	         textoCifrado.append( (char)letraCifradaASCII );
	      }
	      
	      return textoCifrado.toString();
	   }
	   
	   public static String decriptar(String textoCifrado){
	      StringBuilder texto = new StringBuilder();
	      int tamanhoTexto = textoCifrado.length();
	      
	      for(int c=0; c < tamanhoTexto; c++){
	         int letraDecifradaASCII = ((int) textoCifrado.charAt(c)) - 3;
	         
	         while(letraDecifradaASCII < 32)
	            letraDecifradaASCII += 94;
	         texto.append( (char)letraDecifradaASCII );
	      }
	      
	      return texto.toString();
	   }
	   
}