package copyme.unitclass;

import copyme.controls.FieldController;
import copyme.globalvars.Global;
import copyme.hero.Hero;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * baseclass for all your unit
 */
public class BattleUnit {
    public int x,y;
    public int indexOf;
    private int totalHealth;
    private int maxArmyNumber;
    private int currentArmyNumber;
    private String name;
    private int price;
    private int minDmg;
    private int maxDmg;
    private int health;
    private int speed;
    private int initiative;
    private int finalInit;
    private int range;//this ranks order during turns;
    //for the formula, from hero stats:
    private int statAttack;
    private int statDef;
    private int statHaste;
    private int statCrit;
    //for the formula, from unit
    private int statHealth;
    private int statSpeed;
    private int statDmg;

    private boolean hasSpecialAbility;
    private boolean selected;
    private boolean isEnemy;
    private boolean canRetaliate;
    private boolean isRetaliateAble;

    /**
     * @buffs: so, basically there are 7 types of buffS {A1, A2, A3, D1, D2, D3, P}
     * the combat itself is divided into 6 phases
     * before dealing dmg - A1
     * dealing dmg  - A2
     * after dealing dmg  - A3
     *
     * before taking dmg  - D1
     * taking dmg  - D2
     * after taking dmg  - D3
     *
     * passive - P
     *
     * first string is the key, the name of the buff or special trait
     * the second tells which method its taking place in
     */
    //TODO implent buff list with switch
    public HashMap<String,Integer> buffs; //this one will need a description
    private Image defaultPic;
    public Image topPic;
    public ImageView topPicShow;
    public ImageView picShow;


    /**
     *
     * @param name
     * @param price
     * @param minDmg
     * @param maxDmg
     * @param health
     * @param speed
     * @param initiative
     * @param hasSpecialAbility
     * @param defaultPicPath
     */
    public BattleUnit(String name, int price, int minDmg, int maxDmg, int health, int speed, int initiative, boolean hasSpecialAbility, String defaultPicPath){
        this.x=-1;
        this.y=-1;
        this.isRetaliateAble=true;
        this.finalInit=0;
        this.totalHealth=0;
        this.maxArmyNumber=0;
        this.currentArmyNumber=0;
        this.name=name;
        this.price=price;
        this.minDmg=minDmg;
        this.maxDmg=maxDmg;
        this.health=health;
        this.speed=speed;
        this.initiative = initiative;
        this.range=1;
        this.statAttack=0;
        this.statCrit=0;
        this.statDef=0;
        this.statDmg=0;
        this.statHaste=0;
        this.statHealth=0;
        this.statSpeed=0;
        this.hasSpecialAbility=hasSpecialAbility;
        this.selected=false;
        this.isEnemy=false;
        this.canRetaliate=true;
        this.buffs=new HashMap<>();
        this.defaultPic=new Image(defaultPicPath);
        this.picShow=new ImageView(defaultPic);
        this.topPic=new Image(defaultPicPath);
        this.topPicShow=new ImageView(topPic);
    }

    /**
     * another contructor for initializing without using the special ability, if it doesnt have anything special
     * @param name
     * @param price
     * @param minDmg
     * @param maxDmg
     * @param health
     * @param speed
     * @param initiative
     * @param defaultPicPath
     */
    public BattleUnit(String name, int price, int minDmg, int maxDmg, int health, int speed, int initiative, String defaultPicPath){
        this.x=-1;
        this.y=-1;
        this.finalInit=0;
        this.totalHealth=0;
        this.maxArmyNumber=0;
        this.currentArmyNumber=0;
        this.name=name;
        this.price=price;
        this.minDmg=minDmg;
        this.maxDmg=maxDmg;
        this.health=health;
        this.speed=speed;
        this.initiative = initiative;
        this.range=1;
        this.statAttack=0;
        this.statCrit=0;
        this.statDef=0;
        this.statDmg=0;
        this.statHaste=0;
        this.statHealth=0;
        this.statSpeed=0;
        this.hasSpecialAbility=false;
        this.selected=false;
        this.isEnemy=false;
        this.canRetaliate=true;
        this.buffs=new HashMap<>();
        this.defaultPic=new Image(defaultPicPath);
        this.picShow=new ImageView(defaultPic);

        this.topPic=new Image(defaultPicPath);
        this.topPicShow=new ImageView(topPic);
    }

