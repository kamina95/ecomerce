package scrapers;

import user.product.Product;


import java.math.BigDecimal;

/**
 * Abstract class representing a web scraper for graphic cards.
 */
public abstract class Scraper implements Runnable {

    /**
     * Data Access Object for interacting with the database.
     */
    public ECommerceDao eCommerceDao;

    /**
     * Flag to indicate whether the scraping process should stop.
     */
    private volatile boolean stop = false;

    /**
     * Counter to keep track of the number of graphic cards scraped.
     */
    public static int count = 0;

    /**
     * Implementation of the Runnable interface to initiate the scraping process.
     */
    public void run() {
        if (stop) return;
        scrapeAll();
        System.out.println(count);
    }

    /**
     * Method to stop the scraping process.
     */
    public void stop() {
        stop = true;
    }

    /**
     * Abstract method to be implemented by subclasses for scraping graphic card information.
     */
    public abstract void scrapeAll();


    public void setHibernate(ECommerceDao eCommerceDao) {
        this.eCommerceDao = eCommerceDao;
    }


    public void createProducts(String title, String desc, BigDecimal price){
        Product newProduct = new Product();
        newProduct.setProduct_price(price);
        newProduct.setProduct_name(title);
        newProduct.setProduct_desc(desc);
        eCommerceDao.simpleSave(newProduct);
    }

    /**
     * A utility method to pause the execution for a specified amount of time.
     * This is typically used to wait for page elements to load.
     *
     * @param milliseconds The amount of time to sleep in milliseconds.
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
