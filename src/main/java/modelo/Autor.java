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
			(	name = "Autor.recuperaUmAutor",
				query = "select a from Autor a where a.numero = ?1"
			),
			@NamedQuery
			(	name = "Autor.recuperaAutores",
				query = "select a from Autor a order by a.numero asc"
			),
			@NamedQuery
			(	name = "Autor.recuperaQtdPeloTitulo",
				query = "select count(a) from Autor a where a.nome like ?1"
			),
		})


@Entity
@Table(name="AUTOR")
@SequenceGenerator(name="SEQUENCIA2", 
		           sequenceName="SEQ_AUTOR",
		           allocationSize=1)

public class Autor 
{
	private Long numero;
	private String nome;
	private String sexo;
	private int idade;
	private String nacionalidade;

	
	public Autor(String nome, String sexo, int idade, String nacionalidade ) 
	{
		super();
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.nacionalidade = nacionalidade;

	}

	public Autor() 
	{	
	}

	public String toString()
	{	return "Numero = " + numero + " nome = " + nome + " sexo = " + sexo + " idade = " + idade + " nacionalidade = " + nacionalidade;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQUENCIA2")
	@Column(name="NUMERO")
	public Long getNumero() 
	{	return numero;
	}

	@SuppressWarnings("unused")
	private void setNumero(Long numero) 
	{	this.numero = numero;
	}
	
	public String getNome() 
	{	return nome.toUpperCase();
	}
	
	public void setNome(String nome) 
	{	this.nome = nome;
	}
	
	public String getSexo() 
	{	return sexo;
	}
	
	public void setSexo(String sexo) 
	{	this.sexo = sexo;
	}
	
	public int getIdade() 
	{	return idade;
	}
	
	public void setIdade(int idade) 
	{	this.idade = idade;
	}
	
	public String getNacionalidade() 
	{	return this.nacionalidade;
	}
	
	public void setNacionalidade(String nacionalidade) 
	{	this.nacionalidade = nacionalidade;
	}
	

}
