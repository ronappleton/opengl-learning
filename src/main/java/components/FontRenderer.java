package components;

import jade.Component;

public class FontRenderer extends Component {

    @Override
    public void start() {
        if (gameObject.getComponent(SpriteRenderer.class) != null) {
            System.out.println("Fount Font renderer class");
        }
    }

    @Override
    public void update(float dt) {

    }
}
