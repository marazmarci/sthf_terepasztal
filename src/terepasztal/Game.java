package terepasztal;

import terepasztal.annotations.*;
import terepasztal.tests.TrainCrashedException;

import java.util.HashSet;
import java.util.Set;


/**
 * Egy játékot reprezentáló osztály. Tárolja a játék állapotát, és objektumait. Lépteti a léptetendő objektumokat.
 * Továbbítja a felhasználó kattintását az interaktív elemek felé.
 * Ha egy konkrét játékot fejlesztenénk ebből, akkor ez az osztály végezné a kirajzolást is.
 */
public class Game {

    /** Azon objektumok, amiket léptetni kell. */
    private Set<Updatable> updatables;

    /** Azon objektumok, amikkel interakcióba léphet (rákattinthat) a játékos. */
    @UmlTodo @New
    private Set<Interactable> interactables;

    /** A pályát jelentő sínelemek. */
    private Set<RailTile> railTiles;

    /** Vége van-e a játéknak. Alap értéke false. */
    @UmlTodo @New @ToDo // test miatt public
    public boolean gameOver = false;

    /** A játékos pontszáma. */
    @UmlTodo @New
    private int score;


    /** Legenerálja sínhálózatot (szabályosan).
     */
    @UmlTodo @New
    public void initLevel() {
       // ...
    }

    /** A játék futása ebbben a függvényben történik.
     * Lépteti az updatable objektumokat, és a rákattintott interaktív objektumoknak meghívja az .interact() függvényét.
     */
    @Implemented
    @SeqDiagramDone
    @UmlTodo // runGameLoop -> runGame
    public void runGame() {
        while (!gameOver) {
            for (Updatable updatable : updatables)
                updatable.update();
            Set<Interactable> clicked = new HashSet<>();
            for (Interactable interactable : clicked)
                interactable.interact();
        }
    }

    /**
     * @param u Hozzáad egyet a léptethető objektumok listájához.
     */
    @UmlTodo @New
    public void addUpdatable(Updatable u) {
        updatables.add(u);
    }

    /** Hozzáad egy pontot a játékos pontszámához. */
    @UmlTodo @New
    public void givePoint() {
        score++;
    }


    /** A játékot game over állapotba vivő függvény. Egy mozdony hívja, ami ütközött.
     */
    public void gameOver() {
        gameOver = true;
        new TrainCrashedException().printStackTrace();
    }

}