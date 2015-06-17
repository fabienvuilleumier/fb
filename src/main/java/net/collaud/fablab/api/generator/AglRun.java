package net.collaud.fablab.api.generator;

import java.io.IOException;
import net.collaud.fablab.api.data.PurchaseEO;
import net.collaud.fablab.api.data.SupplyUnityEO;

/**
 *
 * @author Fabien Vuilleumier
 */
public class AglRun {

    private final BackendEOGenerator EO;
    private BackendBaseGenerator base;
    private BackendAngularListEditGenerator angular;
    private final String CLASS_NAME = "SupplyUnity";
    private final String TABLE_NAME = "t_supply_unity";
    /*
    * BEGIN CHANGE SECTION
    Must be a table of : {"java type", "java name", "nullable ? [t,f]", "db type"} 
    */
    private final String[][] FIELDS = new String[][]{
        {"Integer", "id", "f", "INT"},
        {"String", "label", "f", "VARCHAR"},
        {"List<SupplyEO>", "supplyList", "f", "INT"}};
    
    private final boolean WRITE = true;
    private final String[] ROLES = new String[]{"SUPPLY_MANAGE"};
    private final Class KLAZZ = SupplyUnityEO.class;
    
     public static void main(String[] args) {
        AglRun agl = new AglRun();
        //agl.runEO();
        agl.runBase();
        agl.runAngular();
    }
     
     /*END CHANGE SECTION */

    private AglRun() {
        this.EO = BackendEOGenerator.getInstance(CLASS_NAME, TABLE_NAME, FIELDS);
    }

    private void runEO() {
        try {
            EO.genereEO();

        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runBase() {
        try {
            this.base = BackendBaseGenerator.getInstance(KLAZZ);
            base.genere(WRITE, ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runAngular() {
        try {
            this.angular = BackendAngularListEditGenerator.getInstance(KLAZZ);
            angular.genere("", ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }
}
