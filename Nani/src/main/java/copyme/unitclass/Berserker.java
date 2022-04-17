package copyme.unitclass;

import copyme.controls.FieldController;
import copyme.globalvars.Global;
import copyme.hero.Hero;

/**
 * berserker units class
 */
public class Berserker extends BattleUnit implements SpecialAbiDesc {
    public Berserker(){
            super("Berserker",9,6,9,13,6,13,true,"file:textures/berserker.png");
    }

    @Override
    public String special() {
        return "When it dies or kills incr. its atk stat by 1";
    }


    @Override
    public void attack(int indexPlayer, boolean isEnemyAttacking, int indexEnemy) {
        FieldController controller=new FieldController();
        int startEnem=Global.enemy.army[indexEnemy].getCurrentArmyNumber();
        int startPlaya=Global.player.army[indexPlayer].getCurrentArmyNumber();
        super.attack(indexPlayer, isEnemyAttacking, indexEnemy);

        int endEnem=startEnem-Global.enemy.army[indexEnemy].getCurrentArmyNumber();
        int endPlaya=startPlaya-Global.player.army[indexPlayer].getCurrentArmyNumber();
        controller.addLogMsg("'zerker gained "+(getStatAttack()+endEnem+endPlaya)+" atk");
        setStatAttack(getStatAttack()+endEnem+endPlaya);
    }
}
