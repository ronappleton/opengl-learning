package physics2d.primitives;

import org.joml.Vector2f;
import physics2d.rigidbody.RigidBody2D;

public class Circle {
    public float radius = 1.0f;
    private RigidBody2D rigidbody = null;

    public float getRadius() {
        return radius;
    }

    public Vector2f getCentre() {
        return rigidbody.getPosition();
    }
}
