package BaseDados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SingleSelectionModel;


public class sgbd {
	
	
	public static void insere(String descricaoMedida, long codigoMedida, String descricao, float preco, long codigo) throws IOException{
		boolean exists = (new File("bd/produtos.dat")).exists();
		if(exists) {
			FileWriter fw2 = new FileWriter("bd/produtos.dat", true);
			BufferedWriter bw = new BufferedWriter(fw2);
			
			if(consulta(codigo)!=null) { //existe esse codigo ja cadastrado
				JOptionPane.showMessageDialog(null, "Produto com codigo ja existe");
			}else {
				produto Produto = cria(descricaoMedida, codigoMedida, descricao, preco, codigo);
				fw2.write(Produto.getDescricao()+","+Produto.getCodigo()+","+Produto.getPreco()+","+Produto.getDescricaoMedida()+","+Produto.getCodigoMedida());
				JOptionPane.showMessageDialog(null, "Produto Inserido!");
				bw.newLine();
				
				bw.close();
				fw2.close();
			}
		}else {
			File file = new File("bd");
			file.mkdir();
			FileWriter fw2 = new FileWriter("bd/produtos.dat");
			BufferedWriter bw = new BufferedWriter(fw2);
			produto Produto = cria(descricaoMedida, codigoMedida, descricao, preco, codigo);
			fw2.write(Produto.getDescricao()+","+Produto.getCodigo()+","+Produto.getPreco()+","+Produto.getDescricaoMedida()+","+Produto.getCodigoMedida());
			bw.newLine();
			
			
			bw.close();
			fw2.close();
		}
	}
	
	public static void novaMedida(String novaMedidaCodigoString, String novaMedidaDescricao) throws IOException {
		boolean exists = (new File("bd/medidas.dat")).exists();
		if(exists) {
			FileWriter fw2 = new FileWriter("bd/medidas.dat", true);
			BufferedWriter bw = new BufferedWriter(fw2);
			int novaMedidaCodigo = Integer.parseInt(novaMedidaCodigoString);
			fw2.write(novaMedidaCodigo+","+novaMedidaDescricao);
			bw.newLine();
			
			bw.close();
			fw2.close();
		}else {
			File file = new File("bd");
			file.mkdir();
			
			FileWriter fw2 = new FileWriter("bd/medidas.dat");
			BufferedWriter bw = new BufferedWriter(fw2);
			int novaMedidaCodigo = Integer.parseInt(novaMedidaCodigoString);
			fw2.write(novaMedidaCodigo+","+novaMedidaDescricao);
			bw.newLine();
			
			bw.close();
			fw2.close();
		}
	}
	
