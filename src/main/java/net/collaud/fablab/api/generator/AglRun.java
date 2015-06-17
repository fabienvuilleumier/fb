package net.collaud.fablab.api.generator;

import java.io.IOException;
import net.collaud.fablab.api.data.PurchaseEO;

/**
 *
 * @author Fabien Vuilleumier
 */
public class AglRun {

    private final BackendEOGenerator EO;
    private BackendBaseGenerator base;
    private BackendAngularListEditGenerator angular;
    private final String CLASS_NAME = "Purchase";
    private final String TABLE_NAME = "t_purchase";
    /*
    Must be a table of : {"java type", "java name", "nullable ? [t,f]", "db type"} 
    */
    private final String[][] FIELDS = new String[][]{
        {"Integer", "id", "f", "INT"},
        {"Date", "purchaseDate", "f", "DATE"}, 
        {"Integer", "quantity", "f", "INT"}, 
        {"Double", "purchasePrice", "f", "FLOAT"}, 
        {"SupplyEO", "supply", "f", "INT"},
        {"UserEO", "user", "f", "INT"}};
    
    private final boolean WRITE = true;
    private final String[] ROLES = new String[]{"SUPPLY_VIEW"};

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
            this.base = BackendBaseGenerator.getInstance(PurchaseEO.class);
            base.genere(WRITE, ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runAngular() {
        try {
            this.angular = BackendAngularListEditGenerator.getInstance(PurchaseEO.class);
            angular.genere("", ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        AglRun agl = new AglRun();
        //agl.runEO();
        agl.runBase();
        agl.runAngular();
    }

}
