package copyme.controls;

import copyme.field.Background;
import copyme.field.Battlefield;
import copyme.globalvars.Global;
import copyme.hero.*;
import copyme.unitclass.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * this class will handle all the loadings ingame
 */
public class MyLoader {
    String fontPath;

    public MyLoader(){
        fontPath="file:fonts/AncientModernTales-a7Po.ttf";
    }



    public void fieldUpdateLeft(){


        //player part:
        Global.fieldStatsPlayer[0].setText(Global.player.getName());
        Global.fieldStatsPlayer[1].setText("Mana: "+Global.player.getMana());
        for (int i = 2; i < Global.player.getBuyArmy().keySet().size()+2 ; i++) {
            if (Global.player.army[i-2]!=null){
                Global.fieldStatsPlayer[i].setText(":"+Global.player.army[i-2].getCurrentArmyNumber()+":");
            }else {
                Global.fieldStatsPlayer[i].setText("DEAD");
            }
        }
        for (int i = 2+Global.player.getBuyArmy().keySet().size(); i <2+Global.player.getBuyArmy().keySet().size()+Global.player.getBuySpells().keySet().size() ; i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsPlayer[i].setText(":"+Global.player.spells[i-2-Global.player.getBuyArmy().keySet().size()].getManaCost()+":");
        }

        //enemy part:
        Global.fieldStatsEnemy[0].setText(Global.enemy.getName());
        Global.fieldStatsEnemy[1].setText("Mana: "+Global.enemy.getMana());
        for (int i = 2; i < Global.enemy.getBuyArmy().keySet().size()+2 ; i++) {
            if (Global.enemy.army[i-2]!=null){
                Global.fieldStatsEnemy[i].setText(":"+Global.enemy.army[i-2].getCurrentArmyNumber()+":");
            }else {
                Global.fieldStatsEnemy[i].setText("DEAD");
            }
        }
        for (int i = 2+Global.enemy.getBuyArmy().keySet().size(); i <2+Global.enemy.getBuyArmy().keySet().size()+Global.enemy.getBuySpells().keySet().size() ; i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsEnemy[i].setText(":"+Global.enemy.spells[i-2-Global.enemy.getBuyArmy().keySet().size()].getManaCost()+":");
        }


    }

    public void fieldUpdate(){
        fieldUpdateLeft();
    }



    public void update(){
        leftTextUpdate();
        middleTextUpdate();
        readErrMsg();

    }

    /**
     * tbh its for deleting unsude nodes, but whateva, i guess ill leave it in
     */
    public void deleteStarterNodes(){
        Global.middleText.setText("");
        Global.leftText.setText("");
        Global.rightText.setText("");
        Global.root.getChildren().remove(Global.topText);
        Global.root.getChildren().remove(Global.leftText);
    }

