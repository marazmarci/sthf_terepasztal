package terepasztal.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import terepasztal.*;

public class TerepasztalTest {

    private RailTile[] railTiles;
    private MasterStation masterStation;
    private Game game;

    private Train wagon1, wagon2;
    private Locomotive loco;



    @Before
    public void before() {
        game = new Game();
        masterStation = null;
    }

    private void createRails(boolean master) {
        railTiles = new SimpleRailTile[10];
        for (int i = 0; i < railTiles.length; i++) {
            SimpleRailTile railTile = new SimpleRailTile();
            railTile.ID = i;
            railTiles[i] = railTile;
        }
        SimpleRailTile prev = (SimpleRailTile) railTiles[1];
        RailTile prevprev = railTiles[0];
        if (master) {
            this.masterStation = new MasterStation(railTiles[1], game);
            prevprev = this.masterStation;
        }
        for (int i = 2; i < railTiles.length; i++) {
            SimpleRailTile actual = (SimpleRailTile) railTiles[i];
            if (prev != null && prevprev != null)
                prev.link = new RailLink(prevprev, actual);
            prevprev = prev;
            prev = actual;
        }
    }

    private void attachTrainsToLocomotive() {
        /* Before:
         *   6   5   4   3   2   1   0
         * *****************************
         * *   *   *   * L * W * W * M *
         * *****************************
         *                   w1  w2
         */
        wagon1 = new Train(loco, railTiles[2]);
        wagon2 = new Train(wagon1, railTiles[1]);
    }

    private void createLocomotive(boolean master) {
        loco = new Locomotive(masterStation, game);
        if (!master) {
            loco.setRailTile(railTiles[3]);
            loco.setRailTileBehind(railTiles[2]);
        }
    }


    /**
     * Step 1x starting from masterStation station without attached trains
     */
    @Test
    public void trainTest1() {

        createRails(false);
        createLocomotive(false);

        loco.update();

        Assert.assertFalse("CRASHED!", game.gameOver);
        Assert.assertEquals(loco.getRailTile().ID, 4);
        //Assert.assertEquals(wagon1.getRailTile().ID, 4);
        //Assert.assertEquals(wagon2.getRailTile().ID, 3);
        //for (int i = 0; i < railTiles.length; i++)
            //Assert.assertEquals(railTiles[i].isOccupied(), 5 >= i && i >= 3);

    }


    @Test
    public void trainTest2() {

        createRails(false);
        createLocomotive(false);
        attachTrainsToLocomotive();

        final SimpleRailTile semaphoreRail = (SimpleRailTile) railTiles[4];
        semaphoreRail.setSemaphore(new Semaphore());

        int i = 0;
        RailTile locoRailTile;
        while ((locoRailTile = loco.getRailTile()) != railTiles[railTiles.length-1]) {
            loco.update();
            ++i;
            if (i == 10) semaphoreRail.interact();
        }

        Assert.assertFalse("CRASHED!", game.gameOver);

        /*
        Assert.assertEquals(loco.getRailTile().ID, 5);
        Assert.assertEquals(wagon1.getRailTile().ID, 4);
        Assert.assertEquals(wagon2.getRailTile().ID, 3);
        for (int i = 0; i < 7; i++)
            Assert.assertEquals(railTiles[i].isOccupied(), 5 >= i && i >= 3);
        */


    }

    @Test
    public void trainTest3() {

        createLocomotive(true);


    }

}
