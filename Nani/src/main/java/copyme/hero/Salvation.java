package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;
import copyme.unitclass.BattleUnit;

/**
 * your heal spell
 */
public class Salvation extends Spell{
    public Salvation(){
        super("Salvation",false,false, 50, 120, 6, "Heals/resurrects allies", "file:textures/salvation.png");
    }



    @Override
    public boolean castSpell(boolean isEnemyCasting, int x, int y) {
        boolean haveHealed=false;

        FieldController controller=new FieldController();
        if (isEnemyCasting){
            for (int i = 0; i < Global.enemy.army.length; i++) {
                if (Global.enemy.army[i]!=null){
                    if (Global.enemy.army[i].x==x&&Global.enemy.army[i].y==y){
                        int amountHealedhp=Global.enemy.army[i].getTotalHealth();
                        Global.enemy.army[i].takeHealing(Global.enemy.getSpellpowa()*getValue());
                        amountHealedhp=Global.enemy.army[i].getTotalHealth()-amountHealedhp;
                        controller.addLogMsg("Enemy Healed "+Global.enemy.army[i].getName()+" for "+amountHealedhp+".");
                        haveHealed=true;
                        break;
                    }
                }
            }
        }else {
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.player.army[i]!=null){
                    if (Global.player.army[i].x==x&&Global.player.army[i].y==y){
                        int amountHealedhp=Global.player.army[i].getTotalHealth();
                        Global.player.army[i].takeHealing(Global.player.getSpellpowa()*getValue());
                        amountHealedhp=Global.player.army[i].getTotalHealth()-amountHealedhp;
                        controller.addLogMsg("Healed "+Global.player.army[i].getName()+" for "+amountHealedhp+".");
                        haveHealed=true;
                        break;
                    }
                }
            }
        }

        return haveHealed;
    }
}
