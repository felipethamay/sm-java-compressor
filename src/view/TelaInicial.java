package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

import model.LZW;
import model.RLE;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private boolean LZWselecao = false;
	private boolean RLEselecao = false;
	private boolean Hufselecao = false;
	private File file;
	private String filepath;
	private String localArquivo;
	private FileInputStream arquivoEntrada;
	private static DataInputStream dataEntrada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				TelaInicial frame = new TelaInicial();
				frame.setVisible(true);
			}
		});
	}

	public TelaInicial() {
		setTitle("JavaCompressor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagens/logo2.png")));
		initialize();
		this.setLocationRelativeTo(null);
		setResizable(false);
		this.dispose();
	}

	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnadicionar = new JButton("");
		btnadicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnadicionarAction(arg0);
			}

			private void btnadicionarAction(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setSize(640, 520);
				int returnVal = fc.showOpenDialog(TelaInicial.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						file = fc.getSelectedFile();
						filepath = file.getAbsolutePath();
						localArquivo = fc.getSelectedFile().getParent();
						arquivoEntrada = new FileInputStream(file);
						dataEntrada = new DataInputStream(arquivoEntrada);

						/**
						 * int matrizImagem [] = LerArquivo.pixels(filepath); for (int i = 0 ; i <
						 * matrizImagem.length; i++){ System.out.print(matrizImagem[i]+", "); }
						 * System.out.println("");
						 */
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});
		btnadicionar.setBounds(4, 1, 90, 90);
		contentPane.add(btnadicionar);
		btnadicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/add.png")));

		JButton btnCompactar = new JButton("");
		btnCompactar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCompactarAction(arg0);
			}

			private void btnCompactarAction(ActionEvent arg0) {
				if (LZWselecao == true) {
					LZW.criaDicionario();
					LZW.compactar(filepath, file, localArquivo);
					System.out.println("compactado !!!");
				}
				if (RLEselecao == true) {
					try {
						RLE.compactar(filepath, file, localArquivo);
						System.out.println("compactado !!!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnCompactar.setBounds(99, 1, 90, 90);
		contentPane.add(btnCompactar);
		btnCompactar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/pasta.jpg")));

		JButton btnDescompactar = new JButton("");
		btnDescompactar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnDescompactarAction(arg0);
			}

			private void btnDescompactarAction(ActionEvent arg0) {
				if (LZWselecao == true) {
					LZW.criaDicionario();
					LZW.descompactar(filepath, file, localArquivo, dataEntrada);
					System.out.println("Descompactado !!!");
				}
				if (RLEselecao == true) {
					try {
						RLE.descompactar(filepath, file, localArquivo);
						System.out.println("descompactado !!!");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		btnDescompactar.setBounds(193, 1, 90, 90);
		contentPane.add(btnDescompactar);
		btnDescompactar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/descomp.png")));

		JButton btnAjuda = new JButton("");
		btnAjuda.setBounds(287, 1, 90, 90);
		contentPane.add(btnAjuda);
		btnAjuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/help.png")));

		JLabel logo = new JLabel("");
		logo.setBounds(0, 90, 385, 344);
		logo.setIcon(new ImageIcon(getClass().getResource("imagens/logo.png")));
		contentPane.add(logo);

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);

		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mntmAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnadicionarAction(arg0);
			}

			private void btnadicionarAction(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setSize(640, 520);
				int returnVal = fc.showOpenDialog(TelaInicial.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						file = fc.getSelectedFile();
						filepath = file.getAbsolutePath();
						localArquivo = fc.getSelectedFile().getParent();
						arquivoEntrada = new FileInputStream(file);
						dataEntrada = new DataInputStream(arquivoEntrada);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
				}
			}
		});

		menuArquivo.add(mntmAdicionar);
		mntmAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/ico_add.png")));

		JMenuItem mntmCompactar = new JMenuItem("Compactar");
		menuArquivo.add(mntmCompactar);
		mntmCompactar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/pasta2.png")));

		JMenuItem mntmDescompactar = new JMenuItem("Descompactar");
		menuArquivo.add(mntmDescompactar);
		mntmDescompactar.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/descomp2.png")));

		JMenuItem menuItemSair = new JMenuItem("Sair");
		menuArquivo.add(menuItemSair);
		menuItemSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/fechar.png")));
		menuItemSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu menuAlgoritmos = new JMenu("Algoritmos");
		menuBar.add(menuAlgoritmos);

		JRadioButtonMenuItem rdbtnmntmRle = new JRadioButtonMenuItem("RLE");
		rdbtnmntmRle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LZWselecao = false;
				RLEselecao = true;
				Hufselecao = false;
			}
		});
		menuAlgoritmos.add(rdbtnmntmRle);

		JRadioButtonMenuItem rdbtnmntmHuffman = new JRadioButtonMenuItem("Huffman");
		rdbtnmntmHuffman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuAlgoritmos.add(rdbtnmntmHuffman);

		JRadioButtonMenuItem rdbtnmntmLzw = new JRadioButtonMenuItem("LZW");
		rdbtnmntmLzw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LZWselecao = true;
				RLEselecao = false;
				Hufselecao = false;

			}
		});
		menuAlgoritmos.add(rdbtnmntmLzw);

		JMenu menuAjuda = new JMenu("Ajuda");
		menuBar.add(menuAjuda);

		JMenuItem mntmInstrues = new JMenuItem("Instrucoes");
		menuAjuda.add(mntmInstrues);
		mntmInstrues.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/instrucoes.png")));

		JMenuItem mntmCreditos = new JMenuItem("Creditos");
		menuAjuda.add(mntmCreditos);
		mntmCreditos.setIcon(new javax.swing.ImageIcon(getClass().getResource("imagens/creditos.png")));
		mntmCreditos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				TelaCreditos cr1 = new TelaCreditos();
				cr1.setVisible(true);
			}
		});
	}
}
