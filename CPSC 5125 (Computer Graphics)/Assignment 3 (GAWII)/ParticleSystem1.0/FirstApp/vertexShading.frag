#version 150

#define pi 3.141592653589793238462643383279

out vec4  fColor;

in vec4 Color;
in vec2 fTexCoord;

vec4 computeTexture(vec2 texCoord)
{
	return vec4(vec3(sin(5*2*pi*length(texCoord - vec2(0.5))) + 1.0), 1.0);
}

void
main()
{
    fColor = Color * computeTexture(fTexCoord);
}
