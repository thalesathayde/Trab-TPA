package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.AutorNaoEncontradoException;
import modelo.Autor;
import servico.AutorService;

public class DialogAutor extends JDialog implements ActionListener 
{
	private static final long serialVersionUID = 1L;

	private static AutorService autorService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	autorService = (AutorService)fabrica.getBean ("autorService");
    }
	
	private JButton novoButton;
	private JButton cadastrarButton;
	private JButton editarButton;
	private JButton alterarButton;
	private JButton removerButton;
	private JButton cancelarButton;
	private JButton buscarButton;
	
	private JTextField nomeTextField;
	private JRadioButton sexoFemRadioButton;
	private JRadioButton sexoMascRadioButton;
	private JComboBox<String> idadeComboBox;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel nomeMensagem;
	private JLabel sexoMensagem;
	private JLabel idadeMensagem;
	

	private JPanel panel;
	
	private Autor umAutor;
	private JTextField nacionalidadeTextField;
	private JLabel nacionalidadeMensagem;
	
	public void designaAutorAFrame(Autor umAutor)
	{
		this.umAutor = umAutor;
		
		nomeTextField.setText(umAutor.getNome());
		
		if(umAutor.getSexo().equals("M"))
			sexoMascRadioButton.setSelected(true);
		else
			sexoFemRadioButton.setSelected(true);
		
		idadeComboBox.setSelectedIndex(umAutor.getIdade());
		nacionalidadeTextField.setText(umAutor.getNacionalidade());
		
		nomeMensagem.setText("");
		sexoMensagem.setText("");
		idadeMensagem.setText("");
		nacionalidadeMensagem.setText("");
	}
	
	public DialogAutor(JFrame frame)
	{
		super(frame);
		
		setBounds(105, 147, 618, 330);
		setTitle("Cadastro de Autores");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 602, 292);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel cadastrarLabel = new JLabel("Cadastro de Autores");
		cadastrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cadastrarLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		cadastrarLabel.setBounds(189, 21, 190, 26);
		panel.add(cadastrarLabel);
		
		JLabel nomeLabel = new JLabel("Nome");
		nomeLabel.setBounds(62, 78, 46, 14);
		panel.add(nomeLabel);
		
		nomeTextField = new JTextField();
		nomeLabel.setLabelFor(nomeTextField);
		nomeTextField.setBounds(138, 75, 278, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(50);
		nomeTextField.setBackground(SystemColor.window);
		
		JLabel sexoLabel = new JLabel("Sexo");
		sexoLabel.setBounds(62, 116, 46, 14);
		panel.add(sexoLabel);
		
		sexoMascRadioButton = new JRadioButton("Masculino");
		sexoLabel.setLabelFor(sexoMascRadioButton);
		buttonGroup.add(sexoMascRadioButton);
		sexoMascRadioButton.setBounds(138, 113, 88, 18);
		sexoMascRadioButton.setBackground(SystemColor.control);
		panel.add(sexoMascRadioButton);
		
		sexoFemRadioButton = new JRadioButton("Feminino");
		buttonGroup.add(sexoFemRadioButton);
		sexoFemRadioButton.setBounds(228, 113, 110, 18);
		sexoFemRadioButton.setBackground(SystemColor.control);

		panel.add(sexoFemRadioButton);
		
		JLabel idadeLabel = new JLabel("Idade");
		idadeLabel.setBounds(62, 156, 46, 14);
		panel.add(idadeLabel);
		
		idadeComboBox = new JComboBox<String>();
		idadeLabel.setLabelFor(idadeComboBox);
		idadeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"", "at\u00E9 30 anos", "de 31 a 40 anos", "de 41 a 50 anos", "acima de 50 anos"}));
		idadeComboBox.setBounds(138, 152, 142, 20);
		idadeComboBox.setBackground(SystemColor.window);
		panel.add(idadeComboBox);
		
		nomeMensagem = new JLabel("");
		nomeMensagem.setForeground(Color.RED);
		nomeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nomeMensagem.setBounds(138, 92, 241, 14);
		panel.add(nomeMensagem);
		
		sexoMensagem = new JLabel("");
		sexoMensagem.setForeground(Color.RED);
		sexoMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		sexoMensagem.setBounds(138, 127, 241, 14);
		panel.add(sexoMensagem);
		
		idadeMensagem = new JLabel("");
		idadeMensagem.setForeground(Color.RED);
		idadeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		idadeMensagem.setBounds(138, 170, 241, 14);
		panel.add(idadeMensagem);
		
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
		
		JLabel nacionalidadeLabel = new JLabel("Nacionalidade");
		nacionalidadeLabel.setBounds(62, 191, 72, 14);
		panel.add(nacionalidadeLabel);
		
		nacionalidadeTextField = new JTextField();
		nacionalidadeTextField.setColumns(50);
		nacionalidadeTextField.setBackground(Color.WHITE);
		nacionalidadeTextField.setBounds(138, 186, 110, 20);
		panel.add(nacionalidadeTextField);
		
		nacionalidadeMensagem = new JLabel("");
		nacionalidadeMensagem.setForeground(Color.RED);
		nacionalidadeMensagem.setFont(new Font("Tahoma", Font.PLAIN, 9));
		nacionalidadeMensagem.setBounds(138, 205, 241, 14);
		panel.add(nacionalidadeMensagem);
		buscarButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object obj = e.getSource();
		if (obj == novoButton)
		{	novo();
		}
		else if (obj == cadastrarButton)
		{
			boolean deuErro = validaAutor();
			
			if(!deuErro)
			{	
				umAutor = new Autor();
				umAutor.setNome(nomeTextField.getText().toUpperCase());
				umAutor.setSexo(sexoMascRadioButton.isSelected() ? "M" : "F");
				umAutor.setIdade(idadeComboBox.getSelectedIndex());
				umAutor.setNacionalidade(nacionalidadeTextField.getText().toUpperCase());

				autorService.inclui(umAutor);
				
				salvo();
				
				JOptionPane.showMessageDialog(this, "Autor cadastrado com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(obj == editarButton)
		{
			editavel();
		}
		else if(obj == alterarButton)
		{
			boolean deuErro = validaAutor();
			
			if(!deuErro)
			{	
				umAutor.setNome(nomeTextField.getText().toUpperCase());
				umAutor.setSexo(sexoMascRadioButton.isSelected() ? "M" : "F");
				umAutor.setIdade(idadeComboBox.getSelectedIndex());
				umAutor.setNacionalidade(nacionalidadeTextField.getText().toUpperCase());


				try 
				{
					autorService.altera(umAutor);
					
					salvo();
					
					JOptionPane.showMessageDialog(this, "Autor atualizado com sucesso", "", 
							JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (AutorNaoEncontradoException e1) 
				{
					novo();
					
					JOptionPane.showMessageDialog(this, "Autor não encontrado", "", 
						JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		else if(obj == removerButton)
		{
			try 
			{
				autorService.exclui(umAutor);

				removido();
				
				JOptionPane.showMessageDialog(this, "Autor removido com sucesso", "", 
						JOptionPane.INFORMATION_MESSAGE);
			} 
			catch (AutorNaoEncontradoException e1) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Autor não encontrado", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == cancelarButton)
		{
			try 
			{
				umAutor = autorService.recuperaUmAutor(umAutor.getNumero());

				nomeTextField.setText(umAutor.getNome());
				
				if(umAutor.getSexo().equals("M"))
					sexoMascRadioButton.setSelected(true);
				else
					sexoFemRadioButton.setSelected(true);
				
				idadeComboBox.setSelectedIndex(umAutor.getIdade());
				nacionalidadeTextField.setText(umAutor.getNacionalidade());
				
				
				cancelado();
			} 
			catch (AutorNaoEncontradoException e1) 
			{
				novo();
				
				JOptionPane.showMessageDialog(this, "Autor não encontrado", "", 
					JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(obj == buscarButton)
		{	
			DialogTabelaAutor dialog = new DialogTabelaAutor(this);
			dialog.setVisible(true);
		}
	}
	
	private boolean validaAutor()
	{
		boolean deuErro = false;
		if (nomeTextField.getText().trim().length() == 0)
		{	deuErro = true;
			nomeMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	nomeMensagem.setText("");
		}
		
		if (!sexoMascRadioButton.isSelected() && !sexoFemRadioButton.isSelected())
		{	deuErro = true;
			sexoMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	sexoMensagem.setText("");
		}

		if (idadeComboBox.getSelectedIndex() == 0)
		{	deuErro = true;
			idadeMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	idadeMensagem.setText("");
		}
		
		if (nacionalidadeTextField.getText().trim().length() == 0)
		{	deuErro = true;
			nacionalidadeMensagem.setText("Campo de preenchimento obrigatório");
		}
		else
		{	nacionalidadeMensagem.setText("");
		}
		
		return deuErro;
	}
	
	public void novo()
	{
		nomeTextField.setEnabled(true);
		sexoMascRadioButton.setEnabled(true);
		sexoFemRadioButton.setEnabled(true);
		idadeComboBox.setEnabled(true);
		nacionalidadeTextField.setEnabled(true);
		
		nomeTextField.setText("");
		buttonGroup.clearSelection();
		idadeComboBox.setSelectedIndex(0);
		nacionalidadeTextField.setText("");

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(true);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void salvo()
	{
		nomeTextField.setEnabled(false);
		sexoMascRadioButton.setEnabled(false);
		sexoFemRadioButton.setEnabled(false);
		idadeComboBox.setEnabled(false);
		nacionalidadeTextField.setEnabled(false);

		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void editavel()
	{
		nomeTextField.setEnabled(true);
		sexoMascRadioButton.setEnabled(true);
		sexoFemRadioButton.setEnabled(true);
		idadeComboBox.setEnabled(true);
		nacionalidadeTextField.setEnabled(true);

		novoButton.setEnabled(false);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(true);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(true);
		buscarButton.setEnabled(false);
	}

	public void removido()
	{
		nomeTextField.setEnabled(false);
		sexoMascRadioButton.setEnabled(false);
		sexoFemRadioButton.setEnabled(false);
		idadeComboBox.setEnabled(false);
		nacionalidadeTextField.setEnabled(false);
		
		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(false);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}

	public void cancelado()
	{
		nomeTextField.setEnabled(false);
		sexoMascRadioButton.setEnabled(false);
		sexoFemRadioButton.setEnabled(false);
		idadeComboBox.setEnabled(false);
		nacionalidadeTextField.setEnabled(false);
		
		novoButton.setEnabled(true);
		cadastrarButton.setEnabled(false);
		editarButton.setEnabled(true);
		alterarButton.setEnabled(false);
		removerButton.setEnabled(true);
		cancelarButton.setEnabled(false);
		buscarButton.setEnabled(true);
	}
}