    public boolean isCanRetaliate() {
        return canRetaliate;
    }

    public void setCanRetaliate(boolean canRetaliate) {
        this.canRetaliate = canRetaliate;
    }

    public boolean isRetaliateAble() {
        return isRetaliateAble;
    }

    public void setRetaliateAble(boolean retaliateAble) {
        isRetaliateAble = retaliateAble;
    }

    public boolean getIsEnemy() {
        return isEnemy;
    }

    public void setIsEnemy(boolean enemy) {
        isEnemy = enemy;
    }

    public int getFinalInit() {
        return finalInit;
    }

    public void setFinalInit(Hero meineHero){
        this.finalInit=this.initiative+meineHero.getMoral();
    }

    //TODO CHECK if this is working correctly
    public double a2(boolean isEnemyAttacking,int indexedOF){



        boolean haveHealed=false;

        FieldController controller=new FieldController();
        /*if (isEnemyAttacking) {
            if (Global.enemy.army[indexedOF] != null) {
                int amountHealedhp = Global.enemy.army[i].getTotalHealth();
                Global.enemy.army[i].takeHealing(Global.enemy.getSpellpowa() * getValue());
                amountHealedhp = Global.enemy.army[i].getTotalHealth() - amountHealedhp;
                controller.addLogMsg("Healed " + Global.enemy.army[i].getName() + " for " + amountHealedhp + ".");
                haveHealed = true;

            }

        }else {

                if (Global.enemy.army[indexedOF]!=null)

                        int amountHealedhp=Global.player.army[i].getTotalHealth();
                        Global.player.army[i].takeHealing(Global.player.getSpellpowa()*getValue());
                        amountHealedhp=Global.player.army[i].getTotalHealth()-amountHealedhp;
                        controller.addLogMsg("Healed "+Global.player.army[i].getName()+" for "+amountHealedhp+".");
                        haveHealed=true;


                }
            }
        }
        */
        /*if(isEnemyAttacking)
        Random rnd=new Random();
        int rndnum=rnd.nextInt(minDmg,maxDmg);
        double number= (rndnum*myHero.getNumber(this))*(1+ ((myHero.getAtk()/10) + (statAttack/10)));
        rndnum=rnd.nextInt(21);
        if ((myHero.getCritchance()+statCrit)>=rndnum){

            return number*2;
        }else {
            return number;
        }*/
        return 0;
    }
    public void a3(boolean isEnemyAttacking,int indexedOF){

    }


    public void setStatAttack(int statAttack) {
        this.statAttack = statAttack;
    }

    //TODO IMPLEMENT LATER

    /**
     * calculates the dmg to be taken
     * @param indexPlayer
     * @param isEnemyAttacking
     * @param indexEnemy
     * @return double
     * returns the value that the other unit will then mitigate
     */
    public double a1(int indexPlayer, boolean isEnemyAttacking, int indexEnemy){
        FieldController controller=new FieldController();
        if (isEnemyAttacking){
            Random rnd=new Random();
            int rndnum=0;
            if (minDmg==maxDmg){
                rndnum=rnd.nextInt(minDmg,maxDmg+1);

            }else {
                rndnum=rnd.nextInt(minDmg,maxDmg);

            }
            double number= (rndnum*Global.enemy.army[indexEnemy].getCurrentArmyNumber())*(1+ ((Global.enemy.getAtk()/10) + (statAttack/10)));
            rndnum=rnd.nextInt(21);
            if ((Global.enemy.getCritchance()+statCrit)>=rndnum){
                controller.addLogMsg("!!!CRITICAL!!!");
                return number*2;

            }else {
                System.out.println(number+" this is the a1 number");
                return number;
            }
        }else{


            Random rnd=new Random();
            int rndnum=0;
            if (minDmg==maxDmg){
                rndnum=rnd.nextInt(minDmg,maxDmg+1);

            }else {
                rndnum=rnd.nextInt(minDmg,maxDmg);

            }
            double number= (rndnum*Global.player.army[indexPlayer].getCurrentArmyNumber())*(1+ ((Global.player.getAtk()/10) + (statAttack/10)));
            rndnum=rnd.nextInt(21);
            if ((Global.player.getCritchance()+statCrit)>=rndnum){
                controller.addLogMsg("!!!CRITICAL!!!");
                return number*2;

            }else {
                System.out.println(number+" this is the a1 number");
                return number;
            }

        }
    }

