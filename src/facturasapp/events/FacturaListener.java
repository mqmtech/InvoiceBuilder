/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.events;

import java.util.EventListener;

/**
 *
 * @author CiberXtrem
 */
public interface FacturaListener extends EventListener{
    public abstract void productAdded (FacturaEvent e);
    public abstract void productRemoved (FacturaEvent e);
    public abstract void invoiceUpdated (FacturaEvent e);
    public abstract void invoiceStored(FacturaEvent e);
    public abstract void newInvoice(FacturaEvent e);
}
