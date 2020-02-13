package log;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
public class loginInterface {
	
	public static String usuarioLogado;
	
	public static void interfaceLogin() {
		
		JFrame login = new JFrame();
		JLabel usuario = new JLabel("Usuario:");
		JLabel senha = new JLabel("Senha:");
		JTextField usuarioText = new JTextField();
		JPasswordField senhaText = new JPasswordField();
		JButton criar = new JButton("Cria nova conta");
		JButton entrar = new JButton("Entrar");
		JButton ajuda = new JButton("Duvidas!");
		JLabel labelTopoTitulo = new JLabel("Sistema de Cadastro de Produtos - SCP");
		
		login.setSize(550, 280);
		login.setLayout(null);
		
		login.add(usuario);
		login.add(senha);
		login.add(ajuda);
		login.add(usuarioText);
		login.add(senhaText);
		login.add(criar);
		login.add(entrar);
		login.add(labelTopoTitulo);

		usuario.setBounds(30,50,100,25);
		senha.setBounds(30,80,100,30);
		ajuda.setBounds(480,225,52,15);
		usuarioText.setBounds(85,50,200,25);
		senhaText.setBounds(85,85,200,25);
		criar.setBounds(300,50,150,25);
		entrar.setBounds(300,85,150,25);
		labelTopoTitulo.setBounds(100,0,350,50);
		ajuda.setBorder(null);
		ajuda.setForeground(Color.blue);
		labelTopoTitulo.setFont(new Font("Calibri", Font.PLAIN, 20));
	
		criar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!usuarioText.getText().equals("") && !senhaText.getText().equals("")) {
						String usuarioCript = criptografia.encriptar(usuarioText.getText());
						String senhaCript = criptografia.encriptar(senhaText.getText());
						
						loginBD.escArq(usuarioCript,senhaCript);
						usuarioText.setText("");
						senhaText.setText("");
						JOptionPane.showMessageDialog(null, "Cadastro criado com sucesso!");
					}else {
						JOptionPane.showMessageDialog(null, "Nenhum valor inserido!");
					}

					} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		entrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!usuarioText.getText().equals("") && !senhaText.getText().equals("")) {
						String usuarioDEript = criptografia.encriptar(usuarioText.getText());
						String senhaDEript = criptografia.encriptar(senhaText.getText());
						if(loginBD.autenticar(usuarioDEript,senhaDEript)) {
							BaseDados.interfaceGeral.janelaLancador(usuarioText.getText());
							login.dispose();
							usuarioLogado = usuarioText.getText();
						}else {
							JOptionPane.showMessageDialog(null, "Usuario ou senha digitado incorretamente!");
							senhaText.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Campos nao preenchidos!");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		ajuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Insira seu usuario e senha e clique em Criar nova conta para criar um conta "
						+"\n"+ "e em seguida coloque as mesmas informações e clique em Entrar para entrar");				
			}
		});
		
		login.setLocationRelativeTo(null);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setResizable(false);
		login.setVisible(true);
	}
	public static void main(String[] args) {
		loginBD.inicia();
		loginBD.creatArq();
		interfaceLogin();
	}
}