    /**
     * depending on who the attacker is, chooses which units defend method to call
     * @param indexPlayer
     * @param isEnemyAttacking
     * @param indexEnemy
     */
    public void attack(int indexPlayer, boolean isEnemyAttacking, int indexEnemy){







        if (isEnemyAttacking){
            Global.player.army[indexPlayer].defend(indexPlayer,isEnemyAttacking,indexEnemy,a1(indexPlayer,isEnemyAttacking, indexEnemy));
        }else {
            Global.enemy.army[indexEnemy].defend(indexPlayer,isEnemyAttacking,indexEnemy,a1(indexPlayer,isEnemyAttacking, indexEnemy));
        }


    }


    /**
     * calculates the dmg to take, after the mitigation,
     * @param indexPlayer
     * @param isEnemyAttacking
     * @param indexEnemy
     * @param dmgToMitigate
     */
    public void defend(int indexPlayer, boolean isEnemyAttacking, int indexEnemy, double dmgToMitigate) {

            takeDamage(d1(indexPlayer,isEnemyAttacking,indexEnemy,dmgToMitigate));



            if (distanceCheckForRetaliate(indexPlayer,indexEnemy)){
                if (isEnemyAttacking){
                    if (Global.player.army[indexPlayer].canRetaliate){
                        retaliate(indexPlayer,isEnemyAttacking,indexEnemy);

                    }

                }else {
                    if (Global.enemy.army[indexEnemy].canRetaliate){
                        retaliate(indexPlayer,isEnemyAttacking,indexEnemy);

                    }

                }

            }


       /* }else{
            //i might not even need this else part
            takeDamage(d1(indexPlayer,isEnemyAttacking,indexEnemy,dmgToMitigate));
            retaliate(indexPlayer,isEnemyAttacking,indexEnemy);


        }*/
    }


        /*a1(myHero, enemy);
        enemy.defend(hostileHero, this, a2(myHero,enemy));

        a3(myHero, enemy);*/


    /**
     * this is to ensure that a ranged attack won't be retaliated
     * @param indexPlayer
     * @param indexEnemy
     * @return boolean
     */
    private boolean distanceCheckForRetaliate(int indexPlayer, int indexEnemy){
        double aPow=Math.pow((Global.player.army[indexPlayer].x-Global.enemy.army[indexEnemy].x),2);
        double bPow=Math.pow((Global.player.army[indexPlayer].y-Global.enemy.army[indexEnemy].y),2);
        double c=Math.sqrt(aPow+bPow);

        return c<2;
    }

    /**
     * the retaliation, based on who was the attack and indexing
     * @param indexPlayer
     * @param isEnemyAttacking
     * @param indexEnemy
     */
    public void retaliate(int indexPlayer, boolean isEnemyAttacking, int indexEnemy){
        int value=0;

        if (isEnemyAttacking){



            double tmp= Global.player.army[indexPlayer].a1(indexPlayer,!isEnemyAttacking,indexEnemy);
            tmp/=2;
            value=Global.enemy.army[indexEnemy].d1(indexPlayer,!isEnemyAttacking,indexEnemy,tmp);
            Global.enemy.army[indexEnemy].takeDamage(value);
            if (Global.player.army[indexPlayer].getName()!="Griff"){
                Global.player.army[indexPlayer].canRetaliate=false;

            }
        }else {
            double tmp= Global.enemy.army[indexEnemy].a1(indexPlayer,!isEnemyAttacking,indexEnemy);
            tmp/=2;
            value=Global.player.army[indexPlayer].d1(indexPlayer,!isEnemyAttacking,indexEnemy,tmp);
            Global.player.army[indexPlayer].takeDamage(value);
            if(Global.enemy.army[indexEnemy].getName()!="Griff"){
                Global.enemy.army[indexEnemy].canRetaliate=false;

            }
        }
    }


