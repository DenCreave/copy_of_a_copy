package copyme.controls;

import copyme.globalvars.Global;
import copyme.hero.*;
import copyme.unitclass.*;
import javafx.scene.input.KeyCode;


/**
 * all the keyEvent is handled by this class
 */
public class MyEventHandler {
    public MyEventHandler(){};


    public void onCursor(KeyCode event){
        FieldController controller=new FieldController();
        boolean isEnemy=controller.isItAnEnemyAtTheMoment();

        if (!isEnemy){
            switch (Global.cache.getCursorSwitch()){
                case 0:{
                    controller.eventZero(event);
                    break;
                }
                case 1:{
                    controller.eventOne(event);
                    break;
                }
                case 2:{
                    controller.eventTwo(event);
                    break;
                }
                case 3:{
                    controller.eventThree(event);
                    break;
                }

            }
        }
    }



    public void battleHandler(KeyCode event){
        FieldController controller=new FieldController();

        switch (event){
            case ENTER:{
                controller.preEndTurn();
                controller.postEndTurn();
                if (controller.havePlayerDied()){
                    controller.addLogMsg("Congratulations! you lost...\nYou can safely close the game");
                    Global.mySwitch=99;
                }

                if (controller.haveEnemyDied()){
                    controller.addLogMsg("Congratulations! you won!\nYou can safely close the game");
                    Global.mySwitch=99;
                }
                controller.update();
                break;
            }
            default:{
                onCursor(event);
                controller.postEndTurn();
                if (controller.havePlayerDied()){
                    controller.addLogMsg("Congratulations! you lost...\nYou can safely close the game");
                    Global.mySwitch=99;
                }

                if (controller.haveEnemyDied()){
                    controller.addLogMsg("Congratulations! you won!\nYou can safely close the game");
                    Global.mySwitch=99;
                }
                controller.update();
                break;
            }
        }
    }

    public void unitSetToField(KeyCode event){
        MyLoader loader=new MyLoader();
        FieldController controller=new FieldController();

        switch (event){
            case W:{
                loader.moveUp();
                controller.readLogMsg();
                break;
            }
            case A:{
                loader.moveLeft();
                controller.readLogMsg();
                break;
            }
            case S:{
                loader.moveDown();
                controller.readLogMsg();
                break;
            }
            case D:{
                loader.moveRight();
                controller.readLogMsg();
                break;
            }
            case ENTER:{
                loader.postSetupField();
                controller.readLogMsg();
                break;
            }
            default:{
                loader.setupField(event);
                controller.readLogMsg();
                break;
            }
        }
    }

    public void enemyMakeHandler(KeyCode event){
        MyLoader loader=new MyLoader();


        switch (event){
            case ENTER:{
                loader.postMakeEnemy();
                loader.update();
                break;
            }
            case ESCAPE:{
                loader.addErrMsg("YOU CAN'T ESCAPE ME!!!");
                break;
            }
        }
    }


    public void spellHandler(KeyCode event){
        MyLoader loader=new MyLoader();

        switch (event) {
            case DIGIT1: {
                Lightning lightning = new Lightning();
                Global.player.setBuySpells(lightning, -1);
                loader.update();
                break;
            }
            case DIGIT3: {
                Lightning lightning = new Lightning();
                Global.player.setBuySpells(lightning, 1);
                loader.update();
                break;
            }
            case Q: {
                Inferno inferno = new Inferno();
                Global.player.setBuySpells(inferno, -1);
                loader.update();
                break;
            }
            case E: {
                Inferno inferno = new Inferno();
                Global.player.setBuySpells(inferno, 1);
                loader.update();
                break;
            }
            case A: {
                Salvation salvation = new Salvation();
                Global.player.setBuySpells(salvation, -1);
                loader.update();
                break;
            }
            case D: {
                Salvation salvation = new Salvation();
                Global.player.setBuySpells(salvation, 1);
                loader.update();
                break;
            }
            case U: {
                Armor armor = new Armor();
                Global.player.setBuySpells(armor, -1);
                loader.update();
                break;
            }
            case O: {
                Armor armor = new Armor();
                Global.player.setBuySpells(armor, 1);
                loader.update();
                break;
            }
            case J: {
                Destruction destruction = new Destruction();
                Global.player.setBuySpells(destruction, -1);
                loader.update();
                break;
            }
            case L: {
                Destruction destruction = new Destruction();
                Global.player.setBuySpells(destruction, 1);
                loader.update();
                break;
            }
            case ENTER: {
                loader.postSpellLoader();
                loader.update();
                break;
            }
        }
    }

