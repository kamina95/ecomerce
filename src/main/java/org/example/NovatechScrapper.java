package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import user.product.Product;

import java.math.BigDecimal;

public class NovatechScrapper {

    public static Document doc;
    private static final String ABS_URL = "https://www.novatech.co.uk";

    public static eCommerceDao ecommercedao = new eCommerceDao();

    public static void scrapeAll(){
        Elements productUrlList = new Elements();
        String url = "https://www.novatech.co.uk/products/components/nvidiageforcegraphicscards/?pg=";
        for(int i=1; i<=4 ; i++){
            productUrlList.addAll(returnElements(url+i));
        }
        System.out.println(productUrlList.size());
        extractFeatures(productUrlList);
    }


    public static void extractFeatures(Elements products){

        ecommercedao.init();
        for(Element e: products){
            String price = "";
            BigDecimal newPrice = new BigDecimal(-1);
            String title = e.select(".search-box-title > h2 > a").text();
            String url = ABS_URL + e.select(".search-box-title > h2 > a").attr("href");
            String desc = e.select(".search-box-details").text();
            Element priceElement = e.select(".newspec-price-listing").first();
            if (priceElement != null){
                price = priceElement.text().replace("£", "").replace(" inc vat", "");
                newPrice = new BigDecimal(price);
            }
            System.out.println(title + " \n  " + url + "  \n " + desc + " \n " + newPrice.toString());
            if (newPrice.compareTo(new BigDecimal(-1)) != 0){
                createProducts(title, desc, newPrice);
            }
        }
    }

    public static void createProducts(String title, String desc, BigDecimal price){
        Product newProduct = new Product();
        newProduct.setProduct_price(price);
        newProduct.setProduct_name(title);
        newProduct.setProduct_desc(desc);
        ecommercedao.simpleSave(newProduct);
    }

    public static Elements returnElements(String url) {
        try {
            doc = Jsoup.connect(url).get();
            //System.out.println(doc.toString());
        }
        catch(Exception e){
            System.out.println("Error");
        }
        assert doc!=null;
        return doc.select(".col-xs-12 > div.search-box-results");
    }

    public static void main(String[] args) {
            scrapeAll();
    }
}


