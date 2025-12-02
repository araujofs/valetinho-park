/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/

package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Aluno;
import modelo.Telefone;
import requisito.Fachada;

public class TelaEstacionamento {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JButton button_1;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_7;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JLabel label_1;
	private JTextField textField_6;
	private JLabel label_6;
	private JTextField textField_4;


	/**
	 * Create the application.
	 */
	public TelaEstacionamento() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);		//janela modal
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				listagem();
			}
		});
		frame.setTitle("Alunos");
		frame.setBounds(100, 100, 646, 428);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 45, 575, 155);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			// proibir alteracao de celulas
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		
		// evento de selecao de uma linha da tabela
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						// pegar o nome, data nascimento e apelidos da pessoa selecionada
						String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
						Aluno a = Fachada.localizarAluno(nome);
						String data = a.getDtNascimento();
						String nota = a.getNota()+"";
						textField_1.setText(nome);
						textField_2.setText(data);
						textField_3.setText(String.join(",", a.getApelidos()));
						textField_5.setText("");
						textField_6.setText(nota);
						label.setText("");
						String telefones ;
						if (a.getTelefones().size() == 0)
							telefones = "sem telefone";
						else {
							telefones = "";
							for (Telefone t : a.getTelefones())
								telefones = telefones + " " + t.getNumero();
						}
						textField_4.setText(telefones);

					}
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}

			}
		});

		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setColumnHeaderView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		button_4 = new JButton("Apagar");
		button_4.setToolTipText("apagar aluno e seus dados");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					// pegar o nome na linha selecionada
					String nome = textField_1.getText();
					Object[] options = { "Confirmar", "Cancelar" };
					int escolha = JOptionPane.showOptionDialog(null,
							"Esta opera��o apagar� os telefones e remover� as reunioes de " + nome, "Alerta",
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					if (escolha == 0) {
						Fachada.apagarPessoa(nome);
						label.setText("aluno excluido");
						listagem(); // listagem
					} else
						label.setText("exclus�o cancelada");

				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_4.setBounds(169, 340, 74, 23);
		frame.getContentPane().add(button_4);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 372, 599, 14);
		frame.getContentPane().add(label);

		button = new JButton("Listar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(276, 11, 109, 23);
		frame.getContentPane().add(button);

		label_2 = new JLabel("selecione um aluno para editar");
		label_2.setBounds(31, 199, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(31, 222, 62, 14);
		frame.getContentPane().add(label_3);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(101, 218, 165, 20);
		frame.getContentPane().add(textField_1);

		label_4 = new JLabel("nascimento");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(31, 247, 62, 14);
		frame.getContentPane().add(label_4);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(101, 243, 86, 20);
		frame.getContentPane().add(textField_2);

		label_5 = new JLabel("apelidos");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setBounds(31, 272, 62, 14);
		frame.getContentPane().add(label_5);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(101, 267, 264, 20);
		frame.getContentPane().add(textField_3);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar novo aluno");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField_1.getText().trim();
					String nascimento = textField_2.getText().trim();
					List<String> apelidos = Arrays.asList(textField_3.getText().trim().split(","));
					double nota = Double.parseDouble(textField_6.getText().trim());

					Fachada.criarAluno(nome, nascimento, apelidos, nota);
					
					String numero = textField_5.getText();
					if (!numero.isEmpty())
						Fachada.criarTelefone(nome, numero);

					label.setText("aluno criado");
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_1.setBounds(21, 340, 62, 23);
		frame.getContentPane().add(button_1);

		button_5 = new JButton("Atualizar");
		button_5.setToolTipText("atualizar aluno ");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (textField_1.getText().trim().isEmpty()) {
						label.setText("nome vazio");
						return;
					}
					String nome = textField_1.getText();
					String nascimento = textField_2.getText();
					List<String> apelidos = Arrays.asList(textField_3.getText().trim().split(","));
					double nota = Double.parseDouble(textField_6.getText().trim());
				
					Fachada.alterarAluno(nome, nascimento, apelidos, nota);

					String numero = textField_5.getText();
					if (!numero.isEmpty())
						Fachada.criarTelefone(nome, numero);
					label.setText("aluno alterado");
					listagem();
				} catch (Exception ex2) {
					label.setText(ex2.getMessage());
				}
			}
		});
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_5.setBounds(82, 340, 87, 23);
		frame.getContentPane().add(button_5);

		button_3 = new JButton("Limpar");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
			}
		});
		button_3.setBounds(276, 217, 89, 23);
		frame.getContentPane().add(button_3);

		label_7 = new JLabel("novo numero");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(21, 317, 74, 14);
		frame.getContentPane().add(label_7);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(101, 313, 86, 20);
		frame.getContentPane().add(textField_5);
		
		label_1 = new JLabel("nota");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setBounds(235, 317, 74, 14);
		frame.getContentPane().add(label_1);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_6.setColumns(10);
		textField_6.setBounds(276, 313, 86, 20);
		frame.getContentPane().add(textField_6);
		
		label_6 = new JLabel("telefones");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(31, 291, 62, 14);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBackground(new Color(255, 255, 255));
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(101, 287, 264, 20);
		frame.getContentPane().add(textField_4);


		frame.setVisible(true);
	}

	public void listagem() {
		try {
			List<Aluno> lista = Fachada.listarAlunos();

			// objeto model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			// criar as colunas (0,1,2) da tabela
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Nascimento");
			model.addColumn("Nota");

			// criar as linhas da tabela
			for (Aluno a : lista) 
				model.addRow(new Object[] {a.getId(), a.getNome(), a.getDtNascimento(), a.getNota() });
			
			label_2.setText("resultados: " + lista.size() + " alunos   - selecione uma linha para editar");

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
}
