import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
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
    protected Vector2 vectorVelocity;
    protected Vector2 vectorAcceleration;
    private float acc;
    private float dec;
    private float maxSpeed;
    private static Rectangle bounds;
    private Rectangle boundaryRectangle;

    public BaseActor(float x, float y, Stage s) {
        super();
        setPosition(x, y);
        s.addActor(this);

        animation = null;
        elTime = 0.0F;
        vectorVelocity = new Vector2(0, 0);
        vectorAcceleration = new Vector2(0,0);
        maxSpeed = 1000.0F;
        acc = 0.0F;
        dec = 0.0F;
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

    //Getters
    public float getSpeed()
    {
        return vectorVelocity.len();
    }

    //Setters
    public void setMaxSpeed(float maxSp)
    {
        maxSpeed = maxSp;
    }

    public void setSpeed(float speed)
    {
        if (vectorVelocity.len() == 0)
        {
            vectorVelocity.set(speed,0);
        } else {
            vectorVelocity.setLength(speed);
        }
    }

    public void setAcceleration(float acceleration)
    {
        acc = acceleration;
    }

    public void setDeceleration(float deceleration)
    {
        dec = deceleration;
    }

    public void accelerateAngle(float angle)
    {
        vectorAcceleration.add(new Vector2(acc, 0).setAngleDeg(angle));
    }

    public static void setBounds(float width, float height)
    {
        bounds = new Rectangle(0,0,width,height);
    }

    public void setBoundaryRectangle() {
        if (bounds == null) {
            throw new IllegalStateException("Bounds must be set using setBounds method");
        }
        Rectangle rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());
        rectangle.setPosition(getX(), getY());
        boundaryRectangle = rectangle;
    }
}
