package copyme.hero;


import copyme.unitclass.BattleUnit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * base class for spells
 */
public abstract class  Spell {
    private String name;
    private boolean isBuff;
    private boolean isDmg;
    private int indexOf;
    private int value;
    private int learnPrice;
    private int manaCost;
    private String description;
    private Image picResource;
    public ImageView picShow;

    public Spell(String name,boolean isBuff,boolean isDmg, int value, int learnPrice, int manaCost, String description, String picPath){
        this.name=name;
        this.isBuff=isBuff;
        this.isDmg=isDmg;
        this.indexOf=0;
        this.value=value;
        this.learnPrice=learnPrice;
        this.manaCost=manaCost;
        this.description=description;
        this.picResource=new Image(picPath);
        this.picShow=new ImageView();
        picShow.setImage(picResource);
    }

    public String getName() {
        return name;
    }

    public boolean isBuff() {
        return isBuff;
    }



    public boolean isDmg() {
        return isDmg;
    }

    public int getIndexOf() {
        return indexOf;
    }

    public void setIndexOf(int indexOf) {
        this.indexOf = indexOf;
    }

    public int getValue() {
        return value;
    }

    public int getLearnPrice() {
        return learnPrice;
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getDescription() {
        return description;
    }

    public Image getPicResource() {
        return picResource;
    }

    public ImageView getPicShow() {
        return picShow;
    }


    public abstract boolean castSpell(boolean isEnemyCasting, int x, int y);
}
