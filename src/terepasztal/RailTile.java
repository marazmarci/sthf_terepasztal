package terepasztal;

import terepasztal.annotations.*;

import java.util.HashSet;
import java.util.Set;


/** Egy a pályán lévő sínelemet reprezentáló absztrakt osztály. Tárolja, hogy el van-e foglalva egy vonatelem által (karambol megállípítására).
 * Képes előremozdítani egy mozdonyt, specifikusan, a sínelem adott típusának megfelelő módon.
 * Ezeket a sínelem-típusok közötti viselkedésbeli különbözőségeket az absztrakt getNext(..) és az accept(...) függvények biztosítják.
 * */
public abstract class RailTile {

    /** El van-e foglalva egy vonatelem által. */
    private boolean occupied;

    /** Absztrakt függvény, ami megadja, hogy melyik sínelem következik,
     * ha a prev sínelem felől érkezünk (amelyikben az ezelőtti léptetésben voltunk). */
    protected abstract RailTile getNext(RailTile prev);

    /** Azon a sínelemek listája, amelyekre nem lehet továbbmenni (Mert pl. egy váltó elváltott). */
    @UmlTodo @New
    private Set<RailTile> blockedOutputs = new HashSet<>(4);

    /** Előremozdítja a megadott mozdonyt. Megkérdezi tőle, hogy merről jött (getRailTileBehind),
     *   a sínelem getNext függvényével kideríti, hogy merre kéne tovább haladnia,
     *   majd szól a soron következő sínelemnek, hogy a szóban forgó mozdony rá szándékozik gurulni.
     * Amennyiben a következő sínelem már foglalt, az bizony karambolt jelent,
     *   amit egy true logikai érték visszaadásával jelez a függvény (egyébként false).
     *   */
    @TodoSeparateSeqDiagram
    @Implemented
    public boolean forwardLocomotive(Locomotive loco) {
        RailTile railTileBehind = loco.getRailTileBehind();
        RailTile next = this.getNext(railTileBehind);
        if (blockedOutputs.contains(next))
            return true;
        return next.accept(loco); // boolean crashed;
    }

    /** Beállítja a sínelem elfoglaltságát jelző változó értékét a megadott értékre. */
    @Implemented
    public void setOccupied(boolean o) {
        occupied = o;
    }

    /** Beállítja egy adott kijáratra, hogy ennek a sínelemnek az irányából le van-e tiltva, vagy nem. */
    @UmlTodo @New
    public void blockOutput(RailTile railTile, boolean block) {
        if (block)
            blockedOutputs.add(railTile);
        else
            blockedOutputs.remove(railTile);
    }


    /** Egy vonatelem új sínelemre kerülésének utolsó előtti fázisa, ahol ellenőrződik a karambol,
     *   és ha nincs, akkor a vonatelem elfoglalja a sínelemet.
     *  (lásd: szekvencia diagram)
     *   */
    @Implemented
    protected boolean accept(Locomotive train) {
        if (occupied)
            return true;
        train.moveTo(this);
        return false;
    }

    /** @return El van-e foglalva?
     */
    @UmlTodo @New
    public boolean isOccupied() {
        return occupied;
    }




    @UmlExclude
    public int ID = -1;
}