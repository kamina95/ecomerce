package scrapers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScraperMain {
    public static void main( String[] args ) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ThreadService.class);
        ThreatController threatController = context.getBean(ThreatController.class);
        threatController.runScrapers();
    }
}
