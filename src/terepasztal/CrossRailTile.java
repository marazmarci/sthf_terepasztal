package terepasztal;

import terepasztal.annotations.Implemented;
import terepasztal.annotations.New;
import terepasztal.annotations.TodoSeqDiagram;
import terepasztal.annotations.UmlTodo;

/**
 * Egy kereszteződést reprezentáló osztály. Sínelem típusú. Két sín-összeköttetést tárol, amik egymástól függetlenek.
 */
public class CrossRailTile extends RailTile {

    /**
     * A két sín-összeköttetés
     */
    @UmlTodo @New
    private final RailLink[] links = new RailLink[2];

    /**
     * Létrehozza a kereszteződést a szomszédainak megadásával.
     *
     * @param a1 Az első összeköttetés egyik végpontja
     * @param a2 Az első összeköttetés másik végpontja
     * @param b1 A másik összeköttetés egyik végpontja
     * @param b2 A másik összeköttetés másik végpontja
     */
    @UmlTodo @New
    public CrossRailTile(RailTile a1, RailTile a2, RailTile b1, RailTile b2) {
        links[0] = new RailLink(a1,a2);
        links[1] = new RailLink(b1,b2);
    }

    /** #inheritJavaDoc */
    @Implemented
    @TodoSeqDiagram
    @Override
    protected RailTile getNext(RailTile prev) {
        for (RailLink link : links) {
            RailTile railTile = link.getBinding(prev);
            if (railTile != null)
                return railTile;
        }
        return null;
    }

}