package copyme.controls;

import copyme.globalvars.Global;
import copyme.unitclass.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

import java.util.*;

/**
 * this is the controller for the battle itself
 */
public class FieldController {


    public void moveUp() {
        if (!(Global.cursor.getY() - 1 < 0)) {
            Global.cursor.setY(Global.cursor.getY() - 1);
            Global.cursor.picShow.setY(Global.cursor.getOffsetY() + (Global.cursor.getY() * Global.cursor.getDistanceXY()));
        } else {
            addLogMsg("cant move there");
        }
    }


    public void moveDown() {
        if ((Global.cursor.getY() + 1 < Global.cursor.getMaxY())) {
            Global.cursor.setY(Global.cursor.getY() + 1);
            Global.cursor.picShow.setY(Global.cursor.getOffsetY() + (Global.cursor.getY() * Global.cursor.getDistanceXY()));
        } else {
            addLogMsg("cant move there");
        }
    }

    public void moveLeft() {
        if (!(Global.cursor.getX() - 1 < 0)) {
            Global.cursor.setX(Global.cursor.getX() - 1);
            Global.cursor.picShow.setX(Global.cursor.getOffsetX() + (Global.cursor.getX() * Global.cursor.getDistanceXY()));
        } else {
            addLogMsg("cant move there");
        }
    }

    public void moveRight() {
        if ((Global.cursor.getX() + 1 < Global.cursor.getMaxX())) {
            Global.cursor.setX(Global.cursor.getX() + 1);
            Global.cursor.picShow.setX(Global.cursor.getOffsetX() + (Global.cursor.getX() * Global.cursor.getDistanceXY()));
        } else {
            addLogMsg("cant move there");
        }
    }

    public void castDesu() {

        if (Global.cache.getHeroAction() > 0) {
            if (Global.player.useSpell(Global.player.spells[Global.cache.getHeroAction() - 1].getManaCost())) {
                if (Global.player.spells[Global.cache.getHeroAction() - 1].castSpell(false, Global.cursor.getX(), Global.cursor.getY())) {
                    Global.player.setLastAttackedRound(Global.cache.getRound());
                    Global.player.useSpellMana(Global.player.spells[Global.cache.getHeroAction() - 1].getManaCost());
                    //todo might wanna do later Global.cache.setHeroAction(-1);
                    preEndTurn();
                }
            }
        } else {
            if (Global.player.basicAttack.castSpell(false, Global.cursor.getX(), Global.cursor.getY())) {
                Global.player.setLastAttackedRound(Global.cache.getRound());
                //todo might wanna do later Global.cache.setHeroAction(-1);
                preEndTurn();

            }
        }
    }


