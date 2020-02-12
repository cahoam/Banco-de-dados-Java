package BaseDados;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.Random;


public class interfaceGeral {
	
	private static JComboBox<String> comboBoxMedida = new JComboBox<>();
	private static CardLayout cardDireita = new CardLayout();
	private static JPanel panelBaseCardDireita = new JPanel();
	private static int idAutoMedida = 1;
	
	public static void janelaLancador(String usuario) throws IOException {
		JFrame framePrincipal = new JFrame("SCP - WELCOME");
		framePrincipal.setPreferredSize(new Dimension(550, 280));
		
		JLabel labelTopoTitulo = new JLabel("Sistema de Cadastro de Produtos - SCP");
		labelTopoTitulo.setFont(new Font("Calibri", Font.PLAIN, 20));
		labelTopoTitulo.setHorizontalAlignment(JLabel.CENTER);
		
		Calendar data = Calendar.getInstance();
		
		JLabel labelRodapeCreditos = new JLabel(" Usuario: "+usuario+" | Acesso em: "+String.valueOf(data.get(Calendar.HOUR_OF_DAY))+":"+String.valueOf(data.get(Calendar.MINUTE)));
		JLabel labelDescricaoMedida = new JLabel(" Descricao Medida:");
		JLabel labelCodigoMedida = new JLabel(" Quantidade:");
		JLabel labelDescricaoProduto = new JLabel(" Descricao do Produto:");
		JLabel labelCodigoProduto = new JLabel(" Codigo do Produto:");
		JLabel labelPrecoProduto = new JLabel(" Preco do Produto");
		JLabel labelAjuda = new JLabel("<html><center>====Instrucoes de uso====<br> -Use o menu a esquerda para realizar as operacoes com os produtos-<br> -Use ponto ao inves de virgula no preco do produto-<br><br> INSERIR - Cadastra um Novo Produto Na Base de Dados <br> ATUALIZAR -  Atualiza um produto já existente<br> DELETAR - Deleta um produto da base de dados<br> CONSULTAR - Consulta um produto na base de dados<br><br> Contato suporte:<br> calebe.holanda@mail.uft.edu.br<br> willyan.arruda@mail.uft.edu.br</html>");
		JLabel labelResultadoConsulta = new JLabel();
		
		
		atualizaComboBox();
		
		JTextField textDescricaoMedida = new JTextField();
		JTextField textQuantidadeInsere = new JTextField();
		JTextField textDescricaoProduto = new JTextField();
		JTextField textCodigoProduto = new JTextField();
		JTextField textPrecoProduto = new JTextField();
		JTextField textCodigoProdutoAtualiza = new JTextField();
		JTextField textCodigoProdutoDeleta = new JTextField();
		JTextField textCodigoProdutoConsulta = new JTextField();
		JTextField textNovaMedidaCodigo = new JTextField();
		JTextField textNovaMedidaDescricao = new JTextField();
		
		JButton buttonInsere = new JButton("Inserir");
		JButton buttonAtualiza = new JButton("Atualizar");
		JButton buttonDeleta = new JButton("Deletar");
		JButton buttonConsulta = new JButton("Consultar");
		JButton buttonInicio = new JButton("Logoff");
		JButton buttonAtualizarOk = new JButton("Ok");
		JButton buttonDeletaOk = new JButton("Ok");
		JButton buttonInsereOk = new JButton("Ok");
		JButton buttonConsultaOk = new JButton("Ok");
		JButton buttonInsereLimpar = new JButton("Limpar");
		JButton buttonNovaMedidaOk = new JButton("Ok");
		JButton buttonAtualizaInsere = new JButton("ok");
		JButton buttonExclusao = new JButton("Exclusao");
		JButton buttonExclusaoOk = new JButton("Ok");
		
		
		
		JComboBox<String> comboBoxConsulta = new JComboBox<String>();
		comboBoxConsulta.addItem("1-Consulta por Nome");
		comboBoxConsulta.addItem("2-Consulta por codigo");
		comboBoxConsulta.addItem("3-Tabela de Produtos");
		
		Random random = new Random();
		
		JPanel panelEsquerdaOptions = new JPanel();
		JPanel panelInsere = new JPanel();
		JPanel panelInsereCima = new JPanel();
		
		JPanel panelAtualizaBaixo = new JPanel();
		panelAtualizaBaixo.setLayout(new GridLayout(2, 3));
		panelAtualizaBaixo.add(new JLabel(""));
		panelAtualizaBaixo.add(buttonAtualizarOk);
		panelAtualizaBaixo.add(new JLabel(""));
		panelAtualizaBaixo.add(new JLabel(""));
		panelAtualizaBaixo.add(new JLabel(""));
		panelAtualizaBaixo.add(new JLabel(""));
		
		JPanel panelAtualizaCima = new JPanel();
		panelAtualizaCima.setLayout(new GridLayout(2,2));
		panelAtualizaCima.add(new JLabel(""));
		panelAtualizaCima.add(new JLabel(""));
		panelAtualizaCima.add(new JLabel(" Digite o codigo do produto: "));
		panelAtualizaCima.add(textCodigoProdutoAtualiza);
		
		JPanel panelAtualiza = new JPanel();
		panelAtualiza.setLayout(new GridLayout(2, 1));
		panelAtualiza.add(panelAtualizaCima);
		panelAtualiza.add(panelAtualizaBaixo);
		
		JPanel panelDeletaCima = new JPanel();
		panelDeletaCima.setLayout(new GridLayout(2,2));
		panelDeletaCima.add(new JLabel(""));
		panelDeletaCima.add(new JLabel(""));
		panelDeletaCima.add(new JLabel(" Digite o codigo do produto: "));
		panelDeletaCima.add(textCodigoProdutoDeleta);
		
		JPanel panelDeletaBaixo = new JPanel();
		panelDeletaBaixo.setLayout(new GridLayout(2, 3));
		panelDeletaBaixo.add(new JLabel(""));
		panelDeletaBaixo.add(buttonDeletaOk);
		panelDeletaBaixo.add(new JLabel(""));
		panelDeletaBaixo.add(new JLabel(""));
		panelDeletaBaixo.add(new JLabel(""));
		panelDeletaBaixo.add(new JLabel(""));
		
		JPanel panelDeleta = new JPanel();
		panelDeleta.setLayout(new GridLayout(2, 1));
		panelDeleta.add(panelDeletaCima);
		panelDeleta.add(panelDeletaBaixo);
		
		//---------------------------PAINEL CONSULTA---------------------------//
		JPanel panelConsultaCima = new JPanel();
		panelConsultaCima.setLayout(new GridLayout(2,2));
		panelConsultaCima.add(new JLabel(""));
		panelConsultaCima.add(new JLabel(""));
		panelConsultaCima.add(comboBoxConsulta);
		panelConsultaCima.add(textCodigoProdutoConsulta);
		
		
		JPanel panelConsultaButton = new JPanel();
		panelConsultaButton.setLayout(new GridLayout(1, 3));
		panelConsultaButton.add(new JLabel(""));
		panelConsultaButton.add(buttonConsultaOk);
		panelConsultaButton.add(new JLabel(""));
		
		JPanel panelConsultaResultado = new JPanel();
		panelConsultaResultado.setLayout(new GridLayout(1, 1));
		panelConsultaResultado.add(labelResultadoConsulta);
		
		JPanel panelConsultaBaixo = new JPanel();
		panelConsultaBaixo.setLayout(new GridLayout(2, 1));
		panelConsultaBaixo.add(panelConsultaButton);
		
		
		JPanel panelConsulta = new JPanel();
		panelConsulta.setLayout(new GridLayout(2, 1));
		panelConsulta.add(panelConsultaCima);
		panelConsulta.add(panelConsultaBaixo);
		
		//---------------------------NOVA MEDIDA---------------------------//
		JPanel panelNovaMedidaCima = new JPanel();
		panelNovaMedidaCima.setLayout(new GridLayout(2,2));
		panelNovaMedidaCima.add(new JLabel(" Digite o codigo da medida: "));
		try {
			textNovaMedidaCodigo.setText(String.valueOf(Integer.parseInt(comboBoxMedida.getItemAt(comboBoxMedida.getItemCount()-1).split("-")[0])+1));
		} catch (NullPointerException e) {
			textNovaMedidaCodigo.setText("1");
		}
		panelNovaMedidaCima.add(textNovaMedidaCodigo);
		panelNovaMedidaCima.add(new JLabel(" Digite a descricao da medida: "));
		panelNovaMedidaCima.add(textNovaMedidaDescricao);
		
				
				
		JPanel panelNovaMedidaBaixo = new JPanel();
		panelNovaMedidaBaixo.setLayout(new GridLayout(2, 3));
		panelNovaMedidaBaixo.add(new JLabel(""));
		panelNovaMedidaBaixo.add(buttonNovaMedidaOk);
		panelNovaMedidaBaixo.add(new JLabel(""));
		panelNovaMedidaBaixo.add(new JLabel(""));
		panelNovaMedidaBaixo.add(new JLabel(""));
		panelNovaMedidaBaixo.add(new JLabel(""));
				
		JPanel panelNovaMedida = new JPanel();
		panelNovaMedida.setLayout(new GridLayout(2, 1));
		panelNovaMedida.add(panelNovaMedidaCima);
		panelNovaMedida.add(panelNovaMedidaBaixo);
		
		//----------------------------PAINEL AJUDA-----------------------------//
		JPanel panelAjuda = new JPanel();
		labelAjuda.setHorizontalAlignment(JLabel.CENTER);
		labelAjuda.setHorizontalTextPosition(SwingConstants.CENTER);
		panelAjuda.add(labelAjuda);
		
		
		panelEsquerdaOptions.setLayout(new GridLayout(6, 1));
		panelEsquerdaOptions.add(buttonInsere);
		panelEsquerdaOptions.add(buttonAtualiza);
		panelEsquerdaOptions.add(buttonConsulta);
		panelEsquerdaOptions.add(buttonDeleta);
		panelEsquerdaOptions.add(buttonExclusao);
		panelEsquerdaOptions.add(buttonInicio);
		
		
		JPanel panelButtonInsereCima = new JPanel();
		panelButtonInsereCima.setLayout(new GridLayout(1,2));
		panelButtonInsereCima.add(buttonInsereLimpar);
		panelButtonInsereCima.add(buttonInsereOk);
		
		JPanel panelComboBoxButtonInsere = new JPanel();
		panelComboBoxButtonInsere.setLayout(new GridLayout(1, 2));
		panelComboBoxButtonInsere.add(comboBoxMedida);
		JButton buttonNovaMedida = new JButton("Nova Medida");
		panelComboBoxButtonInsere.add(buttonNovaMedida);
		
		
		//--------------------------------------------------------------------//
		panelInsere.setLayout(new GridLayout(1,1));
		panelInsereCima.setLayout(new GridLayout(6,2));
		panelInsereCima.add(labelDescricaoMedida);
		panelInsereCima.add(panelComboBoxButtonInsere);
		panelInsereCima.add(labelCodigoMedida);
		panelInsereCima.add(textQuantidadeInsere);
		panelInsereCima.add(labelDescricaoProduto);
		panelInsereCima.add(textDescricaoProduto);
		panelInsereCima.add(labelCodigoProduto);
		panelInsereCima.add(textCodigoProduto);
		panelInsereCima.add(labelCodigoProduto);
		panelInsereCima.add(textCodigoProduto);
		panelInsereCima.add(labelPrecoProduto);
		panelInsereCima.add(textPrecoProduto);
		panelInsereCima.add(new JLabel(""));
		panelInsereCima.add(panelButtonInsereCima);
		panelInsere.add(panelInsereCima);
		
		
		
		//-----------------------CONFIGURACAO DO CARD LAYOUT----------------------------//
		panelBaseCardDireita.setLayout(cardDireita);
		panelBaseCardDireita.add(panelInsere, "Painel insere");
		panelBaseCardDireita.add(panelAjuda, "Painel ajuda");
		panelBaseCardDireita.add(panelAtualiza, "Painel atualiza");
		panelBaseCardDireita.add(panelDeleta, "Painel deleta");
		panelBaseCardDireita.add(panelConsulta, "Painel consulta");
		panelBaseCardDireita.add(panelNovaMedida, "Painel medida");
		cardDireita.show(panelBaseCardDireita, "Painel ajuda");
		
		
		//---------------------CONFIGURACAO DOS BOTOES----------------------------------//
		
		buttonExclusao.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonInsere.setBackground(null);
				buttonAtualiza.setBackground(null);
				buttonDeleta.setBackground(null);
				buttonConsulta.setBackground(null);
				buttonInicio.setBackground(null);
				buttonExclusao.setBackground(new Color(50,250,50));
				excluiArq();
				
			}
		});
		buttonInsere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonInsere.setBackground(new Color(50,250,50));
				buttonAtualiza.setBackground(null);
				buttonDeleta.setBackground(null);
				buttonConsulta.setBackground(null);
				buttonInicio.setBackground(null);
				buttonExclusao.setBackground(null);
				panelButtonInsereCima.removeAll();
				panelButtonInsereCima.validate();
				panelButtonInsereCima.add(buttonInsereLimpar);
				panelButtonInsereCima.add(buttonInsereOk);
				panelButtonInsereCima.validate();
				textCodigoProduto.setText(Integer.toString(random.nextInt(10000)));
				textDescricaoProduto.setText("");
				textPrecoProduto.setText("");
				textQuantidadeInsere.setText("");
				try {
					atualizaComboBox();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				cardDireita.show(panelBaseCardDireita, "Painel insere");
				
			}
			
		});
		buttonInsereOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(sgbd.testaVirgula(textDescricaoProduto.getText())){
						JOptionPane.showMessageDialog(null, "Nunca utilize virgulas");
						textDescricaoProduto.setText("");
						textCodigoProduto.setText(Integer.toString(random.nextInt(10000)));
						textPrecoProduto.setText("");
						textQuantidadeInsere.setText("");
					}else {
						if(textQuantidadeInsere.getText().equals("") || textDescricaoProduto.getText().equals("") || textPrecoProduto.getText().equals("") || textCodigoProduto.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Campos nao preenchidos!");
						}else {
							sgbd.insere(comboBoxMedida.getSelectedItem().toString(), Long.parseLong(textQuantidadeInsere.getText()), textDescricaoProduto.getText(), Float.parseFloat(textPrecoProduto.getText()), Long.parseLong(textCodigoProduto.getText()));
							textDescricaoProduto.setText("");
							textCodigoProduto.setText(Integer.toString(random.nextInt(10000)));
							textPrecoProduto.setText("");
							textQuantidadeInsere.setText("");
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Entradas inválidas");
				} catch(IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro IOException, Comunicar suporte");
					textDescricaoProduto.setText("");
					textPrecoProduto.setText("");
					textQuantidadeInsere.setText("");
				}
				
			}
		});
		
		buttonInicio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonInsere.setBackground(null);
				buttonAtualiza.setBackground(null);
				buttonDeleta.setBackground(null);
				buttonConsulta.setBackground(null);
				buttonExclusao.setBackground(null);
				buttonInicio.setBackground(new Color(50,250,50));
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Logoff - SCP", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					framePrincipal.dispose();
					try {
						launcher.main(null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (resposta == JOptionPane.NO_OPTION) {
				   cardDireita.show(panelBaseCardDireita, "Painel ajuda");
				}
			}
		});
		
		buttonAtualiza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonInsere.setBackground(null);
				buttonAtualiza.setBackground(new Color(50,250,50));
				buttonDeleta.setBackground(null);
				buttonConsulta.setBackground(null);
				buttonInicio.setBackground(null);
				buttonExclusao.setBackground(null);
				cardDireita.show(panelBaseCardDireita, "Painel atualiza");
				
			}
		});
		
		buttonDeleta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buttonInsere.setBackground(null);
				buttonAtualiza.setBackground(null);
				buttonDeleta.setBackground(new Color(50,250,50));
				buttonConsulta.setBackground(null);
				buttonInicio.setBackground(null);
				buttonExclusao.setBackground(null);
				cardDireita.show(panelBaseCardDireita, "Painel deleta");
				
			}
		});
		
		buttonConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxConsulta.setSelectedIndex(0);
				textCodigoProdutoConsulta.setEnabled(true);
				textCodigoProdutoConsulta.setText("");
				buttonInsere.setBackground(null);
				buttonAtualiza.setBackground(null);
				buttonDeleta.setBackground(null);
				buttonConsulta.setBackground(new Color(50,250,50));
				buttonInicio.setBackground(null);
				buttonExclusao.setBackground(null);
				cardDireita.show(panelBaseCardDireita, "Painel consulta");
				
			}
		});
		
		buttonInsereLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textDescricaoProduto.setText("");
				textCodigoProduto.setText("");
				textPrecoProduto.setText("");
				textQuantidadeInsere.setText("");
				
			}
		});
		
		buttonNovaMedida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardDireita.show(panelBaseCardDireita, "Painel medida");
				
			}
		});
		
		buttonNovaMedidaOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sgbd.novaMedida(textNovaMedidaCodigo.getText(), textNovaMedidaDescricao.getText());
					atualizaComboBox();
					textNovaMedidaCodigo.setText(String.valueOf(Integer.parseInt(comboBoxMedida.getItemAt(comboBoxMedida.getItemCount()-1).split("-")[0])+1));
					textNovaMedidaDescricao.setText("");
					JOptionPane.showMessageDialog(null, "Nova medida inserida!");
					cardDireita.show(panelBaseCardDireita, "Painel insere");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Erro IOException, Comunicar suporte");
					textNovaMedidaDescricao.setText("");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Entradas invalidas");
					textNovaMedidaDescricao.setText("");
				}
			}
		});
		
		buttonConsultaOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String tempVerifica[] = comboBoxConsulta.getSelectedItem().toString().split("-");
					DefaultTableCellRenderer centralizado = new DefaultTableCellRenderer();
					centralizado.setHorizontalAlignment(SwingConstants.CENTER); 
					if(tempVerifica[0].equals("1")) {
						Object temp[][] = sgbd.consultaNome(textCodigoProdutoConsulta.getText());
						if(temp==null) {
							JOptionPane.showMessageDialog(null, "Produto Nao Encontrado!");
						}else {
							String nomesTabela[] = {"Produto", "Codigo", "Preco", "Medida", "Quantidade"};
							JTable tabelaConsulta = new JTable(temp, nomesTabela);
							
							for (int i = 0; i < 5; i++) {
								tabelaConsulta.getColumnModel().getColumn(i).setPreferredWidth(50);
								tabelaConsulta.getColumnModel().getColumn(i).setCellRenderer(centralizado);
							}
							tabelaConsulta.getTableHeader().setResizingAllowed(false); 
							tabelaConsulta.getTableHeader().setReorderingAllowed(false); 
							tabelaConsulta.getTableHeader().setEnabled(false);
							tabelaConsulta.setEnabled(false); 	
							JScrollPane scrollTabelaConsulta = new JScrollPane(tabelaConsulta);
							scrollTabelaConsulta.setPreferredSize(new Dimension(400, 100));
							
							JOptionPane.showMessageDialog(null, scrollTabelaConsulta);
						}
						textCodigoProdutoConsulta.setText("");
					}else if(tempVerifica[0].equals("2")) {
						String temp[] = sgbd.consulta(Long.parseLong(textCodigoProdutoConsulta.getText())).split(",");
						if(temp.equals(null)) {
							JOptionPane.showMessageDialog(null, "Produto Nao Encontrado!");
						}else {
							String temp2[] = temp[3].split("-");
							JOptionPane.showMessageDialog(null, "Descricao: "+temp[0]+"\n"+"Codigo: "+temp[1]+"\n"+"Preco: "+temp[2]+"\n"+"Descr. Quant.: "+temp2[1]+"\n"+"Quantidade: "+temp[4]);
						}
						textCodigoProdutoConsulta.setText("");
					}else if(tempVerifica[0].equals("3")) {
						String nomesTabela[] = {"Produto", "Codigo", "Preco", "Medida", "Quantidade"};
						JTable tabelaProdutos = new JTable(sgbd.geraTabela(), nomesTabela);
						JScrollPane scrollTabela = new JScrollPane(tabelaProdutos);
						scrollTabela.setPreferredSize(new Dimension(400, 190));
						JPanel panelTabela = new JPanel();
						panelTabela.add(scrollTabela);
						
						for (int i = 0; i < 5; i++) {
							tabelaProdutos.getColumnModel().getColumn(i).setPreferredWidth(50);
							tabelaProdutos.getColumnModel().getColumn(i).setCellRenderer(centralizado);
						}
		
						tabelaProdutos.getTableHeader().setResizingAllowed(false); 
						tabelaProdutos.getTableHeader().setReorderingAllowed(false); 
						tabelaProdutos.getTableHeader().setEnabled(false);
						tabelaProdutos.setEnabled(false);
						
						panelBaseCardDireita.add(panelTabela, "Painel tabela");
						cardDireita.show(panelBaseCardDireita, "Painel tabela");
						
					}
					
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Formato Invalido!");
					textCodigoProdutoConsulta.setText("");
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Produto Nao Encontrado!");
					textCodigoProdutoConsulta.setText("");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Arquivo inexistente, inserir para criar");
					textCodigoProdutoConsulta.setText("");
				}
			}
		});
		
		buttonDeletaOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(sgbd.consulta(Long.parseLong(textCodigoProdutoDeleta.getText()))!=null){
						sgbd.deleta(Long.parseLong(textCodigoProdutoDeleta.getText()));
						JOptionPane.showMessageDialog(null, "Removido com sucesso!");
					}else {
						JOptionPane.showMessageDialog(null, "Arquivo nao encontrado!");
					}
					textCodigoProdutoDeleta.setText("");
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Formato invalido!");
					textCodigoProdutoDeleta.setText("");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Arquivo inexistente, inserir para criar");
					textCodigoProdutoDeleta.setText("");
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Produto nao encontrado");
					textCodigoProdutoDeleta.setText("");
				}
			}
		});
		
		buttonAtualizarOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						String temp = sgbd.consulta(Long.parseLong(textCodigoProdutoAtualiza.getText()));
						String temp2[] = temp.split(",");

						panelButtonInsereCima.removeAll();
						panelButtonInsereCima.validate();
						panelButtonInsereCima.add(buttonInsereLimpar);
						panelButtonInsereCima.add(buttonAtualizaInsere);
						textDescricaoProduto.setText(temp2[0]);
						textCodigoProduto.setText(temp2[1]);
						textPrecoProduto.setText(temp2[2]);
						comboBoxConsulta.setSelectedItem(temp2[3]);
						textQuantidadeInsere.setText(temp2[4]);
						cardDireita.show(panelBaseCardDireita, "Painel insere");
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Formato invalido!");
						textCodigoProdutoAtualiza.setText("");
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Arquivo inexistente, inserir para criar");
						textCodigoProdutoAtualiza.setText("");
					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "Produto nao encontrado");
						textCodigoProdutoAtualiza.setText("");
					}
					
			}
		});
		
		
		buttonAtualizaInsere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					sgbd.atualiza(comboBoxMedida.getSelectedItem().toString(), Long.parseLong(textQuantidadeInsere.getText()), textDescricaoProduto.getText(), Float.parseFloat(textPrecoProduto.getText()), Long.parseLong(textCodigoProduto.getText()));
					cardDireita.show(panelBaseCardDireita, "Painel atualiza");
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
					textCodigoProdutoAtualiza.setText("");
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		comboBoxConsulta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempVerifica[] = comboBoxConsulta.getSelectedItem().toString().split("-");
				if(tempVerifica[0].equals("3")) {
					textCodigoProdutoConsulta.setEnabled(false);
					textCodigoProdutoConsulta.setText("Clique em Ok para exibir");
				}else {
					textCodigoProdutoConsulta.setEnabled(true);
				}
				
			}
		});
		
		atualizaComboBox();
		framePrincipal.setLayout(new BorderLayout());
		framePrincipal.add(labelTopoTitulo, BorderLayout.NORTH);
		framePrincipal.add(panelEsquerdaOptions, BorderLayout.WEST);
		framePrincipal.add(labelRodapeCreditos, BorderLayout.SOUTH);
		framePrincipal.add(panelBaseCardDireita, BorderLayout.CENTER);
		
		
		framePrincipal.pack();
		framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		framePrincipal.setVisible(true);
		framePrincipal.setLocationRelativeTo(null);
		framePrincipal.setResizable(false);
	}
	public static void atualizaComboBox() throws IOException {
		boolean exists = (new File("bd/medidas.dat")).exists();
		if(exists) {
			FileReader arq = new FileReader("bd/medidas.dat");
			BufferedReader lerArq = new BufferedReader(arq);
			comboBoxMedida.removeAllItems();
			String linha = lerArq.readLine();
			
			while(linha != null) {
				String temp[] = linha.split(",");
				comboBoxMedida.addItem(temp[0]+"-"+temp[1]);
				linha = lerArq.readLine();
			}
			lerArq.close();
			arq.close();
		}else {
			comboBoxMedida.removeAllItems();
		}
	}
	public static void excluiArq() {
		String tituloTabelaExclusao[] = {"ARQUIVOS"};
		JTable tabelaExclusao = new JTable(sgbd.geraTabelaArquivos(), tituloTabelaExclusao);
		tabelaExclusao.getTableHeader().setResizingAllowed(false); 
		tabelaExclusao.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane ScrollTabelaExclusao = new JScrollPane(tabelaExclusao);
		ScrollTabelaExclusao.setPreferredSize(new Dimension(400, 60));
		JPanel panelExclusaoTabela = new JPanel();
		JLabel labelTituloExclusao = new JLabel("Selecione os arquivos que Deseja Deletar");
		labelTituloExclusao.setHorizontalAlignment(SwingConstants.CENTER);
		panelExclusaoTabela.add(ScrollTabelaExclusao);
		JButton buttonExclusaoOk = new JButton("Ok");
		
		JPanel panelExclusaoBaixo = new JPanel();
		panelExclusaoBaixo.setLayout(new GridLayout(1, 3));
		panelExclusaoBaixo.add(new JLabel());
		panelExclusaoBaixo.add(buttonExclusaoOk);
		panelExclusaoBaixo.add(new JLabel());
		
		JPanel panelExclusao = new JPanel();
		panelExclusao.setLayout(new GridLayout(3, 1));
		panelExclusao.add(labelTituloExclusao);
		panelExclusao.add(panelExclusaoTabela);
		panelExclusao.add(panelExclusaoBaixo);
		panelBaseCardDireita.add(panelExclusao, "Painel exclusao");
		cardDireita.show(panelBaseCardDireita, "Painel exclusao");
		
		buttonExclusaoOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o arquivo?", "Confirmacao de Exclusao - SCP", JOptionPane.YES_NO_OPTION);

				if (resposta == JOptionPane.YES_OPTION) {
					int[] linhas = tabelaExclusao.getSelectedRows();
					for (int i = 0; i < linhas.length; i++) {
						sgbd.deletaArquivo((String) tabelaExclusao.getValueAt(linhas[i], 0));
					}
					excluiArq();
				} else if (resposta == JOptionPane.NO_OPTION) {
				   excluiArq();
				}
			}
		});
		
		
	}
}
