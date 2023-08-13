import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public class TilemapActor extends Actor {

    private TiledMap map;
    private OrthographicCamera camera;
    private OrthoCachedTiledMapRenderer renderer;

    public TilemapActor(String filename, Stage stage)
    {
        map = new TmxMapLoader().load(filename);

        int windowWidth = 800;
        int windowHeight = 640;

        renderer = new OrthoCachedTiledMapRenderer(map);
        renderer.setBlending(true);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, windowWidth, windowHeight);
        camera.update();

        stage.addActor(this);

        BaseActor.setBounds(map.getProperties().get("width", Integer.class),
                map.getProperties().get("height", Integer.class));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void draw(Batch batch, float parentAlpha)
    {
        Camera mainCamera = getStage().getCamera();
        camera.position.set(mainCamera.position.x, mainCamera.position.y, 0);
        camera.update();
        renderer.setView(camera);

        batch.end();
        renderer.render();
        batch.begin();
    }

    public Array<MapObject> getObjectsWithProperty(MapLayer layer, String propertyName) {
        Array<MapObject> list = new Array<>();

        for (MapObject obj : layer.getObjects()) {
            if (obj.getProperties().containsKey("name") && obj.getProperties().get("name").equals(propertyName)) {
                list.add(obj);
            }
        }

        return list;
    }

    public List<MapObject> getRectangleList(String propertyName) {
        List<MapObject> list = new ArrayList<>();

        for (MapLayer layer : map.getLayers()) {
            Array<MapObject> objectsWithProperty = getObjectsWithProperty(layer, propertyName);
            for (MapObject obj : objectsWithProperty) {
                list.add(obj);
            }
        }

        return list;
    }

    public List<MapObject> getTileList(String propertyName) {
        List<MapObject> list = new ArrayList<>();

        for (MapLayer layer : map.getLayers()) {
            Array<MapObject> objectsWithProperty = getObjectsWithProperty(layer, propertyName);
            for (MapObject obj : objectsWithProperty) {
                list.add(obj);
            }
        }

        return list;
    }


}
