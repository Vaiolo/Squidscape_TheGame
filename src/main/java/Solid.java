import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Solid extends BaseActor{
    public Solid(MapProperties properties, Stage stage)
    {
        super(properties.get("x",Float.class), properties.get("y",Float.class), stage);
        setSize(properties.get("width", Float.class), properties.get("height", Float.class));
        setBoundaryRectangle();
    }
}
