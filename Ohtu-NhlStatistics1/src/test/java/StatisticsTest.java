/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import ohtuesimerkki.Reader;
import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.Statistics;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Arto
 */
public class StatisticsTest {

    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    public StatisticsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.stats = new Statistics(this.readerStub);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void searchPlayerToimiiJosNimiListalla() {
        Player pelaaja = this.stats.search("Kurri");
        assertEquals("Kurri", pelaaja.getName());
        assertEquals("EDM", pelaaja.getTeam());
        assertEquals(37, pelaaja.getGoals());
        assertEquals(53, pelaaja.getAssists());
    }

    @Test
    public void searchPlayerToimiiJosOsaNimestaListalla() {
        Player pelaaja = this.stats.search("urri");
        assertEquals("Kurri", pelaaja.getName());
        assertEquals("EDM", pelaaja.getTeam());
        assertEquals(37, pelaaja.getGoals());
        assertEquals(53, pelaaja.getAssists());
    }

    @Test
    public void searchPlayerPalauttaaNullJosPelaajaaEiListalla() {
        assertTrue(this.stats.search("Arto") == null);
    }

    @Test
    public void teamPalauttaaOikeanKokoisenJoukkueenKunPelaajiaKolme() {
        List joukkue = stats.team("EDM");
        assertEquals(3, joukkue.size());
    }

    @Test
    public void teamPalauttaaTyhjänListanKunPelaajiaEiOle() {
        List joukkue = stats.team("HIFK");
        assertEquals(0, joukkue.size());
    }

    @Test
    public void topScorersPalauttaaKolmePelaajaaKunHaetaanKolmePelaajaa() {
        List pelaajat = stats.topScorers(3);
        assertEquals(3, pelaajat.size());
    }

    @Test
    public void topScorersPalauttaaOikeatKolmePelaajaa() {
        List<Player> pelaajat = stats.topScorers(3);
        assertEquals("Gretzky",pelaajat.get(0).getName());
        assertEquals("Lemieux",pelaajat.get(1).getName());
        assertEquals("Yzerman",pelaajat.get(2).getName());       
    }
    
        @Test
    public void topScorersPalauttaaKaikkiPelaajatKunHaetaanEnemmanPelaajiaKuinListassaOn() {
        List pelaajat = stats.topScorers(7);
        assertEquals(5, pelaajat.size());
    }
}
