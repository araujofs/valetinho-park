/**
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Persistencia de Objetos
 * Prof. Fausto Maranh�o Ayres
 */

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

import modelo.Estacionamento;
import modelo.Localizacao;
import modelo.Bilhete;
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
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;
  private JLabel label_6;
  private JTextField textField_4;

  public TelaEstacionamento() {
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
    frame.setTitle("Estacionamentos");
    frame.setBounds(100, 100, 646, 428);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setResizable(false);

    scrollPane = new JScrollPane();
    scrollPane.setBounds(21, 45, 575, 155);
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
            String nome = (String) table.getValueAt(table.getSelectedRow(), 1);
            Estacionamento a = Fachada.localizarEstacionamento(nome);
            String id = a.getId() + "";
            Localizacao loc = a.getLocalizacao();
            String localizacao = loc.x() + ", " + loc.y();
            textField_1.setText(id);
            textField_2.setText(nome);
            textField_3.setText(localizacao);
            label.setText("");
            String bilhetes;
            if (a.getBilhetes().size() == 0)
              bilhetes = "sem bilhetes";
            else {
              bilhetes = "";
              for (Bilhete t : a.getBilhetes())
                bilhetes += " " + t.getId();
            }
            textField_4.setText(bilhetes.trim());
          }
        } catch (Exception erro) {
          label.setText(erro.getMessage());
          System.out.println(erro.getMessage());
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
    button_4.setToolTipText("apagar estacionamento e seus dados");
    button_4.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_2.getText().isEmpty()) {
            label.setText("nome vazio");
            return;
          }
          String nome = textField_2.getText();
          Object[] options = { "Confirmar", "Cancelar" };
          int escolha = JOptionPane.showOptionDialog(null,
              "Esta operação apagará o estacionamento e seus bilhetes de " + nome, "Alerta",
              JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
          if (escolha == 0) {
            Fachada.apagarEstacionamento(nome);
            label.setText("estacionamento excluido");
            listagem();
          } else
            label.setText("exclusão cancelada");
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

    label_2 = new JLabel("selecione um estacionamento para editar");
    label_2.setBounds(31, 199, 394, 14);
    frame.getContentPane().add(label_2);

    label_3 = new JLabel("id:");
    label_3.setHorizontalAlignment(SwingConstants.LEFT);
    label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_3.setBounds(31, 222, 62, 14);
    frame.getContentPane().add(label_3);

    textField_1 = new JTextField();
    textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_1.setColumns(10);
    textField_1.setBackground(Color.WHITE);
    textField_1.setEditable(false);
    textField_1.setBounds(101, 218, 165, 20);
    frame.getContentPane().add(textField_1);

    label_4 = new JLabel("nome:");
    label_4.setHorizontalAlignment(SwingConstants.LEFT);
    label_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_4.setBounds(31, 247, 62, 14);
    frame.getContentPane().add(label_4);

    textField_2 = new JTextField();
    textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_2.setColumns(10);
    textField_2.setBounds(101, 243, 86, 20);
    frame.getContentPane().add(textField_2);

    label_5 = new JLabel("localizacao (x,y):");
    label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
    label_5.setHorizontalAlignment(SwingConstants.LEFT);
    label_5.setBounds(31, 272, 140, 14);
    frame.getContentPane().add(label_5);

    textField_3 = new JTextField();
    textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
    textField_3.setColumns(10);
    textField_3.setBounds(120, 267, 264, 20);
    frame.getContentPane().add(textField_3);

    button_1 = new JButton("Criar");
    button_1.setToolTipText("cadastrar novo estacionamento");
    button_1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_2.getText().isEmpty()) {
            label.setText("nome vazio");
            return;
          }
          String nome = textField_2.getText().trim();
          String locText = textField_3.getText().trim();
          String[] parts = locText.split("\\s*,\\s*|\\s+");
          if (parts.length < 2)
            throw new Exception("localizacao invalida. use 'x,y' ou 'x y'");
          Double lx = Double.parseDouble(parts[0]);
          Double ly = Double.parseDouble(parts[1]);
          Localizacao localizacao = new Localizacao(lx, ly);

          Fachada.criarEstacionamento(nome, localizacao);

          label.setText("estacionamento criado");
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
    button_5.setToolTipText("atualizar estacionamento");
    button_5.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try {
          if (textField_2.getText().trim().isEmpty()) {
            label.setText("nome vazio");
            return;
          }
          String nome = textField_2.getText();
          ArrayList<Bilhete> bilhetes = new ArrayList<>();
          String bilhetesText = textField_4.getText().trim();
          if (!bilhetesText.isEmpty() && !bilhetesText.equals("sem bilhetes")) {
            String[] tokens = bilhetesText.split("\\s+");
            for (String t : tokens) {
              try {
                Integer id = Integer.parseInt(t);
                Bilhete b = Fachada.localizarBilhete(id);
                bilhetes.add(b);
              } catch (Exception exInner) {
                // ignorar token inválido
              }
            }
          }
          String locText = textField_3.getText().trim();
          String[] parts = locText.split("\\s*,\\s*|\\s+");
          if (parts.length < 2)
            throw new Exception("localizacao invalida. use 'x,y' ou 'x y'");
          Double lx = Double.parseDouble(parts[0]);
          Double ly = Double.parseDouble(parts[1]);
          Localizacao localizacao = new Localizacao(lx, ly);
          Integer id = Integer.valueOf(textField_1.getText());

          Fachada.alterarEstacionamento(id, nome, bilhetes, localizacao);

          label.setText("estacionamento alterado");
          listagem();
        } catch (Exception ex2) {
          label.setText(ex2.getMessage());

          System.out.println(ex2.getMessage());
        }
      }
    });
    button_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
    button_5.setBounds(82, 340, 87, 23);
    frame.getContentPane().add(button_5);

    button_3 = new JButton("Limpar");
    button_3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        table.clearSelection();
        textField_1.setText("");
        textField_2.setText("");
        textField_3.setText("");
        textField_4.setText("");
      }
    });
    button_3.setBounds(276, 217, 89, 23);
    frame.getContentPane().add(button_3);

    label_6 = new JLabel("bilhetes");
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
      List<Estacionamento> lista = Fachada.listarEstacionamento();

      DefaultTableModel model = new DefaultTableModel();
      table.setModel(model);

      model.addColumn("Id");
      model.addColumn("Nome");
      model.addColumn("Localizacao");
      model.addColumn("Bilhetes");

      for (Estacionamento a : lista)
        model.addRow(new Object[] { a.getId(), a.getNome(), a.getLocalizacao(), a.getBilhetes().size() });

      label_2.setText("resultados: " + lista.size() + " estacionamentos   - selecione uma linha para editar");

    } catch (Exception erro) {
      label.setText(erro.getMessage());
    }
  }
}