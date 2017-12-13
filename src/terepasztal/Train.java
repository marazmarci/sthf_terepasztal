package terepasztal;

import terepasztal.annotations.*;


/**
 * Egy vonatelemet reprezentáló osztály. Tárolja a sínelemet, amin elhelyezkedik,
 *   és az utána csatolt következő vonatelemet.
 * Egy szerelvény elején mindig egy mozdony áll, de ez csak közvetetten van kikényszerítve az osztály implementációjában,
 *   mert a MasterStation osztály ennek megfelelően hozza létre a szerelvényeket.
 *     (lásd a MasterStation osztállyal kapcsolatos állapotgép diagramon)
 * Ennek az osztálynak egy példánya alap esetben egy vagont reprezentál, de egy speciális típusa a Locomotive (mozdony).
 *   (ami ebből az osztályból származik le)
 */
public class Train {

    /** Az ez utána a vonatelem után láncolt következő vonatelem. */
    private Train wagonBehind;

    /** A sínelem, amin ez a vonatelem elhelyezkedik. */
    @NotUmlToDo // UML-en maradjon private!!!
    protected RailTile railTile;


    /** Létrehoz egy új vonatelemet.
     * @param trainAhead Az a vonatelem, ami útán kell ezt láncolni.
     *                   Ez a konstruktor beállítja a trainAhead objektum wagonBehind vááltozóját erre az objektumra (this), ha nem null.
     * @param railTile Az a sínelem, amin tartózkodik a vagon
     */
    @UmlTodo @New
    public Train(Train trainAhead, RailTile railTile) {
        if (trainAhead != null)
            trainAhead.wagonBehind = this;
        this.railTile = railTile;
        if (railTile != null)
            railTile.setOccupied(true);
    }

    /** Beállítja a vonatelem új tartózkodási helyét, és beállítja elfoglaltra a sínelem állapotát (occupied). */
    @Implemented
    private void setRailTile(RailTile r) {
        railTile.setOccupied(false);
        r.setOccupied(true);
        railTile = r;
    }

    /** @return Visszaadja a vonatelem aktuális tartózkodási helyét (sínelemét).
     */
    @Implemented
    public RailTile getRailTile() {
        return railTile;
    }

    /** Áthelyezi a vonatelemet a megadott (következő) sínelemre.
     *  Majd meghívja az utána csatoltnak ugyanezt a függvényét,
     *    paraméterként megadva ennek a vonatelemnek az előrehaladás előtti pozícióját, ahova majd a (köv.-nek kell kerülnie)
     */
    @UmlTodo
    @TodoSeqDiagram
    @Implemented
    public void moveTo(RailTile next) {
        RailTile prev = getRailTile();
        setRailTile(next);
        if (wagonBehind != null)
            wagonBehind.moveTo(prev);
    }

}