/**********************************************************************
Copyright (c) 2006 Andy Jefferson and others. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Contributors:
    ...
**********************************************************************/
package storeDataModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

public class Main
{
    public Main()
    {
        super();
    }

    public static void main(String[] args)
    {
        System.out.println("===================================");
    	System.out.println("Store Data Model Demo");
    	//init();
    	//insert();
        insertProduct();
    	showInfo();
    	//edit();
    	//editDetached();
    	//editHashSetDetached();
    	//delete();
    	//showInfo();
    }
    
    public static void delete(){
    	System.out.println("===================================");
    	System.out.println("Deleting...");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        
        System.out.println("Deleting all products from persistence");
        Query q = pm.newQuery(Product.class);
        long numberInstancesDeleted = q.deletePersistentAll();
        System.out.println("Deleted " + numberInstancesDeleted + " products");
        
        pm.close();
    }

        public static void insertProduct(){
        System.out.println("===================================");
    	System.out.println("Inserting...");
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();

        Product producto1 = new Product("Code001");
        pm.makePersistent(producto1);

        pm.close();

        }
    public static void insert(){
        System.out.println("===================================");
    	System.out.println("Inserting...");
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        
        Invoice factura1 = new Invoice("Matricula001");
        pm.makePersistent(factura1);
        
        Invoice factura2 = new Invoice("Matricula002");
        pm.makePersistent(factura2);
        
                
        Product producto1 = new Product("Code001");
        Product producto2 = new Product("Code002");
        Product producto3 = new Product("Code003");
        Product producto4 = new Product("Code004");
        
        factura1.addProductRelation(new ProductRelation(producto1, 2, 100));
        factura1.addProductRelation(new ProductRelation(producto2, 5, 200));
        factura1.addProductRelation(new ProductRelation(producto3, 1, 300));
        
        factura2.addProductRelation(new ProductRelation(producto1, 10, 250));
        factura2.addProductRelation(new ProductRelation(producto2, 12, 350));
        factura2.addProductRelation(new ProductRelation(producto3, 20, 450));
        factura2.addProductRelation(new ProductRelation(producto4, 1, 20));
       
        pm.close();
        
    }
    
    public static void edit(){
    	Invoice detachedInvoice = null;
    	
        System.out.println("===================================");
    	System.out.println("Editing...");
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();
        //Query query = pm.newQuery("SELECT FROM " + Invoice.class.getName());
        //Query query = pm.newQuery(Invoice.class, "matricula == \"Matricula001\"");
        Query query = pm.newQuery(Invoice.class, "matricula == p_matricula && id == p_id");
        query.declareParameters("String p_matricula, long p_id");
        
        query.setUnique(true);
        Invoice aInvoice = (Invoice) query.execute("matri01", 1);
        detachedInvoice = pm.detachCopy(aInvoice);
        /*List<Invoice> list = (List<Invoice>)query.execute("Matricula001", 1);
        Iterator invoiceIt = list.iterator();
        System.out.println("Elementos encontrados: " + list.size());
        
        while(invoiceIt.hasNext()){
        	Invoice aInvoice= (Invoice)invoiceIt.next();
        	//if(aInvoice.getMatricula().equals("Matricula001")){
        		//aInvoice.getMatricula();
//        		aInvoice.setMatricula("Matricula001");	
        	//}
      		
      		//Detach copy
        	detachedInvoice = pm.detachCopy(aInvoice);
        }*/
        
        pm.currentTransaction().commit();
        pm.close();
        
        /////Detached Test//////
        System.out.println("Detached Invoice: " + detachedInvoice);
        detachedInvoice.setModelo("Modelo pro");
        detachedInvoice.setBaseImponible(2);
        pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();
        
        pm.makePersistent(detachedInvoice);
        
        pm.currentTransaction().commit();
        pm.close();
        ////End Detached Test////
    }
    
    public static void editDetached(){
    	Invoice detachedInvoice = null;
    	
        System.out.println("===================================");
    	System.out.println("Editing...");
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();
        Query query = pm.newQuery(Invoice.class, "matricula == p_matricula && id == p_id");
        query.declareParameters("String p_matricula, long p_id");
        
        query.setUnique(true);
        Invoice aInvoice = (Invoice) query.execute("matri01", 1);

        pm.getFetchPlan().addGroup("detach_productsRelation");
        pm.getFetchPlan().addGroup("detach_product");
        pm.getFetchPlan().setMaxFetchDepth(2);
        detachedInvoice = pm.detachCopy(aInvoice);
        
        pm.currentTransaction().commit();
        pm.close();
        
        /////Detached Test//////
        System.out.println("Detached Invoice: " + detachedInvoice);
        detachedInvoice.setModelo("Modelo pro");
        detachedInvoice.setBaseImponible(2);
        //Show info
        //System.out.println("[Detached copy] ProductRels: " + detachedInvoice.getProductRelations());
        ArrayList<ProductRelation> rels = detachedInvoice.getProductRelations();
        Iterator it = rels.iterator();
        ProductRelation rel= (ProductRelation)it.next();
        System.out.println(rel);
  
        pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();
        pm.makePersistent(detachedInvoice);
        pm.currentTransaction().commit();
        pm.close();
        ////End Detached Test////
    }
    
