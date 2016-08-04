package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.LivroNaoEncontradoException;
import modelo.Livro;
import servico.LivroService;

public class DialogLivro extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static LivroService livroService;

	static {
		@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

		livroService = (LivroService) fabrica.getBean("livroService");
	}

	private JButton novoButton;
	private JButton cadastrarButton;
	private JButton editarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton cancelarButton;
	private JButton buscarButton;

	private JTextField tituloTextField;
	private JComboBox<String> tipoComboBox;
	private JLabel nomeMensagem;
	private JLabel generoMensagem;

	private JPanel panel;

	private Livro umLivro;
	private JTextField autorTextField;
	private JTextField editoraTextField;
	private JTextField preçoTextField;
	private JLabel autorMensagem;
	private JLabel editoraMensagem;
	private JLabel preçoMensagem;

	public void designaLivroAFrame(Livro umLivro) {
            
		this.umLivro = umLivro;

		tituloTextField.setText(umLivro.getTitulo());
		autorTextField.setText(umLivro.getAutor());

		tipoComboBox.setSelectedIndex(umLivro.getTipo());
		editoraTextField.setText(umLivro.getEditora());
		preçoTextField.setText(Float.toString(umLivro.getPreco()));

	}

	public DialogLivro(JFrame frame) {
		super(frame);

		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Livros");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 602, 292);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel cadastrarLabel = new JLabel("Cadastro de Livros");
		cadastrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarLabel.setBounds(189, 21, 190, 26);
		panel.add(cadastrarLabel);

		JLabel tituloLabel = new JLabel("Titulo :");
		tituloLabel.setBounds(62, 78, 46, 14);
		panel.add(tituloLabel);

		tituloTextField = new JTextField();
		tituloLabel.setLabelFor(tituloTextField);
		tituloTextField.setBounds(138, 75, 278, 20);
		panel.add(tituloTextField);
		tituloTextField.setColumns(50);
		tituloTextField.setBackground(SystemColor.window);

		JLabel autorLabel = new JLabel("Autor :");
		autorLabel.setBounds(62, 116, 46, 14);
		panel.add(autorLabel);

		JLabel tipoLabel = new JLabel("Genero :");
		tipoLabel.setBounds(62, 156, 46, 14);
		panel.add(tipoLabel);

		tipoComboBox = new JComboBox<String>();
		tipoLabel.setLabelFor(tipoComboBox);
		tipoComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "", "Aventura", "Bibliografias",
				"Didáticos", "Ficção", "Horror", "Infantil", "Informatica", "Romance", "Outros..." }));
		tipoComboBox.setBounds(138, 152, 142, 20);
		tipoComboBox.setBackground(SystemColor.window);
		panel.add(tipoComboBox);

		JLabel editoraLabel = new JLabel("Editora :");
		editoraLabel.setBounds(62, 193, 78, 14);
		panel.add(editoraLabel);

		nomeMensagem = new JLabel("");
		nomeMensagem.setForeground(Color.RED);
		nomeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nomeMensagem.setBounds(138, 92, 241, 14);
		panel.add(nomeMensagem);

		generoMensagem = new JLabel("");
		generoMensagem.setForeground(Color.RED);
		generoMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		generoMensagem.setBounds(138, 170, 241, 14);
		panel.add(generoMensagem);

		novoButton = new JButton("Novo");
		novoButton.setBounds(452, 50, 96, 23);
		panel.add(novoButton);
		novoButton.addActionListener(this);

		cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.setBounds(452, 77, 96, 23);
		panel.add(cadastrarButton);
		cadastrarButton.addActionListener(this);

		editarButton = new JButton("Editar");
		editarButton.setBounds(452, 104, 96, 23);
		panel.add(editarButton);
		editarButton.addActionListener(this);

		alterarButton = new JButton("Alterar");
		alterarButton.setBounds(452, 131, 96, 23);
		panel.add(alterarButton);
		alterarButton.addActionListener(this);

		removerButton = new JButton("Remover");
		removerButton.setBounds(452, 158, 96, 23);
		panel.add(removerButton);
		removerButton.addActionListener(this);

		cancelarButton = new JButton("Cancelar");
		cancelarButton.setBounds(452, 185, 96, 23);
		panel.add(cancelarButton);
		cancelarButton.addActionListener(this);

		buscarButton = new JButton("Buscar");
		buscarButton.setBounds(452, 212, 96, 23);
		panel.add(buscarButton);

		autorTextField = new JTextField();
		autorTextField.setColumns(50);
		autorTextField.setBackground(Color.WHITE);
		autorTextField.setBounds(138, 113, 278, 20);
		panel.add(autorTextField);

		editoraTextField = new JTextField();
		editoraTextField.setColumns(50);
		editoraTextField.setBackground(Color.WHITE);
		editoraTextField.setBounds(138, 190, 278, 20);
		panel.add(editoraTextField);

		JLabel preçoLabel = new JLabel("Pre\u00E7o :");
		preçoLabel.setBounds(62, 233, 78, 14);
		panel.add(preçoLabel);

		preçoTextField = new JTextField();
		preçoTextField.setColumns(50);
		preçoTextField.setBackground(Color.WHITE);
		preçoTextField.setBounds(138, 230, 56, 20);
		panel.add(preçoTextField);

		autorMensagem = new JLabel("");
		autorMensagem.setForeground(Color.RED);
		autorMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		autorMensagem.setBounds(138, 131, 241, 14);
		panel.add(autorMensagem);

		editoraMensagem = new JLabel("");
		editoraMensagem.setForeground(Color.RED);
		editoraMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		editoraMensagem.setBounds(138, 212, 241, 14);
		panel.add(editoraMensagem);

		preçoMensagem = new JLabel("");
		preçoMensagem.setForeground(Color.RED);
		preçoMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		preçoMensagem.setBounds(138, 252, 241, 14);
		panel.add(preçoMensagem);
		buscarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == novoButton) {
			novo();
		} else if (obj == cadastrarButton) {
			boolean deuErro = validaLivro();

			if (!deuErro) {
				umLivro = new Livro();
				umLivro.setTitulo(tituloTextField.getText().toUpperCase());
				umLivro.setAutor(autorTextField.getText().toUpperCase());
				umLivro.setTipo(tipoComboBox.getSelectedIndex());
				umLivro.setEditora(editoraTextField.getText().toUpperCase());
				umLivro.setPreco(Float.parseFloat(preçoTextField.getText()));

				livroService.inclui(umLivro);

				salvo();

				JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj == editarButton) {
			editavel();
		} else if (obj == alterarButton) {
			boolean deuErro = validaLivro();

			if (!deuErro) {
				umLivro.setTitulo(tituloTextField.getText().toUpperCase());
				umLivro.setAutor(autorTextField.getText().toUpperCase());
				umLivro.setTipo(tipoComboBox.getSelectedIndex());
				umLivro.setEditora(editoraTextField.getText().toUpperCase());
				umLivro.setPreco(Float.parseFloat(preçoTextField.getText()));

				try {
					livroService.altera(umLivro);

					salvo();

					JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso", "",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (LivroNaoEncontradoException e1) {
					novo();

					JOptionPane.showMessageDialog(this, "Livro não encontrado", "", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (obj == removerButton) {
			try {
				livroService.exclui(umLivro);

				removido();

				JOptionPane.showMessageDialog(this, "Livro removido com sucesso", "", JOptionPane.INFORMATION_MESSAGE);
			} catch (LivroNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Livro não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		} else if (obj == cancelarButton) {
			try {
				umLivro = livroService.recuperaUmLivro(umLivro.getNumero());

				tituloTextField.setText(umLivro.getTitulo());
				autorTextField.setText(umLivro.getAutor());
				editoraTextField.setText(umLivro.getEditora());
				preçoTextField.setText(Float.toString(umLivro.getPreco()));
				tipoComboBox.setSelectedIndex(umLivro.getTipo());

				cancelado();
			} catch (LivroNaoEncontradoException e1) {
				novo();

				JOptionPane.showMessageDialog(this, "Livro não encontrado", "", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if (obj == buscarButton) {
			DialogTabelaLivro dialog = new DialogTabelaLivro(this);
			dialog.setVisible(true);
		}
	}

	private boolean validaLivro() {
		boolean deuErro = false;
		if (tituloTextField.getText().trim().length() == 0) {
			deuErro = true;
			nomeMensagem.setText("Campo de preenchimento obrigatório");
		} else {
			nomeMensagem.setText("");
		}

		if (autorTextField.getText().trim().length() == 0) {
			deuErro = true;
			autorMensagem.setText("Campo de preenchimento obrigatório");
		} else {
			autorMensagem.setText("");
		}

		if (tipoComboBox.getSelectedIndex() == 0) {
			deuErro = true;
			generoMensagem.setText("Campo de preenchimento obrigatório");
		} else {
			generoMensagem.setText("");
		}

		if (editoraTextField.getText().trim().length() == 0) {
			deuErro = true;
			editoraMensagem.setText("Campo de preenchimento obrigatório");
		} else {
			editoraMensagem.setText("");
		}

		if (preçoTextField.getText().trim().length() == 0) {
			deuErro = true;
			preçoMensagem.setText("Campo de preenchimento obrigatório");
		} else {
			preçoMensagem.setText("");
		}

		return deuErro;
	}

	public void novo() {
		tituloTextField.setEnabled(true);
		autorTextField.setEnabled(true);
		tipoComboBox.setEnabled(true);
		editoraTextField.setEnabled(true);
		preçoTextField.setEnabled(true);

		tituloTextField.setText("");
		autorTextField.setText("");
		tipoComboBox.setSelectedIndex(0);
		editoraTextField.setText("");
		preçoTextField.setText("");

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo() {
		tituloTextField.setEnabled(false);
		autorTextField.setEnabled(false);
		tipoComboBox.setEnabled(false);
		editoraTextField.setEnabled(false);
		preçoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void editavel() {
		tituloTextField.setEnabled(true);
		autorTextField.setEnabled(true);
		tipoComboBox.setEnabled(true);
		editoraTextField.setEnabled(true);
		preçoTextField.setEnabled(true);

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
	}

	public void removido() {
		tituloTextField.setEnabled(false);
		autorTextField.setEnabled(false);
		tipoComboBox.setEnabled(false);
		editoraTextField.setEnabled(false);
		preçoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado() {
		tituloTextField.setEnabled(false);
		autorTextField.setEnabled(false);
		tipoComboBox.setEnabled(false);
		editoraTextField.setEnabled(false);
		preçoTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
