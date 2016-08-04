package visao;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class DialogTabelaLivro extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTextField nomeTextField;
	private JTable table;
	private LivroModel livroModel;
	private JScrollPane scrollPane;
	
	private JComboBox<String> tipoComboBox;
	
	private TableColumnModel columnModel;
	private TableColumn sexoColumn;

	
	DialogLivro dialogLivro;
	
	public DialogTabelaLivro(DialogLivro dialogLivro)
	{
		super(dialogLivro);
		
		this.dialogLivro = dialogLivro;
		
		setTitle("Pesquisa de Livros");
		setBounds(110, 171, 608, 301);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 588, 111);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPesquisaPorTitulo = new JLabel("Pesquisa por Titulo");
		lblPesquisaPorTitulo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPesquisaPorTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisaPorTitulo.setBounds(203, 11, 195, 22);
		panel.add(lblPesquisaPorTitulo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNome.setBounds(92, 42, 55, 22);
		panel.add(lblNome);
		
		nomeTextField = new JTextField();
		nomeTextField.setBackground(UIManager.getColor("Button.light"));
		nomeTextField.setForeground(SystemColor.desktop);
		nomeTextField.setBounds(142, 44, 324, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(this);
		btnPesquisar.setBounds(249, 75, 102, 23);
		panel.add(btnPesquisar);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(0, 112, 588, 144);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		/**********************************************************************/
		


		tipoComboBox = new JComboBox<String>();
		tipoComboBox.addItem("Aventura");
		tipoComboBox.addItem("Bibliografias");
		tipoComboBox.addItem("Didáticos");
		tipoComboBox.addItem("Ficção");
		tipoComboBox.addItem("Horror");	
		tipoComboBox.addItem("Infantil");
		tipoComboBox.addItem("Informatica");
		tipoComboBox.addItem("Romance");
		tipoComboBox.addItem("Outros...");

		columnModel = table.getColumnModel();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		livroModel = new LivroModel();	
		livroModel.setTituloLivro(nomeTextField.getText());
		table.setModel(livroModel);

		// Designa um renderer e um editor para os botões.
		// Para cada botão exibido é executado o método getTableCellRendererComponent() 
		// que retorna o botão que deve ser renderizado.
		// E sempre que um botão é clicado é executado o método getTableCellEditorComponent()
		// que retorna o botão que foi clicado para que o listener deste botão possa ser executado.
		new ButtonColumnLivro(table, 6, this, dialogLivro);

        // Designa um novo editor para a coluna da tabela
        // Este novo editor é um ComboBox
		sexoColumn = columnModel.getColumn(LivroModel.COLUNA_TIPO);
		sexoColumn.setCellEditor(new DefaultCellEditor(tipoComboBox));
		

		// Designa um valor preferido para a coluna. Se ele for menor
		// ou maior do que o máximo possível, ele será ajustado.
		columnModel.getColumn(0).setPreferredWidth(50);
		columnModel.getColumn(1).setPreferredWidth(150);
		columnModel.getColumn(2).setPreferredWidth(30);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(70);
		columnModel.getColumn(5).setPreferredWidth(60);
		
		scrollPane.setVisible(true);
	}
}