	public static void atualiza(String descricaoMedida, long codigoMedida, String descricao, float preco, long codigo) throws IOException {
		FileReader fr = new FileReader("bd/produtos.dat");
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> arrayTemp = new ArrayList<>();
		
		String linha = null;
		while((linha = br.readLine()) != null) {
			String temp[] = linha.split(",");
			if(Long.parseLong(temp[1])!=codigo) {
				arrayTemp.add(temp[0]+","+temp[1]+","+temp[2]+","+temp[3]+","+temp[4]);
				continue;
			}else {
				arrayTemp.add(descricao+","+codigo+","+preco+","+descricaoMedida+","+codigoMedida);
				continue;
			}
		}
		br.close();
		fr.close();
		
		
		FileWriter fw2 = new FileWriter("bd/produtos.dat", true);
		fw2.close();
		
		FileWriter fw = new FileWriter("bd/produtos.dat");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < arrayTemp.size(); i++) {
			bw.write(arrayTemp.get(i));
			bw.newLine();
		}
		bw.close();
		fw.close();
	}
	
	public static void deleta(long codigo) throws IOException {
		FileReader fr = new FileReader("bd/produtos.dat");
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> arrayTemp = new ArrayList<>();
		
		String linha = br.readLine();
		while(linha != null) {
			String temp[] = linha.split(",");
			if(Long.parseLong(temp[1])!=codigo) {
				arrayTemp.add(temp[0]+","+temp[1]+","+temp[2]+","+temp[3]+","+temp[4]);
				linha = br.readLine();
				continue;
			}else {
				linha = br.readLine();
				continue;
			}
		}
		
		br.close();
		fr.close();
		
		FileWriter fw2 = new FileWriter("bd/produtos.dat", true);
		fw2.close();
		
		FileWriter fw = new FileWriter("bd/produtos.dat");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 0; i < arrayTemp.size(); i++) {
			bw.write(arrayTemp.get(i));
			bw.newLine();
		}
		bw.close();
		fw.close();
	}
	
	public static String consulta(long codigo) throws IOException {
		FileReader arq = new FileReader("bd/produtos.dat");
		BufferedReader lerArq = new BufferedReader(arq);
	
		String linha = lerArq.readLine();
		
		while(linha!= null) {
			String temp[] = linha.split(",");
			if(Long.parseLong(temp[1])==codigo) {
				return linha;
				
			}else {
				linha = lerArq.readLine();
			}
		}
		lerArq.close();
		arq.close();
		return linha = null;
	}
	
	public static Object[][] consultaNome(String nome) throws IOException {
		FileReader arq = new FileReader("bd/produtos.dat");
		BufferedReader lerArq = new BufferedReader(arq);
		
		String linha = lerArq.readLine();
		ArrayList<String> lista = new ArrayList<>();
		
		while(linha != null) {
			String temp[] = linha.split(",");
			if(temp[0].equals(nome)) {
				
				lista.add(linha);
				linha = lerArq.readLine();
			}else {
				linha = lerArq.readLine();
			}
		}
		
		lerArq.close();
		arq.close();
		
		if(lista.isEmpty()) {
			return null;
		}
		
		String array[] = lista.toArray(new String[lista.size()]);
		String retorno[][] = new String[array.length][5];
		
		
		for (int i = 0; i < retorno.length; i++) {
			for (int j = 0; j < retorno[i].length; j++) {
				retorno[i][j]=array[i].split(",")[j];
			}
		}
		
		return retorno;
	}
	
	public static produto cria(String descricaoMedida, long codigoMedida, String descricao, float preco, long codigo) {
		produto Produto = new produto(descricaoMedida, codigoMedida, descricao, preco, codigo);
		return Produto;
	}
	
	public static Object[][] geraTabela() throws IOException {
		FileReader arq = new FileReader("bd/produtos.dat");
		BufferedReader lerArq = new BufferedReader(arq);
		ArrayList<String> arrayTemp = new ArrayList<>();
				
		String tabela[][] = null;
		String linha = lerArq.readLine();
		int contador=0;
		while(linha != null) {
			arrayTemp.add(linha);
			contador+=1;
			linha=lerArq.readLine();
		}
		
		tabela = new String[contador][5];
		
		for (int i = 0; i < tabela.length; i++) {
			String temp2[] = arrayTemp.get(i).split(",");
			for (int j = 0; j < temp2.length; j++) {
				if(j==3) {
					String temp3[] = temp2[3].split("-");
					tabela[i][j]=temp3[1];
				}else {
					tabela[i][j]=temp2[j];
				}	
			}
		}
		
		Object retorno2[][] = tabela;
		
		lerArq.close();
		arq.close();
		
		return retorno2;
	}
	
	
	public static Object[][] geraTabelaArquivos(){
		File file = new File("bd");
		String arquivos[]=file.list();
		Object[][] retorno = new Object[arquivos.length][1];
		
		for (int i = 0; i < arquivos.length; i++) {
			retorno[i][0]=arquivos[i];
		}
		return retorno;
	}
	
	public static void deletaArquivo(String endereco) {
		File file = new File("bd/"+endereco);
		file.delete();
	}
	
	public static boolean testaVirgula(String descricao) {
		char[] testeString = descricao.toCharArray();
		for (int i = 0; i < testeString.length; i++) {
			if(testeString[i]==',') {
				return true;
			}
		}
		return false;
	}
	
}
