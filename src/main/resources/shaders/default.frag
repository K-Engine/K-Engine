#version 330

in vec2 texCoord;
out vec4 FragColor;

uniform sampler2D textureIn;

void main()
{
    FragColor = texture(textureIn, texCoord);
}