package copyme.hero;

import copyme.controls.FieldController;
import copyme.controls.MyLoader;
import copyme.unitclass.*;
import javafx.scene.image.ImageView;

import java.util.*;

/**
 * this is  the hero class for you and the npc
 */
public class Hero {
    private String name;
    private int gold;
    private int atk;
    private int def;
    private int spellpowa;
    private int intellect;
    private int moral;
    private int critchance;
    private int mana;
    private int lastAttackedRound;
    private boolean isEnemy;
    private LinkedList<Integer> statCost;
    private HashMap<String, Integer> buyArmy;
    private HashMap<String, Integer> buySpells; //well, the key is the name of the spell, we could call it the id cos of no duplicate
    public BattleUnit[] army;
    public ImageView[] armyIcons;
    public Spell[] spells;
    public Spell basicAttack;
    public ImageView[] spellsIcons;


    public Hero(int gold, String namaewa,boolean isEnemy) {
        this.name=namaewa;
        this.mana=0;
        this.gold = gold;
        this.atk = 1;
        this.def = 1;
        this.spellpowa = 1;
        this.intellect = 1;
        this.moral = 1;
        this.critchance = 1;
        this.isEnemy=isEnemy;
        this.statCost = new LinkedList<>();
        statCost.add(5);
        this.buySpells = new HashMap<>();
        this.buyArmy = new HashMap<>();
        this.lastAttackedRound=0;
        this.basicAttack=new Attack();
    }


    public boolean isEnemy() {
        return isEnemy;
    }

    public int getStatCost() {
        return statCost.getLast();
    }

    public String getName() {
        return name;
    }

    public int getMana() {
        return mana;
    }

    /**
     * boolean if i have enough mana
     * @param value
     * @return boolean
     */
    public boolean useSpell(int value) {
        FieldController controller=new FieldController();
        if (this.mana - value > -1) {
            //this.mana-=value;
            return true;
        } else {
            controller.addLogMsg("Not enough mana");
            return false;
        }

    }


    /**
     * actually using the mana
     * @param value
     */
    public void useSpellMana(int value){
        FieldController controller=new FieldController();
        if (this.mana - value > -1) {
            this.mana-=value;

        } else {
            controller.addLogMsg("Not enough mana");

        }
    }


    public int getLastAttackedRound() {
        return lastAttackedRound;
    }

    public void setLastAttackedRound(int lastAttackedRound) {
        this.lastAttackedRound = lastAttackedRound;
    }

    public void setDefaultMana(){
        this.mana=getIntellect()*10;
    }

    public void statCostIncrease() {
        int value = getStatCost();
        int temp = 0;

        if (value % 10 != 0) {
            temp = 1;
        }


        value *= 1.1;
        value += temp;
        statCost.add(value);

    }

    public void statCostDecrease() {
        statCost.removeLast();
    }

    public int getGold() {
        return gold;
    }

