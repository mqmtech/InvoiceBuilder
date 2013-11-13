/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.events;

/**
 *
 * @author CiberXtrem
 */
public class EventManager {

    private static EventManager singleton = null;

    //Factura
    public static final int INVOICE_PRODUCT_ADDED = 0;
    public static final int INVOICE_PRODUCT_REMOVED = 1;
    public static final int INVOICE_UPDATED = 2;
    public static final int INVOICE_NEW = 3;
    public static final int INVOICE_STORED = 4;

    //Search
    public static final int SEARCH_LIST_RETRIEVED = 20;
    public static final int SEARCH_INVOICE_SELECTED = 21;
    public static final int SEARCH_NEW = 22;
    public static final int SEARCH_INVOICE_UNSELECTED = 23;

    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList(); 

    private EventManager(){
    }

    public static EventManager getInstance(){
        if(singleton == null){
            singleton = new EventManager();
        }
        return singleton;
    }
    public static EventManager clearInstance(){
        singleton = new EventManager();
        return singleton;
    }

    public void addFacturaListener(FacturaListener listener){
        listenerList.add(FacturaListener.class, listener);
    }

    public void removeFacturaListener(FacturaListener listener){
        listenerList.remove(FacturaListener.class, listener);
    }

    public void addSearchListener(SearchListener listener){
        listenerList.add(SearchListener.class, listener);
    }

    public void removeSearchListener(SearchListener listener){
        listenerList.remove(SearchListener.class, listener);
    }

    public void fireFacturaEvent(FacturaEvent e, int type){
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==FacturaListener.class) {
                switch(type){
                    case INVOICE_PRODUCT_ADDED:
                        ((FacturaListener)listeners[i+1]).productAdded(e);
                        break;
                    case INVOICE_PRODUCT_REMOVED:
                        ((FacturaListener)listeners[i+1]).productRemoved(e);
                        break;
                    case INVOICE_UPDATED:
                        ((FacturaListener)listeners[i+1]).invoiceUpdated(e);
                        break;
                    case INVOICE_STORED:
                        ((FacturaListener)listeners[i+1]).invoiceStored(e);
                        break;
                    case INVOICE_NEW:
                        ((FacturaListener)listeners[i+1]).newInvoice(e);
                        break;
                }
                
            }
        }
    }

    public void fireSearchEvent(SearchEvent e, int type){
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==SearchListener.class) {
                switch(type){
                    case SEARCH_INVOICE_SELECTED:
                        ((SearchListener)listeners[i+1]).invoiceSelected(e);
                        break;
                    case SEARCH_LIST_RETRIEVED:
                        ((SearchListener)listeners[i+1]).listRetrieved(e);
                        break;
                    case SEARCH_INVOICE_UNSELECTED:
                        ((SearchListener)listeners[i+1]).invoiceUnselected(e);
                        break;
                    case SEARCH_NEW:
                        ((SearchListener)listeners[i+1]).newSearch(e);
                        break;

                }
            }
        }
    }

}
