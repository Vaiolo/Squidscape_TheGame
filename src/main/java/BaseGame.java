import com.badlogic.gdx.Game;

public abstract class BaseGame extends Game {

    private static BaseGame game;

    public BaseGame()
    {
        game = this;
    }

    //Method to switch screens
    public static void setActiveScreen(BaseScreen s)
    {
        game.setScreen(s);
    }

}
