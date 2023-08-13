import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;

public class LevelScreen extends BaseScreen{

    MainCharacter player;

    @Override
    public void initialize() {
        TilemapActor tileMap = new TilemapActor("map.tmx", mainStage);

        for (MapObject obj : tileMap.getRectangleList("Solid")) {
            MapProperties props = obj.getProperties();
            new Solid(props, mainStage);
        }

        MapObject startPoint = tileMap.getRectangleList("start").get(0);
        MapProperties startProps = startPoint.getProperties();
        player = new MainCharacter((float)startProps.get("x"), (float)startProps.get("y"), mainStage);

        player.toFront();
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
