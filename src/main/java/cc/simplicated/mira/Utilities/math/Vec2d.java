package cc.simplicated.mira.Utilities.math;

import java.util.Objects;

public class Vec2d {
    public double x;
    public double y;

    public Vec2d() {
        this(0, 0);
    }

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d(Vec2d other) {
        this(other.x, other.y);
    }

    public Vec2d set(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public Vec2d set(Vec2d other) {
        return set(other.x, other.y);
    }

    public Vec2d add(Vec2d other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vec2d add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vec2d sub(Vec2d other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    public Vec2d sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vec2d mul(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vec2d mul(Vec2d other) {
        this.x *= other.x;
        this.y *= other.y;
        return this;
    }

    public Vec2d div(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
        return this;
    }

    public double dot(Vec2d other) {
        return this.x * other.x + this.y * other.y;
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double distanceSquared(Vec2d other) {
        double dx = other.x - this.x;
        double dy = other.y - this.y;
        return dx * dx + dy * dy;
    }

    public double distance(Vec2d other) {
        return Math.sqrt(distanceSquared(other));
    }

    public Vec2d negate() {
        this.x = -this.x;
        this.y = -this.y;
        return this;
    }

    public Vec2d abs() {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
        return this;
    }

    public Vec2d copy() {
        return new Vec2d(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vec2d)) return false;
        Vec2d Vec2d = (Vec2d) o;
        return x == Vec2d.x && y == Vec2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Vec2d(" + x + ", " + y + ")";
    }

    public static Vec2d add(Vec2d a, Vec2d b) {
        return new Vec2d(a.x + b.x, a.y + b.y);
    }

    public static Vec2d sub(Vec2d a, Vec2d b) {
        return new Vec2d(a.x - b.x, a.y - b.y);
    }

    public static Vec2d mul(Vec2d a, double scalar) {
        return new Vec2d(a.x * scalar, a.y * scalar);
    }

    public static double dot(Vec2d a, Vec2d b) {
        return a.x * b.x + a.y * b.y;
    }
}