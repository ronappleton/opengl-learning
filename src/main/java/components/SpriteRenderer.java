package components;

import imgui.ImGui;
import jade.Component;
import jade.Transform;
import org.joml.Vector2f;
import org.joml.Vector4f;
import renderer.Texture;


public class SpriteRenderer extends Component {
    private Vector4f color = new Vector4f(1,1,1,1);
    private Sprite sprite = new Sprite();

    private transient Transform lastTransform;
    private transient boolean isDirty = true;

//    public SpriteRenderer(Vector4f color) {
//        this.color = color;
//        this.sprite = new Sprite(null);
//        isDirty = true;
//    }
//
//    public SpriteRenderer(Sprite sprite) {
//        this.sprite = sprite;
//        this.color = new Vector4f(1, 1, 1, 1);
//        isDirty = true;
//    }

    @Override
    public void start() {
        this.lastTransform = gameObject.transform.copy();
    }

    @Override
    public void update(float dt) {
        if (!this.lastTransform.equals(gameObject.transform)) {
            gameObject.transform.copy(this.lastTransform);
            isDirty = true;
        }
    }

    @Override
    public void imgui() {
        float[] imColors = {color.x, color.y, color.z, color.w};
        if(ImGui.colorPicker4("Colour Picker", imColors)) {
            this.color.set(imColors[0], imColors[1], imColors[2], imColors[3]);
            this.isDirty = true;
        }
    }

    public Vector4f getColor() {
        return color;
    }

    public Texture getTexture() {
        return sprite.getTexture();
    }

    public Vector2f[] getTexCoords() {
        return sprite.getTexCoords();
    }

    public void setColor(Vector4f color) {
        if (!this.color.equals(color)) {
            this.isDirty = true;
            this.color = color;
        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.isDirty = true;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setClean() {
        this.isDirty = false;
    }
}
