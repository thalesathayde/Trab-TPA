package excecao;

public class Aplica��oException extends Exception  {


	private static final long serialVersionUID = 1L;

	public Aplica��oException(Exception e)
	{	super(e);
	}

	public Aplica��oException(String msg) {
		super(msg);
	}
}
