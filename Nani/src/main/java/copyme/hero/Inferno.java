package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;

/**
 * the 3x3 area spell
 */
public class Inferno extends Spell {
    public Inferno() {
        super("Inferno", false, false, 20, 120, 9, "Burn friend and foe alike in a 3x3 area", "file:textures/fireball.png");
    }

    @Override
    public boolean castSpell(boolean isEnemyCasting, int x, int y) {
        boolean haveDamaged = false;

        FieldController controller = new FieldController();

        for (int xCheck = -1; xCheck < 2; xCheck++) {
            for (int yCheck = -1; yCheck < 2; yCheck++) {
                if (!(x + xCheck < 0) && x + xCheck < Global.cursor.getMaxX() && !(y + yCheck < 0) && y + yCheck < Global.cursor.getMaxY()) {

                    for (int i = 0; i < Global.enemy.army.length; i++) {
                        if (Global.enemy.army[i] != null) {

                            if (Global.enemy.army[i].x == x + xCheck && Global.enemy.army[i].y == y + yCheck) {
                                Global.cache.inRangeUnits.add(Global.enemy.army[i]);
                            }
                        }
                    }

                    for (int i = 0; i < Global.player.army.length; i++) {
                        if (Global.player.army[i] != null) {

                            if (Global.player.army[i].x == x + xCheck && Global.player.army[i].y == y + yCheck) {
                                Global.cache.inRangeUnits.add(Global.player.army[i]);
                            }
                        }
                    }
                }
            }
        }

        //todo kijavítani hogy saját egység is sebződjön

        for (int j = 0; j <Global.cache.inRangeUnits.size(); j++) {
            if (isEnemyCasting){
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i]!=null){
                        if (Global.player.army[i].x==Global.cache.inRangeUnits.get(j).x&&Global.player.army[i].y==Global.cache.inRangeUnits.get(j).y){
                            int amountHealedhp=Global.player.army[i].getTotalHealth();
                            Global.player.army[i].takeDmgFromHero(Global.enemy.getSpellpowa()*getValue());
                            amountHealedhp=Global.player.army[i].getTotalHealth()-amountHealedhp;
                            controller.addLogMsg("Inferno hit "+Global.player.army[i].getName()+" for "+amountHealedhp+".");
                            haveDamaged=true;
                            break;
                        }
                    }
                }
                for (int i = 0; i < Global.enemy.army.length; i++) {
                    if (Global.enemy.army[i]!=null){
                        if (Global.enemy.army[i].x==Global.cache.inRangeUnits.get(j).x&&Global.enemy.army[i].y==Global.cache.inRangeUnits.get(j).y){
                            int amountHealedhp=Global.enemy.army[i].getTotalHealth();
                            Global.enemy.army[i].takeDmgFromHero(Global.enemy.getSpellpowa()*getValue());
                            amountHealedhp=Global.enemy.army[i].getTotalHealth()-amountHealedhp;
                            controller.addLogMsg("Lightning struck "+Global.enemy.army[i].getName()+" for "+amountHealedhp+".");
                            haveDamaged=true;
                            break;
                        }
                    }
                }
            }else {
                for (int i = 0; i < Global.enemy.army.length; i++) {
                    if (Global.enemy.army[i]!=null){
                        if (Global.enemy.army[i].x==Global.cache.inRangeUnits.get(j).x&&Global.enemy.army[i].y==Global.cache.inRangeUnits.get(j).y){
                            int amountHealedhp=Global.enemy.army[i].getTotalHealth();
                            Global.enemy.army[i].takeDmgFromHero(Global.player.getSpellpowa()*getValue());
                            amountHealedhp=Global.enemy.army[i].getTotalHealth()-amountHealedhp;
                            controller.addLogMsg("Lightning struck "+Global.enemy.army[i].getName()+" for "+amountHealedhp+".");
                            haveDamaged=true;
                            break;
                        }
                    }
                }
                for (int i = 0; i < Global.player.army.length; i++) {
                    if (Global.player.army[i]!=null){
                        if (Global.player.army[i].x==Global.cache.inRangeUnits.get(j).x&&Global.player.army[i].y==Global.cache.inRangeUnits.get(j).y){
                            int amountHealedhp=Global.player.army[i].getTotalHealth();
                            Global.player.army[i].takeDmgFromHero(Global.player.getSpellpowa()*getValue());
                            amountHealedhp=Global.player.army[i].getTotalHealth()-amountHealedhp;
                            controller.addLogMsg("Inferno hit "+Global.player.army[i].getName()+" for "+amountHealedhp+".");
                            haveDamaged=true;
                            break;
                        }
                    }
                }
            }
        }

        return haveDamaged;
    }
}