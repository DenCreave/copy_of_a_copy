package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;

/**
 * gives 30% dmg redu buff to a unit
 */
public class Armor extends Spell {
    public Armor() {
        super("Armor", true,false, 6, 50, 5, "Increases a selected units defense by 6", "file:textures/armor.png");
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
