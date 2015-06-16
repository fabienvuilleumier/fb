package net.collaud.fablab.api.generator;

import java.io.IOException;
import net.collaud.fablab.api.data.ConfigurationEO;

/**
 *
 * @author Fabien Vuilleumier
 */
public class AglRun {

    private final BackendEOGenerator EO;
    private BackendBaseGenerator base;
    private BackendAngularListEditGenerator angular;
    private final String CLASS_NAME = "Configuration";
    private final String TABLE_NAME = "t_configuration";
    private final String[][] FIELDS = new String[][]{
        {"Integer", "id", "f", ""},
        {"String", "key", "f", "VARCHAR"},
        {"String", "value", "f", "VARCHAR"}};
    
    private final boolean WRITE = true;
    private final String[] ROLES = new String[]{"ADMIN"};

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
            this.base = BackendBaseGenerator.getInstance(ConfigurationEO.class);
            base.genere(WRITE, ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    private void runAngular() {
        try {
            this.angular = BackendAngularListEditGenerator.getInstance(ConfigurationEO.class);
            angular.genere("", ROLES);
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        AglRun agl = new AglRun();
        //agl.runEO();
        //agl.runBase();
        agl.runAngular();
    }

}
