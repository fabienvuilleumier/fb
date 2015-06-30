package net.collaud.fablab.api.generator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.collaud.fablab.api.data.UsageEO;
/**
 *
 * @author Fabien Vuilleumier
 */
public class Generator {

    private final BackendEOGenerator EO;
    private BackendBaseGenerator base;
    private BackendAngularListEditGenerator angular;

    /*
     * BEGIN CHANGE SECTION
     Must be a table of : 
    
     {"java type", "java name", "nullable ? [t,f]", "db type"} 
     */
    /*FIRST*/
    private final String CLASS_NAME = "Usage";
    private final String TABLE_NAME = "t_payment";

    private final String[][] FIELDS = new String[][]{
        {"Integer", "id", "f", "INT"},
        {"String", "name", "f", "VARCHAR"},
        {"String", "technicalname", "f", "INT"}};

    private final boolean WRITE = true;
    private final String[] ROLES = new String[]{"PAYMENT_VIEW"};
    private final Map<String, String> nestedObjectReprAttr = new HashMap<>();

    /*SECOND*/
    private final Class KLAZZ = UsageEO.class;//CertificationEO.class;

    public static void main(String[] args) {
        Generator agl = new Generator();
        agl.getNestedObjectReprAttr().put("user", "firstname");
        agl.getNestedObjectReprAttr().put("cashier", "firstname");
        agl.getNestedObjectReprAttr().put("machine", "name");
        agl.getNestedObjectReprAttr().put("membershipType", "name");
        //agl.runEO();
        //agl.runBase();
        agl.runAngular(agl.getNestedObjectReprAttr());
    }

    /*END CHANGE SECTION */
    private Generator() {
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

    private void runAngular(final Map<String, String> nestedObjectReprAttr) {
        try {
            this.angular = BackendAngularListEditGenerator.getInstance(KLAZZ, nestedObjectReprAttr);
            angular.genere(WRITE, "", ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public Map<String, String> getNestedObjectReprAttr() {
        return nestedObjectReprAttr;
    }
}
