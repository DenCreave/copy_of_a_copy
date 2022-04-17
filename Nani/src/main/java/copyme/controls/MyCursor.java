package copyme.controls;

import copyme.globalvars.Global;
import copyme.hero.Hero;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * that yellowish frame is your cursor, this is its class, handles coordinates mostly
 */
public class MyCursor {
    private int maxX,maxY;
    private int offsetY;
    private int offsetX;
    private int distanceXY;
    private int x,y;
    private boolean selectedAUnit;
    private int defaultX;
    private int defaultY;
    public Image selectedUnitOrSpell;
    public Image defaultCursorPic;
    public ImageView picShow;



    public void initCursorCoEnemy(int indexOfEnemy){
        Global.enemy.army[indexOfEnemy].picShow.setX(offsetX+(Global.enemy.army[indexOfEnemy].x*distanceXY));
        Global.enemy.army[indexOfEnemy].picShow.setY(offsetY+(Global.enemy.army[indexOfEnemy].y*distanceXY));
    }

    public void initCursorCoordinates(){
        picShow.setX(offsetX+(x*distanceXY));
        picShow.setY(offsetY+(y*distanceXY));
    }



    public MyCursor(String defaultPicPath){
        this.maxX=0;
        this.maxY=0;
        this.defaultX=0;
        this.defaultY=0;


        this.offsetX=500;
        this.offsetY=100;
        this.distanceXY=70;
        this.x=0;
        this.y=0;
        this.selectedAUnit=false;
        this.defaultCursorPic=new Image(defaultPicPath);
        selectedUnitOrSpell=null;
        picShow=new ImageView(defaultPicPath);
        initCursorCoordinates();

    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public void setToDefaultCo(){
        this.x=defaultX;
        this.y=defaultY;
        initCursorCoordinates();
    }

    public void putDownUnit(Hero whosHero){
        for (int i = 0; i < whosHero.army.length; i++) {
            if (whosHero.army[i]!=null){

                if (whosHero.army[i].x == defaultX && whosHero.army[i].y==defaultY){
                    whosHero.army[i].x=this.x;
                    whosHero.army[i].picShow.setX(offsetX+(this.x*distanceXY));
                    whosHero.army[i].y=this.y;
                    whosHero.army[i].picShow.setY(offsetY+(this.y*distanceXY));

                    setDefaultX(this.x);
                    setDefaultY(this.y);

                }
            }
        }
    }

    public void destructorOfMove(){
        for (int i = 0; i < Global.cache.stepTracker.size(); i++) {
            unColorSteps(Global.cache.stepTracker.get(i).x,Global.cache.stepTracker.get(i).y);
        }
        Global.cache.stepTracker.clear();
        Global.cache.setMaxStepTrack(0);
        loseSelectedImage();
        setToDefaultCo();
    }

    public void initMove(Hero whosHero){

        MyCoordinates coordinates=new MyCoordinates(this.x, this.y);
        for (int i = 0; i < whosHero.army.length; i++) {
            if (whosHero.army[i]!=null){

                if (whosHero.army[i].x == this.x && whosHero.army[i].y==this.y){
                    chooseSelectedImage(whosHero.army[i].getDefaultPic());
                    Global.cache.stepTracker.add(coordinates);
                    Global.cache.setMaxStepTrack(whosHero.army[i].getSpeed());
                }
            }
        }

    }

    /**
     * didnt have time so im just writing another method
     * @param indexOfEnemy
     */
    public void initMove(int indexOfEnemy) {
        MyCoordinates coordinates = new MyCoordinates(Global.enemy.army[indexOfEnemy].x, Global.enemy.army[indexOfEnemy].y);

        Global.cache.stepTracker.add(coordinates);
        Global.cache.setMaxStepTrack(Global.enemy.army[indexOfEnemy].getSpeed());


    }

    public void addToStepTrack(){
        MyCoordinates coordinates=new MyCoordinates(this.x, this.y);
        Global.cache.stepTracker.add(coordinates);
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }

    public void colorfulStepTracker(){
        ColorAdjust adjustme=new ColorAdjust();

        adjustme.setBrightness(-0.5);


        Global.battlefields[this.x][this.y].picShow.setEffect(adjustme);
    }

    public void unColorSteps(int coX, int coY){
        ColorAdjust adjustme=new ColorAdjust();
        adjustme.setBrightness(0);

        Global.battlefields[coX][coY].picShow.setEffect(adjustme);
    }

    public void checkIfIveBeenThere(){
        int track=0;
        boolean trackeMe=false;
        for (int i = 0; i < Global.cache.stepTracker.size() ; i++) {
            if(Global.cache.stepTracker.get(i).x==this.x && Global.cache.stepTracker.get(i).y==this.y){
                track=i;
                trackeMe=true;
                break;
            }
        }
        if (trackeMe){
            int tmp=Global.cache.stepTracker.size();
            //TODO  check later cos might wanna  set track to track+1 as initial int i
            //no increment, by delete i take off from the size
            for (int i = track; i < Global.cache.stepTracker.size(); ) {

                unColorSteps(Global.cache.stepTracker.get(i).x,Global.cache.stepTracker.get(i).y);
                Global.cache.stepTracker.remove(i);

            }
        }
    }

    public boolean isItAnOldCoordinated(int xCoor, int yCoor){
        for (int i = 0; i < Global.cache.stepTracker.size(); i++) {
            if (Global.cache.stepTracker.get(i).x==xCoor && Global.cache.stepTracker.get(i).y==yCoor){
                return true;
            }
        }
        if (getDefaultX()==xCoor && getDefaultY() ==yCoor){
            return true;
        }else {
            return false;
        }

    }

    public boolean moveUpUnit(){
        FieldController controller=new FieldController();

        if (Global.cache.stepTracker.size()<Global.cache.getMaxStepTrack()+1 || isItAnOldCoordinated(this.x, this.y-1)){

            if (controller.isItaValidPlace(this.x , this.y-1)|| isItAnOldCoordinated(this.x, this.y-1)){
                this.y--;
                checkIfIveBeenThere();
                colorfulStepTracker();
                addToStepTrack();
                initCursorCoordinates();
            }
            //think im done
        }else {
            controller.addLogMsg("not enough energy");
        }
        return true;
    }

    public boolean moveDownUnit(){
        MyCoordinates coordinates=new MyCoordinates(this.x, this.y+1);

        FieldController controller=new FieldController();

        if (Global.cache.stepTracker.size()<Global.cache.getMaxStepTrack()+1||isItAnOldCoordinated(this.x, this.y+1 )){

            if (controller.isItaValidPlace(this.x , this.y+1) || isItAnOldCoordinated(this.x, this.y+1)){
                this.y++;
                checkIfIveBeenThere();
                colorfulStepTracker();
                addToStepTrack();
                initCursorCoordinates();
            }
            //think im done
        }else {
            controller.addLogMsg("not enough energy");
        }
        return true;


    }

    public boolean moveLeftUnit(){
        FieldController controller=new FieldController();

        if (Global.cache.stepTracker.size()<Global.cache.getMaxStepTrack()+1 || isItAnOldCoordinated(this.x-1, this.y)){

            if (controller.isItaValidPlace(this.x-1 , this.y) || isItAnOldCoordinated(this.x-1, this.y)){
                this.x--;
                checkIfIveBeenThere();
                colorfulStepTracker();
                addToStepTrack();
                initCursorCoordinates();
            }
            //think im done
        }else {
            controller.addLogMsg("not enough energy");
        }
        return true;
    }

    public boolean moveRightUnit(){
        FieldController controller=new FieldController();

        if (Global.cache.stepTracker.size()<Global.cache.getMaxStepTrack()+1 || isItAnOldCoordinated(this.x+1, this.y)){

            if (controller.isItaValidPlace(this.x+1 , this.y) || isItAnOldCoordinated(this.x+1, this.y)){
                this.x++;
                checkIfIveBeenThere();
                colorfulStepTracker();
                addToStepTrack();
                initCursorCoordinates();
            }
            //think im done
        }else {
            controller.addLogMsg("not enough energy");
        }
        return true;
    }




    public void chooseSelectedImage(Image selection){
        picShow.setImage(selection);
        picShow.setOpacity(0.5);
    }
    public void loseSelectedImage(){
        picShow.setImage(defaultCursorPic);
        picShow.setOpacity(1);
    }



    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }


    public void cursorHide(){
        this.picShow.opacityProperty().setValue(0);
    }

    public void cursorShow(){
        this.picShow.opacityProperty().setValue(1);
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDistanceXY() {
        return distanceXY;
    }
}
