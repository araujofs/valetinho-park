package br.com.valetinho.appswing;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import br.com.valetinho.modelo.Veiculo;
import br.com.valetinho.requisito.Fachada;

public class TelaFotoVeiculo {
  private JDialog frame;
  private JLabel label;
  private Veiculo veiculo;

  public TelaFotoVeiculo(String placa) {
    this.veiculo = Fachada.localizarVeiculo(placa);
    initialize();
    frame.setVisible(true);
  }

  private void initialize() {
    frame = new JDialog();
    frame.setModal(true);
    frame.setTitle("Foto de " + this.veiculo.getPlaca());
    frame.setBounds(100, 100, 900, 600);
    frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    frame.getContentPane().setLayout(null);
    frame.setResizable(false);

    label = new JLabel("");
    label.setFont(new Font("Tahoma", Font.PLAIN, 26));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setBounds(0, 0, 444, 249);
    label.setText("Inicializando...");
    label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    ImageIcon imagem = new ImageIcon(veiculo.getFoto());
    imagem = new ImageIcon(
        imagem.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
    label.setIcon(imagem);
    frame.getContentPane().add(label);
  }
}
