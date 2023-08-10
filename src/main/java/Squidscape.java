public class Squidscape extends BaseGame{
    @Override
    public void create() {
        super.create();
        setActiveScreen(new MenuScreen());
    }
}
