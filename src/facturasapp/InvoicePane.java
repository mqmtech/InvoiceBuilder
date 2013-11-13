/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoicePane.java
 *
 * Created on 24-jul-2010, 12:32:07
 */

package facturasapp;

import facturasapp.data.DataManager;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import storeDataModel.Invoice;
import sun.awt.VerticalBagLayout;

/**
 *
 * @author CiberXtrem
 */
public class InvoicePane extends javax.swing.JPanel {

    /** Creates new form InvoicePane */
    public InvoicePane() {
        //initComponents();
        initCustomComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
 public void initCustomComponents(){
     //this.setLayout(new GridLayout(4,0));
     this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
     this.add(invoiceStartPane);
     this.add(productInputPane);
     this.add(shoppingCartPane);
     this.add(invoiceSummaryPane);
}



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    //Custom Variables
    InvoiceStartPane invoiceStartPane = new InvoiceStartPane();
    ProductInputPane productInputPane = new ProductInputPane();
    //ShoppingCartPane shoppingCartPane = new ShoppingCartPane();
    ShoppingCartPane shoppingCartPane = new ShoppingCartPane();
    InvoiceSummaryPane invoiceSummaryPane = new InvoiceSummaryPane();
    //End Custom Variables

    //Data Custom Variables

    //End Data Custom variables
}
