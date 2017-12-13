package terepasztal;

import terepasztal.annotations.*;

/**
 * Egy mozdonyt reprezentáló osztály. Mindig egy vonat elején van. Utána kötve lehet tetszőleges számú vonatelem (vagon).
 *   Tárolja az előző lépésben elfoglalt sínelemét és egy referenciát a játékpéldányra.
 * Ha egy léptetésnél a soron következő elem egy szemaforos sínelem,
 *   akkor ellenőrzi a szemafor állapotát, és ha nem szabad (CLEAR), akkor nem halad tovább.
 * Ha karambolozott, erről értesíti a játékpéldányt a gameOver függvényének meghívásával.
 * Megvalósítja az Updatable interfészt.
 */
public class Locomotive extends Train implements Updatable {

    /** Referencia a játékpéldányra. A gameOver esemény jelzéséhez van rá szükség. */
    private final Game game;
    /** A mögötte lévő sínelem. Az aktuális sínelem elhagyásakor van rá szükség a következő meghatározásához. */
    private RailTile railTileBehind;

    /** Léterhoz egy mozdonyt egy fejállomáson elhelyezve azt, a játékpéldány megadásával.
     * A konstruktor meghívja a szülő konstruktorát: super(null, railTile);
     * */
    @UmlTodo @New
    public Locomotive(MasterStation railTile, Game game) {
        super(null, railTile);
        this.game = game;
    }

    /** getter... */
    @Implemented
    public RailTile getRailTileBehind() {
        return railTileBehind;
    }

    /** A szülő osztály ugyanilyen fejlécű függvényének leírása érvényes erre is.
     * Először eltárolja a jelenlegi railTile-t a railTileBehind változóban, majd delegálja a hívást super-nek. */
    @SeqDiagramDone
    @Override
    public void moveTo(RailTile next) {
        railTileBehind = getRailTile();
        game.givePoint();
        super.moveTo(next);
    }


    /** A moveTo(RailTile next) függvénynek egy specializált verziója, ami azért volt szükséges, hogy a Locomotive osztály
     *    kapjon egy callback-et a szemaforos sínelem (SimpleRailTile) osztálytól,
     *    hogy a saját döntésére tudjon majd megállni a STOP jelzésnél.
     *  Ellenőrzi a szemafort, és ha szabad (CLEAR), akkor tovább delegálja a hívást a (Locomotive.)this.moveTo(RailTile) fgv-nek.
     *  (lásd szekvencia diagramon)
     *   */
    @Implemented
    @SeqDiagramDone
    public void moveTo(SimpleRailTile next) {
        if (next.isSemaphoreClear())
            this.moveTo((RailTile) next);
        // else stop
    }

    /** Lépteti a mozdonyt.
     * Először elvégzi az aktuális sínelemén a forwardLocomotive(this) függvényhívást a megadott this paraméterrel.
     * Ennek a hívásnak a visszatérési értéke egy logikai érték, ami azt jelenti, hogy foglalt volt-e a soron következő
     *   sínelem (occupied). Ha igen, az azt jelenti, hogy ütközés történt (crashed). Ez esetben game.gameOver() hívás történik.
     * Működését lásd a szekvencia diagramon.
     * */
    @SeqDiagramDone
    @Implemented
    @Override
    public void update() {
        RailTile actual = getRailTile();
        boolean crashed = actual.forwardLocomotive(this);
        this.pöff();
        if (crashed)
            game.gameOver();
    }




    @UmlExclude
    public void pöff() {
        System.out.println("pöff");
    }

    @UmlExclude
    public void setRailTile(RailTile railTile) {
        this.railTile = railTile;
        if (railTile != null)
            railTile.setOccupied(true);
    }

    @UmlExclude
    public void setRailTileBehind(RailTile railTileBehind) {
        this.railTileBehind = railTileBehind;
        if (railTileBehind != null)
            railTileBehind.setOccupied(true);
    }
}