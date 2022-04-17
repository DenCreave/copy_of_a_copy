package copyme.controls;

import copyme.hero.Hero;
import copyme.unitclass.BattleUnit;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * contains frequently used attributes and values
 */
public class MyCache {
    private int round;
    private int heroAction;
    private int cursorSwitch;
    public HashMap<String, Boolean> current;
    public LinkedList<BattleUnit> nextUnitOrder;
    public LinkedList<BattleUnit> inRangeUnits;
    public LinkedList<MyCoordinates> stepTracker;
    private int maxStepTrack;


    public MyCache(){
        heroAction=-1;
        round=0;
        cursorSwitch=0;
        maxStepTrack=0;
        current=new HashMap<>();
        nextUnitOrder=new LinkedList<>();
        inRangeUnits=new LinkedList<>();
        stepTracker=new LinkedList<>();
    }

    public int getMaxStepTrack() {
        return maxStepTrack;
    }

    public void setMaxStepTrack(int maxStepTrack) {
        this.maxStepTrack = maxStepTrack;
    }

    public int getHeroAction() {
        return heroAction;
    }

    public void setHeroAction(int heroAction) {
        this.heroAction = heroAction;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getCursorSwitch() {
        return cursorSwitch;
    }

    public void setCursorSwitch(int cursorSwitch) {
        this.cursorSwitch = cursorSwitch;
    }
}
