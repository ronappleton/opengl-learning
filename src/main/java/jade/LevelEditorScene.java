package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import org.joml.Vector2f;
import org.joml.Vector4f;
import util.AssetPool;

public class LevelEditorScene extends Scene {

    public LevelEditorScene() {
        System.out.println("Inside Level Editor Scene!");
    }

    private GameObject go;

    @Override
    public void init() {
        loadResources();
        this.camera = new Camera(new Vector2f());

        Spritesheet sprites = AssetPool.getSpriteSheet("assets/images/spritesheet.png");

        go = new GameObject("Object 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)));
        go.addComponent(new SpriteRenderer(sprites.getSprite(0)));
        this.addGameObjectToScene(go);

        GameObject go2 = new GameObject("Object 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)));
        go2.addComponent(new SpriteRenderer(sprites.getSprite(21)));
        this.addGameObjectToScene(go2);


    }

    private void loadResources() {
        AssetPool.getShader("assets/shaders/default.glsl");

        AssetPool.addSpritesheet("assets/images/spritesheet.png",
                new Spritesheet(AssetPool.getTexture("assets/images/spritesheet.png"),
                        16,
                        16,
                        26,
                        0
                )
        );
    }

    @Override
    public void update(float dt) {

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

}
