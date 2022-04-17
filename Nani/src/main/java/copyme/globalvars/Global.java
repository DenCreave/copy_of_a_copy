package copyme.globalvars;

import copyme.controls.MyCache;
import copyme.controls.MyCursor;
import copyme.field.Background;
import copyme.field.Battlefield;
import copyme.hero.Hero;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.util.LinkedList;

/**
 * global variables stored in one place
 */
public class Global {
    public static Global enemypix;
    public static Group root;
    public static Scene scene;
    public static Background bg;
    public static Battlefield[][] battlefields;
    public static LinkedList<String> errMsgBuffer;
    public static Text[] fieldStatsPlayer;
    public static Text[] fieldStatsEnemy;
    public static Text topText;
    public static Text leftText;
    public static Text rightText;
    public static Text bottomText;
    public static Text middleText;
    public static Hero player;
    public static Hero enemy;
    public static int mySwitch;
    public static MyCursor cursor;
    public static MyCache cache;
}
