package copyme.field;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * baseclass for the bg and field
 */
public class MyMap {
    //coordinates
    private int x,y;
    private boolean currentlySelected;
    public Image defaultPic;
    public ImageView picShow;

    public MyMap(int x, int y, String defaultPicPath){
        this.x=x;
        this.y=y;
        this.currentlySelected=false;
        this.defaultPic = new Image(defaultPicPath);
        picShow=new ImageView();
        picShow.setImage(defaultPic);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public boolean isCurrentlySelected() {
        return currentlySelected;
    }

    public void setCurrentlySelected(boolean currentlySelected) {
        this.currentlySelected = currentlySelected;
    }
}
