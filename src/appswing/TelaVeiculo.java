/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
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
import java.util.ArrayList;
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

import modelo.Pessoa;
import modelo.Telefone;
import requisito.Fachada;

public class TelaVeiculo {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button_3;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JButton button_1;
	private JButton button_2;
	private JButton button_4;
	private JLabel label_8;

	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_5;
	private JLabel label_5;
	private JLabel label_6;
	private JTextField textField_4;
	private JButton button;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// TelaReuniao window = new TelaReuniao();
	// window.frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 */
	public TelaVeiculo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setResizable(false);
		frame.setModal(true);
		frame.setTitle("Pessoa");
		frame.setBounds(100, 100, 813, 438);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				listagem();
			}

		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 751, 147);
		frame.getContentPane().add(scrollPane);

		table = new JTable() { // heran�a de JTable
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false; // desabilita edicao de celulas
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					label.setText("");
					if (table.getSelectedRow() >= 0) {
						// copiar os dados da pessoa selecionada para os textfields
						String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
						Pessoa p = Fachada.localizarPessoa(nome);

						textField_1.setText(nome);
						textField_2.setText(p.getDtNascimento());
						textField_3.setText(String.join(",", p.getApelidos()));
						textField_5.setText("");
						String telefones;
						if (p.getTelefones().size() == 0)
							telefones = "sem telefone";
						else {
							telefones = "";
							for (Telefone t : p.getTelefones())
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
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 374, 735, 14);
		frame.getContentPane().add(label);

		label_2 = new JLabel("selecione uma pessoa para editar");
		label_2.setBounds(21, 187, 394, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(21, 216, 62, 14);
		frame.getContentPane().add(label_3);

		label_4 = new JLabel("nascimento:");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_4.setBounds(356, 216, 73, 14);
		frame.getContentPane().add(label_4);

		label_8 = new JLabel("novo numero:");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_8.setBounds(21, 300, 74, 14);
		frame.getContentPane().add(label_8);

		button_1 = new JButton("Criar");
		button_1.setToolTipText("cadastrar nova pessoa");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					criarPessoa();
			}
		});
		button_1.setBounds(548, 327, 95, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Atualizar");
		button_2.setToolTipText("atualizar pessoa ");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					atualizarPessoaSelecionada();
			}
		});
		button_2.setBounds(284, 327, 95, 23);
		frame.getContentPane().add(button_2);

		button_3 = new JButton("Apagar");
		button_3.setToolTipText("apagar pessoa e seus dados");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().isEmpty())
					label.setText("nome vazio");
				else
					apagarPessoaSelecionada();
			}
		});
		button_3.setBounds(415, 327, 95, 23);
		frame.getContentPane().add(button_3);

		button_4 = new JButton("Limpar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
			}
		});
		button_4.setBounds(577, 212, 95, 23);
		frame.getContentPane().add(button_4);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(93, 213, 253, 20);
		frame.getContentPane().add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(439, 212, 87, 20);
		frame.getContentPane().add(textField_2);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(93, 238, 433, 20);
		frame.getContentPane().add(textField_3);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_5.setColumns(10);
		textField_5.setBounds(93, 296, 86, 20);
		frame.getContentPane().add(textField_5);

		label_5 = new JLabel("apelidos:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(21, 241, 62, 14);
		frame.getContentPane().add(label_5);

		label_6 = new JLabel("telefones:");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_6.setBounds(21, 270, 62, 14);
		frame.getContentPane().add(label_6);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(93, 266, 433, 20);
		frame.getContentPane().add(textField_4);

		button = new JButton("ver Telefones");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String nome = textField_1.getText();
					Pessoa p = Fachada.localizarPessoa(nome);

					String telefones;
					if (p.getTelefones().size() == 0)
						telefones = "sem telefone";
					else {
						telefones = "";
						for (Telefone t : p.getTelefones())
							telefones = telefones + "\n" + t.getNumero();
					}
					JOptionPane.showMessageDialog(frame, telefones, "Telefones", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button.setBounds(577, 266, 158, 23);
		frame.getContentPane().add(button);

		frame.setVisible(true);
	}

	public void listagem() {
		try {
			// objeto model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			table.setModel(model);

			// adicionar as colunas (0,1,2) do grid
			model.addColumn("Id");
			model.addColumn("Nome");
			model.addColumn("Nascimento");

			// adicionar as linhas do grid
			List<Pessoa> lista = Fachada.listarPessoas();
			for (Pessoa p : lista)
				model.addRow(new Object[] { p.getId(), p.getNome(), p.getDtNascimento() });

			label_2.setText("resultados: " + lista.size() + " pessoas   - selecione uma linha para editar");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void apagarPessoaSelecionada() {
		try {
			label.setText("");
			textField_5.setText("");
			String nome = textField_1.getText();

			Object[] options = { "Confirmar", "Cancelar" };
			int escolha = JOptionPane.showOptionDialog(null, "Esta opera��o apagar� a pessoa " + nome, "Alerta",
					JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
			if (escolha == 0) {
				Fachada.apagarPessoa(nome);
				label.setText("pessoa excluida");
				listagem();
			} else
				label.setText("exclus�o cancelada");

		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}

	public void criarPessoa() {
		try {
			label.setText("");
			String nome = textField_1.getText().trim();
			String nascimento = textField_2.getText().trim();
			List<String> apelidos = Arrays.asList(textField_3.getText().trim().split(","));

			Fachada.criarPessoa(nome, nascimento, apelidos);

			String numero = textField_5.getText();
			if (!numero.isEmpty())
				Fachada.criarTelefone(nome, numero);
			label.setText("pessoa criada");
			listagem();

		} catch (Exception ex) {
			label.setText(ex.getMessage());
		}
	}

	public void atualizarPessoaSelecionada() {
		try {
			label.setText("");
			String nome = textField_1.getText();
			String nascimento = textField_2.getText();
			List<String> apelidos = new ArrayList(Arrays.asList(textField_3.getText().trim().split(",")));

			Fachada.alterarPessoa(nome, nascimento, apelidos);

			String numero = textField_5.getText();
			if (!numero.isEmpty())
				Fachada.criarTelefone(nome, numero);

			label.setText("pessoa atualizada");
			listagem();
		} catch (Exception ex2) {
			label.setText(ex2.getMessage());
		}
	}
}
