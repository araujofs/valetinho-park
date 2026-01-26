/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Veiculo;
import modelo.Bilhete;
import requisito.Fachada;

public class TelaConsulta {
  private JDialog frame;
  private JTable table;
  private JScrollPane scrollPane;
  private JButton button;
  private JLabel label;
  private JLabel label_4;

  private JComboBox<String> comboBox;

  /**
   * Launch the application.
   */
  // public static void main(String[] args) {
  // EventQueue.invokeLater(new Runnable() {
  // public void run() {
  // try {
  // new TelaConsulta();
  // } catch (Exception e) {
  // e.printStackTrace();
  // }
  // }
  // });
  // }

  public TelaConsulta() {
    initialize();
    frame.setVisible(true);
  }

  private void initialize() {
    frame = new JDialog();
    frame.setModal(true);

    frame.setResizable(false);
    frame.setTitle("Consultas");
    frame.setBounds(100, 100, 729, 385);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(21, 43, 674, 148);
    frame.getContentPane().add(scrollPane);

    table = new JTable() {
      public boolean isCellEditable(int rowIndex, int vColIndex) {
        return false;
      }
    };

    table.setGridColor(Color.BLACK);
    table.setRequestFocusEnabled(false);
    table.setFocusable(false);
    table.setBackground(Color.LIGHT_GRAY);
    table.setFillsViewportHeight(true);
    table.setRowSelectionAllowed(true);
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    scrollPane.setViewportView(table);
    table.setBorder(new LineBorder(new Color(0, 0, 0)));
    table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    table.setShowGrid(true);
    table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    label = new JLabel(""); 
    label.setForeground(Color.BLUE);
    label.setBounds(21, 321, 688, 14);
    frame.getContentPane().add(label);

    label_4 = new JLabel("resultados:");
    label_4.setBounds(21, 190, 431, 14);
    frame.getContentPane().add(label_4);

    button = new JButton("Consultar");
    button.setFont(new Font("Tahoma", Font.PLAIN, 12));
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int index = comboBox.getSelectedIndex();
        if (index < 0) {
          label_4.setText("consulta nao selecionada");
        } else {
          label_4.setText("");
          label.setText("");
          try {
            switch (index) {
              case 0:
                String valor = JOptionPane.showInputDialog("digite o valor");
                if (valor == null || valor.isEmpty()) {
                  label.setText("valor cancelado ou vazio");
                  return;
                }
                List<Bilhete> resultado1 = Fachada.consultarBilhetesValorMaiorX(Double.valueOf(valor));
                listagemBilhete(resultado1);
                label_4.setText("resultados: " + resultado1.size() + " bilhete(s)");
                break;
              case 1:
                String data = JOptionPane.showInputDialog("digite a data (dd/MM/yyyy)");
                if (data == null || data.isEmpty()) {
                  label.setText("data cancelada ou vazia");
                  return;
                }
                Date dataParseada;
                try {
                  dataParseada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                } catch (Exception err) {
                  label.setText("erro no formato da data: " + err.getMessage());
                  return;
                }
                String estacionamentoNome = JOptionPane.showInputDialog("digite o nome do estacionamento");
                if (estacionamentoNome == null || estacionamentoNome.isEmpty()) {
                  label.setText("estacionamento cancelado ou vazio");
                  return;
                }
                List<Veiculo> resultado2 = Fachada.consultarVeiculoEstacionadoDataX(dataParseada, estacionamentoNome);
                listagemVeiculo(resultado2);
                label_4.setText("resultados: " + resultado2.size() + " veiculo(s)");
                break;
              case 2:
                String n = JOptionPane.showInputDialog("digite N (quantidade minima de bilhetes)");
                if (n == null || n.isEmpty()) {
                  label.setText("valor cancelado ou vazio");
                  return;
                }
                List<Veiculo> resultado3 = Fachada.consultarVeiculoMaisXBilhetes(Integer.valueOf(n));
                listagemVeiculo(resultado3);
                label_4.setText("resultados: " + resultado3.size() + " veiculo(s)");
                break;
            }
          } catch (Exception ex) {
            label.setText("ERRO: " + ex.getMessage());
            ex.printStackTrace();
          }
        }
      }
    });
    button.setBounds(606, 10, 89, 23);
    frame.getContentPane().add(button);

    comboBox = new JComboBox<String>();
    comboBox.setToolTipText("selecione a consulta");
    comboBox.setModel(new DefaultComboBoxModel<>(
        new String[] { "bilhetes com valor pago maior que X reais",
            "veiculos estacionados na data X no estacionamento Y", "veiculos com mais de N bilhetes" }));
    comboBox.setBounds(21, 10, 513, 22);
    frame.getContentPane().add(comboBox);
  }

  public void listagemBilhete(List<Bilhete> lista) {
    try {
      DefaultTableModel model = new DefaultTableModel();
      table.setModel(model);

      model.addColumn("Id");
      model.addColumn("Data");
      model.addColumn("Valor");
      model.addColumn("Placa_veiculo");
      model.addColumn("Nome_estacionamento");

      for (Bilhete p : lista) {
        model.addRow(new Object[] { p.getId(), p.getDataFormatada(), p.getValorpago(), p.getVeiculo().getPlaca(), p.getEstacionamento().getNome() });
      }
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
      table.getColumnModel().getColumn(0).setMaxWidth(40); 
      table.getColumnModel().getColumn(3).setMinWidth(200); 
      table.getColumnModel().getColumn(4).setMinWidth(200); 
      table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); 

    } catch (Exception erro) {
      label.setText(erro.getMessage());
    }
  }
  public void listagemVeiculo(List<Veiculo> lista) {
    try {
      DefaultTableModel model = new DefaultTableModel();
      table.setModel(model);

      model.addColumn("Placa");
      model.addColumn("Bilhetes");

      String texto2;
      for (Veiculo p : lista) {
        if (p.getBilhetes().size() > 0) {
          texto2 = p.getBilhetes().size() + " bilhete(s): ";
          for (Bilhete t : p.getBilhetes())
            texto2 += t.getId() + " ";
        } else
          texto2 = "sem bilhetes";
        model.addRow(new Object[] { p.getPlaca(), texto2.trim() });

      }
      table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      table.getColumnModel().getColumn(0).setMinWidth(100); 
      table.getColumnModel().getColumn(1).setMinWidth(300); 
      table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

    } catch (Exception erro) {
      label.setText(erro.getMessage());
    }
  }

}
