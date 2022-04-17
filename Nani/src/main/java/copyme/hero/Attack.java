package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;

/**
 * this is the basic hero attack
 */
public class Attack  extends Spell {
    public Attack() {
        super("Attack", false, true, 10, 0, 0, "basic attack", "file:textures/pix.png");

    }

    @Override
    public boolean castSpell(boolean isEnemyCasting, int x, int y) {
        boolean haveDamaged = false;

        FieldController controller = new FieldController();
        if (isEnemyCasting) {
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.player.army[i] != null) {
                    if (Global.player.army[i].x == x && Global.player.army[i].y == y) {
                        int amountHealedhp = Global.player.army[i].getTotalHealth();
                        Global.player.army[i].takeDmgFromHero(Global.enemy.getAtk() * getValue());
                        amountHealedhp = Global.player.army[i].getTotalHealth() - amountHealedhp;
                        controller.addLogMsg("Edgelord attacked " + Global.player.army[i].getName() + " for " + amountHealedhp + ".");
                        haveDamaged = true;
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < Global.enemy.army.length; i++) {
                if (Global.enemy.army[i] != null) {
                    if (Global.enemy.army[i].x == x && Global.enemy.army[i].y == y) {
                        int amountHealedhp = Global.enemy.army[i].getTotalHealth();
                        Global.enemy.army[i].takeDmgFromHero(Global.player.getAtk() * getValue());
                        amountHealedhp = Global.enemy.army[i].getTotalHealth() - amountHealedhp;
                        controller.addLogMsg("Kawaii owo attacc " + Global.enemy.army[i].getName() + " for " + amountHealedhp + ".");
                        haveDamaged = true;
                        break;
                    }
                }
            }
        }

        return haveDamaged;
    }


}