    /**
     * check coordinates if the place i wanna move is a valid place or not
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isItaValidPlace(int x, int y) {
        if (x < 0 || x > Global.cursor.getMaxX() - 1 || y < 0 || y > Global.cursor.getMaxY() - 1) {
            return false;
        } else {
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.player.army[i] != null) {
                    if (Global.player.army[i].y == y && Global.player.army[i].x == x) {
                        addLogMsg("Theres someone already");
                        return false;

                    }
                }
            }
            for (int i = 0; i < Global.enemy.army.length; i++) {
                if (Global.enemy.army[i] != null) {
                    if (Global.enemy.army[i].y == y && Global.enemy.army[i].x == x) {
                        addLogMsg("Theres someone already");

                        return false;
                    }
                }
            }

            return true;
        }
    }

    private void addUnitHelper() {

    }

    public void setupRound() {


        for (int i = 0; i < Global.player.army.length; i++) {
            if (Global.player.army[i] != null) {
                Global.player.army[i].setCanRetaliate(true);


                if (Global.cache.nextUnitOrder.isEmpty()) {
                    Global.cache.nextUnitOrder.add(Global.player.army[i]);


                } else {
                    boolean putAfter = false;

                    for (int j = 0; j < Global.cache.nextUnitOrder.size(); j++) {
                        if (Global.player.army[i].getFinalInit() >= Global.cache.nextUnitOrder.get(j).getFinalInit()) {
                            Global.cache.nextUnitOrder.add(j, Global.player.army[i]);
                            System.out.println(Global.cache.nextUnitOrder.size() + " at the break point");
                            putAfter = true;
                            break;
                        }
                    }

                    if (!putAfter) {
                        Global.cache.nextUnitOrder.add(Global.player.army[i]);
                    }
                }
            }
        }


        for (int i = 0; i < Global.enemy.army.length; i++) {
            if (Global.enemy.army[i] != null) {
                Global.enemy.army[i].setCanRetaliate(true);
                if (Global.cache.nextUnitOrder.isEmpty()) {
                    Global.cache.nextUnitOrder.add(Global.enemy.army[i]);

                } else {
                    boolean putAfter = false;


                    for (int j = 0; j < Global.cache.nextUnitOrder.size(); j++) {
                        if (Global.enemy.army[i].getFinalInit() >= Global.cache.nextUnitOrder.get(j).getFinalInit()) {
                            Global.cache.nextUnitOrder.add(j, Global.enemy.army[i]);
                            putAfter = true;

                            break;
                        }
                    }

                    if (!putAfter) {
                        Global.cache.nextUnitOrder.add(Global.enemy.army[i]);

                    }
                }
            }
        }
        System.out.println(Global.cache.nextUnitOrder.size());

        //int offsetx =25;
        for (int i = 0; i < Global.cache.nextUnitOrder.size(); i++) {



            /*ImageView tmp=new ImageView(Global.cache.nextUnitOrder.get(i).topPic);

            tmp.setX(525+(i*80));
            tmp.setY(15);*/
            if (Global.cache.nextUnitOrder.get(i).getIsEnemy()) {
                ColorAdjust tryMe = new ColorAdjust();
                tryMe.setHue(359);
                Global.cache.nextUnitOrder.get(i).topPicShow.setEffect(tryMe);

            }
            Global.cache.nextUnitOrder.get(i).topPicShow.setX(525 + (i * 80));
            Global.cache.nextUnitOrder.get(i).topPicShow.setY(15);


