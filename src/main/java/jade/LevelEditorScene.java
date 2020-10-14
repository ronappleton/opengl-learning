package jade;

import components.Sprite;
import components.SpriteRenderer;
import components.Spritesheet;
import imgui.ImGui;
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

        GameObject go2 = new GameObject("Object 2", new Transform(new Vector2f(400,100), new Vector2f(256,256)), -1);
        go2.addComponent(new SpriteRenderer(new Sprite(AssetPool.getTexture("assets/images/blendImage1.png"))));
        this.addGameObjectToScene(go2);


        go = new GameObject("Object 1", new Transform(new Vector2f(100,100), new Vector2f(256,256)), 2);
        go.addComponent(new SpriteRenderer(new Vector4f(1,0,0, 1)));
        this.addGameObjectToScene(go);
        this.activeGameObject = go;

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
        //go.transform.position.x += 10 * dt;

        for (GameObject go : this.gameObjects) {
            go.update(dt);
        }

        this.renderer.render();
    }

    @Override
    public void imgui() {
        ImGui.begin("Test Window");
        ImGui.text("Some random test.");
        ImGui.end();
    }
}
