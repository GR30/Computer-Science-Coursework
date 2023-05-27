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
	mat4 bModel = Model;
	  // First colunm.
	bModel[0][0] = 1.0; 
	bModel[0][1] = 0.0; 
	bModel[0][2] = 0.0; 
 
    // Second colunm.
    bModel[1][0] = 0.0; 
    bModel[1][1] = 1.0; 
    bModel[1][2] = 0.0; 
 
  // Third colunm.
	bModel[2][0] = 0.0; 
	bModel[2][1] = 0.0; 
	bModel[2][2] = 1.0; 


	vec3 pos = ( bModel * vPosition ).xyz;

	fL = LightPosition.xyz - pos;
	fE = CameraPosition.xyz - pos;
	fN = (Model * vec4( vNormal, 0.0) ).xyz;

    gl_Position = Projection * View * bModel * vPosition;
}