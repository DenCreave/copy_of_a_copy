package copyme.hero;

import copyme.controls.FieldController;
import copyme.globalvars.Global;

/**
 * a single target spell
 */
public class Lightning extends Spell{
    public Lightning(){
        super("Lightning",false,true, 30, 60, 5, "Select an enemy and strike it with lightning", "file:textures/lightning.png");
    }

    @Override
    public boolean castSpell(boolean isEnemyCasting, int x, int y) {
        boolean haveDamaged=false;

        FieldController controller=new FieldController();
        if (isEnemyCasting){
            for (int i = 0; i < Global.player.army.length; i++) {
                if (Global.player.army[i]!=null){
                    if (Global.player.army[i].x==x&&Global.player.army[i].y==y){
                        int amountHealedhp=Global.player.army[i].getTotalHealth();
                        Global.player.army[i].takeDmgFromHero(Global.enemy.getSpellpowa()*getValue());
                        amountHealedhp=Global.player.army[i].getTotalHealth()-amountHealedhp;
                        controller.addLogMsg("Lightning struck "+Global.player.army[i].getName()+" for "+amountHealedhp+".");
                        haveDamaged=true;
                        break;
                    }
                }
            }
        }else {
            for (int i = 0; i < Global.enemy.army.length; i++) {
                if (Global.enemy.army[i]!=null){
                    if (Global.enemy.army[i].x==x&&Global.enemy.army[i].y==y){
                        int amountHealedhp=Global.enemy.army[i].getTotalHealth();
                        Global.enemy.army[i].takeDmgFromHero(Global.player.getSpellpowa()*getValue());
                        amountHealedhp=Global.enemy.army[i].getTotalHealth()-amountHealedhp;
                        controller.addLogMsg("Lightning struck "+Global.enemy.army[i].getName()+" for "+amountHealedhp+".");
                        haveDamaged=true;
                        break;
                    }
                }
            }
        }

        return haveDamaged;
    }
}
