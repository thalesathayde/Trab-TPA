package visao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import excecao.AutorNaoEncontradoException;
import modelo.Autor;
import servico.AutorService;

public class AutorModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	public static final int COLUNA_NUMERO = 0;
	public static final int COLUNA_NOME = 1;
	public static final int COLUNA_SEXO = 2;
	public static final int COLUNA_IDADE = 3;
	public static final int COLUNA_NACIONALIDADE = 4;
	public static final int COLUNA_ACAO = 5;
	
    private final static int NUMERO_DE_LINHAS_POR_PAGINA = 6;

	private String[] idades = {"", "até 30 anos", "de 31 a 40 anos", "de 41 a 50 anos", "acima de 50 anos" };

	private static AutorService autorService;
	
    static
    {
    	@SuppressWarnings("resource")
		ApplicationContext fabrica = new ClassPathXmlApplicationContext("beans-jpa.xml");

    	autorService = (AutorService)fabrica.getBean ("autorService");
    }

    private Map<Integer, Autor> cache;
    private int rowIndexAnterior = 0;
    private Integer qtd;
    private String nomeAutor;
    
    public AutorModel()
	{	
    	this.qtd = null;
		this.cache = new HashMap<Integer, Autor>(NUMERO_DE_LINHAS_POR_PAGINA * 4 / 75 /100 + 2);
	}

    public void setNomeAutor(String nomeAutor)
    {
    	this.nomeAutor = nomeAutor;
    }
    
	public String getColumnName(int c)
	{
		if(c == COLUNA_NUMERO) return "Número";
		if(c == COLUNA_NOME) return "Nome";
		if(c == COLUNA_SEXO) return "Sexo";
		if(c == COLUNA_IDADE) return "Idade";
		if(c == COLUNA_NACIONALIDADE) return "Nacionalidade";
		if(c == COLUNA_ACAO) return "Ação";
		return null;
	}
	
	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public int getRowCount() {
		if(qtd == null)
			qtd = (int)autorService.recuperaQtdPeloNome(nomeAutor);

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
					List<Autor> resultados = autorService.recuperaPeloNome(nomeAutor, rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1), NUMERO_DE_LINHAS_POR_PAGINA * 2);
				
					for (int j = 0; j < resultados.size(); j++) 
					{	Autor autor = resultados.get(j);
						cache.put(rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA - 1) + j, autor);
					}
				}
				else
				{
					int inicio = rowIndex - NUMERO_DE_LINHAS_POR_PAGINA;
					if (inicio < 0) inicio = 0;
				
					List<Autor> resultados = autorService.recuperaPeloNome(nomeAutor, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Autor autor = resultados.get(j);
						cache.put(inicio + j, autor);
					}
				}
			}
			else
			{
				if(rowIndex >= rowIndexAnterior) 
				{
					List<Autor> resultados = autorService.recuperaPeloNome(nomeAutor, rowIndex, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Autor autor = resultados.get(j);
						cache.put(rowIndex + j, autor);
					}
				}
				else
				{
					int inicio = rowIndex - (NUMERO_DE_LINHAS_POR_PAGINA * 2 - 1);
					if (inicio < 0) inicio = 0;

					List<Autor> resultados = autorService.recuperaPeloNome(nomeAutor, inicio, NUMERO_DE_LINHAS_POR_PAGINA * 2);
					
					for (int j = 0; j < resultados.size(); j++) 
					{	Autor autor = resultados.get(j);
						cache.put(inicio + j, autor);
					}
				}
			}
        }

		rowIndexAnterior = rowIndex;
        
		Autor autor = cache.get(rowIndex);

		if(columnIndex == COLUNA_NUMERO)
			return autor.getNumero();
		else if (columnIndex == COLUNA_NOME)
			return autor.getNome();
		else if (columnIndex == COLUNA_SEXO)
			return autor.getSexo();
		else if (columnIndex == COLUNA_IDADE)
			return idades[autor.getIdade()];
		else if (columnIndex == COLUNA_NACIONALIDADE)
			return autor.getNacionalidade();
		else
			return null;
	}
	
	// Para que os campos booleanos sejam renderizados como check box.
	// Neste caso, não há campo boleano.
	public Class<?> getColumnClass(int c)
	{
		Class<?> classe = null;
		if(c == COLUNA_NUMERO) classe = Integer.class;
		if(c == COLUNA_NOME) classe = String.class;
		if(c == COLUNA_SEXO) classe = String.class;
		if(c == COLUNA_IDADE) classe = Integer.class;
		if(c == COLUNA_NACIONALIDADE) classe = String.class;
		if(c == COLUNA_ACAO) classe = ButtonColumn.class;

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
		Autor umAutor = cache.get(r);

		if(c == COLUNA_NOME) umAutor.setNome((String)obj);
		if(c == COLUNA_SEXO) umAutor.setSexo((String)obj);

		if(c == COLUNA_IDADE)
		{
			if(((String)obj).equals("até 30 anos"))
				umAutor.setIdade(1);
			else if(((String)obj).equals("de 31 a 40 anos"))
				umAutor.setIdade(2);
			else if(((String)obj).equals("de 41 a 50 anos"))
				umAutor.setIdade(3);
			else if(((String)obj).equals("acima de 50 anos"))
				umAutor.setIdade(4);
		}
		
		if(c == COLUNA_NACIONALIDADE) umAutor.setNacionalidade((String)obj);

		try 
		{	autorService.altera(umAutor);
		} 
		catch (AutorNaoEncontradoException e) 
		{	e.printStackTrace();
		}
	}
}
