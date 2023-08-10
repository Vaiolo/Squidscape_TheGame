import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] args)
    {
        // Create a new instance of the Squidscape game
        Game squidscape = new Squidscape();

        // Configure the properties of the game window
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("Squidscape The Game");
        configuration.setWindowIcon("windowLogo.jpg");
        configuration.setWindowedMode(800, 640);

        //Initialize the game
        new Lwjgl3Application(squidscape, configuration);
    }
}
