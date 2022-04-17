package copyme.unitclass;

import copyme.globalvars.Global;
import copyme.hero.Hero;

/**
 * the undead units class
 */
public class Undead extends BattleUnit implements SpecialAbiDesc {
    public Undead(){
        super("Undead", 8, 5, 8, 13, 5, 10, true, "file:textures/undead_army.png");
    }

    @Override
    public String special() {
        return "Heal for 50% of the dmg";
    }




}