    public static void editHashSetDetached(){
    	List<Invoice> invoiceList = null;
    	
        System.out.println("===================================");
    	System.out.println("Editing...");
    	PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();
        Query query = pm.newQuery(Invoice.class, "matricula == p_matricula");
        query.declareParameters("String p_matricula");
        
        //query.setUnique(true);
        List<Invoice> queryInvoiceList = (List<Invoice>) query.execute("matri01");

        pm.getFetchPlan().addGroup("detach_productsRelation");
        pm.getFetchPlan().addGroup("detach_product");
        pm.getFetchPlan().setMaxFetchDepth(3);
        invoiceList = (List<Invoice>) pm.detachCopyAll(queryInvoiceList);
        
        pm.currentTransaction().commit();
        pm.close();
        
        Iterator invoicesIt = invoiceList.iterator();
        while(invoicesIt.hasNext()){
        	Invoice detachedInvoice = (Invoice) invoicesIt.next();
	        /////Detached Test//////
	        System.out.println("Detached Invoice: " + detachedInvoice);
	        detachedInvoice.setModelo("Modelo pro");
	        detachedInvoice.setBaseImponible(2);
	        //Show info
	        //System.out.println("[Detached copy] ProductRels: " + detachedInvoice.getProductRelations());
	        ArrayList<ProductRelation> rels = detachedInvoice.getProductRelations();
	        Iterator it = rels.iterator();
	        ProductRelation rel= (ProductRelation)it.next();
	        System.out.println("rel:" + rel);
	  
	        pm = pmf.getPersistenceManager();
	        pm.currentTransaction().begin();
	        pm.makePersistent(detachedInvoice);
	        pm.currentTransaction().commit();
	        pm.close();
	        ////End Detached Test////
        }
    }
    
    public static void showInfo(){
    	System.out.println("===================================");
    	System.out.println("Showing Info...");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        PersistenceManager pm = pmf.getPersistenceManager();
        
        Query query = pm.newQuery("SELECT FROM " + Invoice.class.getName());
        List<Invoice> list = (List<Invoice>)query.execute();
        Iterator facturasIt = list.iterator();
        while(facturasIt.hasNext()){
        	Invoice aFactura= (Invoice)facturasIt.next();
        	System.out.println("---------------");
        	System.out.println(aFactura);
        	
        	ArrayList<ProductRelation> productRelations = aFactura.getProductRelations();
        	
        	Iterator producRelIt = productRelations.iterator();
        	while(producRelIt.hasNext()){
        		ProductRelation aProductRelation = (ProductRelation) producRelIt.next();
        		System.out.println(aProductRelation);
        	}
        }
        
        pm.close();
    }
      
    public static void init(){
        System.out.println("DataNucleus Samples : M-N Relation");
        System.out.println("===================================");
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

        // Persist some objects
        System.out.println(">> Persisting some Customers and Suppliers");
        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        Object[] facturaIds = new Object[2];
        Object[] productoIds = new Object[3];
        try
        {
            tx.begin();
            Invoice factura1 = new Invoice("matricula 1");
            Invoice factura2 = new Invoice("matricula 2");
            Product producto1 = new Product("codigo001");
            Product producto2 = new Product("codigo002");
            Product producto3 = new Product("codigo003");
            
            pm.makePersistent(factura1);
            pm.makePersistent(factura2);
            pm.makePersistent(producto1);
            pm.makePersistent(producto2);
            pm.makePersistent(producto3);
            tx.commit();

            facturaIds[0] = JDOHelper.getObjectId(factura1);
            System.out.println(facturaIds[0]);
            facturaIds[1] = JDOHelper.getObjectId(factura2);
            productoIds[0] = JDOHelper.getObjectId(producto1);
            productoIds[1] = JDOHelper.getObjectId(producto2);
            productoIds[2] = JDOHelper.getObjectId(producto3);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        // Establish the relationships
        System.out.println(">> Adding relationships between Customers and Suppliers");
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();
        
            Invoice factura1 = (Invoice)pm.getObjectById(facturaIds[0]);
            Invoice factura2= (Invoice)pm.getObjectById(facturaIds[1]);
            Product producto1 = (Product)pm.getObjectById(productoIds[0]);
            Product producto2 = (Product)pm.getObjectById(productoIds[1]);
        
            // Establish the relation factura1 uses producto2
            ProductRelation rel1 = new ProductRelation(producto2, 20, 50);
            factura1.addProductRelation(rel1);
            
            ProductRelation rel2 = new ProductRelation(producto1, 10, 150);
            factura1.addProductRelation(rel2);
            //producto2.addPack(factura1);

            // Establish the relation factura2 uses producto1
            //producto1.addPack(factura2);
            factura2.addProductRelation(rel1);
           
            tx.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }

        System.out.println(">> Retrieving Customer/Supplier");
        pm = pmf.getPersistenceManager();
        tx = pm.currentTransaction();
        try
        {
            tx.begin();

            Invoice factura1 = (Invoice)pm.getObjectById(facturaIds[0]);
            System.out.println(">> Retrieved factura : " + factura1);
            ArrayList productosRelations = factura1.getProductRelations();
            Iterator productosIter = productosRelations.iterator();
            while (productosIter.hasNext())
            {
                System.out.println(">>     with producto : " + productosIter.next());
            }
            
            Invoice factura2 = (Invoice)pm.getObjectById(facturaIds[1]);
            System.out.println(">> Retrieved factura : " + factura2);
            productosRelations = factura2.getProductRelations();
            productosIter = productosRelations.iterator();
            while (productosIter.hasNext())
            {
                System.out.println(">>     with producto : " + productosIter.next());
            }

            /*Producto producto2 = (Producto)pm.getObjectById(productoIds[1]);
            System.out.println(">> Retrieved producto : " + producto2);
            Set facturas = producto2.getPacks();
            Iterator facturasIter = facturas.iterator();
            while (facturasIter.hasNext())
            {
                System.out.println(">>     with factura : " + facturasIter.next());
            }*/

            tx.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.exit(3);
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
    }

}
