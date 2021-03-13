// Bezier Utils at https://github.com/Gregeg/Bezier-Curves-Processing

package frc.robot.util;

public interface BezierUnit {
    public Vector2D getPos(double t);

    public Vector2D getDeriv(double t);
}
