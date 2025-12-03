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

import modelo.Veiculo;
import modelo.Bilhete;
import requisito.Fachada;

public class TelaVeiculo {
  private JDialog frame;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton button;
  private JButton button_1;
  private JButton button_2;
  private JButton button_3;
  private JButton button_4;
  private JLabel label;
  private JLabel label_2;
  private JLabel label_3;
  private JLabel label_4;
  private JLabel label_5;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;

  private String placaSelecionada = null; // guarda a placa original selecionada

  public TelaVeiculo() {
    initialize();
  }

  private void initialize() {
    frame = new JDialog();
    frame.setModal(true);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent arg0) {
        listagem();
      }
    });
    frame.setTitle("Veículos");
    frame.setBounds(100, 100, 550, 400);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setResizable(false);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(21, 45, 490, 140);
    frame.getContentPane().add(scrollPane);

    table = new JTable() {
      public boolean isCellEditable(int rowIndex, int vColIndex) {
        return false;
      }
    };

    table.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          if (table.getSelectedRow() >= 0) {
            String placa = (String) table.getValueAt(table.getSelectedRow(), 0);
            Veiculo v = Fachada.localizarVeiculo(placa);
            
            placaSelecionada = placa; // guarda a placa original
            textField_1.setText(placa);
            textField_2.setText("");
            
            String bilhetes;
            int qtd = v.getBilhetes().size();
            if (qtd == 0) {
              bilhetes = "0 bilhete(s)";
            } else {
              bilhetes = qtd + " bilhete(s): ";
              for (Bilhete b : v.getBilhetes()) {
                bilhetes += b.getId() + " ";
              }
            }
            textField_3.setText(bilhetes.trim());
            label.setText("");
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
    label.setBounds(21, 340, 500, 14);
    frame.getContentPane().add(label);

    button = new JButton("Listar");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listagem();
      }
    });
    button.setFont(new Font("Tahoma", Font.PLAIN, 12));
    button.setBounds(220, 11, 109, 23);
    frame.getContentPane().add(button);

    label_2 = new JLabel("selecione um veículo para editar");
    label_2.setBounds(31, 190, 394, 14);
    frame.getContentPane().add(label_2);

    label_3 = new JLabel("placa:");
    label_3.setHorizontalAlignment(SwingConstants.LEFT);
    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_3.setBounds(31, 215, 62, 14);
    frame.getContentPane().add(label_3);

    textField_1 = new JTextField();
    textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_1.setColumns(10);
    textField_1.setBackground(Color.WHITE);
    textField_1.setBounds(100, 212, 120, 20);
    textField_1.setToolTipText("Ex: ABC-1234 ou ABC1D23");
    frame.getContentPane().add(textField_1);

    label_4 = new JLabel("nova placa:");
    label_4.setHorizontalAlignment(SwingConstants.LEFT);
    label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_4.setBounds(230, 215, 70, 14);
    frame.getContentPane().add(label_4);

    textField_2 = new JTextField();
    textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_2.setColumns(10);
    textField_2.setBounds(300, 212, 120, 20);
    textField_2.setToolTipText("Nova placa para atualizar (Ex: XYZ-9999)");
    frame.getContentPane().add(textField_2);

    label_5 = new JLabel("bilhetes:");
    label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_5.setHorizontalAlignment(SwingConstants.LEFT);
    label_5.setBounds(31, 245, 62, 14);
    frame.getContentPane().add(label_5);

    textField_3 = new JTextField();
    textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_3.setColumns(10);
    textField_3.setEditable(false);
    textField_3.setBackground(Color.WHITE);
    textField_3.setBounds(100, 242, 320, 20);
    textField_3.setToolTipText("IDs dos bilhetes associados (somente leitura)");
    frame.getContentPane().add(textField_3);

    button_1 = new JButton("Criar");
    button_1.setToolTipText("cadastrar novo veículo");
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_1.getText().isEmpty()) {
            label.setText("placa vazia");
            return;
          }
          String placa = textField_1.getText().trim();
          Fachada.criarVeiculo(placa);
          label.setText("veículo criado");
          listagem();
        } catch (Exception ex) {
          label.setText(ex.getMessage());
        }
      }
    });
    button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
    button_1.setBounds(31, 300, 80, 23);
    frame.getContentPane().add(button_1);

    button_2 = new JButton("Atualizar");
    button_2.setToolTipText("atualizar placa do veículo");
    button_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (placaSelecionada == null || placaSelecionada.isEmpty()) {
            label.setText("selecione um veículo primeiro");
            return;
          }
          if (textField_2.getText().isEmpty()) {
            label.setText("nova placa vazia");
            return;
          }
          String novaPlaca = textField_2.getText().trim();
          Fachada.alterarPlacaVeiculo(placaSelecionada, novaPlaca);
          label.setText("placa alterada");
          placaSelecionada = null;
          textField_1.setText("");
          textField_2.setText("");
          textField_3.setText("");
          listagem();
        } catch (Exception ex) {
          label.setText(ex.getMessage());
        }
      }
    });
    button_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
    button_2.setBounds(120, 300, 90, 23);
    frame.getContentPane().add(button_2);

    button_3 = new JButton("Apagar");
    button_3.setToolTipText("apagar veículo e seus bilhetes");
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_1.getText().isEmpty()) {
            label.setText("placa vazia");
            return;
          }
          String placa = textField_1.getText();
          Object[] options = { "Confirmar", "Cancelar" };
          int escolha = JOptionPane.showOptionDialog(null,
              "Esta operação apagará o veículo e seus bilhetes: " + placa, "Alerta",
              JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
          if (escolha == 0) {
            Fachada.apagarVeiculo(placa);
            label.setText("veículo excluído");
            placaSelecionada = null;
            listagem();
          } else {
            label.setText("exclusão cancelada");
          }
        } catch (Exception erro) {
          label.setText(erro.getMessage());
        }
      }
    });
    button_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
    button_3.setBounds(220, 300, 80, 23);
    frame.getContentPane().add(button_3);

    button_4 = new JButton("Limpar");
    button_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        table.clearSelection();
        placaSelecionada = null;
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        label.setText("");
      }
    });
    button_4.setBounds(310, 300, 80, 23);
    frame.getContentPane().add(button_4);

    frame.setVisible(true);
  }

  public void listagem() {
    try {
      List<Veiculo> lista = Fachada.listarVeiculos();

      DefaultTableModel model = new DefaultTableModel();
      table.setModel(model);

      model.addColumn("Placa");
      model.addColumn("Qtd Bilhetes");

      for (Veiculo v : lista) {
        model.addRow(new Object[] { v.getPlaca(), v.getBilhetes().size() });
      }

      label_2.setText("resultados: " + lista.size() + " veículos - selecione uma linha para editar");

    } catch (Exception erro) {
      label.setText(erro.getMessage());
    }
  }
}
