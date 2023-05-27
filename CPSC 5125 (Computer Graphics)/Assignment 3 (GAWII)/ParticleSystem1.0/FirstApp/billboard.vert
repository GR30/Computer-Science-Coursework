#version 150

in vec4 vPosition;
in vec3 vNormal;
uniform mat4 Model;
uniform mat4 View;
uniform mat4 Projection;
uniform vec4 CameraPosition;
uniform vec4 LightPosition;

out vec3 fN;
out vec3 fE;
out vec3 fL;

void
main()
{
	vec3 pos = ( Model * vPosition ).xyz;

	fL = LightPosition.xyz - pos;
	fE = CameraPosition.xyz - pos;
	fN = (Model * vec4( vNormal, 0.0) ).xyz;

    //gl_Position = Projection * View * Model * vPosition;
	gl_Position = Projection * (Model*vPosition + vec4((View * Model)[3].xyz, 0.0));
}