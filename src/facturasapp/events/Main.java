/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.events;

//import storeDataModel.Invoice;

/**
 *
 * @author CiberXtrem
 */
public class Main {

    public static void main(String argvs[]){
        /*FacturaEventManager.getInstance().addFacturaListener(new FacturaListener(){

            public void productAdded(FacturaEvent e) {
                System.out.println("Product ADDED!!");
                Invoice invoice = (Invoice)e.getSource();
                System.out.println("Source: " + invoice);
            }

            public void productRemoved(FacturaEvent e) {
                System.out.println("Product REMOVED!!");
                Invoice invoice = (Invoice)e.getSource();
                System.out.println("Source: " + invoice);
            }

        });

        Invoice invoice = new Invoice("Matricula 001");
        FacturaEventManager.getInstance().fireFacturaEvent(new FacturaEvent(invoice), FacturaEventManager.TYPE_PRODUCT_ADDED);

         */
    }
}
