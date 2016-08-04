package visao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.LivroNaoEncontradoException;
import modelo.Livro;
import servico.LivroService;

public class LivroModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	public static final int COLUNA_NUMERO = 0;
	public static final int COLUNA_TITULO = 1;
	public static final int COLUNA_AUTOR = 2;
	public static final int COLUNA_TIPO = 3;
	public static final int COLUNA_EDITORA = 4;
	public static final int COLUNA_PRECO = 5;
	public static final int COLUNA_ACAO = 6;
	
    private final static int NUMERO_DE_LINHAS_POR_PAGINA = 6;

	private String[] tipo = {"", "Aventura", "Bibliografias", "Didáticos", "Ficção", "Horror", "Infantil", "Informatica", "Romance", "Outros..."};

	private static LivroService livroService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	livroService = (LivroService)fabrica.getBean ("livroService");
    }

    private Map<Integer, Livro> cache;
    private int rowIndexAnterior = 0;
    private Integer qtd;
    private String tituloLivro;
    
    public LivroModel()
	{	
    	this.qtd = null;
		this.cache = new HashMap<Integer, Livro>(NUMERO_DE_LINHAS_POR_PAGINA * 4 / 75 /100 + 2);
	}

    public void setTituloLivro(String tituloLivro)
    {
    	this.tituloLivro = tituloLivro;
    }
    
	public String getColumnName(int c)
	{
		if(c == COLUNA_NUMERO) return "Número";
		if(c == COLUNA_TITULO) return "Titulo";
		if(c == COLUNA_AUTOR) return "Autor";
		if(c == COLUNA_TIPO) return "Genero";
		if(c == COLUNA_EDITORA) return "Editora";
		if(c == COLUNA_PRECO) return "Preço";
		if(c == COLUNA_ACAO) return "Ação";
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		try {
				if(qtd == null){
					qtd = (int)livroService.recuperaQtdPeloTitulo(tituloLivro);
				}	
		}
		
		catch(Throwable e){	
			System.out.println("TESTE");
			throw e;
		}

		return qtd;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{   
		if (!cache.containsKey(rowIndex)) 
		{	
			if(cache.size() > (NUMERO_DE_LINHAS_POR_PAGINA * 3))
			{	
				cache.clear();
				
				if(rowIndex >= rowIndexAnterior) 
				{
					List<Livro> resultados = livroService.recuperaPeloTitulo(tituloLivro, rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);
				
					for (int j = 0; j < resultados.size(); j++) 
					{	Livro livro = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, livro);
					}
				}
				else
				{
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0) inicio = 0;
				
					List<Livro> resultados = livroService.recuperaPeloTitulo(tituloLivro, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Livro livro = resultados.get(j);
						cache.put(inicio + j, livro);
					}
				}
			}
			else
			{
				if(rowIndex >= rowIndexAnterior) 
				{
					List<Livro> resultados = livroService.recuperaPeloTitulo(tituloLivro, rowIndex, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Livro livro = resultados.get(j);
						cache.put(rowIndex + j, livro);
					}
				}
				else
				{
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0) inicio = 0;

					List<Livro> resultados = livroService.recuperaPeloTitulo(tituloLivro, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Livro livro = resultados.get(j);
						cache.put(inicio + j, livro);
					}
				}
			}
        }

		rowIndexAnterior = rowIndex;
        
        Livro livro = cache.get(rowIndex);

		if(columnIndex == COLUNA_NUMERO)
			return livro.getNumero();
		else if (columnIndex == COLUNA_TITULO)
			return livro.getTitulo();
		else if (columnIndex == COLUNA_AUTOR)
			return livro.getAutor();
		else if (columnIndex == COLUNA_TIPO)
			return tipo[livro.getTipo()];
		else if (columnIndex == COLUNA_EDITORA)
			return livro.getEditora();
		else if (columnIndex == COLUNA_PRECO)
			return livro.getPreco();
		else
			return null;
	}
	
	// Para que os campos booleanos sejam renderizados como check box.
	// Neste caso, não há campo boleano.
	public Class<?> getColumnClass(int c)
	{
		Class<?> classe = null;
		if(c == COLUNA_NUMERO) classe = Integer.class;
		if(c == COLUNA_TITULO) classe = String.class;
		if(c == COLUNA_AUTOR) classe = String.class;
		if(c == COLUNA_TIPO) classe = Integer.class;
		if(c == COLUNA_EDITORA) classe = String.class;
		if(c == COLUNA_PRECO) classe = Float.class;
		if(c == COLUNA_ACAO) classe = ButtonColumnLivro.class;

		return classe;
	}
	
	// Para que as células referentes às colunas 1 em diante possam ser editadas
	public boolean isCellEditable(int r, int c)
	{
		return c != 0;
	}

	@Override
	public void setValueAt(Object obj, int r, int c) 
	{
		Livro umLivro = cache.get(r);

		if(c == COLUNA_TITULO) umLivro.setTitulo((String)obj);
		if(c == COLUNA_AUTOR) umLivro.setAutor((String)obj);

		if(c == COLUNA_TIPO)
		{
			if(((String)obj).equals("Aventura"))
				umLivro.setTipo(1);
			else if(((String)obj).equals("Bibliografias"))
				umLivro.setTipo(2);
			else if(((String)obj).equals("Didáticos"))
				umLivro.setTipo(3);
			else if(((String)obj).equals("Ficção"))
				umLivro.setTipo(4);
			else if(((String)obj).equals("Horror"))
				umLivro.setTipo(5);
			else if(((String)obj).equals("Infantil"))
				umLivro.setTipo(6);
			else if(((String)obj).equals("Informatica"))
				umLivro.setTipo(7);
			else if(((String)obj).equals("Romance"))
				umLivro.setTipo(8);
			else if(((String)obj).equals("Outros..."))
				umLivro.setTipo(9);
		}
		
		if(c == COLUNA_EDITORA) umLivro.setEditora((String)obj);
		if(c == COLUNA_PRECO) umLivro.setPreco((Float)obj);

		try 
		{	livroService.altera(umLivro);
		} 
		catch (LivroNaoEncontradoException e) 
		{	e.printStackTrace();
		}
	}
}
