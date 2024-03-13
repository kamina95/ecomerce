//package scrapers;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import user.product.Product;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.logging.Logger;
//
//
///**
// * The {@code EbuyerScraper} class extends {@code Scraper} and is designed to scrape product data from the Ebuyer website.
// * It focuses on extracting information about various products, with a specific emphasis on Nvidia graphics cards.
// * This class uses Selenium WebDriver for web navigation and data extraction.
// */
//public class EbuyerScraper {
//
//    // Logger for logging messages
//    private static final Logger logger = Logger.getLogger(EbuyerScraper.class.getName());
//
//    // WebDriver instance for Chrome browser
//    private static WebDriver driver;
//
//    // Chrome options for configuring WebDriver
//    private static final ChromeOptions options = new ChromeOptions();
//
//    // Counter for counting the number of products scraped
//    private static int count = 0;
//
//    public static ECommerceDao ecommercedao = new ECommerceDao();
//
//    /**
//     * Initiates the scraping process for the Ebuyer website.
//     * It iterates over a set number of pages, extracting product information from each.
//     * This method is configured to run in headless mode using ChromeDriver.
//     */
//    public static void scrapeAll() {
//        // Configuring ChromeOptions to run in headless mode
//        options.setHeadless(false);
//        // Initializing ChromeDriver with configured options
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//
//        // Looping through pages (1 to 9) to scrape product information
//        for (int i = 1; i < 10; i++) {
//            try {
//                // Navigating to the Ebuyer page for Nvidia graphics cards
//                driver.get("https://www.ebuyer.com/store/Components/cat/Graphics-Cards-Nvidia?page=" + i);
//
//                // Scrolling down the page using JavaScriptExecutor
//                JavascriptExecutor js = (JavascriptExecutor) driver;
//                js.executeScript("window.scrollBy(0,2000)");
//                sleep(1000);
//                System.out.println("Page: " + i);
//
//                // Handling the cookie consent by clicking on the "OK" button if present
//                try {
//                    clickCookie();
//                } catch (Exception ignored) {
//                }
//
//                // Waiting for additional page content to load
//                sleep(2000);
//
//                // Extracting product information from the current page
//                productsInfo();
//
//                // Waiting for some time before moving to the next page
//                sleep(7000);
//            } catch (Exception e) {
//                // Logging a warning if there's an exception while scraping the page
//                logger.warning("Ebuyer scraper not able to extract documentation from the page, retrying\n" + e.getMessage());
//            }
//        }
//
//        // Closing the WebDriver instance
//        driver.close();
//        // Printing the total count of products scraped
//        System.out.println(count);
//    }
//
//    /**
//     * Extracts product information from a grid view on the current webpage.
//     * This method gathers details such as URL, image URL, description, brand, price,
//     * and model of each product and utilizes a method to create classes with this extracted information.
//     */
//    public static void productsInfo() {
//        WebElement grid = driver.findElement(By.cssSelector(".grid-view.js-taxonomy-view.is-active"));
//        List<WebElement> products = grid.findElements(By.cssSelector(".grid-item.js-listing-product"));
//        for (WebElement product : products) {
//            try {
//                System.out.println("Product: " + count);
//                System.out.println("Product: " + product.getText());
//                String description = product.findElement(By.cssSelector(".grid-item__title")).getText();
//                String priceSpan = product.findElement(By.cssSelector(".price")).getText().split(" ")[1];
//                BigDecimal price = new BigDecimal(-1);
//                if (priceSpan != null) price = new BigDecimal(priceSpan);
//
//                String model = product.findElement(By.cssSelector(".grid-item__ksp li:first-child")).getText();
//
//                // Calling the method to create classes with the extracted information
//                createProducts(model, description,price);
//                count++;
//            } catch (Exception e) {
//                // Logging info if an element from Ebuyer scraper is not found
//                logger.info("Element from Ebuyer scraper not found");
//            }
//        }
//    }
//
//    public static void createProducts(String title, String desc, BigDecimal price){
//        Product newProduct = new Product();
//        newProduct.setProduct_price(price);
//        newProduct.setProduct_name(title);
//        newProduct.setProduct_desc(desc);
//        ecommercedao.simpleSave(newProduct);
//    }
//
//    /**
//     * Handles the action of clicking the cookie consent button on the Ebuyer webpage.
//     * This method is essential for enabling further interactions and scraping on pages that require cookie consent.
//     */
//    public static void clickCookie() {
//        driver.findElement(By.cssSelector(".glyphicon.glyphicon-ok")).click();
//    }
//
//    public static void sleep(int milliseconds) {
//        try {
//            Thread.sleep(milliseconds);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//    public static void main(String[] args){
//        scrapeAll();
//    }
//}
