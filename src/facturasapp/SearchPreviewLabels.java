/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SearchPreviewLabel.java
 *
 * Created on 30-jul-2010, 1:45:24
 */

package facturasapp;

/**
 *
 * @author CiberXtrem
 */
public class SearchPreviewLabels extends javax.swing.JPanel {

    /** Creates new form SearchPreviewLabel */
    public SearchPreviewLabels() {
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

        facturaIdField = new javax.swing.JLabel();
        fechaField = new javax.swing.JLabel();
        matriculaField = new javax.swing.JLabel();
        modeloField = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(facturasapp.FacturasApp.class).getContext().getResourceMap(SearchPreviewLabels.class);
        facturaIdField.setFont(resourceMap.getFont("facturaIdField.font")); // NOI18N
        facturaIdField.setText(resourceMap.getString("facturaIdField.text")); // NOI18N
        facturaIdField.setName("facturaIdField"); // NOI18N

        fechaField.setFont(resourceMap.getFont("fechaField.font")); // NOI18N
        fechaField.setText(resourceMap.getString("fechaField.text")); // NOI18N
        fechaField.setName("fechaField"); // NOI18N

        matriculaField.setFont(resourceMap.getFont("matriculaField.font")); // NOI18N
        matriculaField.setText(resourceMap.getString("matriculaField.text")); // NOI18N
        matriculaField.setName("matriculaField"); // NOI18N

        modeloField.setFont(resourceMap.getFont("modeloField.font")); // NOI18N
        modeloField.setText(resourceMap.getString("modeloField.text")); // NOI18N
        modeloField.setName("modeloField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(facturaIdField)
                .addGap(44, 44, 44)
                .addComponent(fechaField)
                .addGap(127, 127, 127)
                .addComponent(matriculaField)
                .addGap(40, 40, 40)
                .addComponent(modeloField)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facturaIdField)
                    .addComponent(fechaField)
                    .addComponent(matriculaField)
                    .addComponent(modeloField))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void initCustomComponents(){
        facturaIdField = new javax.swing.JLabel();
        fechaField = new javax.swing.JLabel();
        matriculaField = new javax.swing.JLabel();
        modeloField = new javax.swing.JLabel();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(facturasapp.FacturasApp.class).getContext().getResourceMap(SearchPreviewLabels.class);
        facturaIdField.setFont(resourceMap.getFont("facturaIdField.font")); // NOI18N
        facturaIdField.setText(resourceMap.getString("facturaIdField.text")); // NOI18N
        facturaIdField.setName("facturaIdField"); // NOI18N

        fechaField.setFont(resourceMap.getFont("fechaField.font")); // NOI18N
        fechaField.setText(resourceMap.getString("fechaField.text")); // NOI18N
        fechaField.setName("fechaField"); // NOI18N

        matriculaField.setFont(resourceMap.getFont("matriculaField.font")); // NOI18N
        matriculaField.setText(resourceMap.getString("matriculaField.text")); // NOI18N
        matriculaField.setName("matriculaField"); // NOI18N

        modeloField.setFont(resourceMap.getFont("modeloField.font")); // NOI18N
        modeloField.setText(resourceMap.getString("modeloField.text")); // NOI18N
        modeloField.setName("modeloField"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(facturaIdField)
                .addGap(44, 44, 44)
                .addComponent(fechaField)
                .addGap(127, 127, 127)
                .addComponent(matriculaField)
                .addGap(40, 40, 40)
                .addComponent(modeloField)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facturaIdField)
                    .addComponent(fechaField)
                    .addComponent(matriculaField)
                    .addComponent(modeloField))
                //.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    )
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel facturaIdField;
    private javax.swing.JLabel fechaField;
    private javax.swing.JLabel matriculaField;
    private javax.swing.JLabel modeloField;
    // End of variables declaration//GEN-END:variables

}
