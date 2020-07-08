package view;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TelaCreditos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public TelaCreditos() {
		setTitle("Creditos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo2.png")));
		initialize();
		this.setLocationRelativeTo(null);
		setResizable(false);
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel UEPB = new JLabel("image");
		UEPB.setBounds(0, 0, 427, 320);
		UEPB.setIcon(new ImageIcon(getClass().getResource("imagens/telaCreditos.jpg")));
		contentPane.add(UEPB);

	}
}
