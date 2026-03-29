#version 150

in vec2 texCoord;
out vec4 fragColor;

void main() {
    bool flag1 = (true);
    bool flag2 = (true);

    vec4 color = !(flag1 || flag2) ? vec4(0.0, 0.0, 0.0, 0.0) : vec4(1.0, 1.0, 1.0, 1.0);
    fragColor = color;
}