package aspecto;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import excecao.AplicaçãoException;

//Para utilizar este aspecto é preciso:
// 1. Sempre que for definida uma nova constraint no CREATE TABLE será necessário acrescentar 
//    esta nova constraint nesta classe.
// 2. Criar uma classe de exceção nova para esta constraint acrescentada ao CREATE TABLE.
// 3. Acrescentar código referente a esta nova constraint nos métodos afetados do managedbean.

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
			if(!(e instanceof AplicaçãoException)){
				
				logger.error("Falha na aplicação. Erro : "+e.getMessage());
			}
			System.out.println("TESTE 222");	

			

			

			throw e;
		}
	}
}