    public void setupFieldLeft(){
        Font meineFont= Font.loadFont(fontPath,40);



        //player

        Global.fieldStatsPlayer=new Text[Global.player.getBuySpells().keySet().size()+Global.player.getBuyArmy().keySet().size()+2];


        for (int i = 0; i < Global.fieldStatsPlayer.length; i++) {
            Global.fieldStatsPlayer[i]=new Text();
            Global.fieldStatsPlayer[i].setFont(meineFont);
        }


        Global.fieldStatsPlayer[0].setText(Global.player.getName());
        Global.fieldStatsPlayer[0].setX(130);
        Global.fieldStatsPlayer[0].setY(50);
        Global.root.getChildren().add(Global.fieldStatsPlayer[0]);

        Global.fieldStatsPlayer[1].setText("Mana: "+Global.player.getMana());
        Global.fieldStatsPlayer[1].setX(130);
        Global.fieldStatsPlayer[1].setY(100);
        Global.root.getChildren().add(Global.fieldStatsPlayer[1]);
        for (int i = 2; i < Global.player.getBuyArmy().keySet().size()+2 ; i++) {
            if (Global.player.army[i-2]!=null){
                Global.fieldStatsPlayer[i].setText(":"+Global.player.army[i-2].getCurrentArmyNumber()+":");
                Global.fieldStatsPlayer[i].setX(60+((i-2)*80));
                Global.fieldStatsPlayer[i].setY(220);
                Global.root.getChildren().add(Global.fieldStatsPlayer[i]);



                Global.player.armyIcons[i-2].setX(50+((i-2)*80));
                Global.player.armyIcons[i-2].setY(125);
                Global.root.getChildren().add(Global.player.armyIcons[i-2]);


            }else {
                Global.fieldStatsPlayer[i].setText("");
            }
        }
        for (int i = 2+Global.player.getBuyArmy().keySet().size(); i <2+Global.player.getBuyArmy().keySet().size()+Global.player.getBuySpells().keySet().size() ; i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsPlayer[i].setText(":"+Global.player.spells[i-2-Global.player.getBuyArmy().keySet().size()].getManaCost()+":");
            Global.fieldStatsPlayer[i].setY(375);
            Global.fieldStatsPlayer[i].setX(70+((i-2-Global.player.getBuyArmy().keySet().size())*80));
            Global.root.getChildren().add(Global.fieldStatsPlayer[i]);

            Global.player.spellsIcons[i-2-Global.player.getBuyArmy().keySet().size()].setY(270);
            Global.player.spellsIcons[i-2-Global.player.getBuyArmy().keySet().size()].setX(50+((i-2-Global.player.getBuyArmy().keySet().size())*80));
            Global.root.getChildren().add(Global.player.spellsIcons[i-2-Global.player.getBuyArmy().keySet().size()]);


        }



        //enemy
        Global.fieldStatsEnemy=new Text[Global.enemy.getBuySpells().keySet().size()+Global.enemy.getBuyArmy().keySet().size()+2];

        for (int i = 0; i < Global.fieldStatsEnemy.length; i++) {
            Global.fieldStatsEnemy[i]=new Text();
            Global.fieldStatsEnemy[i].setFont(meineFont);
        }

        Global.fieldStatsEnemy[0].setText(Global.enemy.getName());
        Global.fieldStatsEnemy[0].setX(130);
        Global.fieldStatsEnemy[0].setY(450);
        Global.root.getChildren().add(Global.fieldStatsEnemy[0]);


        Global.fieldStatsEnemy[1].setText("Mana: "+Global.enemy.getMana());
        Global.fieldStatsEnemy[1].setY(500);
        Global.fieldStatsEnemy[1].setX(130);
        Global.root.getChildren().add(Global.fieldStatsEnemy[1]);


        for (int i = 2; i < Global.enemy.getBuyArmy().keySet().size()+2 ; i++) {
            if (Global.enemy.army[i-2]!=null){
                Global.fieldStatsEnemy[i].setText(":"+Global.enemy.army[i-2].getCurrentArmyNumber()+":");
                Global.fieldStatsEnemy[i].setX(60+((i-2)*80));
                Global.fieldStatsEnemy[i].setY(620);
                Global.root.getChildren().add(Global.fieldStatsEnemy[i]);


                //TODO  prolly gonna need to change coordinate values
                Global.enemy.armyIcons[i-2].setX(50+((i-2)*80));
                Global.enemy.armyIcons[i-2].setY(516);
                Global.root.getChildren().add(Global.enemy.armyIcons[i-2]);

            }else {
                Global.fieldStatsEnemy[i].setText("");
            }
        }
        for (int i = 2+Global.enemy.getBuyArmy().keySet().size(); i <2+Global.enemy.getBuyArmy().keySet().size()+Global.enemy.getBuySpells().keySet().size() ; i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsEnemy[i].setText(":"+Global.enemy.spells[i-2-Global.enemy.getBuyArmy().keySet().size()].getManaCost()+":");
            Global.fieldStatsEnemy[i].setY(760);
            Global.fieldStatsEnemy[i].setX(70+((i-2-Global.enemy.getBuyArmy().keySet().size())*80));
            Global.root.getChildren().add(Global.fieldStatsEnemy[i]);


            Global.enemy.spellsIcons[i-2-Global.enemy.getBuyArmy().keySet().size()].setY(655);
            Global.enemy.spellsIcons[i-2-Global.enemy.getBuyArmy().keySet().size()].setX(50+((i-2-Global.enemy.getBuyArmy().keySet().size())*80));
            Global.root.getChildren().add(Global.enemy.spellsIcons[i-2-Global.enemy.getBuyArmy().keySet().size()]);
        }


    }

    public void setupFieldMidlle()  {
        try {
            File pathToField = new File("map/battlefield.txt");
            Scanner myScanner = new Scanner(pathToField);
            Global.battlefields = new Battlefield[12][10];
            for (int y = 0; y < 10; y++) {
                String read = myScanner.nextLine();
                String[] mapped = read.split(" ");
                for (int x = 0; x < 12; x++) {
                    switch (Integer.valueOf(mapped[x])) {
                        case 4: {
                            Global.battlefields[x][y] = new Battlefield(500 + (x * 70), 100 + (y * 70), "file:textures/rockland.png");
                            Global.battlefields[x][y].picShow.setX(Global.battlefields[x][y].getX());
                            Global.battlefields[x][y].picShow.setY(Global.battlefields[x][y].getY());
                            Global.root.getChildren().add(Global.battlefields[x][y].picShow);
                            break;
                        }
                        case 5: {
                            Global.battlefields[x][y] = new Battlefield(500 + (x * 70), 100 + (y * 70), "file:textures/rockmixfire.png");
                            Global.battlefields[x][y].picShow.setX(Global.battlefields[x][y].getX());
                            Global.battlefields[x][y].picShow.setY(Global.battlefields[x][y].getY());
                            Global.root.getChildren().add(Global.battlefields[x][y].picShow);
                            break;
                        }
                        case 6: {
                            Global.battlefields[x][y] = new Battlefield(500 + (x * 70), 100 + (y * 70), "file:textures/fireland.png");
                            Global.battlefields[x][y].picShow.setX(Global.battlefields[x][y].getX());
                            Global.battlefields[x][y].picShow.setY(Global.battlefields[x][y].getY());
                            Global.root.getChildren().add(Global.battlefields[x][y].picShow);
                            break;
                        }
                        case 9: {
                            Global.battlefields[x][y] = new Battlefield(500 + (x * 70), 100 + (y * 70), "file:textures/firebotright.png");
                            Global.battlefields[x][y].picShow.setX(Global.battlefields[x][y].getX());
                            Global.battlefields[x][y].picShow.setY(Global.battlefields[x][y].getY());
                            Global.root.getChildren().add(Global.battlefields[x][y].picShow);
                            break;
                        }
                        case 3: {
                            Global.battlefields[x][y] = new Battlefield(500 + (x * 70), 100 + (y * 70), "file:textures/firetopright.png");
                            Global.battlefields[x][y].picShow.setX(Global.battlefields[x][y].getX());
                            Global.battlefields[x][y].picShow.setY(Global.battlefields[x][y].getY());
                            Global.root.getChildren().add(Global.battlefields[x][y].picShow);
                            break;
                        }
                    }

                }
            }

        }catch (FileNotFoundException e){
            System.out.println("didnt find the file, sorry");
        }
    }

