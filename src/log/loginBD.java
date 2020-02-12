package log;
import java.io.*;

import javax.swing.JOptionPane;
public class loginBD {

	public static void inicia() {
		boolean exists = (new File("bd/medidas.dat")).exists();
		if(!exists) {
			File file = new File("bd");
			file.mkdir();
		}
	}
	
	static File arquivo = new File("bd/login.dat");

	
	public static void creatArq() {
		
		try {
			arquivo.createNewFile();
		}catch(IOException ex) {
			
		}	
	}
	
	public static void escArq(String usuario,String senha) throws IOException {
		
		FileWriter fileWriter = new FileWriter(arquivo,true);
		BufferedWriter escrever = new BufferedWriter(fileWriter);
	
		escrever.write(usuario+","+senha);
		escrever.newLine();
		escrever.close();	
	}
	
	public static boolean autenticar(String usuario,String senha) throws IOException {
		
		FileReader fileReader = new FileReader(arquivo);
		BufferedReader bufferedWriter = new BufferedReader(fileReader);
		boolean confirmacao = false;
		String linha = bufferedWriter.readLine();
		while(linha!=null) {
			String temp[] = linha.split(",");
			if(temp[0].equals(usuario) && temp[1].equals(senha)) {
				
				confirmacao = true;
				break;
			}
			linha = bufferedWriter.readLine();
		}
		bufferedWriter.close();
		fileReader.close();
			return confirmacao;
	}
	
}
