package cc.simplicated.mira.Utilities.math;

import java.util.Objects;

public class Vec2i {
    public int x;
    public int y;

    public Vec2i() {
        this(0, 0);
    }

    public Vec2i(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vec2i(Vec2i other) {
        this(other.x, other.y);
    }

    public Vec2i set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2i set(Vec2i other) {
        return set(other.x, other.y);
    }

    public Vec2i add(Vec2i other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vec2i add(int x, int y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2i sub(Vec2i other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vec2i sub(int x, int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2i mul(int scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vec2i mul(Vec2i other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public Vec2i div(int scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public int dot(Vec2i other) {
        return this.x * other.x + this.y * other.y;
    }

    public int lengthSquared() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public int distanceSquared(Vec2i other) {
        int dx = other.x - this.x;
        int dy = other.y - this.y;
        return dx * dx + dy * dy;
    }

    public double distance(Vec2i other) {
        return Math.sqrt(distanceSquared(other));
    }

    public Vec2i negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    public Vec2i abs() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        return this;
    }

    public Vec2i copy() {
        return new Vec2i(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec2i)) return false;
        Vec2i vec2i = (Vec2i) o;
        return x == vec2i.x && y == vec2i.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vec2i(" + x + ", " + y + ")";
    }

    public static Vec2i add(Vec2i a, Vec2i b) {
        return new Vec2i(a.x + b.x, a.y + b.y);
    }

    public static Vec2i sub(Vec2i a, Vec2i b) {
        return new Vec2i(a.x - b.x, a.y - b.y);
    }

    public static Vec2i mul(Vec2i a, int scalar) {
        return new Vec2i(a.x * scalar, a.y * scalar);
    }

    public static int dot(Vec2i a, Vec2i b) {
        return a.x * b.x + a.y * b.y;
    }
}