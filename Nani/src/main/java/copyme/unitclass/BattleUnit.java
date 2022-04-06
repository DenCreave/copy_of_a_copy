package copyme.unitclass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BattleUnit {
    private String name;
    private int price;
    private int minDmg;
    private int maxDmg;
    private int health;
    private int speed;
    private int haste; //ez a kezdeményezés, hogy milyen gyorsan kezdeményez;
    private boolean hasSpecialAbility;
    private String specialDescription;
    private Image defaultPic;
    private Image opacityPic;
    public ImageView picShow;

    /**
     * Constructor
     * @param name
     * @param price
     * @param minDmg
     * @param maxDmg
     * @param health
     * @param speed
     * @param haste
     * @param hasSpecialAbility
     */
    public BattleUnit(String name, int price, int minDmg, int maxDmg, int health, int speed, int haste, boolean hasSpecialAbility, String specialDescription, String defaultPicPath,String opacityPicPath){
        this.name=name;
        this.price=price;
        this.minDmg=minDmg;
        this.maxDmg=maxDmg;
        this.health=health;
        this.speed=speed;
        this.haste=haste;
        this.hasSpecialAbility=hasSpecialAbility;
        this.specialDescription=specialDescription;
        this.defaultPic=new Image(defaultPicPath);
        this.opacityPic=new Image(opacityPicPath);
        this.picShow=new ImageView(defaultPic);
    }

    /**
     * another contructor for initializing without using the special ability, if it doesnt have anything special
     * then by default it 0
     * @param name
     * @param price
     * @param minDmg
     * @param maxDmg
     * @param health
     * @param speed
     * @param haste
     */
    public BattleUnit(String name, int price, int minDmg, int maxDmg, int health, int speed, int haste, String defaultPicPath,String opacityPicPath){
        this.name=name;
        this.price=price;
        this.minDmg=minDmg;
        this.maxDmg=maxDmg;
        this.health=health;
        this.speed=speed;
        this.haste=haste;
        this.hasSpecialAbility=false;
        this.defaultPic=new Image(defaultPicPath);
        this.opacityPic=new Image(opacityPicPath);
        this.picShow=new ImageView(defaultPic);
    }


}
