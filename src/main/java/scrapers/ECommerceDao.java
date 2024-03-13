package scrapers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import user.product.Product;

import java.util.List;

public class ECommerceDao {
    private SessionFactory sessionFactory;
    public void init(){
        try {
            //Create a builder for the standard service registry
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();

            //Load configuration from hibernate configuration file.
            //Here we are using a configuration file that specifies Java annotations.
            standardServiceRegistryBuilder.configure("hibernate.cfg.xml");

            //Create the registry that will be used to build the session factory
            StandardServiceRegistry registry = standardServiceRegistryBuilder.build();
            try {
                //Create the session factory - this is the goal of the init method.
                sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
            }
            catch (Exception e) {
                    /* The registry would be destroyed by the SessionFactory,
                        but we had trouble building the SessionFactory, so destroy it manually */
                System.err.println("Session Factory build failed.");
                e.printStackTrace();
                StandardServiceRegistryBuilder.destroy( registry );
            }

            //Ouput result
            System.out.println("Session factory built.");

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("SessionFactory creation failed." + ex);
        }
    }

//    public Product simpleSave(Product product){
//        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
//        session.saveOrUpdate(product);
//        session.getTransaction().commit();
//        session.close();
//        return product;
//    }

    public void simpleSave(Product comparison) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Query to retrieve GraphicCard based on the model
        String queryStr = "from Product where product_desc='" + comparison.getProduct_desc() + "'";
        List<Product> graphicCardList = session.createQuery(queryStr).getResultList();

         if (graphicCardList.size() == 0) {
            // Save or update GraphicCard if not found
            session.saveOrUpdate(comparison);
        }
        // Commit the transaction and close the session
        session.getTransaction().commit();
        session.close();
    }
}
