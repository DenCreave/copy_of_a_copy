package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;

/**
 * 50% crit chance buff
 */
public class Destruction extends Spell {
    public Destruction() {
        super("Destruction", true,false, 10, 50, 5, "Increases crit chance of selected unit by 50%", "file:textures/destruction.png");
    }

    @Override
    public boolean castSpell(boolean isEnemyCasting, int x, int y) {
        boolean haveDamaged = false;

        FieldController controller = new FieldController();
        if (isEnemyCasting) {
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.enemy.army[i] != null) {
                    if (Global.enemy.army[i].x == x && Global.enemy.army[i].y == y) {

                        Global.enemy.army[i].buffUp(this.getName(), this.getValue());

                        controller.addLogMsg("Enemy buffed  " + Global.enemy.army[i].getName() + " with " + getName() + ".");
                        haveDamaged = true;
                        break;
                    }
                }
            }
        } else {
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.player.army[i] != null) {
                    if (Global.player.army[i].x == x && Global.player.army[i].y == y) {

                        Global.player.army[i].buffUp(getName(), getValue());

                        controller.addLogMsg("You buffed " + Global.player.army[i].getName() + " with " + getName() + ".");
                        haveDamaged = true;
                        break;
                    }
                }
            }
        }

        return haveDamaged;
    }
}