    public void setupCursor(){
        Global.cursor=new MyCursor("file:textures/cursor.png");
        Global.cursor.setMaxX(2);
        Global.cursor.setMaxY(10);
        Global.root.getChildren().add(Global.cursor.picShow);

    }

    public void setupBottomText(){
        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        String space8="        ";

        concatMe.append("W :-> UP");
        concatMe.append(space8);
        concatMe.append("A :-> LEFT");
        concatMe.append(space8);
        concatMe.append("S :-> DOWN");
        concatMe.append(space8);
        concatMe.append("D :-> RIGHT");
        concatMe.append(space8);
        concatMe.append("D :-> RIGHT");
        for (int i = 0; i <Global.player.army.length  ; i++) {
            if (i%4==0){
                concatMe.append(nl);
            }
            concatMe.append((i+1)+" :-> put/take "+Global.player.army[i].getName()+space8);

        }
        concatMe.append(space8);
        concatMe.append("ENTER :-> Confirm, let's go!");


        Global.bottomText.setText(concatMe.toString());
    }

    public void setupFieldRight(){
        Global.rightText.setText("Put your units on the field");
        Global.rightText.setX(1400);

        Font newFont=Font.loadFont(fontPath,35);
        Global.rightText.setFont(newFont);
    }

    public void preSetupField()  {
        deleteStarterNodes();
        setupFieldLeft();
        setupFieldMidlle();
        setupCursor();
        setupBottomText();
        setupFieldRight();



        Global.mySwitch=6;


    }