            Global.root.getChildren().add(Global.cache.nextUnitOrder.get(i).topPicShow);
        }

        Global.cache.setRound(Global.cache.getRound() + 1);

    }


    public void addLogMsg(String msg) {
        if (Global.errMsgBuffer == null) {
            Global.errMsgBuffer = new LinkedList<>();

        }
        Global.errMsgBuffer.add(msg);

    }

    public void attackUnitHelper(int indexPlayer, boolean isEnemyAttacking, int indexEnemy) {
        int preHealthPlayer = Global.player.army[indexPlayer].getTotalHealth();
        int preHealthEnemy = Global.enemy.army[indexPlayer].getTotalHealth();


        if (isEnemyAttacking) {

        } else {
            Global.player.army[indexPlayer].attack(indexPlayer, isEnemyAttacking, indexEnemy);
        }

    }


    public void preEndTurn() {
        postEndTurn();
        Global.cursor.destructorOfMove();
        Global.cache.inRangeUnits.clear();
        Global.cache.current.clear();
        if (Global.cache.nextUnitOrder.isEmpty()) {
            // System.out.println(Global.cache.nextUnitOrder.size()+" this is the size");
            setupRound();
        }
        for (int i = 0; i < Global.cache.nextUnitOrder.size(); ) {
            if (Global.cache.nextUnitOrder.getFirst().getIsEnemy()) {
                if (Global.enemy.army[Global.cache.nextUnitOrder.getFirst().indexOf] == null) {
                    addLogMsg("Next should be " + (Global.cache.nextUnitOrder.getFirst().getIsEnemy() ? "Enemy" : "my") + " " + Global.cache.nextUnitOrder.getFirst().getName() + "\nbut its dead");
                    Global.root.getChildren().remove(Global.cache.nextUnitOrder.getFirst().topPicShow);
                    Global.cache.nextUnitOrder.removeFirst();
                } else {
                    Global.cache.current.put(Global.cache.nextUnitOrder.getFirst().getName(), Global.cache.nextUnitOrder.getFirst().getIsEnemy());
                    addLogMsg("Now in turn " + (isItAnEnemyAtTheMoment() ? "enemy" : "my") + Global.cache.nextUnitOrder.getFirst().getName());
                    Global.root.getChildren().remove(Global.cache.nextUnitOrder.getFirst().topPicShow);
                    Global.cache.nextUnitOrder.removeFirst();
                    break;

                }
            } else {
                if (Global.player.army[Global.cache.nextUnitOrder.getFirst().indexOf] == null) {
                    addLogMsg("Next should be " + (Global.cache.nextUnitOrder.getFirst().getIsEnemy() ? "Enemy" : "my") + " " + Global.cache.nextUnitOrder.getFirst().getName() + "\nbut its dead. enter to Continue");
                    Global.root.getChildren().remove(Global.cache.nextUnitOrder.getFirst().topPicShow);
                    Global.cache.nextUnitOrder.removeFirst();
                } else {
                    Global.cache.current.put(Global.cache.nextUnitOrder.getFirst().getName(), Global.cache.nextUnitOrder.getFirst().getIsEnemy());
                    addLogMsg("Now in turn " + (isItAnEnemyAtTheMoment() ? "enemy" : "my") + Global.cache.nextUnitOrder.getFirst().getName());
                    Global.root.getChildren().remove(Global.cache.nextUnitOrder.getFirst().topPicShow);
                    Global.cache.nextUnitOrder.removeFirst();
                    break;

                }
            }
        }
        //System.out.println(Global.cache.nextUnitOrder.size());

        //addLogMsg("Now in turn "+(isItAnEnemyAtTheMoment()?"enemy":"my")+Global.cache.nextUnitOrder.getFirst().getName());
        //  Global.root.getChildren().remove(Global.cache.nextUnitOrder.getFirst().topPicShow);
        // Global.cache.nextUnitOrder.removeFirst();
        //Global.cursor.cursorHide();
        Global.cache.setCursorSwitch(0);


        setDefaults();
        endTurn();
    }


    public void setBottomTextPlayer(boolean isItEnemyTurn) {
        StringBuilder concatMe = new StringBuilder();
        String nl = "\n";
        String space8 = "        ";
        if (!isItEnemyTurn) {

            switch (Global.cache.getCursorSwitch()) {
                case 0: {
                    concatMe.append("1 :-> Use Hero" + space8);
                    concatMe.append("2 :-> attack with unit" + space8);
                    concatMe.append("3 :-> move with unit");
                    break;
                }
                case 1: {
                    if (Global.cache.getHeroAction() < 0) {
                        concatMe.append("0 :-> Heroic Strike"+space8);
                        for (int i = 0; i < Global.player.spells.length; i++) {
                            concatMe.append((i + 1) + " :-> " + Global.player.spells[i].getName() + space8);
                        }
                    } else {
                        concatMe.append("W :-> UP" + space8);
                        concatMe.append("A :-> LEFT" + space8);
                        concatMe.append("S :-> DOWN" + space8);
                        concatMe.append("D :-> RIGHT" + space8);
                        concatMe.append("Space :-> Cast" + nl);
                    }
                    concatMe.append("ESC :-> oh f@&# go back!");
                    break;
                }
                case 2: {
                    for (int i = 0; i < Global.cache.inRangeUnits.size(); i++) {
                        concatMe.append((i + 1) + " :-> " + Global.cache.inRangeUnits.get(i).getName() + space8);
                    }

                    concatMe.append("\nESC :-> oh f@&# go back!");
                    break;
                }
                case 3: {
                    concatMe.append("W :-> UP" + space8);
                    concatMe.append("A :-> LEFT" + space8);
                    concatMe.append("S :-> DOWN" + space8);
                    concatMe.append("D :-> RIGHT" + space8);
                    concatMe.append("SPACE :-> put down" + space8);
                    concatMe.append("\nESC :-> oh f@&# go back!");
                    break;
                }
            }
        }
        concatMe.append(nl + "ENTER :-> next turn");
        Global.bottomText.setText(concatMe.toString());
    }


    public void fieldUpdateLeft() {


        //player part:
        Global.fieldStatsPlayer[0].setText(Global.player.getName() + " Round: " + Global.cache.getRound());
        Global.fieldStatsPlayer[1].setText("Mana: " + Global.player.getMana());
        for (int i = 2; i < Global.player.getBuyArmy().keySet().size() + 2; i++) {
            if (Global.player.army[i - 2] != null) {
                Global.fieldStatsPlayer[i].setText(":" + Global.player.army[i - 2].getCurrentArmyNumber() + ":");
            } else {
                Global.fieldStatsPlayer[i].setText("-");
            }
        }
        for (int i = 2 + Global.player.getBuyArmy().keySet().size(); i < 2 + Global.player.getBuyArmy().keySet().size() + Global.player.getBuySpells().keySet().size(); i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsPlayer[i].setText(":" + Global.player.spells[i - 2 - Global.player.getBuyArmy().keySet().size()].getManaCost() + ":");
        }

        //enemy part:
        Global.fieldStatsEnemy[0].setText(Global.enemy.getName());
        Global.fieldStatsEnemy[1].setText("Mana: " + Global.enemy.getMana());
        for (int i = 2; i < Global.enemy.getBuyArmy().keySet().size() + 2; i++) {
            if (Global.enemy.army[i - 2] != null) {
                Global.fieldStatsEnemy[i].setText(":" + Global.enemy.army[i - 2].getCurrentArmyNumber() + ":");
            } else {
                Global.fieldStatsEnemy[i].setText("-");
            }
        }
        for (int i = 2 + Global.enemy.getBuyArmy().keySet().size(); i < 2 + Global.enemy.getBuyArmy().keySet().size() + Global.enemy.getBuySpells().keySet().size(); i++) {
            //will check back later here if its working correctly or not
            Global.fieldStatsEnemy[i].setText(":" + Global.enemy.spells[i - 2 - Global.enemy.getBuyArmy().keySet().size()].getManaCost() + ":");
        }


    }


    public boolean isItAnEnemyAtTheMoment() {
        boolean isEnemy = true;
        for (String name : Global.cache.current.keySet()) {
            isEnemy = Global.cache.current.get(name);
            System.out.println(Global.cache.current.get(name) + " this is the current unit");

        }
        return isEnemy;
    }


    public void eventZero(KeyCode event) {
        switch (event) {
            case DIGIT1: {
                if (Global.cache.getRound() > Global.player.getLastAttackedRound()) {
                    Global.cache.setCursorSwitch(1);

                } else {
                    addLogMsg("Hero has already atkd in this round");
                }
                break;
            }
            case DIGIT2: {
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i] != null) {
                        for (String namaewa : Global.cache.current.keySet()) {
                            if (namaewa == Global.player.army[i].getName()) {
                                Global.player.army[i].getTargetables(true);
                            }
                        }

                    }

                }
                if (!Global.cache.inRangeUnits.isEmpty()) {
                    Global.cache.setCursorSwitch(2);

                } else {
                    addLogMsg("there is nobody in range");
                }
                break;
            }
            case DIGIT3: {
                Global.cache.setCursorSwitch(3);
                Global.cursor.initMove(Global.player);
                break;
            }
        }
    }

    public void eventOne(KeyCode event) {
        if (Global.cache.getHeroAction() < 0) {

            switch (event) {
                case DIGIT0: {
                    Global.cache.setHeroAction(0);

                    Global.cursor.cursorShow();
                    break;
                }
                //todo majd ki kell vonni 1 et a set hero actionból mert 1 től spellek csak
                case DIGIT1: {
                    if (1 < Global.player.spells.length + 1) {
                        Global.cache.setHeroAction(1);
                        Global.cursor.cursorShow();

                        Global.cursor.chooseSelectedImage(Global.player.spells[0].getPicResource());
                    }
                    break;
                }
                case DIGIT2: {
                    if (2 < Global.player.spells.length + 1) {
                        Global.cache.setHeroAction(2);
                        Global.cursor.cursorShow();

                        Global.cursor.chooseSelectedImage(Global.player.spells[1].getPicResource());
                    }
                    break;
                }
                case DIGIT3: {
                    if (3 < Global.player.spells.length + 1) {
                        Global.cache.setHeroAction(3);
                        Global.cursor.cursorShow();

                        Global.cursor.chooseSelectedImage(Global.player.spells[2].getPicResource());
                    }
                    break;
                }
                case DIGIT4: {
                    if (4 < Global.player.spells.length + 1) {
                        Global.cache.setHeroAction(4);
                        Global.cursor.cursorShow();

                        Global.cursor.chooseSelectedImage(Global.player.spells[3].getPicResource());
                    }
                    break;
                }
                case DIGIT5: {
                    if (5 < Global.player.spells.length + 1) {
                        Global.cache.setHeroAction(5);
                        Global.cursor.cursorShow();


                        Global.cursor.chooseSelectedImage(Global.player.spells[4].getPicResource());
                    }
                    break;
                }
                case ESCAPE: {
                    Global.cache.setCursorSwitch(0);
                    Global.cursor.loseSelectedImage();
                    Global.cursor.setToDefaultCo();
                    break;
                }
            }
        } else {
            switch (event) {
                case W: {
                    moveUp();
                    break;
                }
                case A: {
                    moveLeft();
                    break;
                }
                case S: {
                    moveDown();
                    break;
                }
                case D: {
                    moveRight();
                    break;
                }
                case SPACE: {
                    castDesu();
                    Global.cursor.loseSelectedImage();
                    break;
                }
                case ESCAPE: {
                    Global.cache.setHeroAction(-1);
                    Global.cursor.loseSelectedImage();
                    Global.cursor.setToDefaultCo();
                    break;
                }

            }

        }
    }

    public void eventTwo(KeyCode event) {
        String tmp = "";

        for (String name : Global.cache.current.keySet()) {
            tmp = name;
        }
        int ind = 0;
        for (int i = 0; i < Global.player.army.length; i++) {
            if (Global.player.army[i] != null) {

                if (Global.player.army[i].getName() == tmp) {
                    ind = Global.player.army[i].indexOf;
                }
            }
        }


        switch (event) {

            case DIGIT1: {
                if (0 < Global.cache.inRangeUnits.size()) {

                    Global.player.army[ind].attack(ind, false, Global.cache.inRangeUnits.get(0).indexOf);
                    preEndTurn();
                }
                break;
            }
            case DIGIT2: {
                if (1 < Global.cache.inRangeUnits.size()) {

                    Global.player.army[ind].attack(ind, false, Global.cache.inRangeUnits.get(1).indexOf);
                    preEndTurn();
                }

                break;
            }
            case DIGIT3: {
                if (2 < Global.cache.inRangeUnits.size()) {

                    Global.player.army[ind].attack(ind, false, Global.cache.inRangeUnits.get(2).indexOf);
                    preEndTurn();
                }
                break;
            }
            case DIGIT4: {
                if (3 < Global.cache.inRangeUnits.size()) {

                    Global.player.army[ind].attack(ind, false, Global.cache.inRangeUnits.get(3).indexOf);
                    preEndTurn();
                }
                break;
            }
            case DIGIT5: {
                if (4 < Global.cache.inRangeUnits.size()) {

                    Global.player.army[ind].attack(ind, false, Global.cache.inRangeUnits.get(4).indexOf);
                    preEndTurn();
                }
                break;
            }
            case ESCAPE: {
                Global.cache.setCursorSwitch(0);
                Global.cursor.loseSelectedImage();
                Global.cursor.setToDefaultCo();
                break;
            }

        }
    }

    /**
     * this is the
     *
     * @param event
     */
    public void eventThree(KeyCode event) {
        switch (event) {
            case W: {
                Global.cursor.moveUpUnit();

                break;
            }
            case A: {
                Global.cursor.moveLeftUnit();
                break;
            }
            case S: {
                Global.cursor.moveDownUnit();
                break;
            }
            case D: {
                Global.cursor.moveRightUnit();
                break;
            }
            case SPACE: {
                Global.cursor.putDownUnit(Global.player);
                Global.cursor.destructorOfMove();
                preEndTurn();
                break;
            }

            case ESCAPE: {
                Global.cache.setCursorSwitch(0);
                Global.cursor.destructorOfMove();
                /*Global.cursor.loseSelectedImage();
                Global.cursor.setToDefaultCo();*/
                break;
            }
        }
    }


    public void setDefaults() {
        Global.cache.setCursorSwitch(0);
        Global.cache.setHeroAction(-1);
    }

    public void update() {
        readLogMsg();
        fieldUpdateLeft();
        setBottomTextPlayer(isItAnEnemyAtTheMoment());

    }


    /**
     * no time, this will stay ugly
     */
    public void enemyDoSomething() {
        String tmp = "";

        for (String name : Global.cache.current.keySet()) {
            tmp = name;
        }
        int indexOfEnemyUnit = 0;

        for (int i = 0; i < Global.enemy.army.length; i++) {
            if (Global.enemy.army[i] != null) {
                if (tmp == Global.enemy.army[i].getName()) {
                    indexOfEnemyUnit = Global.enemy.army[i].indexOf;
                }
            }
        }
        Random rnd = new Random();
        int isItCasting = rnd.nextInt(3);
        if (isItCasting == 0 && Global.enemy.getLastAttackedRound() < Global.cache.getRound()) {
            int anotherRand = rnd.nextInt(Global.enemy.spells.length + 1);
            if (anotherRand == 0) {
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i] != null) {
                        Global.enemy.basicAttack.castSpell(true, Global.player.army[i].x, Global.player.army[i].y);
                        Global.enemy.setLastAttackedRound(Global.cache.getRound());
                        break;
                    }
                }
            } else {
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i] != null) {
                        if (Global.enemy.useSpell(Global.enemy.spells[anotherRand - 1].getManaCost())) {

                            if (!Global.enemy.spells[anotherRand - 1].isDmg()) {
                                if (Global.enemy.spells[anotherRand - 1].castSpell(true, Global.enemy.army[indexOfEnemyUnit].x, Global.enemy.army[indexOfEnemyUnit].y)) {
                                    Global.enemy.setLastAttackedRound(Global.cache.getRound());
                                    Global.enemy.useSpellMana(Global.enemy.spells[anotherRand - 1].getManaCost());
                                    break;
                                }
                            } else {

                                if (Global.enemy.spells[anotherRand - 1].castSpell(true, Global.player.army[i].x, Global.player.army[i].y)) {
                                    Global.enemy.setLastAttackedRound(Global.cache.getRound());
                                    Global.enemy.useSpellMana(Global.enemy.spells[anotherRand - 1].getManaCost());
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }else{

            System.out.println("spells done, moving to elsewhere");
            //spell done

            HashMap<Integer, Double> whoWhere = new HashMap<>(); //indexOf, and their distance

            //todo cries for nullpointer but nullcheck is above
            Global.enemy.army[indexOfEnemyUnit].getTargetables(false);

            if (!Global.cache.inRangeUnits.isEmpty()) {
                Global.enemy.army[indexOfEnemyUnit].attack(Global.cache.inRangeUnits.getFirst().indexOf, true, indexOfEnemyUnit);
            } else {
                //todo implement move to closer

                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i] != null) {

                        double distance = calculateDistanceForEnemy(indexOfEnemyUnit, i);
                        whoWhere.put(i, distance);

                    }
                }

                double tmpValue = 100;
                int tmpIndex = -1;

                for (Integer index : whoWhere.keySet()) {
                    if (whoWhere.get(index) < tmpValue) {
                        tmpIndex = index;
                        tmpValue = whoWhere.get(index);
                    }
                }

                if (!(tmpIndex < 0)) {

                    Global.cache.stepTracker.clear();
                    double distanceRam = calculateDistanceForEnemy(indexOfEnemyUnit, tmpIndex);


                    Global.cursor.initMove(indexOfEnemyUnit);

                    boolean left=false;
                    boolean right=false;
                    boolean up=false;
                    boolean down=false;

                    do {
                        left=false;
                        right=false;
                        up=false;
                        down=false;

                        left=moveLeftEnemy(indexOfEnemyUnit,tmpIndex);
                        down=moveDownEnemy(indexOfEnemyUnit,tmpIndex);
                        up=moveUpEnemy(indexOfEnemyUnit,tmpIndex);
                        right=moveRightEnemy(indexOfEnemyUnit,tmpIndex);


                    } while (!(Global.cursor.isItAnOldCoordinated(Global.enemy.army[indexOfEnemyUnit].x, Global.enemy.army[indexOfEnemyUnit].y)) && (left || right || up || down));


                }


            }
        }
    }

    public void initCoordinates(int indexOfEnemy) {
        System.out.println("woooork you fuck, x"+Global.enemy.army[indexOfEnemy].x+" and y"+Global.enemy.army[indexOfEnemy].y);
        Global.enemy.army[indexOfEnemy].picShow.setX(Global.cursor.getOffsetX() + (Global.enemy.army[indexOfEnemy].x * Global.cursor.getDistanceXY()));
        Global.enemy.army[indexOfEnemy].picShow.setY(Global.cursor.getOffsetY() + (Global.enemy.army[indexOfEnemy].y * Global.cursor.getDistanceXY()));
    }

    public boolean moveDownEnemy(int indexOfEnemy, int playerIndex) {
        double distanceRam = calculateDistanceForEnemy(indexOfEnemy, playerIndex);
        MyCoordinates coordinates = new MyCoordinates(Global.enemy.army[indexOfEnemy].x, Global.enemy.army[indexOfEnemy].y + 1);
        double distanceNow = calculateDistanceForEnemy(indexOfEnemy, playerIndex, 0,1);

        if (distanceNow < distanceRam) {


            FieldController controller = new FieldController();

            if (Global.cache.stepTracker.size() < Global.cache.getMaxStepTrack() + 1) {

                if (controller.isItaValidPlace(coordinates.x, coordinates.y) && !Global.cursor.isItAnOldCoordinated(coordinates.x, coordinates.y)) {
                    Global.enemy.army[indexOfEnemy].y++;

                    Global.cursor.addToStepTrack();
                    initCoordinates(indexOfEnemy);
                    return true;
                } else {
                    return false;
                }
                //think im done
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean moveUpEnemy(int indexOfEnemy, int playerIndex) {
        double distanceRam = calculateDistanceForEnemy(indexOfEnemy, playerIndex);
        MyCoordinates coordinates = new MyCoordinates(Global.enemy.army[indexOfEnemy].x, Global.enemy.army[indexOfEnemy].y-1);
        double distanceNow = calculateDistanceForEnemy(indexOfEnemy, playerIndex, 0, -1);

        if (distanceNow < distanceRam) {


            FieldController controller = new FieldController();

            if (Global.cache.stepTracker.size() < Global.cache.getMaxStepTrack() + 1) {

                if (controller.isItaValidPlace(coordinates.x, coordinates.y) && !Global.cursor.isItAnOldCoordinated(coordinates.x, coordinates.y)) {
                    Global.enemy.army[indexOfEnemy].y--;

                    Global.cursor.addToStepTrack();
                    initCoordinates(indexOfEnemy);
                    return true;
                } else {
                    return false;
                }
                //think im done
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean moveLeftEnemy(int indexOfEnemy, int playerIndex) {
        double distanceRam = calculateDistanceForEnemy(indexOfEnemy, playerIndex);
        MyCoordinates coordinates = new MyCoordinates(Global.enemy.army[indexOfEnemy].x-1, Global.enemy.army[indexOfEnemy].y);
        double distanceNow = calculateDistanceForEnemy(indexOfEnemy, playerIndex, -1,0);

        if (distanceNow < distanceRam) {


            FieldController controller = new FieldController();

            if (Global.cache.stepTracker.size() < Global.cache.getMaxStepTrack() + 1) {

                if (controller.isItaValidPlace(coordinates.x, coordinates.y) && !Global.cursor.isItAnOldCoordinated(coordinates.x, coordinates.y)) {
                    Global.enemy.army[indexOfEnemy].x--;

                    Global.cursor.addToStepTrack();
                    initCoordinates(indexOfEnemy);
                    return true;
                } else {
                    return false;
                }
                //think im done
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public boolean moveRightEnemy(int indexOfEnemy, int playerIndex) {
        double distanceRam = calculateDistanceForEnemy(indexOfEnemy, playerIndex);
        MyCoordinates coordinates = new MyCoordinates(Global.enemy.army[indexOfEnemy].x+1, Global.enemy.army[indexOfEnemy].y);
        double distanceNow = calculateDistanceForEnemy(indexOfEnemy, playerIndex, 1, 0);

        if (distanceNow < distanceRam) {


            FieldController controller = new FieldController();

            if (Global.cache.stepTracker.size() < Global.cache.getMaxStepTrack() + 1) {

                if (controller.isItaValidPlace(coordinates.x, coordinates.y) && !Global.cursor.isItAnOldCoordinated(coordinates.x, coordinates.y)) {
                    Global.enemy.army[indexOfEnemy].x++;

                    Global.cursor.addToStepTrack();
                    initCoordinates(indexOfEnemy);
                    return true;
                } else {
                    return false;
                }
                //think im done
            } else {
                return false;
            }

        } else {
            return false;
        }

    }


    public double calculateDistanceForEnemy(int indexOfEnemy, int indexOfPLayer, int xPlus, int yPlus){
        double a=Global.enemy.army[indexOfEnemy].x+xPlus-Global.player.army[indexOfPLayer].x;
        double b=Global.enemy.army[indexOfEnemy].y+yPlus-Global.player.army[indexOfPLayer].y;

        double aPow=Math.pow(a,2);
        double bPow=Math.pow(b,2);

        double c=Math.sqrt(aPow+bPow);

        return c;
    }

    public double calculateDistanceForEnemy(int indexOfEnemy, int indexOfPLayer){
        double a=Global.enemy.army[indexOfEnemy].x-Global.player.army[indexOfPLayer].x;
        double b=Global.enemy.army[indexOfEnemy].y-Global.player.army[indexOfPLayer].y;

        double aPow=Math.pow(a,2);
        double bPow=Math.pow(b,2);

        double c=Math.sqrt(aPow+bPow);

        return c;

    }


    public void endTurn(){

        boolean isEnemy=isItAnEnemyAtTheMoment();


        if (isEnemy){
            Global.cursor.cursorHide();
            //todo  implement auto attack of enemy etc
            enemyDoSomething();
        }else {
            for (String name : Global.cache.current.keySet()) {
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i]!=null){
                        if (Global.player.army[i].getName()==name) {
                            System.out.println(name);
                            Global.cursor.setDefaultX(Global.player.army[i].x);
                            Global.cursor.setX(Global.player.army[i].x);
                            Global.cursor.setDefaultY(Global.player.army[i].y);
                            Global.cursor.setY(Global.player.army[i].y);
                            Global.cursor.initCursorCoordinates();
                        }
                    }
                }
            }
            Global.cursor.cursorShow();
            //todo implement playeroptions
            setBottomTextPlayer(false);
        }

       // if ()

    }

    public boolean havePlayerDied(){
        boolean well=true;
        for (int i = 0; i <Global.player.army.length; i++) {
            if (Global.player.army[i]!=null){
                well=false;
                break;
            }
        }
        return well;
    }

    public boolean haveEnemyDied(){
        boolean well=true;
        for (int i = 0; i <Global.enemy.army.length; i++) {
            if (Global.enemy.army[i]!=null){
                well=false;
                break;
            }
        }
        return well;
    }

    public void postEndTurn(){


        for (int i = 0; i < Global.player.army.length ; i++) {
            if (Global.player.army[i]!=null){
                if (Global.player.army[i].getTotalHealth()==0){
                    Global.root.getChildren().remove(Global.player.army[i].picShow);
                    Global.player.army[i]=null;

                }
            }
        }
        for (int i = 0; i < Global.enemy.army.length ; i++) {
            if (Global.enemy.army[i]!=null){
                if (Global.enemy.army[i].getTotalHealth()==0){
                    Global.root.getChildren().remove(Global.enemy.army[i].picShow);
                    Global.enemy.army[i]=null;
                }
            }
        }


        //todo gotta check if it works
        for (int i = 0; i < Global.cache.nextUnitOrder.size(); i++) {
            if (Global.cache.nextUnitOrder.get(i)==null){
                Global.root.getChildren().remove(Global.cache.nextUnitOrder.get(i).topPicShow);
                Global.cache.nextUnitOrder.remove(i);
                i--;
            }
        }





    }
    public void readLogMsg(){
        if (Global.errMsgBuffer==null){
            Global.rightText.setText("");
        }else{
            StringBuilder concatMe=new StringBuilder();
            for (String me: Global.errMsgBuffer) {
                concatMe.append(me+"\n");
            }
            Global.rightText.setText(concatMe.toString());
            Global.errMsgBuffer=null;
        }
    }
}
