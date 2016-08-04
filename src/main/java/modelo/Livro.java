package modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries(
		{	@NamedQuery
			(	name = "Livro.recuperaUmLivro",
				query = "select l from Livro l where l.numero = ?1"
			),
			@NamedQuery
			(	name = "Livro.recuperaLivros",
				query = "select l from Livro l order by l.numero asc"
			),
			@NamedQuery
			(	name = "Livro.recuperaQtdPeloTitulo",
				query = "select count(l) from Livro l where l.titulo like ?1"
			),
		})

@Entity
@Table(name="LIVRO")
@SequenceGenerator(name="SEQUENCIA", 
		           sequenceName="SEQ_LIVRO",
		           allocationSize=1)

public class Livro
{
	private Long numero;
	private String titulo;
	private String autor;
	private int tipo;
	private String editora;
	private float preco;

	
	public Livro(String titulo, String autor, int tipo, String editora, float preço) 
	{
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.tipo = tipo;
		this.editora = editora;
		this.preco = preço;
	}

	public Livro() 
	{	
	}

	public String toString()
	{	return "Numero = " + numero + " Titulo = " + titulo + " Autor = " + autor + "Tipo = " + tipo + " Editora = " + editora + " Preço = " + preco;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCIA")
	@Column(name="NUMERO")
	public Long getNumero() 
	{	return numero;
	}

	@SuppressWarnings("unused")
	private void setNumero(Long numero) 
	{	this.numero = numero;
	}
	
	public String getTitulo() 
	{	return titulo.toUpperCase();
	}
	
	public void setTitulo(String nome) 
	{	this.titulo = nome;
	}
	
	public String getAutor() 
	{	return autor;
	}
	
	public void setAutor(String autor) 
	{	this.autor = autor;
	}
	
	public int getTipo()
	{	return tipo;
		
	}
	
	public void setTipo(int tipo)
	{	this.tipo = tipo;
		
	}
	
	public String getEditora() 
	{	return editora;
	}
	
	public void setEditora(String editora) 
	{	this.editora = editora;
	}
	
	public float getPreco() 
	{	return preco;
	}
	
	public void setPreco(float preco) 
	{	this.preco = preco;
	}
}
