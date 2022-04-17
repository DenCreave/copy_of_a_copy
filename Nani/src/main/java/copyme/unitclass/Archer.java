package copyme.unitclass;

import copyme.globalvars.Global;
import copyme.hero.Hero;

/**
 * the archer unit
 */
public class Archer extends BattleUnit implements SpecialAbiDesc {
    public Archer(){
        super("Archer", 6, 2, 4,7, 4,9,true,"file:textures/archer.png");
        setRange(12);
    }

    @Override
    public String special() {
        return "Ranged attack if no enemy next to it";
    }



    private boolean isAnyoneNearMe(boolean lookingForEnemy){
        boolean isAnyoneNearMe=false;
        for (int i = -1; i < 2 ; i++) {
            for (int j = -1; j < 2 ; j++) {
                if (lookingForEnemy){
                    for (int k = 0; k < Global.enemy.army.length; k++) {
                        if (Global.enemy.army[k]!= null){
                            if (Global.enemy.army[k].x== i+this.x && Global.enemy.army[k].y== j+this.y){
                                isAnyoneNearMe=true;
                            }

                        }

                    }
                }else {
                    for (int k = 0; k < Global.player.army.length; k++) {
                        if (Global.player.army[k]!= null){
                            if (Global.player.army[k].x== i+this.x && Global.player.army[k].y== j+this.y){
                                isAnyoneNearMe=true;
                            }

                        }

                    }
                }
            }
        }

        return isAnyoneNearMe;
    }




    @Override
    public void getTargetables(boolean lookingForEnemy) {
        if (isAnyoneNearMe(lookingForEnemy)){
            setRange(1);
        }else {
            setRange(12);
        }
        //todo might not work and has to copy the whole method from parent class
        super.getTargetables(lookingForEnemy);
    }
}