    /**
     * calculates the mitigation and the returns it
     * @param indexPlayer
     * @param isEnemyAttacking
     * @param indexEnemy
     * @param dmgToMitigate
     * @return  int
     */
    public int d1(int indexPlayer, boolean isEnemyAttacking, int indexEnemy, double dmgToMitigate){
        FieldController controller=new FieldController();
        int retnum=0;
        System.out.println(dmgToMitigate+"dmg to mitigate is ");

        //System.out.println(rate+" this is enemy rate");
        if (isEnemyAttacking){
            double rate=Global.player.getDef()+Global.player.army[indexPlayer].getStatDef();
            rate=20-rate;
            rate/=20;
            retnum=(int)Math.round (dmgToMitigate*rate);
            if (Global.enemy.army[indexEnemy].getName()=="Undead"){
                int start=Global.enemy.army[indexEnemy].getTotalHealth();

                Global.enemy.army[indexEnemy].takeHealing(retnum/2);
                int end=Global.enemy.army[indexEnemy].getTotalHealth()-start;
                controller.addLogMsg("Undead healed "+end);
            }
        }else {
            //retnum=(int) Math.round(dmgToMitigate*((20 - (Global.enemy.getDef()+Global.enemy.army[indexEnemy].getStatDef()))/20));
            double rate=Global.enemy.getDef()+Global.enemy.army[indexEnemy].getStatDef();
            rate=20-rate;
            rate/=20;
            retnum=(int)Math.round (dmgToMitigate*rate);
            if (Global.player.army[indexPlayer].getName()=="Undead"){
               // Global.player.army[indexPlayer].takeHealing(retnum/2);
                int start=Global.player.army[indexPlayer].getTotalHealth();

                Global.player.army[indexPlayer].takeHealing(retnum/2);
                int end=Global.player.army[indexPlayer].getTotalHealth()-start;
                controller.addLogMsg("Undead healed "+end);
            }
        }
        System.out.println("retnum is "+retnum);
        return retnum;
    }

    public void d2(Hero myHero, double damage){
        FieldController controller=new FieldController();

        double tmp = damage*(1 -(myHero.getDef()/20)-(getStatDef()/20));
        int taken= (int)Math.round(tmp);
        takeDamage(taken);
        d3(myHero,damage);


    }

    public double d3(Hero myHero, double damage) {
        FieldController controller = new FieldController();
        if (canRetaliate) {
            canRetaliate=false;
            Random rnd = new Random();
            int rndnum = rnd.nextInt(minDmg, maxDmg);
            double number = (rndnum * myHero.getNumber(this)) * (1 + ((myHero.getAtk() / 10) + (statAttack / 10)));
            rndnum = rnd.nextInt(21);
            if ((myHero.getCritchance() + statCrit) >= rndnum) {
                controller.addLogMsg("crit retaliate");
                return number;

            } else {
                return number / 2;
            }

        }
        return 0;
    }
    //TODO IMPLEMENT LATER


