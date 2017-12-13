package terepasztal;

import terepasztal.annotations.New;
import terepasztal.annotations.UmlTodo;

/**
 * Egy irányítatlan vasúti sín-összeköttetést reprezentál. (irányítatlan = kétirányú)
 *
 */
public class RailLink {

    /** Az "egyik" végpont */
    @UmlTodo
    private final RailTile first;

    /** A "másik" végpont */
    @UmlTodo
    private final RailTile second;

    /** Létrehozza az osztályt a két végpontjának megadásával. */
    @UmlTodo @New
    public RailLink(RailTile first, RailTile second) {
        this.first = first;
        this.second = second;
    }

    /** Megadja ennek a sínkapcsolatnak egy adott sínelemhez tartozó másik végpontját. */
    @UmlTodo @New
    public RailTile getBinding(RailTile railTile) {
        if (getFirst().equals(railTile))
            return getSecond();
        else if (getSecond().equals(railTile))
            return getFirst();
        return null;
    }

    /**
     * @return Visszaadja az összeköttetés egyik végpontját.
     */
    @UmlTodo @New
    public RailTile getFirst() {
        return first;
    }
    /**
     * @return Visszaadja az összeköttetés másik végpontját.
     */
    @UmlTodo @New
    public RailTile getSecond() {
        return second;
    }
}