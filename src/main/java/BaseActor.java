import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.Animation;

public class BaseActor extends Actor {
    private Animation<TextureRegion> animation;
    private float elTime;
    protected Vector2 velocity;
    private float acc;

    public BaseActor(float x, float y, Stage s) {
        super();
        setPosition(x, y);
        s.addActor(this);
        elTime = 0.0F;
        velocity = new Vector2(0, 0);
        acc = 0.0F;
    }

    public void centerAtPosition(float x, float y) {
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

    public Animation<TextureRegion> loadAnimationFromFiles(String[] fileNames, float frameDuration, boolean loop) {
        Array<TextureRegion> textureArray = new Array<>(); //Array for each frame
        for (int n = 0; n < fileNames.length; n++) { //Looping through the files
            Texture texture = new Texture(Gdx.files.internal(fileNames[n])); //Loading texture from file
            textureArray.add(new TextureRegion(texture)); //add it to the array
        }
        Animation<TextureRegion> anim = new Animation<>(frameDuration, textureArray); //Animation created with the array of textures + frame duration
        if (loop) {
            anim.setPlayMode(Animation.PlayMode.LOOP);
        } else {
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        }
        if (animation == null) { //If the first animation is loaded the actor's size is created based on the first frame
            animation = anim;
            TextureRegion tr = animation.getKeyFrame(0);
            setSize(tr.getRegionWidth(), tr.getRegionHeight());
            setOrigin(getWidth() / 2, getHeight() / 2);
        }
        return anim;
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        return loadAnimationFromFiles(new String[]{fileName}, 1, true);
    }

    public void draw(Batch batch, float parentAlpha) { //
        Color c = getColor(); //Color of actor
        batch.setColor(c.r, c.g, c.b, c.a); //actor's color and alpha value
        if (animation != null && isVisible()) { //if the animation is visible then the current frame is drawn at the actor's position
            batch.draw(animation.getKeyFrame(elTime),
                    getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
