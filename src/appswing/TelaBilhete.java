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

import modelo.Bilhete;
import requisito.Fachada;

public class TelaBilhete {
  private JDialog frame;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton button;
  private JButton button_1;
  private JButton button_2;
  private JButton button_3;
  private JLabel label;
  private JLabel label_1;
  private JLabel label_2;
  private JLabel label_3;
  private JLabel label_4;
  private JLabel label_5;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JTextField textField_4;
  private JTextField textField_5;

  private Integer idSelecionado = null;

  public TelaBilhete() {
    initialize();
  }

  private void initialize() {
    frame = new JDialog();
    frame.setResizable(false);
    frame.setModal(true);
    frame.setTitle("Bilhetes");
    frame.setBounds(100, 100, 650, 450);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent arg0) {
        listagem();
      }
    });

    scrollPane = new JScrollPane();
    scrollPane.setBounds(21, 45, 590, 150);
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
            Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
            Bilhete b = Fachada.localizarBilhete(id);
            
            idSelecionado = id;
            textField_1.setText(id.toString());
            textField_2.setText(b.getDataFormatada());
            textField_3.setText(b.getValorpago().toString());
            textField_4.setText(b.getVeiculo().getPlaca());
            textField_5.setText(b.getEstacionamento().getNome());
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
    table.setFont(new Font("Tahoma", Font.PLAIN, 12));
    scrollPane.setViewportView(table);
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setShowGrid(true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    label = new JLabel("");
    label.setForeground(Color.RED);
    label.setBounds(21, 390, 600, 14);
    frame.getContentPane().add(label);

    button = new JButton("Listar");
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listagem();
      }
    });
    button.setFont(new Font("Tahoma", Font.PLAIN, 12));
    button.setBounds(270, 11, 112, 23);
    frame.getContentPane().add(button);

    label_1 = new JLabel("selecione um bilhete para editar/apagar");
    label_1.setBounds(21, 200, 350, 14);
    frame.getContentPane().add(label_1);

    // Campo ID (somente leitura)
    JLabel label_id = new JLabel("id:");
    label_id.setHorizontalAlignment(SwingConstants.LEFT);
    label_id.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_id.setBounds(21, 225, 80, 14);
    frame.getContentPane().add(label_id);

    textField_1 = new JTextField();
    textField_1.setEditable(false);
    textField_1.setBackground(Color.LIGHT_GRAY);
    textField_1.setColumns(10);
    textField_1.setBounds(120, 222, 60, 20);
    textField_1.setToolTipText("ID gerado automaticamente (somente leitura)");
    frame.getContentPane().add(textField_1);

    // Campo Data
    label_2 = new JLabel("data:");
    label_2.setHorizontalAlignment(SwingConstants.LEFT);
    label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_2.setBounds(21, 255, 80, 14);
    frame.getContentPane().add(label_2);

    textField_2 = new JTextField();
    textField_2.setColumns(10);
    textField_2.setBounds(120, 252, 150, 20);
    textField_2.setToolTipText("Ex: 02/12/2025 14:30:00");
    frame.getContentPane().add(textField_2);

    JLabel label_formato = new JLabel("(dd/MM/yyyy HH:mm:ss)");
    label_formato.setFont(new Font("Tahoma", Font.PLAIN, 10));
    label_formato.setBounds(280, 255, 150, 14);
    frame.getContentPane().add(label_formato);

    // Campo Valor Pago
    label_3 = new JLabel("valor pago:");
    label_3.setHorizontalAlignment(SwingConstants.LEFT);
    label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_3.setBounds(21, 285, 80, 14);
    frame.getContentPane().add(label_3);

    textField_3 = new JTextField();
    textField_3.setColumns(10);
    textField_3.setBounds(120, 282, 80, 20);
    textField_3.setToolTipText("Ex: 15.50");
    frame.getContentPane().add(textField_3);

    // Campo Placa Veículo
    label_4 = new JLabel("placa veículo:");
    label_4.setHorizontalAlignment(SwingConstants.LEFT);
    label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_4.setBounds(21, 315, 100, 14);
    frame.getContentPane().add(label_4);

    textField_4 = new JTextField();
    textField_4.setColumns(10);
    textField_4.setBounds(120, 312, 120, 20);
    textField_4.setToolTipText("Placa de um veículo existente (Ex: ABC-1234)");
    frame.getContentPane().add(textField_4);

    // Campo Estacionamento
    label_5 = new JLabel("estacionamento:");
    label_5.setHorizontalAlignment(SwingConstants.LEFT);
    label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
    label_5.setBounds(260, 315, 110, 14);
    frame.getContentPane().add(label_5);

    textField_5 = new JTextField();
    textField_5.setColumns(10);
    textField_5.setBounds(370, 312, 150, 20);
    textField_5.setToolTipText("Nome de um estacionamento existente (Ex: Shopping)");
    frame.getContentPane().add(textField_5);

    // Botão Criar
    button_1 = new JButton("Criar");
    button_1.setToolTipText("criar novo bilhete");
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_2.getText().isEmpty()) {
            label.setText("data vazia");
            return;
          }
          if (textField_3.getText().isEmpty()) {
            label.setText("valor vazio");
            return;
          }
          if (textField_4.getText().isEmpty()) {
            label.setText("placa do veículo vazia");
            return;
          }
          if (textField_5.getText().isEmpty()) {
            label.setText("nome do estacionamento vazio");
            return;
          }

          String data = textField_2.getText().trim();
          Double valor = Double.parseDouble(textField_3.getText().trim());
          String placa = textField_4.getText().trim();
          String nomeEstac = textField_5.getText().trim();

          Fachada.criarBilhete(data, valor, placa, nomeEstac);
          label.setText("bilhete criado");
          listagem();
        } catch (Exception ex) {
          label.setText(ex.getMessage());
        }
      }
    });
    button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
    button_1.setBounds(21, 350, 80, 23);
    frame.getContentPane().add(button_1);

    // Botão Apagar
    button_2 = new JButton("Apagar");
    button_2.setToolTipText("apagar bilhete selecionado");
    button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
    button_2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (idSelecionado == null) {
            label.setText("selecione um bilhete primeiro");
            return;
          }

          Object[] options = { "Confirmar", "Cancelar" };
          int escolha = JOptionPane.showOptionDialog(null, 
              "Confirma exclusão do bilhete " + idSelecionado + "?", "Alerta",
              JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
          
          if (escolha == 0) {
            Fachada.apagarBilhete(idSelecionado);
            label.setText("bilhete excluído");
            idSelecionado = null;
            limparCampos();
            listagem();
          }
        } catch (Exception erro) {
          label.setText(erro.getMessage());
        }
      }
    });
    button_2.setBounds(110, 350, 80, 23);
    frame.getContentPane().add(button_2);

    // Botão Limpar
    button_3 = new JButton("Limpar");
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        table.clearSelection();
        idSelecionado = null;
        limparCampos();
        label.setText("");
      }
    });
    button_3.setBounds(200, 350, 80, 23);
    frame.getContentPane().add(button_3);

    frame.setVisible(true);
  }

  private void limparCampos() {
    textField_1.setText("");
    textField_2.setText("");
    textField_3.setText("");
    textField_4.setText("");
    textField_5.setText("");
  }

  public void listagem() {
    try {
      List<Bilhete> lista = Fachada.listarBilhete();
      DefaultTableModel model = new DefaultTableModel();
      table.setModel(model);

      model.addColumn("ID");
      model.addColumn("Data");
      model.addColumn("Valor");
      model.addColumn("Veículo");
      model.addColumn("Estacionamento");

      for (Bilhete b : lista) {
        model.addRow(new Object[] { 
          b.getId(), 
          b.getDataFormatada(), 
          b.getValorpago(),
          b.getVeiculo().getPlaca(),
          b.getEstacionamento().getNome()
        });
      }

      label_1.setText("resultados: " + lista.size() + " bilhetes - selecione uma linha para editar");
    } catch (Exception erro) {
      label.setText(erro.getMessage());
    }
  }
}
