package mainPkg;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author MustDy
 */
public class MainTest {

    public MainTest() {
    }

    @BeforeClass
    public static void setUpClass() {    
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIcon() {
        assertEquals(ChessBackground.Background(1, 1).toString(), ChessBackground.class.getResource("/chess/King.jpg").toString());
    }

    /**
     * User input for player name should be null in order to receive this exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void nameTest() { 
        Chess.defaultPlayerName = "test";
        new Chess();       
    }
}
