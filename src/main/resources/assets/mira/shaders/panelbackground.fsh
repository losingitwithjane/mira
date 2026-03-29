#version 150

in vec2 texCoord;
out vec4 fragColor;

void main() {
    int spacing = 1;
    int size = 1;

    int cell = size + spacing + 1;

    int x = int(texCoord.x) / cell;
    int y = int(texCoord.y) / cell;

    int rx = int(texCoord.x) % cell;
    int ry = int(texCoord.y) % cell;

    bool onDot = (rx >= 1) && (rx < 1 + size) && (ry >= 1) && (ry < 1 + size);
    bool checker = ((x % 2) == 1) ^^ ((y % 2) == 1);

    if (onDot && checker) {
        fragColor = vec4(0x21/255.0, 0x21/255.0, 0x20/255.0, 1.0);
    } else {
        discard;
    }
}