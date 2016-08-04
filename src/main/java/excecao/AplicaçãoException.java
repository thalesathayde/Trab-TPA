package excecao;

public class AplicaçãoException extends Exception  {


	private static final long serialVersionUID = 1L;

	public AplicaçãoException(Exception e)
	{	super(e);
	}

	public AplicaçãoException(String msg) {
		super(msg);
	}
}
