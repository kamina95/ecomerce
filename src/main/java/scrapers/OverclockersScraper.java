package scrapers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import user.product.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

/**
 * The {@code OverclockersScraper} class extends {@code Scraper} and is designed to scrape product data from the Overclockers website.
 * This class focuses on extracting information about graphics cards, including their features, prices, and descriptions.
 */
public class OverclockersScraper extends Scraper {

    // Logger for logging messages
    private static final Logger logger = Logger.getLogger(OverclockersScraper.class.getName());

    /**
     * Returns all product elements from a given page URL of the Overclockers website.
     * It uses Jsoup to parse HTML content and extract product information.
     *
     * @param pageUrl The URL of the page to scrape.
     * @return Elements containing product data.
     */
    public Elements returnAllProducts(String pageUrl) {
        Document doc = null;
        try {
            // Download HTML document from the website
            doc = Jsoup.connect(pageUrl).get();
        } catch (IOException ex) {
            // Log a warning if there's an exception while fetching the page
            logger.warning("Overclockers scraper not able to extract documentation from the page, the page will not be scraped \n" + ex.getMessage());
        }
        return doc.select("ck-product-box");
    }

    /**
     * Initiates the scraping process for multiple pages on the Overclockers website.
     * It iterates through a predetermined number of pages, extracting product information
     * from each and compiling it into a list.
     */
    @Override
    public void scrapeAll() {
        // List to store product elements
        Elements productUrlList = new Elements();
        for (int i = 1; i <= 8; i++) { // Looping through 8 pages
            // Scraping products from Overclockers website
            Elements productsList = returnAllProducts("https://www.overclockers.co.uk/pc-components/graphics-cards/nvidia-graphics-cards?page=" + i);
            productUrlList.addAll(productsList);
        }

        // Extracting elements from the product URLs
        extractElements(productUrlList);
    }

    /**
     * Extracts detailed information from a collection of product elements.
     * This method gathers product details such as URL, image URL, description, brand, and price,
     * and then calls another method to process or store this extracted information.
     *
     * @param elements Elements representing a collection of product listings.
     */
    public void extractElements(Elements elements) {
        for (Element element : elements) {
            // Extracting page URL, image URL, description, and brand
            String pageUrl = "https://www.overclockers.co.uk" + element.select("a").attr("href");
            String imgUrl = element.select("img.w-100.h-auto").attr("src");
            String description = element.select("img.w-100.h-auto").attr("alt");
            String brand = description.split(" ")[0];

            // Extracting price information
            Element priceSpan = element.select("span.price__amount").first();
            BigDecimal price = new BigDecimal(-1);
            if (priceSpan != null) {
                String priceText = priceSpan.text().replace("£", "").replace(",", "");
                price = new BigDecimal(priceText);
            }

            Document doc = null;
            String model = "";
            try {
                // Download HTML document from the product URL
                doc = Jsoup.connect(pageUrl).get();

                // Extracting model information from the breadcrumb
                Element anchor = doc.select(".breadcrumb .breadcrumb-item:last-of-type a").first();

                if (anchor != null) {
                    String extractedText = anchor.text();
                    model = extractedText.replace(" Graphics Cards", "");
                } else {
                    System.out.println("Element not found!");
                }

            } catch (IOException e) {
                // Logging info if an element from Overclocker scraper is not found
                logger.info("Element from Overclocker scraper not found");
            }

            // Calling the method to create classes with the extracted information
            createProducts(model, description, price);
        }
    }

//    public static void main(String[] args) {
//        scrapeAll();
//    }
}
