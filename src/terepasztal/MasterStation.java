package terepasztal;

import terepasztal.annotations.*;

/**
 * Egy fejállomást reprezentáló osztály. Sínelem típusú. Ez "készíti" az új szerelvényeket.
 * Tárolja a belőle kigördülő vonatelemek ezután soron következő helyét és egy játékpéldányt.
 * Megvalósítja az Updatable interfészt, így képes periodikusan a beépített állapotgépe segítségével
 *   új szerelvényeket létrehozni és kibocsátani magából.
 */
public class MasterStation extends RailTile implements Updatable {

    /** A sínelem, ahova a kigördülő vonatelem továbblép. */
    private final RailTile output;

    /** A játék példánya, hogy hozzá tudja adni az updatables listához az új mozdonyokat. */
    private final Game game;

    /** A szerelvények létrehozására való beépített állapotgép aktuális állapota */
    @UmlTodo @New
    private MasterStationState state = MasterStationState.WAITING;

    /** Ha az állapotgép WAITING állapotban van, a hátralévő léptetések száma a következő mozdony létrehozásáig. */
    @UmlTodo @New
    private int remainingWait = 1;

    /** Ha az állapotgép PRODUCING_TRAIN állapotban van, a hátralévő léptetések száma a vagonok létrehozásának befejeztéig. */
    @UmlTodo @New
    private int remainingProduce;

    /** Az állapotgép által legutoljára létrehozott vonatelem, amihez majd hozzá kell fűzni az aktuálisan létrehozottat. */
    @UmlTodo @New
    private Train lastTrain;

    /** Létrehozza a fejállomást a kijárat és a játékpéldány megadásával. */
    @UmlTodo @New
    public MasterStation(RailTile output, Game game) {
        this.output = output;
        this.game = game;
    }


    /** A szerelvényeket létrehozó játéklogika állapotgépes működését megvalósító függvény.
     * Az Updatable interfész megvalósítása. */
    @Implemented
    @TodoStateMachine
    @Override
    public void update() {
        //Random random = new Random();
        switch (state) {
            case WAITING:
                if (--remainingWait <= 0) {
                    state = MasterStationState.PRODUCING_LOCOMOTIVE;
                    //remainingProduce = random.nextInt(10);
                    remainingProduce = 10;
                }
                break;
            case PRODUCING_LOCOMOTIVE:
                Locomotive loco = new Locomotive(this, game);
                game.addUpdatable(loco);
                lastTrain = loco;
                state = MasterStationState.PRODUCING_TRAIN;
                break;
            case PRODUCING_TRAIN:
                if (--remainingProduce <= 0) {
                    state = MasterStationState.WAITING;
                    //remainingWait = random.nextInt(500) + 100;
                    remainingWait = 500;
                } else {
                    Train newTrain = new Train(lastTrain, this); // attach to last train
                    lastTrain = newTrain;
                }
                break;
        }
    }

    /** #inheritJavaDoc */
    @ToDo
    @Override
    protected RailTile getNext(RailTile prev) {
        return output;
    }

}