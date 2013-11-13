/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.data;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 *
 * @author CiberXtrem
 */
public class StoreManager {
    private static PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

    public static PersistenceManagerFactory init(){
        return get();
    }

    public static PersistenceManagerFactory get(){
        return pmf;
    }

}
