/*
 * FacturasApp.java
 */
package facturasapp;

import facturasapp.data.DataManager;
import facturasapp.data.StoreManager;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import storeDataModel.*;

/**
 * The main class of the application.
 */
public class FacturasApp extends SingleFrameApplication {

    public FacturasApp() {
    }

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup() {
        show(new FacturasView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of FacturasApp
     */
    public static FacturasApp getApplication() {
        return Application.getInstance(FacturasApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {

        //Set Global Data objects
        Invoice invoice = new Invoice("default");
        DataManager.getInstance().addDataObject("currentInvoice", invoice);

        Properties props = new Properties();
        try {
            props.load(new FileInputStream("facturasBuilder.properties"));
            //End Set Data Objects
        } catch (Exception ex) {
            Logger.getLogger(InvoicePane.class.getName()).log(Level.SEVERE, null, ex);
        }

        DataManager.getInstance().addDataObject("invoiceProperties", props);
        //End Set Global Data Objects

        //Init Database
        StoreManager.init();

        launch(FacturasApp.class, args);
        //new FacturasApp().startup();
        //showInfo();
    }

    public static void showInfo() {
        System.out.println("===================================");
        System.out.println("Showing Info...");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        Query query = pm.newQuery("SELECT FROM " + Invoice.class.getName());
        List<Invoice> list = (List<Invoice>) query.execute();
        Iterator facturasIt = list.iterator();
        while (facturasIt.hasNext()) {
            Invoice aFactura = (Invoice) facturasIt.next();
            System.out.println("---------------");
            System.out.println(aFactura);

            ArrayList<ProductRelation> productRelations = aFactura.getProductRelations();

            Iterator producRelIt = productRelations.iterator();
            while (producRelIt.hasNext()) {
                ProductRelation aProductRelation = (ProductRelation) producRelIt.next();
                System.out.println(aProductRelation);
            }
        }

        pm.close();
    }
}
