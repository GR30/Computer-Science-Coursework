#version 150

in vec4 vPosition;
out vec3 Color;
uniform vec3 uColor;
uniform mat4 View;
uniform mat4 Projection;

void
main()
{
    gl_Position = Projection * View * vPosition;
	Color = uColor;
}