    protected void setRange(int range) {
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getMinDmg() {
        return minDmg;
    }

    public int getMaxDmg() {
        return maxDmg;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getInitiative() {

        return initiative;
    }

    public int getRange() {
        return range;
    }

    public int getStatAttack() {
        return statAttack;
    }

    public int getStatDef() {
        return statDef;
    }

    public int getStatHaste() {
        return statHaste;
    }

    public int getStatCrit() {
        return statCrit;
    }

    public int getStatHealth() {
        return statHealth;
    }

    public int getStatSpeed() {
        return statSpeed;
    }

    public int getStatDmg() {
        return statDmg;
    }

    public boolean isHasSpecialAbility() {
        return hasSpecialAbility;
    }

    public boolean isSelected() {
        return selected;
    }


    public Image getDefaultPic() {
        return defaultPic;
    }

    public int getTotalHealth() {
        return totalHealth;
    }

    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;
    }

    public int getMaxArmyNumber() {
        return maxArmyNumber;
    }

    public void setMaxArmyNumber(int maxArmyNumber) {
        this.maxArmyNumber = maxArmyNumber;
        setCurrentArmyNumber(this.maxArmyNumber);
    }

    public int getCurrentArmyNumber() {
        return currentArmyNumber;
    }

    public void setCurrentArmyNumber(int currentArmyNumber) {
        this.currentArmyNumber = currentArmyNumber;
    }

    public void updateUnitCount(){
        this.currentArmyNumber=totalHealth/health;
        if (!(totalHealth%health==0)){
            this.currentArmyNumber++;
        }
    }

    public void setStartUnitHp(){
        this.totalHealth=health*maxArmyNumber;
    }

    public void takeDmgFromHero(int value){
        if (this.totalHealth-value<1){
            this.totalHealth=0;

        }else{
            this.totalHealth-=value;

        }
        updateUnitCount();

    }

    public void takeDamage(int value){
        FieldController controller=new FieldController();
        int tmp=getTotalHealth();
        if (this.totalHealth-value<1){
            this.totalHealth=0;
            this.canRetaliate=false;
        }else{
            this.totalHealth-=value;

        }
        controller.addLogMsg(getName()+" took "+(tmp-this.getTotalHealth())+"dmg");
        updateUnitCount();
    }

    public void takeHealing(int value){
        if (this.totalHealth+value<health*maxArmyNumber){
            totalHealth+=value;
        }else {
            totalHealth=health*maxArmyNumber;
        }
        updateUnitCount();
    }



    public void getTargetables (boolean lookingForEnemy){
        for (int xCheck = (range*-1); xCheck < range+1; xCheck++) {
            for (int yCheck = (range*-1); yCheck < range+1; yCheck++) {
                System.out.println((x+xCheck)+" ez x : ez y "+(y+yCheck));

                if (!(this.x+xCheck<0) && this.x+xCheck< Global.cursor.getMaxX() && !(this.y+yCheck<0) && this.y+yCheck<Global.cursor.getMaxY() ){
                    if (lookingForEnemy){
                        for (int i = 0; i < Global.enemy.army.length; i++) {
                            if (Global.enemy.army[i]!= null){

                                if (Global.enemy.army[i].x== this.x+xCheck && Global.enemy.army[i].y==this.y+yCheck){
                                    System.out.println(x+ "x and y" +y);
                                    Global.cache.inRangeUnits.add(Global.enemy.army[i]);
                                }
                            }
                        }
                    }else {
                        for (int i = 0; i < Global.player.army.length; i++) {
                            if (Global.player.army[i]!= null){

                                if (Global.player.army[i].x== this.x+xCheck && Global.player.army[i].y==this.y+yCheck){
                                    Global.cache.inRangeUnits.add(Global.player.army[i]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void buffHandler(String nameOfBuff, int value){
        switch (nameOfBuff){
            case "Destruction":{
                this.statCrit+=value;
                break;
            }
            case "Armor":{
                this.statDef+=value;
                break;
            }
        }
    }

    public boolean buffUp(String nameOfBuff, int value){
        FieldController controller=new FieldController();

        if (buffs.containsKey(nameOfBuff)){
            controller.addLogMsg("got dis baff already bro");
            return false;
        }else {
            buffs.put(nameOfBuff, value);
            buffHandler(nameOfBuff,value);
            return true;
        }
    }


}
