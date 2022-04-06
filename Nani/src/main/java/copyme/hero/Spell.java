package copyme.hero;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spell {
    private String name;
    private int value;
    private int manaCost;
    private String description;
    private int learnPrice;
    private Image picResource;
    public ImageView picShow;

    public Spell(String name, int value, int manaCost, String description, int learnPrice, String picPath){
        this.name=name;
        this.value=value;
        this.manaCost=manaCost;
        this.description=description;
        this.learnPrice=learnPrice;
        this.picResource=new Image(picPath);
        this.picShow=new ImageView();
        picShow.setImage(picResource);
    }
}
