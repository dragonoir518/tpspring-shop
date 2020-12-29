package fr.training.samples.spring.shop.exposition.common;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    //il fait rien, cette méthode ça sert à poser une annotation.
    //méthode pour qu'on puisse pouser une anotation

    //on logge execution de toutes les méthode public quelques soient leur arguments
    //@Pointcut("execution(public * fr.training.samples.spring.shop.application..*.*(..))")
    @Pointcut("within(fr.training.samples.spring.shop..*)")
    private void monPointCutExecutionMethodPublic() {

    }

    @Before("monPointCutExecutionMethodPublic()") //avant l'exécution de la méthode monPointCut
    public  void log(JoinPoint jp) {
        logger.info(jp.getSignature().toLongString()); //avoir le nom complet de la signature de la méthode.
        Object[] args = jp.getArgs();
        StringBuilder sb = new StringBuilder();
        for(Object obj: args) {
            sb.append(obj).append(" , ");
        }
        logger.info("Ici on logge les paramètres de la méthode : "+sb);// récupérer les paramètres et les logger

    }

    //Arround retourner quelques chose
    @Around("monPointCutExecutionMethodPublic()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        //logger le temps passé
        //debut
        long start = System.currentTimeMillis();

        Object obj = pjp.proceed();
        //fin
        long end = System.currentTimeMillis();

        logger.info("Temps exécution : "+(end-start) +" millisecondes");

        return obj;
    }

}
