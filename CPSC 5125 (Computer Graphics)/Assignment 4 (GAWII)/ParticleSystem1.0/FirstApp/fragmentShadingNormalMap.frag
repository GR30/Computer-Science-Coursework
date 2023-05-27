#version 150

#define pi 3.141592653589793238462643383279

out vec4  fColor;

in vec3 fN;
in vec3 fE;
in vec3 fL;
in vec2 fTexCoord;

uniform vec4 LightAmbient, LightDiffuse, LightSpecular;
uniform vec4 MaterialAmbient, MaterialDiffuse, MaterialSpecular;
uniform float Shininess;

uniform mat4 Model;

vec3 computeNormal(vec2 texCoord)
{
	return vec3((vec2(cos(5*2*pi*length(texCoord - 0.5))*(texCoord - 0.5)) + 0.5), 1.0);
}

void
main()
{
	vec3 N = (computeNormal(fTexCoord).yxz - 0.5) * 2.0;
	vec3 E = normalize(fE);
	vec3 L = normalize(fL);

	vec3 H = normalize(L + E);

	vec4 ambient = MaterialAmbient * LightAmbient;

	float Kd = max( dot( L, N ), 0.0 );
	vec4 diffuse = Kd * MaterialDiffuse * LightDiffuse;

	float Ks = pow( max( dot( N, H ), 0.0 ), Shininess );
	vec4 specular = Ks * MaterialSpecular * LightSpecular;
	if( dot( L, N ) < 0.0)
	{
		specular = vec4( 0.0, 0.0, 0.0, 1.0 );
	}

	fColor =  ambient + diffuse + specular;
	fColor.xyz = vec3(fTexCoord.xy, 1.0);
	fColor.a = 1.0;
}
