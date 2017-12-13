package terepasztal;


import terepasztal.annotations.New;
import terepasztal.annotations.UmlTodo;

/**
 * Egy váltható sínelemet reprezentáló osztály. Egyszerre mindig csak két sínelemet köt össze.
 * A két összeköttetésben az egyik végpont azonos (az első).
 * Ha vált a váltó, a szomszéd sínelemek efelé a sínelem felé vezető összeköttetéseit letiltja, illetve engedélyezi (logikusan).
 * Megvalósítja az Interactable interfészt, tehát ha a felhasználó pl. rákattint, akkor vált a váltó.
 */
public class SwitchRailTile extends RailTile implements Interactable {

    /** A két lehetséges sín-összeköttetés */
    @UmlTodo @New
    private final RailLink[] links = new RailLink[2];

    /** A két összeköttetés közül az első van-e kiválasztva. */
    @UmlTodo @New
    private boolean firstLinkSelected = true;

    /** Létrehozza a váltót a lehetséges összeköttetések megadásával.
     * A fix értelemszerűen azt az irányt jelenti, ami nem változik,
     *   az 'a' illetve 'b' pedig a váltás hatására megváltozóakat.
     * */
    @UmlTodo @New
    public SwitchRailTile(RailTile fix, RailTile a, RailTile b) {
        links[0] = new RailLink(fix,a);
        links[1] = new RailLink(fix,b);
    }

    /** #inheritJavaDocs
     * Az aktuális váltási állapottól függően. */
    @Override
    protected RailTile getNext(RailTile prev) {
        return getSelectedLink().getBinding(prev);
    }

    /** Akkor hívódik, amikor a játékos interakcióba lép az objektummal (rákattint)
     * Ekkor vált a váltó a két összeköttetés között.
     * */
    @Override
    public void interact() {
        RailLink unselectedLink = getSelectedLink();
        firstLinkSelected = !firstLinkSelected;
        RailLink selectedLink = getSelectedLink();
        unselectedLink.getSecond().blockOutput(this, true);
        selectedLink.getSecond().blockOutput(this, false);
    }

    /** Megadja az aktuálisan aktív sín-összeköttetést. */
    @UmlTodo @New
    private RailLink getSelectedLink() {
        return links[firstLinkSelected ? 0 : 1];
    }

}