    public void unitHandler(KeyCode event){
        MyLoader loader=new MyLoader();


        switch (event){
            case DIGIT1:{
                Peasant peasant=new Peasant();
                Global.player.setBuyArmy(peasant, -1);
                loader.update();
                break;
            }
            case DIGIT3:{
                Peasant peasant=new Peasant();
                Global.player.setBuyArmy(peasant, 1);
                loader.update();
                break;
            }
            case Q:{
                Archer archer=new Archer();
                Global.player.setBuyArmy(archer,-1);
                loader.update();
                break;
            }
            case E:{
                Archer archer=new Archer();
                Global.player.setBuyArmy(archer,1);
                loader.update();
                break;
            }
            case A:{
                Griff griff=new Griff();
                Global.player.setBuyArmy(griff,-1);
                loader.update();
                break;
            }
            case D:{
                Griff griff=new Griff();
                Global.player.setBuyArmy(griff,1);
                loader.update();
                break;
            }
            case U:{
                Berserker berserker=new Berserker();
                Global.player.setBuyArmy(berserker,-1);
                loader.update();
                break;
            }
            case O:{
                Berserker berserker=new Berserker();
                Global.player.setBuyArmy(berserker,1);
                loader.update();
                break;
            }
            case J:{
                Undead undead=new Undead();
                Global.player.setBuyArmy(undead,-1);
                loader.update();
                break;
            }
            case L:{
                Undead undead=new Undead();
                Global.player.setBuyArmy(undead,1);
                loader.update();
                break;
            }
            case ENTER:{
                loader.unitLoader();
                loader.update();
                break;
            }
        }

    }


    public void statHandler(KeyCode event){
        MyLoader loader=new MyLoader();


        switch (event){
            case DIGIT1:{
                Global.player.setAtk(-1);
                loader.update();
                break;
            }
            case DIGIT3:{
                Global.player.setAtk(1);
                loader.update();
                break;
            }
            case Q:{
                Global.player.setDef(-1);
                loader.update();
                break;
            }
            case E:{
                Global.player.setDef(1);
                loader.update();
                break;
            }
            case A:{
                Global.player.setSpellpowa(-1);
                loader.update();
                break;
            }
            case D:{
                Global.player.setSpellpowa(1);
                loader.update();
                break;
            }
            case DIGIT7:{
                Global.player.setIntellect(-1);
                loader.update();
                break;
            }
            case DIGIT9:{
                Global.player.setIntellect(1);
                loader.update();
                break;
            }
            case U:{
                Global.player.setMoral(-1);
                loader.update();
                break;
            }
            case O:{
                Global.player.setMoral(1);
                loader.update();
                break;
            }
            case J:{
                Global.player.setCritchance(-1);
                loader.update();
                break;
            }
            case L:{
                Global.player.setCritchance(1);
                loader.update();
                break;
            }
            case ENTER:{
                loader.preUnitLoader();
                loader.update();
                break;
            }
        }

    }

    public void difficultyHandler(KeyCode event){
        MyLoader loader=new MyLoader();

        switch (event){
            case F:{
                loader.difficultyLoader(1300);
                break;
            }
            case DIGIT5:{
                loader.difficultyLoader(1000);
                break;
            }
            case K:{
                loader.difficultyLoader(700);
                break;
            }
        }
    }

    /**
     * all the keyEvents will start here
     * @param event
     */
    public void handleMe(KeyCode event){
        switch (Global.mySwitch){
            case 1: {
                difficultyHandler(event);
                break;
            }
            case 2:{
                statHandler(event);
                break;
            }
            case 3:{
                unitHandler(event);
                break;
            }
            case 4:{
                spellHandler(event);
                break;
            }
            case 5:{
                enemyMakeHandler(event);
                break;
            }
            case 6: {
                unitSetToField(event);
                break;
            }
            case 7:{
                battleHandler(event);
                break;
            }
        }
    }

}
