/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package facturasapp.data;

import java.util.HashMap;
//import storeDataModel.Invoice;

/**
 *
 * @author CiberXtrem
 */
public class DataManager {
    private static DataManager singleton = null;

    //public static final int OBJECT_ADDED = 0;
    //public static final int ERROR_OBJECTKEY_ALREADY_EXISTS = 1;

    private HashMap dataList = new HashMap();

    private DataManager(){
    }

    public static DataManager getInstance(){
        if(singleton == null){
            singleton = new DataManager();
        }
        return singleton;
    }

    public static DataManager clearInstance(){
        singleton = new DataManager();
        return singleton;
    }

    public Object addDataObject(String key, Object obj){
        return dataList.put(key, obj);
    }

    public Object getDataObject(String key){
        return dataList.get(key);
    }

    public HashMap getObjects(){
        return dataList;
    }

    public static void main(String args[]){
        /*Invoice i = new Invoice("Matricula 01");
        DataManager.getInstance().addDataObject("matricula", i);
        System.out.println(i);*/
    }
}
