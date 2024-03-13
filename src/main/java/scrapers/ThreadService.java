package scrapers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for defining Spring beans related to web scrapers and data access.
 */
@Configuration
public class ThreadService {


    /**
     * Bean definition for NovatechScraper.
     *
     * @return A new instance of NovatechScraper with a configured GraphicDAO.
     */
    @Bean
    public NovatechScraper myNovatech() {
        NovatechScraper novatechScraper = new NovatechScraper();
        novatechScraper.setHibernate(myGraphicDAO());
        return novatechScraper;
    }


    /**
     * Bean definition for OverclockersScraper.
     *
     * @return A new instance of OverclockersScraper with a configured GraphicDAO.
     */
    @Bean
    public OverclockersScraper myOverclockersScraper() {
        OverclockersScraper overclockersScraper = new OverclockersScraper();
        overclockersScraper.setHibernate(myGraphicDAO());
        return overclockersScraper;
    }


    /**
     * Bean definition for ThreatController.
     *
     * @return A new instance of ThreatController with an array of configured scrapers and a configured GraphicDAO.
     */
    @Bean
    public ThreatController myThreatController() {
        ThreatController threatController = new ThreatController();
        Scraper[] scrapers = { myNovatech(), myOverclockersScraper()};
        threatController.setScrapers(scrapers);
        return threatController;
    }

    /**
     * Bean definition for GraphicDAO.
     *
     * @return A new instance of GraphicDAO with initialization.
     */
    @Bean
    public ECommerceDao myGraphicDAO() {
        ECommerceDao eCommerceDao = new ECommerceDao();
        eCommerceDao.init();
        return eCommerceDao;
    }
}
