/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InvoiceStartPane.java
 *
 * Created on 24-jul-2010, 20:09:12
 */

package facturasapp;

import facturasapp.data.DataManager;
import facturasapp.data.StoreManager;
import facturasapp.events.EventManager;
import facturasapp.events.SearchEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import storeDataModel.Invoice;

/**
 *
 * @author CiberXtrem
 */
public class SearchStartPane extends javax.swing.JPanel {

    /** Creates new form InvoiceStartPane */
    public SearchStartPane() {
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

        matriculaLabel = new javax.swing.JLabel();
        matriculaField = new javax.swing.JTextField();
        historialButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(facturasapp.FacturasApp.class).getContext().getResourceMap(SearchStartPane.class);
        matriculaLabel.setFont(resourceMap.getFont("matriculaLabel.font")); // NOI18N
        matriculaLabel.setText(resourceMap.getString("matriculaLabel.text")); // NOI18N
        matriculaLabel.setName("matriculaLabel"); // NOI18N

        matriculaField.setName("matriculaField"); // NOI18N
        matriculaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                matriculaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                matriculaFieldFocusLost(evt);
            }
        });
        matriculaField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                matriculaFieldInputMethodTextChanged(evt);
            }
        });
        matriculaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                matriculaFieldKeyTyped(evt);
            }
        });

        historialButton.setIcon(resourceMap.getIcon("historialButton.icon")); // NOI18N
        historialButton.setText(resourceMap.getString("historialButton.text")); // NOI18N
        historialButton.setName("historialButton"); // NOI18N
        historialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matriculaLabel)
                    .addComponent(matriculaField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(historialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(historialButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(matriculaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(matriculaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
public void initCustomComponents(){

    matriculaLabel = new javax.swing.JLabel();
        matriculaField = new javax.swing.JTextField();
        historialButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(facturasapp.FacturasApp.class).getContext().getResourceMap(SearchStartPane.class);
        matriculaLabel.setFont(resourceMap.getFont("matriculaLabel.font")); // NOI18N
        matriculaLabel.setText(resourceMap.getString("matriculaLabel.text")); // NOI18N
        matriculaLabel.setName("matriculaLabel"); // NOI18N

        matriculaField.setName("matriculaField"); // NOI18N
        matriculaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                matriculaFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                matriculaFieldFocusLost(evt);
            }
        });
        matriculaField.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                matriculaFieldInputMethodTextChanged(evt);
            }
        });
        matriculaField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                matriculaFieldKeyTyped(evt);
            }
        });

        historialButton.setIcon(resourceMap.getIcon("historialButton.icon")); // NOI18N
        historialButton.setText(resourceMap.getString("historialButton.text")); // NOI18N
        historialButton.setName("historialButton"); // NOI18N
        historialButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historialButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(matriculaLabel)
                    .addComponent(matriculaField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(historialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(historialButton)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(matriculaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(matriculaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                //        )
        );
}

public void searchInviceByMatricula(String p_matricula){
        /*JOptionPane.showMessageDialog(this,
        "Panel de Historial en contruccion...",
        "Información",
        JOptionPane.INFORMATION_MESSAGE);*/
    
        List<Invoice> invoicesList = null;
        PersistenceManagerFactory pmf = StoreManager.get();
        PersistenceManager pm = pmf.getPersistenceManager();
        pm.currentTransaction().begin();

        //Obtener el producto de la base de datos y si no existe...
        Query query = pm.newQuery(Invoice.class, "matricula == p_matricula");
        query.declareParameters("String p_matricula");
        query.setOrdering("id descending");

        //query.setUnique(true);
        List<Invoice> queryInvoiceList = (List<Invoice>) query.execute(p_matricula);

        pm.getFetchPlan().addGroup("detach_productsRelation");
        pm.getFetchPlan().addGroup("detach_product");
        pm.getFetchPlan().setMaxFetchDepth(3);
        invoicesList = (List<Invoice>) pm.detachCopyAll(queryInvoiceList);

        pm.currentTransaction().commit();
        pm.close();
        ///End obtener producto de base de datos

        //Notify retrieved invoices
        EventManager.getInstance().fireSearchEvent(new SearchEvent(invoicesList), EventManager.SEARCH_LIST_RETRIEVED);
}

    private void historialButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historialButtonActionPerformed
       EventManager.getInstance().fireSearchEvent(new SearchEvent(matriculaField.getText()), EventManager.SEARCH_NEW);
       searchInviceByMatricula(matriculaField.getText());
       

}//GEN-LAST:event_historialButtonActionPerformed

    private void matriculaFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_matriculaFieldKeyTyped
        //System.out.println("Key: " + ((JTextField)evt.getSource()).getText());
        //System.out.println("Key: " + matriculaField.getText());
    }//GEN-LAST:event_matriculaFieldKeyTyped

    private void matriculaFieldInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_matriculaFieldInputMethodTextChanged
        //System.out.println("Key: " + matriculaField.getText());
    }//GEN-LAST:event_matriculaFieldInputMethodTextChanged

    private void matriculaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_matriculaFieldFocusLost
        matriculaField.setText(matriculaField.getText().toUpperCase());
    }//GEN-LAST:event_matriculaFieldFocusLost

    private void matriculaFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_matriculaFieldFocusGained
        matriculaField.selectAll();
    }//GEN-LAST:event_matriculaFieldFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton historialButton;
    private javax.swing.JTextField matriculaField;
    private javax.swing.JLabel matriculaLabel;
    // End of variables declaration//GEN-END:variables

    //Custom Data Variables
    //End Custom Data Variables
}
