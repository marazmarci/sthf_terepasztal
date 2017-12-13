package terepasztal;

import terepasztal.annotations.*;

/**
 * Egy egyszerű sínt reprezentáló osztály. (egyszerű = 2 másik sínt köt össze ÉS nem váltó)
 * Opcionálisan tartalmazhat egy szemafort (ha nem null, tartalmaz). A szemafor állapota kapcsolható.
 * Megvalósítja az Interactable interfészt, tehát ha a felhasználó pl. rákattint, akkor vált a szemafor.
 */
public class SimpleRailTile extends RailTile implements Interactable {

    /** A sín összeköttetése */
    @ToDo // test miatt public
    public RailLink link;

    /** A szemafor */
    private Semaphore semaphore;

    /** #inheritJavaDoc
     * így delegálja: return link.getBinding(prev); */
    @Implemented
    @Override
    protected RailTile getNext(RailTile prev) {
        return link.getBinding(prev);
    }

    /** #inheritJavaDoc
     * Specifikusan SimpleRailTile paraméterrel hívja a mozdony moveTo(..) függvényét,
     *   így az figyelembe tudja venni a szemafort.
     * */
    @Implemented
    protected boolean accept(Locomotive train) {
        if (this.isOccupied())
            return true;
        train.moveTo(this);
        return false;
    }

    /** Szabad jelzést ad-e a szemafor? */
    @TodoSeqDiagram
    @Implemented
    public boolean isSemaphoreClear() {
        return semaphore == null || semaphore.isClear();
    }

    /** Akkor hívódik, amikor a játékos interakcióba lép az objektummal (rákattint)
     * Ekkor vált a szemafor állapota.
     * */
    @Override
    public void interact() {
        semaphore.toggle();
    }

    /** Hozzárendel egy szemafort a sínelemhez. */
    @UmlTodo
    @New
    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }
}