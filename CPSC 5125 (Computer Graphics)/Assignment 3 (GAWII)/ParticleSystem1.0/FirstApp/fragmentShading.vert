#version 150

in vec4 vPosition;
in vec3 vNormal;
in vec3 vTangent;
in vec2 vTexCoord;

uniform mat4 Model;
uniform mat4 View;
uniform mat4 Projection;
uniform vec4 CameraPosition;
uniform vec4 LightPosition;

out vec3 fN;
out vec3 fE;
out vec3 fL;
out vec2 fTexCoord;

void
main()
{

	vec3 pos = ( Model * vPosition ).xyz;

	fL = LightPosition.xyz - pos;
	fE = CameraPosition.xyz - pos;
	fN = (Model * vec4( vNormal, 0.0) ).xyz;

	fTexCoord = vTexCoord;

    gl_Position = Projection * View * Model * vPosition;
}
