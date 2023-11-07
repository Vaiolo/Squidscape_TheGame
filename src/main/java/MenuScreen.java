import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class MenuScreen extends BaseScreen{
    @Override
    public void initialize() {
        BaseActor background = new BaseActor(0, 0, mainStage);
        background.setColor(Color.BLACK);
        background.setSize(800, 600);

        BaseActor title = new BaseActor(0,0, mainStage);
        title.loadTexture( "logoSquidscape.png" );
        float desiredWidth = 700;
        float desiredHeight = 60;
        title.setSize(desiredWidth, desiredHeight);
        title.centerAtPosition(400,250);
        title.moveBy(0,100);

        BaseActor start = new BaseActor(0,0, mainStage);
        start.loadTexture( "startMessage.png" );
        start.centerAtPosition(400,300);
        start.moveBy(0,-100);
    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            Squidscape.setActiveScreen(new LevelScreen());
        }
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
