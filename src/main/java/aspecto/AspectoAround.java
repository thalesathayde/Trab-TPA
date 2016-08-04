package aspecto;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import excecao.Aplica��oException;

//Para utilizar este aspecto � preciso:
// 1. Sempre que for definida uma nova constraint no CREATE TABLE ser� necess�rio acrescentar 
//    esta nova constraint nesta classe.
// 2. Criar uma classe de exce��o nova para esta constraint acrescentada ao CREATE TABLE.
// 3. Acrescentar c�digo referente a esta nova constraint nos m�todos afetados do managedbean.

@Aspect
public class AspectoAround 
{   private static Logger logger;

	static {
		logger = Logger.getLogger(AspectoAround.class);
		
	}
	@Pointcut("execution(* visao.*.*(..))")
	public void traduzExcecaoAround() {}

	@Around("traduzExcecaoAround()")
	public Object traduzExcecaoAround(ProceedingJoinPoint joinPoint) throws Throwable 
	{	try
		{	System.out.println("ASPECTO ENTRANDO");	
			Object o = joinPoint.proceed();
			System.out.println("ASPECTO DEPOIS");
			return o;
		}
		catch(Throwable e)
		{	
			if(!(e instanceof Aplica��oException)){
				
				logger.error("Falha na aplica��o. Erro : "+e.getMessage());
			}
			System.out.println("TESTE 222");	

			

			

			throw e;
		}
	}
}