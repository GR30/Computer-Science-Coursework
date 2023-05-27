#version 150
#define pi 3.141592653589793238462643383279

// per-fragment interpolated values from the vertex shader
in  vec3 normal;
in  vec3 LightDir;
in  vec3 CameraDir;
in  vec2 fTexCoord;

out vec4  fColor;

uniform vec4 LightAmbient, LightDiffuse, LightSpecular;
uniform vec4 MaterialAmbient, MaterialDiffuse, MaterialSpecular;
uniform float Shininess;
uniform float nRipples;
uniform float center;
uniform sampler2D texture;


vec4
ripples(vec2 coordinates)
{
	float s = coordinates.x - trunc(coordinates.x);
	float t = coordinates.y - trunc(coordinates.y);
	//return vec4(vec3(cos((5*2*pi*length(vec2(s,t) - vec2(0.5))) + 1.0)), 1.0);
	return vec4(vec3((cos((nRipples*2*pi*length(vec2(s,t) - vec2(center)))) + 1.0)/2), 1.0);
}

vec4
checker(vec2 coordinates)
{
	float s = coordinates.x - trunc(coordinates.x);
	float t = coordinates.y - trunc(coordinates.y);
	if(s < 0.5 && t < 0.5 || s > 0.5 && t > 0.5)
	{
		return vec4(1.0, 1.0, 1.0, 1.0);
	}
	return vec4(0.0, 0.0, 0.0, 1.0);
}

void
main()
{
	vec4 specular = vec4(0.0);
	float nxDir = max(0.0, dot(normalize(normal), LightDir));
	vec4 diffuse = LightDiffuse * MaterialDiffuse * texture2D(texture, fTexCoord) * nxDir;
	//diffuse = vec4(0.0);
	if(nxDir != 0.0)
	{
		vec3 halfVector = normalize(normalize(LightDir) + normalize(CameraDir));
		float nxHalf = max(0.0, dot(normal, halfVector));
		float specularPower = pow(nxHalf, Shininess);
		specular = LightSpecular * MaterialSpecular * specularPower * texture2D(texture, fTexCoord);
	}
	vec4 ambient = LightAmbient * MaterialAmbient *texture2D(texture, fTexCoord);
	fColor =  ambient + diffuse + specular;
}
