#version 150

in vec4 vPosition;
in vec3 vColor;
out vec3 Color;
uniform mat4 ModelView;
uniform mat4 Projection;

void
main()
{
    gl_Position = Projection * ModelView * vPosition;
	Color = vec3(1.0, 1.0, 0.0);
}