    public void moveUp() {
        if (!(Global.cursor.getY() - 1 < 0)) {
            Global.cursor.setY(Global.cursor.getY()-1);
            Global.cursor.picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));
        } else {
            addErrMsg("cant move there");
        }
    }


    public void moveDown(){
        if ((Global.cursor.getY() +1 < Global.cursor.getMaxY())) {
            Global.cursor.setY(Global.cursor.getY()+1);
            Global.cursor.picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));
        } else {
            addErrMsg("cant move there");
        }
    }

    public void moveLeft(){
        if (!(Global.cursor.getX() -1 < 0)) {
            Global.cursor.setX(Global.cursor.getX()-1);
            Global.cursor.picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
        } else {
            addErrMsg("cant move there");
        }
    }

    public void moveRight(){
        if ((Global.cursor.getX() +1 < Global.cursor.getMaxX())) {
            Global.cursor.setX(Global.cursor.getX()+1);
            Global.cursor.picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
        } else {
            addErrMsg("cant move there");
        }
    }

    /**
     * its basically hardcoded on switch cases on setting starting coordinates of units
     * its ugly, i know
     * @param value
     */
    public void setupField(KeyCode value){

        int treshold=Global.player.army.length;
        FieldController controller=new FieldController();



        switch (value){
            case DIGIT1:{
                if (0<treshold){
                    if (Global.player.army[0].x<0){
                        if (controller.isItaValidPlace(Global.cursor.getX(),Global.cursor.getY())){

                            Global.player.army[0].x=Global.cursor.getX();
                            Global.player.army[0].y=Global.cursor.getY();
                            Global.player.army[0].picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
                            Global.player.army[0].picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));

                            Global.root.getChildren().add(Global.player.army[0].picShow);

                        }
                    }else {
                        Global.player.army[0].x=-1;
                        Global.player.army[0].y=-1;
                        Global.root.getChildren().remove(Global.player.army[0].picShow);

                    }
                }
                break;
            }
            case DIGIT2:{

                if (1<treshold){
                    if (Global.player.army[1].x<0){
                        if (controller.isItaValidPlace(Global.cursor.getX(),Global.cursor.getY())){

                            Global.player.army[1].x=Global.cursor.getX();
                            Global.player.army[1].y=Global.cursor.getY();
                            Global.player.army[1].picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
                            Global.player.army[1].picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));

                            Global.root.getChildren().add(Global.player.army[1].picShow);

                        }
                    }else {
                        Global.player.army[1].x=-1;
                        Global.player.army[1].y=-1;
                        Global.root.getChildren().remove(Global.player.army[1].picShow);

                    }

                }
                break;
            }
            case DIGIT3:{
                if (2<treshold){
                    if (Global.player.army[2].x<0){
                        if (controller.isItaValidPlace(Global.cursor.getX(),Global.cursor.getY())){

                            Global.player.army[2].x=Global.cursor.getX();
                            Global.player.army[2].y=Global.cursor.getY();
                            Global.player.army[2].picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
                            Global.player.army[2].picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));

                            Global.root.getChildren().add(Global.player.army[2].picShow);

                        }
                    }else {
                        Global.player.army[2].x=-1;
                        Global.player.army[2].y=-1;
                        Global.root.getChildren().remove(Global.player.army[2].picShow);

                    }
                }
                break;
            }
            case DIGIT4:{
                if (3<treshold){
                    if (Global.player.army[3].x<0){
                        if (controller.isItaValidPlace(Global.cursor.getX(),Global.cursor.getY())){

                            Global.player.army[3].x=Global.cursor.getX();
                            Global.player.army[3].y=Global.cursor.getY();
                            Global.player.army[3].picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
                            Global.player.army[3].picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));

                            Global.root.getChildren().add(Global.player.army[3].picShow);

                        }
                    }else {
                        Global.player.army[3].x=-1;
                        Global.player.army[3].y=-1;
                        Global.root.getChildren().remove(Global.player.army[3].picShow);

                    }
                }
                break;
            }
            case DIGIT5:{
                if (4<treshold){
                    if (Global.player.army[4].x<0){
                        if (controller.isItaValidPlace(Global.cursor.getX(),Global.cursor.getY())){

                            Global.player.army[4].x=Global.cursor.getX();
                            Global.player.army[4].y=Global.cursor.getY();
                            Global.player.army[4].picShow.setX(Global.cursor.getOffsetX()+(Global.cursor.getX()*Global.cursor.getDistanceXY()));
                            Global.player.army[4].picShow.setY(Global.cursor.getOffsetY()+(Global.cursor.getY()*Global.cursor.getDistanceXY()));

                            Global.root.getChildren().add(Global.player.army[4].picShow);

                        }
                    }else {
                        Global.player.army[4].x=-1;
                        Global.player.army[4].y=-1;
                        Global.root.getChildren().remove(Global.player.army[4].picShow);

                    }
                }
                break;
            }

        }



    }

    public boolean areTheUnitsOnTheField(){
        FieldController controller=new FieldController();


        for (int i = 0; i < Global.player.army.length; i++) {
            if (Global.player.army[i].x <0){
                controller.addLogMsg("Place all your units on the field");
                return false;
            }
        }

        return true;

    }




    private void setupEnemyField(){
        FieldController controller=new FieldController();
        Global.cursor.setMaxX(12);
        Random rnd=new Random();
        int rnx=0;
        int rny=0;
        for (int i = 0; i < Global.enemy.army.length; i++) {

            do {
                rnx=rnd.nextInt(10,12);
                rny=rnd.nextInt(0,10);
            }while (!controller.isItaValidPlace(rnx,rny));

            Global.enemy.army[i].x=rnx;
            Global.enemy.army[i].y=rny;
            Global.enemy.army[i].picShow.setX(Global.cursor.getOffsetX()+(rnx*Global.cursor.getDistanceXY()));
            Global.enemy.army[i].picShow.setY(Global.cursor.getOffsetY()+(rny*Global.cursor.getDistanceXY()));

            Global.root.getChildren().add(Global.enemy.army[i].picShow);

        }
    }



    public void postSetupField(){
        FieldController controller=new FieldController();


        if (areTheUnitsOnTheField()){
            setupEnemyField();
            Global.cache=new MyCache();
            controller.setupRound();

            Global.bottomText.setText("ENTER :-> Hmmmmmm........ yea, should be fine, lets go");

            //todo ez a vége

            Global.root.getChildren().remove(Global.cursor.picShow);
            Global.root.getChildren().add(Global.cursor.picShow);

            Global.mySwitch=7;
        }
    }


    private void preMakeEnemyStatHelper(){
        Random rnd = new Random();
        int value= rnd.nextInt(6);
        switch (value){
            case 0:{
                Global.enemy.setAtk(1);
                break;
            }
            case 1:{
                Global.enemy.setDef(1);
                break;
            }
            case 2:{
                Global.enemy.setSpellpowa(1);
                break;
            }
            case 3:{
                Global.enemy.setIntellect(1);
                break;
            }
            case 4:{
                Global.enemy.setMoral(1);
                break;
            }
            case 5:{
                Global.enemy.setCritchance(1);
                break;
            }
        }

    }

    private void preMakeEnemySpellHelper(){
        Random rnd = new Random();

        Armor armor=new Armor();
        Destruction destruction=new Destruction();
        Salvation salvation=new Salvation();
        Lightning lightning=new Lightning();
        Inferno inferno=new Inferno();





        //this is so ughly, i should drink a coffee, too bad i dont drink coffee

        if (Global.enemy.getBuySpells().isEmpty()){
            LinkedList<Spell> helper=new LinkedList<>();
            helper.add(armor);
            helper.add(destruction);
            helper.add(salvation);
            helper.add(lightning);
            helper.add(inferno);

            int value= rnd.nextInt(helper.size());

            if (Global.enemy.setGold(helper.get(value).getLearnPrice())){
                Global.enemy.setBuySpells(helper.get(value),1);
            }
        }else {

            String[] keys = Global.enemy.getBuySpells().keySet().toArray(new String[0]);

            boolean isArmor=false;
            boolean isDestruction=false;
            boolean isSalvation=false;
            boolean isLightning=false;
            boolean isInferno=false;

            for (int i = 0; i < keys.length ; i++) {

                if (keys[i]==armor.getName()) {
                    isArmor=true;
                }
                if (keys[i]==destruction.getName()) {
                    isDestruction=true;
                }
                if (keys[i]==salvation.getName()) {
                    isSalvation=true;
                }
                if (keys[i]==lightning.getName()) {
                    isLightning=true;
                }
                if (keys[i]==inferno.getName()) {
                    isInferno=true;

                }


            }


            LinkedList<Spell> helper=new LinkedList<>();
            if (!isArmor){
                helper.add(armor);
            }
            if (!isDestruction){
                helper.add(destruction);
            }
            if (!isSalvation){
                helper.add(salvation);
            }
            if (!isLightning){
                helper.add(lightning);
            }
            if (!isInferno){
                helper.add(inferno);
            }

            int value= rnd.nextInt(helper.size());

            if (Global.enemy.setGold(helper.get(value).getLearnPrice())){
                Global.enemy.setBuySpells(helper.get(value),1);
            }

        }
    }


    public void preMakeEnemyUnitHelper(){
        Random rnd=new Random();
        int howMany=rnd.nextInt(3,6);

        LinkedList<BattleUnit> selectable=new LinkedList<>();

        Peasant peasant=new Peasant();
        Archer archer=new Archer();
        Griff griff=new Griff();
        Berserker berserker=new Berserker();
        Undead undead =new Undead();

        boolean isPeasant=false;
        boolean isArcher=false;
        boolean isGriff=false;
        boolean isBerserker=false;
        boolean isUndead=false;


        //this one is going to be ugly

        while (selectable.size()<howMany){
            int rolled= rnd.nextInt(0,6);
            switch (rolled){
                case 0:{
                    if (!isPeasant){
                        selectable.add(peasant);
                        isPeasant=true;
                    }

                    break;
                }
                case 1:{
                    if (!isArcher){
                        selectable.add(archer);
                        isArcher=true;
                    }
                    break;
                }
                case 2:{
                    if (!isGriff){
                        selectable.add(griff);
                        isGriff=true;
                    }
                    break;
                }
                case 3:{
                    if (!isBerserker){
                        selectable.add(berserker);
                        isBerserker=true;
                    }
                    break;
                }
                case 4:{
                    if(!isUndead){
                        selectable.add(undead);
                        isUndead=true;
                    }
                    break;

                }
            }
        }


        int buyWho=0;
        do {

            buyWho= rnd.nextInt(selectable.size());

            Global.enemy.setBuyArmy(selectable.get(buyWho),1 );

        }while (Global.errMsgBuffer==null);
        Global.errMsgBuffer=null;
        //TODO might wanna set errMsdBuffer = null before and after;
    }



    public void preMakeEnemy() {
        Global.enemy = new Hero(1000,"tehDakREdGL0rD", true);
        Random rnd = new Random();
        int statsToSpend = rnd.nextInt(9, 21);

        for (int i = 0; i < statsToSpend; i++) {
            preMakeEnemyStatHelper();
        }
        //stats done

        int spellsToBuy = rnd.nextInt(0, 4);
        for (int i = 0; i < spellsToBuy; i++) {
            preMakeEnemySpellHelper();
        }
        //spells done, now spend all on units

        preMakeEnemyUnitHelper();
        //units done

        Global.enemy.initSpells();
        Global.enemy.initArmy();
        for(int i = 0; i < Global.enemy.army.length; i++) {
            Global.enemy.army[i].setIsEnemy(true);
        }
        Global.enemy.initMana();




        //TODO this should set the color to red, im not sure so ill check it later
        ColorAdjust tryMe=new ColorAdjust();
        tryMe.setHue(359);
        for (int i = 0; i < Global.enemy.armyIcons.length; i++) {
            Global.enemy.armyIcons[i].setEffect(tryMe);
            Global.enemy.army[i].picShow.setEffect(tryMe);
        }



        makeEnemy();

    }

    public void makeEnemyRightText(){
        String concat="";
        String nl="\n";
        StringBuilder concatMe=new StringBuilder();
        concatMe.append("tehDakREdGL0rD"+nl);
        concatMe.append("Gold............"+Global.enemy.getGold()+nl);
        concatMe.append("Attack......."+Global.enemy.getAtk()+nl);
        concatMe.append("Defense......"+Global.enemy.getDef()+nl);
        concatMe.append("Spellpowa..."+Global.enemy.getSpellpowa()+nl);
        concatMe.append("Intellect....."+Global.enemy.getIntellect()+nl);
        concatMe.append("Moral........."+Global.enemy.getMoral()+nl);
        concatMe.append("Crit............"+Global.enemy.getCritchance()+nl);

        if (!(Global.enemy.getBuyArmy().isEmpty())) {

            for (Map.Entry<String, Integer> entry : Global.enemy.getBuyArmy().entrySet()) {
                //first time im using Map.Entry, it works like a charm, i love it,

                concatMe.append(entry.getKey() + " ~~~ " + entry.getValue() +" units"+ nl);
            }
        }

        if (!(Global.enemy.getBuySpells().isEmpty())) {

            for (String key: Global.enemy.getBuySpells().keySet()) {

                concatMe.append("Spell :~> "+key+ nl);
            }
        }

        Global.rightText.setText(concatMe.toString());
    }

    public void makeEnemy(){
        makeEnemyRightText();


        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        String space8="        ";

        concatMe.append("Welcome DA GRETEST, DA BESTEST!!!"+nl );
        concatMe.append(space8+space8+space8+" <tehDakREdGL0rD>44!!11"+nl+nl);
        concatMe.append(space8+"Bla, bla blablabla bla blabla bla bla bla," +nl);
        concatMe.append("blabla BLumbla bla, balabla blambla, lababa."+ nl);
        concatMe.append("Blabla bla, blablabla bla blabla blabla bla." +nl);
        concatMe.append("Balabamba bambala labamba bambla babla"+nl);
        concatMe.append("Lalalal babab mamama labambamalblamblambl lbbbbb"+nl);
        concatMe.append("lbalmabmlbalmbamlbamlblmbamlbamalblmabbalmba"+nl);
        concatMe.append("lllllllllllllllllllllllllllllllllllllllllllllllllllll"+nl);
        concatMe.append("mmmmmmmmmmmmmmmmmmmmmmmmmmmmmm"+nl);
        concatMe.append("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+nl);
        concatMe.append("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+nl);
        concatMe.append("Bla blablablblblblbla bla bla blaaa, blüüüüaaaaaaa!");



        Global.middleText.setText(concatMe.toString());

        concatMe=new StringBuilder();
        concatMe.append(space8+space8+space8+"ESC :-> this is terrible i wanna escape"+space8);
        concatMe.append("ENTER :-> you are the greatest story writer!"+space8);


        Global.bottomText.setText(concatMe.toString());

        Global.mySwitch=5;

    }

    public void postMakeEnemy(){
        preSetupField();
    }


    public void postSpellLoader(){
        Global.player.initSpells();
        Global.player.initMana();
        preMakeEnemy();
        makeEnemy();


    }

    public void spellLoader(){
        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        String space8="        ";
        boolean isLightning=false;
        boolean isInferno=false;
        boolean isSalvation=false;
        boolean isArmor=false;
        boolean isDestruction=false;

        if (!(Global.player.getBuySpells().isEmpty())){
            for (String spellDa: Global.player.getBuySpells().keySet()) {
                switch (spellDa){
                    case "Armor":{
                        isArmor=true;
                        break;
                    }
                    case "Destruction":{
                        isDestruction=true;
                        break;
                    }
                    case "Salvation":{
                        isSalvation=true;
                        break;
                    }
                    case "Lightning":{
                        isLightning=true;
                        break;
                    }
                    case "Inferno":{
                        isInferno=true;
                        break;
                    }
                }
            }
        }

        Lightning lightning=new Lightning();
        Inferno inferno=new Inferno();
        Salvation salvation=new Salvation();
        Armor armor=new Armor();
        Destruction destruction=new Destruction();
        concatMe.append("Learn spells if you'd like"+nl+nl);
        if (!isLightning){
            concatMe.append("Lightning ~ "+lightning.getDescription()+nl);
        }
        if (!isInferno){
            concatMe.append("Inferno ~ "+inferno.getDescription()+nl);
        }
        if (!isSalvation){
            concatMe.append("Salvation ~ "+salvation.getDescription()+nl);
        }
        if (!isArmor){
            concatMe.append("Armor ~ "+armor.getDescription()+nl);
        }

        if (!isDestruction){
            concatMe.append("Destruction ~ "+destruction.getDescription()+nl);
        }

        Global.middleText.setText(concatMe.toString());


    }

    public void preSpellLoader(){
        if (Global.player.getBuyArmy().isEmpty()){
            addErrMsg("You cant win a war alone");
        }else {
            StringBuilder concatMe=new StringBuilder();
            String nl="\n";
            String space8="        ";
            Lightning lightning=new Lightning();
            Inferno inferno=new Inferno();
            Salvation salvation=new Salvation();
            Armor armor=new Armor();
            Destruction destruction=new Destruction();
            concatMe.append("Learn spells if you'd like"+nl+nl);
            concatMe.append("Lightning ~ "+lightning.getDescription()+nl);
            concatMe.append("Inferno ~ "+inferno.getDescription()+nl);
            concatMe.append("Salvation ~ "+salvation.getDescription()+nl);
            concatMe.append("Armor ~ "+armor.getDescription()+nl);
            concatMe.append("Destruction ~ "+destruction.getDescription()+nl);


            Global.middleText.setText(concatMe.toString());


            concatMe=new StringBuilder();

            concatMe.append("1 :-> Unlearn Lightning");
            concatMe.append(space8);
            concatMe.append("3 :-> Learn Lightning");
            concatMe.append(space8);
            concatMe.append("Q :-> Unlearn Inferno");
            concatMe.append(space8);
            concatMe.append("E :-> Learn Inferno");
            concatMe.append(space8);
            concatMe.append("A :-> Unlearn Salvation"+nl);
            concatMe.append(space8);
            concatMe.append("D :-> Learn Salvation");
            concatMe.append(space8);
            concatMe.append("U :-> Unlearn Armor");
            concatMe.append(space8);
            concatMe.append("O :-> Learn Armor");
            concatMe.append(space8);
            concatMe.append("J :-> Unlearn Destruction"+nl);
            concatMe.append(space8);
            concatMe.append("L :-> Learn Destruction"+nl);
            for (int i = 0; i < 8; i++) {
                concatMe.append(space8);
            }
            concatMe.append("ENTER :-> Perfect! show me tehDakREdGL0rD");

            Global.bottomText.setText(concatMe.toString());

            Global.mySwitch=4;

        }


    }



    public void unitHandler(){

    }

    public void preUnitLoader(){
        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        Peasant peasant=new Peasant();
        Archer archer=new Archer();
        Griff griff=new Griff();
        Berserker berserker=new Berserker();
        Undead undead =new Undead();


        concatMe.append("                                               Build your army!"+nl);
        concatMe.append("Peasant ~ price: "+peasant.getPrice()+"g");
        concatMe.append(" ~ atk: "+peasant.getMinDmg()+"-"+peasant.getMaxDmg());
        concatMe.append(" ~ hp: "+peasant.getHealth());
        concatMe.append(" ~ speed: "+peasant.getSpeed()+nl);
        concatMe.append(" ~ initiative: "+peasant.getInitiative());
        concatMe.append(" ~ special ability: None"+nl);

        concatMe.append("Archer ~ price: "+archer.getPrice()+"g");
        concatMe.append(" ~ atk: "+archer.getMinDmg()+"-"+archer.getMaxDmg());
        concatMe.append(" ~ hp: "+archer.getHealth());
        concatMe.append(" ~ speed: "+archer.getSpeed()+nl);
        concatMe.append(" ~ initiative: "+archer.getInitiative()+nl);
        concatMe.append(" ~ special ability: "+archer.special()+nl);

        concatMe.append("Griff ~ price: "+griff.getPrice()+"g");
        concatMe.append(" ~ atk: "+griff.getMinDmg()+"-"+griff.getMaxDmg());
        concatMe.append(" ~ hp: "+griff.getHealth());
        concatMe.append(" ~ speed: "+griff.getSpeed()+nl);
        concatMe.append(" ~ initiative: "+griff.getInitiative()+nl);
        concatMe.append(" ~ special ability: "+griff.special()+nl);

        concatMe.append("Berserker ~ price: "+berserker.getPrice()+"g");
        concatMe.append(" ~ atk: "+berserker.getMinDmg()+"-"+berserker.getMaxDmg());
        concatMe.append(" ~ hp: "+berserker.getHealth());
        concatMe.append(" ~ speed: "+berserker.getSpeed()+nl);
        concatMe.append(" ~ initiative: "+berserker.getInitiative()+nl);
        concatMe.append(" ~ special ability: "+berserker.special()+nl);

        concatMe.append("Undead ~ price: "+undead.getPrice()+"g");
        concatMe.append(" ~ atk: "+undead.getMinDmg()+"-"+undead.getMaxDmg());
        concatMe.append(" ~ hp: "+undead.getHealth());
        concatMe.append(" ~ speed: "+undead.getSpeed()+nl);
        concatMe.append(" ~ initiative: "+undead.getInitiative()+nl);
        concatMe.append(" ~ special ability: "+undead.special()+nl);





        Global.middleText.setText(concatMe.toString());
        Global.middleText.setX(520);
        Global.middleText.setY(160);

        concatMe=new StringBuilder();

        String space8="        ";
        concatMe.append("1 :-> -1 Peasant");
        concatMe.append(space8);
        concatMe.append("3 :-> +1 Peasant");
        concatMe.append(space8);
        concatMe.append("Q :-> -1 Archer");
        concatMe.append(space8);
        concatMe.append("E :-> +1 Archer");
        concatMe.append(space8);
        concatMe.append("A :-> -1 Griff");
        concatMe.append(space8);
        concatMe.append("D :-> +1 Griff"+nl);
        concatMe.append(space8);
        concatMe.append("U :-> -1 Berserker");
        concatMe.append(space8);
        concatMe.append("O :-> +1 Berserker");
        concatMe.append(space8);
        concatMe.append("J :-> -1 Undead");
        concatMe.append(space8);
        concatMe.append("L :-> +1 Undead"+nl);
        for (int i = 0; i < 8; i++) {
            concatMe.append(space8);
        }
        concatMe.append("ENTER :-> Perfect! let me buy my spells");

        Global.bottomText.setText(concatMe.toString());
        Global.mySwitch=3;
    }

    public void unitLoader(){
        Global.player.initArmy();
        preSpellLoader();
    }

    //TODO later implement, as a controls value
    public void bottomTextUpdate(){

    }

    public void middleTextUpdate(){
        switch (Global.mySwitch){
            case 2:{
                statLoader();
                break;
            }
            case 4:{
                spellLoader();
                break;
            }
        }

    }

    public void topTextUpdate(){

    }

    public void leftTextUpdate(){
        String concat="";
        String nl="\n";
        StringBuilder concatMe=new StringBuilder();
        concatMe.append("Kawaii Hero OwO"+nl);
        concatMe.append("Gold............"+Global.player.getGold()+nl);
        concatMe.append("Attack......."+Global.player.getAtk()+nl);
        concatMe.append("Defense......"+Global.player.getDef()+nl);
        concatMe.append("Spellpowa..."+Global.player.getSpellpowa()+nl);
        concatMe.append("Intellect....."+Global.player.getIntellect()+nl);
        concatMe.append("Moral........."+Global.player.getMoral()+nl);
        concatMe.append("Crit............"+Global.player.getCritchance()+nl);

        if (!(Global.player.getBuyArmy().isEmpty())) {

            for (Map.Entry<String, Integer> entry : Global.player.getBuyArmy().entrySet()) {
                //first time im using Map.Entry, it works like a charm, i love it,

                concatMe.append(entry.getKey() + " ~~~ " + entry.getValue() +" units"+ nl);
            }
        }

        if (!(Global.player.getBuySpells().isEmpty())) {

            for (String key: Global.player.getBuySpells().keySet()) {

                concatMe.append("Spell :~> "+key+ nl);
            }
        }

        Global.leftText.setText(concatMe.toString());
    }

    public void addErrMsg(String msg){
        if (Global.errMsgBuffer==null){
            Global.errMsgBuffer=new LinkedList<>();

        }
        Global.errMsgBuffer.add(msg);

    }

    public void readErrMsg(){
        if (Global.errMsgBuffer==null){
            Global.topText.setText("");
        }else{
            StringBuilder concatMe=new StringBuilder();
            for (String me: Global.errMsgBuffer) {
                concatMe.append(me+"\n");
            }
            Global.topText.setText(concatMe.toString());
            Global.errMsgBuffer=null;
        }
    }

    public void preStatLoader(){
        leftTextUpdate();

        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        concatMe.append("Set up your Hero's stats"+nl+nl+nl);
        concatMe.append("Attack ~ +10% dmg done by units"+nl);
        concatMe.append("Defense ~ +5% dmg redu"+nl);
        concatMe.append("Spellpowa ~ Power of spells"+nl);
        concatMe.append("Intellect ~ +10 mana"+nl);
        concatMe.append("Moral ~ +1 initiative"+nl);
        concatMe.append("Crit ~ +5% crit"+nl);

        concatMe.append(nl+nl+"Currently stat points cost: "+Global.player.getStatCost()+"g");

        Global.middleText.setText(concatMe.toString());
        Global.middleText.setX(650);
        Global.middleText.setY(250);



        concatMe=new StringBuilder();
        String space8="        ";
        concatMe.append("1 :-> -1 atk");
        concatMe.append(space8);
        concatMe.append("3 :-> +1 atk");
        concatMe.append(space8);
        concatMe.append("Q :-> -1 def");
        concatMe.append(space8);
        concatMe.append("E :-> +1 def");
        concatMe.append(space8);
        concatMe.append("A :-> -1 spellpowa");
        concatMe.append(space8);
        concatMe.append("D :-> +1 spellpowa"+nl);
        concatMe.append(space8);
        concatMe.append("7 :-> -1 int");
        concatMe.append(space8);
        concatMe.append("9 :-> +1 int");
        concatMe.append(space8);
        concatMe.append("U :-> -1 moral");
        concatMe.append(space8);
        concatMe.append("O :-> +1 moral");
        concatMe.append(space8);
        concatMe.append("J :-> -1 crit");
        concatMe.append(space8);
        concatMe.append("L :-> +1 crit"+nl);
        for (int i = 0; i < 8; i++) {
            concatMe.append(space8);
        }
        concatMe.append("ENTER :-> Perfect! let me buy my army");

        Global.bottomText.setText(concatMe.toString());
        Global.bottomText.setX(220);

        Global.mySwitch=2;

    }
    public void statLoader(){
        StringBuilder concatMe=new StringBuilder();
        String nl="\n";
        concatMe.append("Set up your Hero's stats"+nl+nl+nl);
        concatMe.append("Attack ~ +10% dmg done by units"+nl);
        concatMe.append("Defense ~ +5% dmg redu"+nl);
        concatMe.append("Spellpowa ~ Power of spells"+nl);
        concatMe.append("Intellect ~ +10 mana"+nl);
        concatMe.append("Moral ~ +1 initiative"+nl);
        concatMe.append("Crit ~ +5% crit"+nl);

        concatMe.append(nl+nl+"Currently stat points cost: "+Global.player.getStatCost()+"g");

        Global.middleText.setText(concatMe.toString());
    }
    public void postStatLoader(){
        leftTextUpdate();
    }

    /**
     * Case 1:
     * @param gold
     */
    public void difficultyLoader(int gold){
        Global.player=new Hero(gold,"Kawaii Hero OwO",false);
        //leftTextUpdate();
        preStatLoader();


    }


    /**
     * method called when the program starts, executes first.
     */
    public void startup(){
        Background bg=new Background(0,0,"file:textures/background.png");
        bg.picShow.setX(0);
        bg.picShow.setY(0);
        Global.root.getChildren().add(bg.picShow);
        Global.middleText=new Text();

        Font meineFont= Font.loadFont(fontPath,40);
        Global.middleText.setFont(meineFont);
        Global.middleText.setX(760);
        Global.middleText.setY(400);
        Global.middleText.setText("Please select difficulty\n\n\n          Easy - (1300g)\n       Medium - (1000g)\n           Hard - (700g)");
        Global.root.getChildren().add(Global.middleText);

        Global.bottomText =new Text();
        Global.bottomText.setFont(meineFont);
        Global.bottomText.setX(420);
        Global.bottomText.setY(900);
        Global.bottomText.setText("This field here will show you which controls are available at the moment for you\n                                       F : EASY       5 : MEDIUM       K : HARD ");
        Global.root.getChildren().add(Global.bottomText);

        Global.leftText=new Text();
        Global.leftText.setFont(meineFont);
        Global.leftText.setY(50);
        Global.leftText.setX(130);
        Global.leftText.setText("");
        Global.root.getChildren().add(Global.leftText);

        Global.rightText=new Text();
        Global.rightText.setFont(meineFont);
        Global.rightText.setY(50);
        Global.rightText.setX(1440);
        Global.rightText.setText("");
        Global.root.getChildren().add(Global.rightText);

        Global.topText=new Text();
        Global.topText.setFont(meineFont);
        Global.topText.setY(55);
        Global.topText.setX(600);
        Global.topText.setText("");
        Global.root.getChildren().add(Global.topText);


        Global.mySwitch=1;
        System.out.println(Global.mySwitch);

    }

}
