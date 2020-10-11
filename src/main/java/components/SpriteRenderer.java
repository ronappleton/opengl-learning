package components;

import jade.Component;

public class SpriteRenderer extends Component {
    public boolean firstTime = true;

    @Override
    public void start() {
        System.out.println("I am starting '" + getClass() + "'");
    }

    @Override
    public void update(float dt) {
        if (firstTime) {
            System.out.println("I am updating '" + getClass() + "'");
            firstTime = false;
        }

    }
}