    /**
     * Checks if i have enough money
     * @param value
     * @return boolean
     */
    public boolean setGold(int value) {
        MyLoader loader = new MyLoader();

        if (!((this.gold - value) < 0)) {
            this.gold -= value;
            return true;
        } else {
            loader.addErrMsg("Not enough gold");
            return false;
        }
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        MyLoader loadMe = new MyLoader();
        if (this.atk + atk < 1) {
            loadMe.addErrMsg("The minimum of Attack stat must be 1\n");
        } else if (this.atk + atk > 10) {
            loadMe.addErrMsg("The maximum of Attack must not exceed 10\n");
        } else {

            if (atk > 0) {
                if (setGold(getStatCost())) {
                    this.atk += atk;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.atk += atk;
            }

        }

    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        MyLoader loadMe = new MyLoader();
        if (this.def + def < 1) {
            loadMe.addErrMsg("The minimum of Defense stat must be 1\n");
        } else if (this.def + def > 10) {
            loadMe.addErrMsg("The maximum of Defense must not exceed 10\n");
        } else {
            if (def > 0) {
                if (setGold(getStatCost())) {
                    this.def += def;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.def += def;
            }

        }
    }

    public int getSpellpowa() {
        return spellpowa;
    }

    public void setSpellpowa(int spellpowa) {
        MyLoader loadMe = new MyLoader();
        if (this.spellpowa + spellpowa < 1) {
            loadMe.addErrMsg("The minimum of Spellpowa stat must be 1\n");
        } else if (this.spellpowa + spellpowa > 10) {
            loadMe.addErrMsg("The maximum of Spellpowa must not exceed 10\n");
        } else {
            if (spellpowa > 0) {
                if (setGold(getStatCost())) {
                    this.spellpowa += spellpowa;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.spellpowa += spellpowa;
            }

        }
    }

    public int getIntellect() {
        return intellect;
    }

    public void setIntellect(int intellect) {
        MyLoader loadMe = new MyLoader();
        if (this.intellect + intellect < 1) {
            loadMe.addErrMsg("The minimum of Intellect stat must be 1\n");
        } else if (this.intellect + intellect > 10) {
            loadMe.addErrMsg("The maximum of Intellect must not exceed 10\n");
        } else {
            if (intellect > 0) {
                if (setGold(getStatCost())) {
                    this.intellect += intellect;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.intellect += intellect;
            }

        }
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        MyLoader loadMe = new MyLoader();
        if (this.moral + moral < 1) {
            loadMe.addErrMsg("The minimum of Moral stat must be 1\n");
        } else if (this.moral + moral > 10) {
            loadMe.addErrMsg("The maximum of Moral must not exceed 10\n");
        } else {
            if (moral > 0) {
                if (setGold(getStatCost())) {
                    this.moral += moral;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.moral += moral;
            }

        }
    }

    public int getCritchance() {
        return critchance;
    }

    public void setCritchance(int critchance) {
        MyLoader loadMe = new MyLoader();
        if (this.critchance + critchance < 1) {
            loadMe.addErrMsg("The minimum of Critical stat must be 1\n");
        } else if (this.critchance + critchance > 10) {
            loadMe.addErrMsg("The maximum of Critical must not exceed 10\n");
        } else {
            if (critchance > 0) {
                if (setGold(getStatCost())) {
                    this.critchance += critchance;
                    statCostIncrease();

                }

            } else {
                statCostDecrease();
                setGold(-getStatCost());
                this.critchance += critchance;
            }

        }
    }

    public HashMap<String, Integer> getBuySpells() {
        return buySpells;
    }


    public HashMap<String, Integer> getBuyArmy() {
        return buyArmy;
    }


    /**
     * based on unit and buy, but you can only buy a spell once
     * @param spellBuy
     * @param value
     */
    public void setBuySpells(Spell spellBuy, int value){
        MyLoader loader=new MyLoader();
        if (buySpells.containsKey(spellBuy.getName())){
            if (value>0){
                loader.addErrMsg("I already learned this spell");
            }else {
                setGold(-spellBuy.getLearnPrice());
                buySpells.remove(spellBuy.getName());
            }
        }else {
            if (value > 0){
                if (setGold(spellBuy.getLearnPrice())){
                    buySpells.put(spellBuy.getName(), 1);
                }
            }else {
                loader.addErrMsg("I dont have this spell to unlearn");
            }

        }
    }

    /**
     * this initializes the bought spells to an array
     */
    public void initSpells(){
        spells=new Spell[buySpells.size()];
        spellsIcons=new ImageView[buySpells.size()];

        String names[]=buySpells.keySet().toArray(new String[0]);
        for (int i = 0; i < spells.length; i++) {
            switch (names[i]){
                case "Armor":{
                    spells[i]=new Armor();
                    spells[i].setIndexOf(i);
                    spellsIcons[i]=new ImageView(spells[i].getPicResource());
                    break;
                }
                case "Destruction":{
                    spells[i]=new Destruction();
                    spells[i].setIndexOf(i);
                    spellsIcons[i]=new ImageView(spells[i].getPicResource());
                    break;
                }
                case "Salvation":{
                    spells[i]=new Salvation();
                    spells[i].setIndexOf(i);
                    spellsIcons[i]=new ImageView(spells[i].getPicResource());
                    break;
                }
                case "Lightning":{
                    spells[i]=new Lightning();
                    spells[i].setIndexOf(i);
                    spellsIcons[i]=new ImageView(spells[i].getPicResource());
                    break;
                }
                case "Inferno":{
                    spells[i]=new Inferno();
                    spells[i].setIndexOf(i);
                    spellsIcons[i]=new ImageView(spells[i].getPicResource());
                    break;
                }
            }
        }
    }

    /**
     * as long as you have money to buy unit, it lets you
     * @param unitBuy
     * @param value
     * if you dont, it adds a new msg to loaders Myloader
     */
    public void setBuyArmy(BattleUnit unitBuy, int value){
        MyLoader loader=new MyLoader();

        if (buyArmy.containsKey(unitBuy.getName())){
            if (value>0){
                if (setGold(unitBuy.getPrice())){
                    buyArmy.put(unitBuy.getName(),buyArmy.get(unitBuy.getName())+1);
                }
            }else{
                setGold(-unitBuy.getPrice());
                buyArmy.put(unitBuy.getName(),buyArmy.get(unitBuy.getName())-1);
                if (buyArmy.get(unitBuy.getName())==0){
                    buyArmy.remove(unitBuy.getName());
                }
            }
        }else {
            if(value>0){
                if (setGold(unitBuy.getPrice())){
                    buyArmy.put(unitBuy.getName(),1);
                }
            }else {
                loader.addErrMsg("I don't even have this unit to sell");
            }
        }
    }

    public void initMana(){
        this.mana=getIntellect()*10;
    }


    /**
     * this initializes the units into an array
     */
    public void initArmy(){
        army=new BattleUnit[buyArmy.size()];
        armyIcons=new ImageView[buyArmy.size()];
        String[] names=buyArmy.keySet().toArray(new String[0]);
        for (int i = 0; i < army.length ; i++) {
            switch (names[i]){
                case "Peasant":{
                    army[i]=new Peasant();
                    army[i].indexOf=i;
                    army[i].setMaxArmyNumber(buyArmy.get(names[i]));
                    army[i].setStartUnitHp();
                    army[i].setFinalInit(this);
                    armyIcons[i]=new ImageView(army[i].getDefaultPic());
                    break;
                }
                case "Archer":{
                    army[i]=new Archer();
                    army[i].indexOf=i;
                    army[i].setMaxArmyNumber(buyArmy.get(names[i]));
                    army[i].setStartUnitHp();
                    army[i].setFinalInit(this);
                    armyIcons[i]=new ImageView(army[i].getDefaultPic());
                    break;
                }
                case "Griff":{
                    army[i]=new Griff();
                    army[i].indexOf=i;
                    army[i].setMaxArmyNumber(buyArmy.get(names[i]));
                    army[i].setStartUnitHp();
                    army[i].setFinalInit(this);
                    armyIcons[i]=new ImageView(army[i].getDefaultPic());
                    break;
                }
                case "Berserker":{
                    army[i]=new Berserker();
                    army[i].indexOf=i;
                    army[i].setMaxArmyNumber(buyArmy.get(names[i]));
                    army[i].setStartUnitHp();
                    army[i].setFinalInit(this);
                    armyIcons[i]=new ImageView(army[i].getDefaultPic());
                    break;
                }
                case "Undead":{
                    army[i]=new Undead();
                    army[i].indexOf=i;
                    army[i].setMaxArmyNumber(buyArmy.get(names[i]));
                    army[i].setStartUnitHp();
                    army[i].setFinalInit(this);
                    armyIcons[i]=new ImageView(army[i].getDefaultPic());
                    break;
                }
            }
        }
    }




    public int getNumber(BattleUnit given) {
        BattleUnit tmp = given;
        return 0;
    }


}
