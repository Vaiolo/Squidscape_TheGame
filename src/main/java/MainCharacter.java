import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainCharacter extends BaseActor{
    private Animation<TextureRegion> walkAnimation;
    private Animation<TextureRegion> jumpAnimation;
    private float walkAcceleration;
    private float walkDeceleration;
    private float maxHorSpeed;
    private float jumpSpeed;
    private float gravity;

    public MainCharacter(float x, float y, Stage stage)
    {
        super(x,y,stage);

        String[] filenames = {
                "walk1SquidGame.png", "walk2SquidGame.png",
                "walk3SquidGame.png", "walk2SquidGame.png"
        };

        walkAnimation = loadAnimationFromFiles(filenames, 0.2F, true);

        walkAcceleration = 200;
        walkDeceleration = 200;
        maxHorSpeed = 100;
        gravity = 700;
        jumpSpeed = 450;

        jumpAnimation = loadTexture("jumpSquidGame.png");


    }

    @Override
    public void act(float delta) {
        super.act(delta);

        handleInput();
    }

    private void handleInput()
    {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            vectorAcceleration.add(-walkAcceleration, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            vectorAcceleration.add(walkAcceleration, 0);

        if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            float decelerationAmount = walkDeceleration * Gdx.graphics.getDeltaTime();
            vectorVelocity.x = MathUtils.clamp(vectorVelocity.x - Math.signum(vectorVelocity.x) * decelerationAmount, -maxHorSpeed, maxHorSpeed);
        }

        vectorAcceleration.add(0, -gravity);
    }
}
