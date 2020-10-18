package physics2d.primitives;

import org.joml.Vector2f;
import org.joml.Vector2fc;
import physics2d.rigidbody.RigidBody2D;

public class Box2D {
    private Vector2f size = new Vector2f();
    private Vector2f halfSize;
    private RigidBody2D rigidbody = null;

    public Box2D() {
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public Box2D(Vector2f min, Vector2f max) {
        this.size = new Vector2f(max).sub(min);
        this.halfSize = new Vector2f(size).mul(0.5f);
    }

    public Vector2f getMin() {
        return new Vector2f(this.rigidbody.getPosition()).sub(this.halfSize);
    }

    public Vector2f getMax() {
        return new Vector2f(this.rigidbody.getPosition()).add(this.halfSize);
    }

    public Vector2f[] getVertices() {
        Vector2f min = getMin();
        Vector2f max = getMax();

        Vector2f[] vertices = {
                new Vector2f(min.x, min.y), new Vector2f(min.x, max.y),
                new Vector2f(max.x, min.y), new Vector2f(max.x, max.y)
        };

        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2f vert : vertices) {
                // TODO Implement me
                // Rotates point(Vector2f) about centre (Vector2f) by rotation (float in degrees)
                //JMath.rotate(vert, this.rigidbody.getPosition(), this.rigidbody.getRotation());
            }
        }

        return vertices;
    }

    public RigidBody2D getRigidbody() {
        return rigidbody;
    }
}
