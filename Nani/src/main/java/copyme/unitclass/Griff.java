package copyme.unitclass;

import copyme.hero.Hero;

/**
 * Griff units class
 */
public class Griff extends BattleUnit implements SpecialAbiDesc {
    public Griff(){
        super("Griff", 15, 5, 10, 30, 7, 15, true, "file:textures/griff.png");
    }

    @Override
    public String special() {
        return "Unlimited counterattacks";
    }



}
