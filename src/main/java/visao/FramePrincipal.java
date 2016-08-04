package visao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class FramePrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JMenuItem menuItemSair;
	private JPanel panel;
	private JFrame frame;
	private JMenuItem menuItemLivro;
	private JMenuItem menuItemAutor;
	
	public FramePrincipal() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 382);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCadastrar = new JMenu("Cadastro");
		menuBar.add(mnCadastrar);
	
		frame = this;
		
		
		menuItemAutor = new JMenuItem("Autor");
		menuItemAutor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));		
		menuItemAutor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogAutor dialogAutor = new DialogAutor(frame);
				dialogAutor.novo();
				dialogAutor.setVisible(true);
			}
		});
		mnCadastrar.add(menuItemAutor);
		
		
		menuItemLivro = new JMenuItem("Livro");
		menuItemLivro.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.ALT_MASK));
		menuItemLivro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogLivro dialogLivro = new DialogLivro(frame);
				dialogLivro.novo();
				dialogLivro.setVisible(true);
			}
		});
		mnCadastrar.add(menuItemLivro);
		
	
		
		menuItemSair = new JMenuItem("Sair");
		menuItemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK)); // Diz a combinação necessaria para chamar os action listeners
		menuItemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnCadastrar.add(menuItemSair);

		
		
		
		
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 614, 325);
		getContentPane().add(panel);
	}
}
