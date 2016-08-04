package visao;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.LivroNaoEncontradoException;
import modelo.Livro;
import servico.LivroService;

// Essa classe tem os seguintes métodos:
// - getTableCellRendererComponent() - Método que renderiza o botão
// - getTableCellEditorComponent() - Método que indica qual botão foi clicado
// - actionPerformed() - Método listener do botão
public class ButtonColumnLivro extends AbstractCellEditor implements
		TableCellRenderer, TableCellEditor, ActionListener
{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton button;
	private DialogTabelaLivro dialogTabelaLivro;
	private DialogLivro dialogLivro;
	
	private static LivroService livroService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	livroService = (LivroService)fabrica.getBean ("livroService");
    }

	public ButtonColumnLivro(JTable table, int coluna, 
			            DialogTabelaLivro dialogTabelaLivro, 
			            DialogLivro dialogLivro)
	{
		//super();
		this.table = table;
		this.dialogTabelaLivro = dialogTabelaLivro;
		this.dialogLivro = dialogLivro;
		
		button = new JButton();
		button.setText("Editar");
		button.addActionListener(this);

		TableColumnModel tableColumnModel = table.getColumnModel();
		
		// Designa um renderizador (o objeto corrente) para o botão
		tableColumnModel.getColumn(coluna).setCellRenderer(this);
			// getTableCellRendererComponent()
			
		// Designa um editor (o objeto corrente) para o botão
		tableColumnModel.getColumn(coluna).setCellEditor(this);
			// getTableCellEditorComponent()
			// getCellEditorValue()
	}
	
	// Esse método retorna o botão que será exibido
	@Override	// Método de TableCellRenderer
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column)
	{
		// A cor utilizada para exibir as letras será a do componente (table), isto é, preto
		button.setForeground(table.getForeground());
		
		// Será utilizada como cor de fundo a cor default para os botões
		button.setBackground(UIManager.getColor("Button.background"));

		return button; // Esse é o botão que será exibido.
		
		// hasFocus = true quando navegamos com TAB e entramos na célula do botão
		// isSelected = true quando clicamos na linha do botão em alguma outra célula
		// hasFocus = false e isSelected = false quando a janela é exibida.
	}

	// retorna o botão que foi clicado. No clique no botão, por exemplo, o botão pode mudar de cor.
	@Override	// Método de TableCellEditor
	public Component getTableCellEditorComponent(JTable table, Object value, 
			boolean isSelected, int row, int column)
	{
		// IMPORTANTE
		
		// Quando um botão é clicado, esse método é executado, e em seguida o 
		// listener do botão (actionPerformed()) é executado.
		
		return button;  // Se mudar para null o clique do botão pára de funcionar.
						
	}

	@Override	// Método de TableCellEditor - Não é executado
	public Object getCellEditorValue()
	{
		return null;
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			Livro umLivro = livroService.recuperaUmLivro((Long)table
				.getValueAt(table.getSelectedRow(), 0));
			dialogLivro.designaLivroAFrame(umLivro);
			dialogLivro.editavel();
			dialogTabelaLivro.dispose();
		} 
		catch (LivroNaoEncontradoException e1)
		{ 
			dialogLivro.novo();
			dialogTabelaLivro.dispose();
			JOptionPane.showMessageDialog(dialogLivro, "Livro não encontrado", "", JOptionPane.ERROR_MESSAGE);
		}
	}
}
