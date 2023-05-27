/* sierpinski gasket using vertex buffer objects */
#include "SOIL.h"
#include "Angel.h"

const int NumPoints = 50000;
int animation = 1;
int limit = 0;
GLuint nPoints = 1000;
vec4 points[NumPoints];
vec3 normals[NumPoints];
vec3 tangents[NumPoints];
vec2 texCoords[NumPoints];
GLdouble theta = 30.0, speed = 1.0;
GLuint rate = 33;
GLdouble direction = 1.0;
GLfloat scale = 1.0f, zoom = 10.0f;
GLuint *programs;
//GLuint currentShader = 0;
GLfloat aspectRatio = 1.0f;
GLuint startCube = 0, startAxes = 0, startSquare = 0;
const GLuint nShaders = 4;


void
drawCube( void );
void
drawSphere( void );
void
drawSquare( void );

typedef Angel::vec4 point4;
typedef Angel::vec4 color4;

typedef struct Material {
	color4 ambient;
	color4 diffuse;
	color4 specular;
	float shininess;
};

typedef struct Transform {
	vec4 position;
	vec3 rotation;
	vec3 scale;
};

typedef struct Sphere
{
	int firstPoint;
	int numPoints;
	int pointsPerStrip;
	int numStrips;
	int mode;
};

Sphere theSphere;

void
applyMaterial(GLuint theShader, Material mat);

point4 light_position( 0.0, 0.0, 0.0, 1.0 );
// Initialize shader lighting parameters
color4 light_ambient( 0.1, 0.1, 0.1, 1.0 );
color4 light_diffuse( 1.0, 1.0, 1.0, 1.0 );
color4 light_specular( 1.0, 1.0, 1.0, 1.0 );

color4 material_ambient(0.0, 0.0, 0.0, 1.0 );
color4 material_diffuse( 0.0, 0.50980392, 0.50980392, 1.0 );
color4 material_specular( 0.50196078, 0.50196078, 0.50196078, 1.0 );
float  material_shininess = 0.25 * 128;

color4 emerald_ambient( 0.0215, 0.1745, 0.0215, 1.0 );
color4 emerald_diffuse( 0.07568, 0.61424, 0.07568, 1.0 );
color4 emerald_specular( 0.633, 0.727811, 0.633, 1.0 );
float  emerald_shininess = 0.6 * 128;

color4 ruby_ambient( 0.1745,	0.01175,	0.01175, 1.0 );
color4 ruby_diffuse( 0.61424,	0.04136,	0.04136, 1.0 );
color4 ruby_specular( 0.727811,	0.626959,	0.626959, 1.0 );
float  ruby_shininess = 0.6 * 128;

color4 cyan_rubber_ambient( 0.0,	0.05,	0.05, 1.0 );
color4 cyan_rubber_diffuse( 0.4,	0.5,	0.5, 1.0 );
color4 cyan_rubber_specular( 0.04,	0.7,	0.7	, 1.0 );
float  cyan_rubber_shininess = .078125 * 128;

color4 gold_ambient( 0.24725,	0.1995,	0.0745, 1.0 );
color4 gold_diffuse( 0.75164,	0.60648,	0.22648, 1.0 );
color4 gold_specular( 0.628281,	0.555802,	0.366065	, 1.0 );
float  gold_shininess = .4 * 128;

Material material, emerald, ruby, cyan_rubber, gold;

class Shape {
public:
	void (*drawGeometry)();
	Material mat;
	GLuint shader;
	Transform transform;

	Shape();
	void draw( void );
	void env(vec4 *eye, mat4 *view, mat4 *projection);
};

Shape::Shape()
{
	transform.position = vec4(0.0, 0.0, 0.0, 1.0);
	transform.rotation = vec3(0.0, 0.0, 0.0);
	transform.scale = vec3(1.0, 1.0, 1.0);
}

void Shape::draw()
{
	applyMaterial(shader, mat);
	mat4 translate = Translate(transform.position);
	mat4 rotations = RotateX(transform.rotation.x) * RotateY(transform.rotation.y) * RotateZ(transform.rotation.z);
	mat4 scales = Scale(transform.scale);
	glUniformMatrix4fv(glGetUniformLocation(shader, "Model"), 1, GL_TRUE, translate * rotations * scales);
	drawGeometry();
}

void Shape::env(vec4 *eye, mat4 *view, mat4 *projection)
{
	glUseProgram(shader);
	glUniform4fv( glGetUniformLocation(shader, "CameraPosition"), 1, *eye);
	glUniformMatrix4fv(glGetUniformLocation( shader, "View" ), 1, GL_TRUE, *view);
	glUniformMatrix4fv(glGetUniformLocation( shader, "Projection" ), 1, GL_TRUE, *projection);

	glUniform4fv( glGetUniformLocation(shader, "LightAmbient"), 1, light_ambient );
	glUniform4fv( glGetUniformLocation(shader, "LightDiffuse"), 1, light_diffuse );
	glUniform4fv( glGetUniformLocation(shader, "LightSpecular"), 1, light_specular );
	glUniform4fv( glGetUniformLocation(shader, "LightPosition"), 1, light_position );
}

Shape *myCube, *yourCube, *ourCube, *mySphere;

class Particle : public Shape {
public:
	vec3 velocity;
	vec3 rotationSpeed;
	vec3 scaleSpeed;
	Particle *next;

	Particle::Particle();
	void update( void );
	void append( Particle *aParticle);
};

Particle::Particle():Shape()
{
	velocity = vec3(0.0, 0.0, 0.0);
	rotationSpeed = vec3(0.0, 0.0, 0.0);
	scaleSpeed = vec3(0.0, 0.0, 0.0);
	drawGeometry = &drawCube;
	next = NULL;
}

void Particle::update( void )
{
	transform.position += velocity;
	transform.rotation += rotationSpeed;
	transform.scale += scaleSpeed;
}

void Particle::append( Particle *aParticle)
{
	if(next != NULL) {
		next->append(aParticle);
	}
	else {
		next = aParticle;
	}
}

Particle *lonelyParticle;

class ParticleSystem {
public:
	Particle *particles;
	Material mat;
	GLuint shader;
	GLuint textureID;

	ParticleSystem::ParticleSystem(int nParticles, Material newMat);
	void draw( void );
	void env(vec4 *eye, mat4 *view, mat4 *projection);
	void setMaterial(Material mat);
	void setShader(GLuint shader);
	void update( void );
};

ParticleSystem::ParticleSystem(int nParticles, Material newMat)
{
	particles = new Particle();
	particles->transform.position.x = (GLfloat)(rand() % 100)/100.0f - 0.5f;
	particles->transform.position.y = (GLfloat)(rand() % 100)/100.0f - 0.5f;
	particles->transform.position.z = (GLfloat)(rand() % 100)/100.0f - 0.5f;
	particles->velocity = vec3((GLfloat)(rand() % 10)/100.0f - 0.05f,(GLfloat)(rand() % 10)/100.0f - 0.05f,(GLfloat)(rand() % 10)/100.0f - 0.05f);
	particles->rotationSpeed = vec3((GLfloat)(rand() % 10),(GLfloat)(rand() % 10),(GLfloat)(rand() % 10));
	particles->transform.scale = vec3(0.1, 0.1, 0.1);
	for(int i = 1; i < nParticles; i++)
	{
		Particle *tempParticle = new Particle();
		tempParticle->transform.position.x = (GLfloat)(rand() % 100)/100.0f - 0.5f;
		tempParticle->transform.position.y = (GLfloat)(rand() % 100)/100.0f - 0.5f;
		tempParticle->transform.position.z = (GLfloat)(rand() % 100)/100.0f - 0.5f;
		tempParticle->velocity = vec3((GLfloat)(rand() % 10)/100.0f - 0.05f,(GLfloat)(rand() % 10)/100.0f - 0.05f,(GLfloat)(rand() % 10)/100.0f - 0.05f);
		tempParticle->rotationSpeed = vec3((GLfloat)(rand() % 10),(GLfloat)(rand() % 10),(GLfloat)(rand() % 10));
		tempParticle->transform.scale = vec3(0.1, 0.1, 0.1);
		particles->append(tempParticle);
	}
	setMaterial(newMat);
}

void ParticleSystem::draw( void )
{
	Particle *currentParticle = particles;

	glEnable(GL_TEXTURE_2D);
	glUniform1i(glGetUniformLocation(shader, "texture"), 0);
	glActiveTexture(GL_TEXTURE0);
	glBindTexture(GL_TEXTURE_2D, textureID);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST_MIPMAP_LINEAR);

	while(currentParticle != NULL)
	{
		currentParticle->draw();
		currentParticle = currentParticle->next;
	}
	glDisable(GL_TEXTURE_2D);
}

void ParticleSystem::env(vec4 *eye, mat4 *view, mat4 *projection)
{
	particles->env(eye, view, projection);
}

void ParticleSystem::setMaterial(Material mat)
{
	this->mat = mat;
	Particle *currentParticle = particles;
	while(currentParticle != NULL)
	{
		currentParticle->mat = mat;
		currentParticle = currentParticle->next;
	}
}

void ParticleSystem::setShader(GLuint shader)
{
	this->shader = shader;
	Particle *currentParticle = particles;
	while(currentParticle != NULL)
	{
		currentParticle->shader = shader;
		currentParticle = currentParticle->next;
	}
}

void ParticleSystem::update( void )
{
	Particle *currentParticle = particles;
	while(currentParticle != NULL)
	{
		currentParticle->update();
		currentParticle = currentParticle->next;
	}
}



ParticleSystem *onePS;

void
rotationMenu(int choice)
{
	switch(choice)
	{
	case 1:
		speed *= 1.1;
		break;
	case 2:
		speed *= 0.9;
		break;
	default:
		break;
	}
}

void
scaleMenu(int choice)
{
	switch(choice)
	{
	case 1:
		scale *= 1.1;
		break;
	case 2:
		scale *= 0.9;
		break;
	default:
		break;
	}
}

void
mMenu(int choice)
{
	switch(choice)
	{
	case 0:
		exit(0);
		break;
	default:
		break;
	}
}

//----------------------------------------------------------------------------
/*
	Computes the axes points
	thePoints references the array to store the points
	index references the variable to hold the global index
*/
void
computeAxes(vec4 *thePoints, int *index)
{
	thePoints[*index] = vec4(100.0, 0.0, 0.0, 1.0);
	thePoints[*index + 1] = vec4(-100.0, 0.0, 0.0, 1.0);
	thePoints[*index + 2] = vec4(0.0, 100.0, 0.0, 1.0);
	thePoints[*index + 3] = vec4(0.0, -100.0, 0.0, 1.0);
	thePoints[*index + 4] = vec4(0.0, 0.0, 100.0, 1.0);
	thePoints[*index + 5] = vec4(0.0, 0.0, -100.0, 1.0);
	*index += 6;
}

/*
	Computes the points that define a cube
	thePoints references the array to store the points
	theNormals references the array to store the normals
	index references the variable to hold the global index
*/
void
computeCube(vec4 *thePoints, vec3 *theNormals,  vec3 *theTangents, vec2 *theTexCoords, int *index)
{
	thePoints[*index] = vec4(0.5, -0.5, 0.5, 1.0);
	thePoints[*index + 1] = vec4(0.5, 0.5, 0.5, 1.0);
	thePoints[*index + 2] = vec4(-0.5, -0.5, 0.5, 1.0);
	thePoints[*index + 3] = vec4(-0.5, 0.5, 0.5, 1.0);

	theNormals[*index] = vec3(0.0, 0.0, 1.0);
	theNormals[*index + 1] = vec3(0.0, 0.0, 1.0);	
	theNormals[*index + 2] = vec3(0.0, 0.0, 1.0);	
	theNormals[*index + 3] = vec3(0.0, 0.0, 1.0);

	theTangents[*index] = vec3(0.0, 1.0, 0.0);
	theTangents[*index + 1] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 2] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 3] = vec3(0.0, 1.0, 0.0);

	theTexCoords[*index] = vec2(1.0, 0.0);
	theTexCoords[*index + 1] = vec2(1.0, 1.0);
	theTexCoords[*index + 2] = vec2(0.0, 0.0);
	theTexCoords[*index + 3] = vec2(0.0, 1.0);

	mat4 transform = Translate(0.0, 0.0, -0.5) * RotateY(180.0) * Translate(0.0, 0.0, -0.5);
	thePoints[*index + 4] = transform * thePoints[*index + 0];
	thePoints[*index + 5] = transform * thePoints[*index + 1];
	thePoints[*index + 6] = transform * thePoints[*index + 2];
	thePoints[*index + 7] = transform * thePoints[*index + 3];

	theNormals[*index + 4] = vec3(0.0, 0.0, -1.0);
	theNormals[*index + 5] = vec3(0.0, 0.0, -1.0);	
	theNormals[*index + 6] = vec3(0.0, 0.0, -1.0);	
	theNormals[*index + 7] = vec3(0.0, 0.0, -1.0);

	theTangents[*index + 4] = vec3(0.0, 1.0, 0.0);
	theTangents[*index + 5] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 6] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 7] = vec3(0.0, 1.0, 0.0);

	theTexCoords[*index + 4] = vec2(1.0, 0.0);
	theTexCoords[*index + 5] = vec2(1.0, 1.0);
	theTexCoords[*index + 6] = vec2(0.0, 0.0);
	theTexCoords[*index + 7] = vec2(0.0, 1.0);

	transform = Translate(0.0, 0.5, 0.0) * RotateX(270.0) * Translate(0.0, 0.0, -0.5);
	thePoints[*index + 8] = transform * thePoints[*index + 0];
	thePoints[*index + 9] = transform * thePoints[*index + 1];
	thePoints[*index + 10] = transform * thePoints[*index + 2];
	thePoints[*index + 11] = transform * thePoints[*index + 3];

	theNormals[*index + 8] = vec3(0.0, 1.0, 0.0);
	theNormals[*index + 9] = vec3(0.0, 1.0, 0.0);	
	theNormals[*index + 10] = vec3(0.0, 1.0, 0.0);	
	theNormals[*index + 11] = vec3(0.0, 1.0, 0.0);

	theTangents[*index + 8] = vec3(0.0, 0.0, 1.0);
	theTangents[*index + 9] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 10] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 11] = vec3(0.0, 0.0, 1.0);

	theTexCoords[*index + 8] = vec2(1.0, 0.0);
	theTexCoords[*index + 9] = vec2(1.0, 1.0);
	theTexCoords[*index + 10] = vec2(0.0, 0.0);
	theTexCoords[*index + 11] = vec2(0.0, 1.0);

	transform = Translate(0.0, -0.5, 0.0) * RotateX(90.0) * Translate(0.0, 0.0, -0.5);
	thePoints[*index + 12] = transform * thePoints[*index + 0];
	thePoints[*index + 13] = transform * thePoints[*index + 1];
	thePoints[*index + 14] = transform * thePoints[*index + 2];
	thePoints[*index + 15] = transform * thePoints[*index + 3];

	theNormals[*index + 12] = vec3(0.0, -1.0, 0.0);
	theNormals[*index + 13] = vec3(0.0, -1.0, 0.0);	
	theNormals[*index + 14] = vec3(0.0, -1.0, 0.0);	
	theNormals[*index + 15] = vec3(0.0, -1.0, 0.0);

	theTangents[*index + 12] = vec3(0.0, 0.0, 1.0);
	theTangents[*index + 13] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 14] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 15] = vec3(0.0, 0.0, 1.0);

	theTexCoords[*index + 12] = vec2(1.0, 0.0);
	theTexCoords[*index + 13] = vec2(1.0, 1.0);
	theTexCoords[*index + 14] = vec2(0.0, 0.0);
	theTexCoords[*index + 15] = vec2(0.0, 1.0);

	transform = Translate(-0.5, 0.0, 0.0) * RotateY(270.0) * Translate(0.0, 0.0, -0.5);
	thePoints[*index + 16] = transform * thePoints[*index + 0];
	thePoints[*index + 17] = transform * thePoints[*index + 1];
	thePoints[*index + 18] = transform * thePoints[*index + 2];
	thePoints[*index + 19] = transform * thePoints[*index + 3];

	theNormals[*index + 16] = vec3(-1.0, 0.0, 0.0);
	theNormals[*index + 17] = vec3(-1.0, 0.0, 0.0);	
	theNormals[*index + 18] = vec3(-1.0, 0.0, 0.0);	
	theNormals[*index + 19] = vec3(-1.0, 0.0, 0.0);

	theTangents[*index + 16] = vec3(0.0, 0.0, 1.0);
	theTangents[*index + 17] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 18] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 19] = vec3(0.0, 0.0, 1.0);

	theTexCoords[*index + 16] = vec2(1.0, 0.0);
	theTexCoords[*index + 17] = vec2(1.0, 1.0);
	theTexCoords[*index + 18] = vec2(0.0, 0.0);
	theTexCoords[*index + 19] = vec2(0.0, 1.0);

	transform = Translate(0.5, 0.0, 0.0) * RotateY(90.0) * Translate(0.0, 0.0, -0.5);
	thePoints[*index + 20] = transform * thePoints[*index + 0];
	thePoints[*index + 21] = transform * thePoints[*index + 1];
	thePoints[*index + 22] = transform * thePoints[*index + 2];
	thePoints[*index + 23] = transform * thePoints[*index + 3];

	theNormals[*index + 20] = vec3(1.0, 0.0, 0.0);
	theNormals[*index + 21] = vec3(1.0, 0.0, 0.0);	
	theNormals[*index + 22] = vec3(1.0, 0.0, 0.0);	
	theNormals[*index + 23] = vec3(1.0, 0.0, 0.0);

	theTangents[*index + 20] = vec3(0.0, 0.0, 1.0);
	theTangents[*index + 21] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 22] = vec3(0.0, 0.0, 1.0);	
	theTangents[*index + 23] = vec3(0.0, 0.0, 1.0);

	theTexCoords[*index + 20] = vec2(1.0, 0.0);
	theTexCoords[*index + 21] = vec2(1.0, 1.0);
	theTexCoords[*index + 22] = vec2(0.0, 0.0);
	theTexCoords[*index + 23] = vec2(0.0, 1.0);



	*index += 24;
}

/*
   Create a sphere centered at the origin, with radius r, and precision n
   Draw a point for zero radius spheres
   Use CCW facet ordering
   "method" is GL_QUAD_STRIP for quads, GL_TRIANGLE_STRIP for triangles
      (quads look nicer in wireframe mode)
   Partial spheres can be created using theta1->theta2, phi1->phi2
   in radians 0 < theta < 2pi, -pi/2 < phi < pi/2
*/
Sphere CreateSphere(vec4 *thePoints, vec3 *theNormals, vec3 *theTangents, vec2 *theTexCoords, int *index, double r, int n, int method, double theta1, double theta2, double phi1, double phi2)
{
	Sphere sphere;
   int i, j;
   vec3 e, e2;
   vec4 p, p2;
   vec4 tangent, tangent2;
   double jdivn, j1divn, idivn, dosdivn, unodivn=1/(double)n, ndiv2=(double)n/2, t1, t2, t3, cost1, cost2, cte1, cte3;
   cte3 = (theta2-theta1)/n;
   cte1 = (phi2-phi1)/ndiv2;
   dosdivn = 2*unodivn;
   /* Handle special cases */
   if (r < 0)
      r = -r;
   if (n < 0){
      n = -n;
      ndiv2 = -ndiv2;
   }
   if (n < 4 || r <= 0) {
	   thePoints[*index] = vec4(0.0, 0.0, 0.0, 1.0);
	   sphere.firstPoint = *index++;
	   sphere.mode = GL_POINTS;
	   sphere.numPoints = 1;
	   sphere.numStrips = 1;
	   sphere.pointsPerStrip = 1;
	   return sphere;
   }

   t2 = phi1;
   cost2 = cos(phi1);
   j1divn = 0;
   sphere.firstPoint = *index;
   sphere.mode = method;
   sphere.numStrips = ndiv2;
   for (j = 0; j < ndiv2; j++) {
      t1 = t2;
      t2 += cte1;
      t3 = theta1 - cte3;
      cost1 = cost2;
      cost2 = cos(t2);
      e.y = sin(t1);
      e2.y = sin(t2);
      p.y = r * e.y;
      p2.y = r * e2.y;
	  tangent.y = -cost1;
	  tangent2.y = -cost2;

      idivn = 0;
      jdivn = j1divn;
      j1divn += dosdivn;
      for (i = 0; i <= n; i++) {
         t3 += cte3;
         e.x = cost1 * cos(t3);
         e.z = cost1 * sin(t3);
         p.x = r * e.x;
         p.z = r * e.z;
		 theNormals[*index] = e;
		 p.w = 1.0;
		 tangent.x = sin(t1) * cos(t3);
		 tangent.z = sin(t1) * sin(t3);
		 theTangents[*index] = vec3(tangent.x, tangent.y, tangent.z);
		 thePoints[*index] = p;
		 theTexCoords[*index].x = 1.0 - idivn;
		 theTexCoords[*index].y = jdivn;
		 (*index)++;

         e2.x = cost2 * cos(t3);
         e2.z = cost2 * sin(t3);
         p2.x = r * e2.x;
         p2.z = r * e2.z;
		 theNormals[*index] = e2;
		 p2.w = 1.0;
		 tangent2.x = sin(t2) * cos(t3);
		 tangent2.z = sin(t2) * sin(t3);
		 theTangents[*index] = vec3(tangent2.x, tangent2.y, tangent2.z);
		 thePoints[*index] = p2;
		 theTexCoords[*index].x = 1.0 - idivn;
		 theTexCoords[*index].y = j1divn;
		 (*index)++;
         idivn += unodivn;
      }
   }
   sphere.numPoints = *index - sphere.firstPoint;
   sphere.pointsPerStrip = 2 * n + 2;
   return sphere;
}

/*
Computes the points that defines a square
	*thePoints references the array to store the points
	*theNormals references the array to store the normals
	*index references the variable to hold the global index
*/
void
computeSquare( vec4 *thePoints, vec3 *theNormals, vec3 *theTangents, vec2 *theTexCoords,  int *index )
{
	thePoints[*index] = vec4(0.5, -0.5, 0.0, 1.0);
	thePoints[*index + 1] = vec4(0.5, 0.5, 0.0, 1.0);
	thePoints[*index + 2] = vec4(-0.5, -0.5, 0.0, 1.0);
	thePoints[*index + 3] = vec4(-0.5, 0.5, 0.0, 1.0);

	theNormals[*index] = vec3(0.0, 0.0, 1.0);
	theNormals[*index + 1] = vec3(0.0, 0.0, 1.0);	
	theNormals[*index + 2] = vec3(0.0, 0.0, 1.0);	
	theNormals[*index + 3] = vec3(0.0, 0.0, 1.0);

	theTangents[*index] = vec3(0.0, 1.0, 0.0);
	theTangents[*index + 1] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 2] = vec3(0.0, 1.0, 0.0);	
	theTangents[*index + 3] = vec3(0.0, 1.0, 0.0);

	theTexCoords[*index] = vec2(1.0, 0.0);
	theTexCoords[*index + 1] = vec2(1.0, 1.0);
	theTexCoords[*index + 2] = vec2(0.0, 0.0);
	theTexCoords[*index + 3] = vec2(0.0, 1.0);

	*index += 4;
}


void
builMaterials( void )
{
	material.diffuse = material_diffuse;
	material.ambient = material_ambient;
	material.specular = material_specular;
	material.shininess = material_shininess;

	emerald.diffuse = emerald_diffuse;
	emerald.ambient = emerald_ambient;
	emerald.specular = emerald_specular;
	emerald.shininess = emerald_shininess;

	ruby.diffuse = ruby_diffuse;
	ruby.ambient = ruby_ambient;
	ruby.specular = ruby_specular;
	ruby.shininess = ruby_shininess;

	cyan_rubber.diffuse = cyan_rubber_diffuse;
	cyan_rubber.ambient = cyan_rubber_ambient;
	cyan_rubber.specular = cyan_rubber_specular;
	cyan_rubber.shininess = cyan_rubber_shininess;

	gold.diffuse = gold_diffuse;
	gold.ambient = gold_ambient;
	gold.specular = gold_specular;
	gold.shininess = gold_shininess;


}

void
init( void )
{

	//const char *programNames[] = {"simple", "vertexShading", "fragmentShading","fragmentShadingNormalMap", "shader00"};
	const char *programNames[] = {"simple", "vertexShading", "fragmentShading", "shader00","fragmentShadingNormalMap"};

	// Create array to hold IDs for shaders
	programs = (GLuint *) malloc(sizeof(GLuint) * nShaders);

	builMaterials();

	startAxes = limit;
	computeAxes(points, &limit);

	startCube = limit;
 	computeCube(points, normals, tangents, texCoords, &limit);

	theSphere = CreateSphere(points, normals, tangents, texCoords, &limit, 1.0, 36, GL_TRIANGLE_STRIP, 0.0, 2*M_PI, -M_PI/2, M_PI/2);

	startSquare = limit;
	computeSquare(points, normals, tangents, texCoords, &limit);

	onePS = new ParticleSystem(100, gold);

    // Create a vertex array object
    GLuint vao;
    glGenVertexArrays( 1, &vao );
    glBindVertexArray( vao );

    // Create and initialize a buffer object
    GLuint buffer;
    glGenBuffers( 1, &buffer );
    glBindBuffer( GL_ARRAY_BUFFER, buffer );
    //glBufferData( GL_ARRAY_BUFFER, sizeof(points), points, GL_STATIC_DRAW );
	glBufferData( GL_ARRAY_BUFFER, sizeof(points) + sizeof(normals) + sizeof(tangents) + sizeof(texCoords), NULL, GL_STATIC_DRAW );
	glBufferSubData( GL_ARRAY_BUFFER, 0, sizeof(points), points);
	glBufferSubData( GL_ARRAY_BUFFER, sizeof(points), sizeof(normals), normals);
	glBufferSubData( GL_ARRAY_BUFFER, sizeof(points) + sizeof(normals), sizeof(tangents), tangents);
	glBufferSubData( GL_ARRAY_BUFFER, sizeof(points) + sizeof(normals) + sizeof(tangents), sizeof(texCoords), texCoords);

    // Load shaders and use the resulting shader program
	/*
	programs[0] = InitShader( "simple.vert", "simple.frag" );
	programs[1] = InitShader( "vertexShading.vert", "vertexShading.frag" );
	programs[2] = InitShader( "fragmentShading.vert", "fragmentShading.frag" );
	programs[3] = InitShader( "fragmentShadingNormalMap.vert", "fragmentShadingNormalMap.frag" );
	*/
	//programs[4] = InitShader( "billboard.vert", "billboard.frag" );


    // Initialize the vertex position attribute from the vertex shader
	for(int i = 0; i < nShaders; i++) {
		char vertexBuf[256];
		char fragmentBuf[256];
		sprintf(vertexBuf, "%s%s", programNames[i], ".vert");
		sprintf(fragmentBuf, "%s%s", programNames[i], ".frag");
		programs[i] = InitShader( vertexBuf, fragmentBuf ); 
		GLuint loc = glGetAttribLocation( programs[i], "vPosition" );
		glEnableVertexAttribArray( loc );
		glVertexAttribPointer( loc, 4, GL_FLOAT, GL_FALSE, 0, BUFFER_OFFSET(0) );
		GLuint loc2 = glGetAttribLocation( programs[i], "vNormal" );
		glEnableVertexAttribArray( loc2 );
		glVertexAttribPointer( loc2, 3, GL_FLOAT, GL_FALSE, 0, BUFFER_OFFSET(sizeof(points)) );
		GLuint loc3 = glGetAttribLocation( programs[i], "vTangent" );
		glEnableVertexAttribArray( loc3 );
		glVertexAttribPointer( loc3, 3, GL_FLOAT, GL_FALSE, 0, BUFFER_OFFSET(sizeof(points) + sizeof(normals)) );
		GLuint loc4 = glGetAttribLocation( programs[i], "vTexCoord" );
		glEnableVertexAttribArray( loc4 );
		glVertexAttribPointer( loc4, 2, GL_FLOAT, GL_FALSE, 0, BUFFER_OFFSET(sizeof(points) + sizeof(normals) + sizeof(tangents)) );
	}

	onePS->setShader(programs[3]);
	GLuint particleTexture = SOIL_load_OGL_texture("57af91c9955a7.png", SOIL_LOAD_AUTO, SOIL_CREATE_NEW_ID, SOIL_FLAG_POWER_OF_TWO | SOIL_FLAG_MIPMAPS | SOIL_FLAG_INVERT_Y | SOIL_FLAG_MULTIPLY_ALPHA | SOIL_FLAG_DDS_LOAD_DIRECT);
	onePS->textureID = particleTexture;

	glEnable(GL_DEPTH_TEST);

	glPolygonMode(GL_FRONT, GL_FILL);
	glPolygonMode(GL_BACK, GL_FILL);

	glEnable(GL_BLEND);
	glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

	glClearColor( 0.5, 0.5, 0.5, 1.0 ); // gray background
}

//----------------------------------------------------------------------------

void
drawAxes( mat4 *view, mat4 *projection )
{
	glUseProgram(programs[0]);
	glUniformMatrix4fv(glGetUniformLocation( programs[0], "View" ), 1, GL_TRUE, *view);
	glUniformMatrix4fv(glGetUniformLocation( programs[0], "Projection" ), 1, GL_TRUE, *projection);
	glUniform3fv( glGetUniformLocation(programs[0], "uColor"), 1, vec3(1.0, 0.0, 0.0) );
	glDrawArrays( GL_LINES, startAxes, 2);
	glUniform3fv( glGetUniformLocation(programs[0], "uColor"), 1, vec3(0.0, 1.0, 0.0) );
	glDrawArrays( GL_LINES, startAxes + 2, 2);
	glUniform3fv( glGetUniformLocation(programs[0], "uColor"), 1, vec3(0.0, 0.0, 1.0) );
	glDrawArrays( GL_LINES, startAxes + 4, 2);
}

void
drawCube( void )
{
    glDrawArrays( GL_TRIANGLE_STRIP, startCube, 4 );    // draw the points
	glDrawArrays( GL_TRIANGLE_STRIP, startCube + 4, 4 );
	glDrawArrays( GL_TRIANGLE_STRIP, startCube + 8, 4 );
	glDrawArrays( GL_TRIANGLE_STRIP, startCube + 12, 4 );
	glDrawArrays( GL_TRIANGLE_STRIP, startCube + 16, 4 );
	glDrawArrays( GL_TRIANGLE_STRIP, startCube + 20, 4 );
}

void
drawSphere( void )
{
	int currentVertex = theSphere.firstPoint;
	for(int i = 0; i < theSphere.numStrips; i++)
	{
		glDrawArrays( theSphere.mode, currentVertex, theSphere.pointsPerStrip);
		currentVertex += theSphere.pointsPerStrip;
	}
}

void
drawSquare( void )
{
    glDrawArrays( GL_TRIANGLE_STRIP, startSquare, 4 );    // draw the points
}


void
display( void )
{
    glClear( GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);     // clear the window
	vec4 eye = /*RotateZ(theta) * RotateY(-theta) * */vec4(0.0, 0.0, zoom, 1.0);
	vec4 at = vec4(0.0, 0.0, 0.0, 1.0);
	vec4 up = vec4(0.0, 1.0, 0.0, 0.0);

	mat4 view = LookAt(eye, at, up);

	mat4 projection = Perspective(30.0, 1.0, 0.1, 1000);

	light_position = /*RotateX(theta) * */RotateY(theta) * vec4(0.0, 0.0, 10.0, 1.0);

	drawAxes(&view, &projection);

	onePS->env(&eye, &view, &projection);
	onePS->draw();

	glutSwapBuffers();
}

//----------------------------------------------------------------------------

void
keyboard( unsigned char key, int x, int y )
{
    switch ( key ) {
    case 033:
        exit( EXIT_SUCCESS );
        break;
	case '-':
		zoom *= 1.1f;
		break;
	case '+':
		zoom *= 0.9f;
		break;
	case 'a':
	case 'A':
		animation = animation == 0?1:0;
		break;

	case 's':
	case 'S':
		onePS->setShader(programs[0]);
		break;
	case 'n':
	case 'N':
		onePS->setShader(programs[4]);
		break;
	case 'f':
	case 'F':
		onePS->setShader(programs[3]);
		break;
	case 'v':
	case 'V':
		onePS->setShader(programs[2]);
		break;
	case '1':
		onePS->setMaterial(material);
		break;
	case '2':
		onePS->setMaterial(emerald);
		break;
	case '3':
		onePS->setMaterial(ruby);
		break;
	case '4':
		onePS->setMaterial(cyan_rubber);
		break;
	case '5':
		onePS->setMaterial(gold);
		break;
	case 'z':
	case 'Z':
		zoom += 1.0;
		break;
	case 'x':
	case 'X':
		zoom -= 1.0;
		break;
	default:
		break;
    }
}

void
reshape( int w, int h )
{
	aspectRatio = (GLfloat)w/(GLfloat)h;
	glViewport(0, 0, w, h);
}

//----------------------------------------------------------------------------

void
applyMaterial(GLuint theShader, Material mat)
{
	glUniform4fv( glGetUniformLocation(theShader, "MaterialAmbient"), 1, mat.ambient );
	glUniform4fv( glGetUniformLocation(theShader, "MaterialDiffuse"), 1, mat.diffuse );
	glUniform4fv( glGetUniformLocation(theShader, "MaterialSpecular"), 1, mat.specular );
	glUniform1f( glGetUniformLocation(theShader, "Shininess"), mat.shininess );

}

void
timerFunction(GLint value)
{
	if(animation) {
		theta += speed;
	
		if(theta > 360.0)
		{
			theta -= 360.0;
		}
		onePS->update();
	}

	/*
	myCube->transform.rotation.y = theta;
	yourCube->transform.rotation.x = theta;
	ourCube->transform.rotation.z = theta;

	lonelyParticle->update();
	*/

	glutTimerFunc(rate, timerFunction, 1);
	glutPostRedisplay();
}
//----------------------------------------------------------------------------

void
createMenus()
{
	GLint rotMenu = glutCreateMenu(rotationMenu);
	glutAddMenuEntry("Increase Speed", 1);
	glutAddMenuEntry("Decrease Speed", 2);

	GLint scalMenu = glutCreateMenu(scaleMenu);
	glutAddMenuEntry("Increase Scale", 1);
	glutAddMenuEntry("Decrease Scale", 2);

	GLint mainMenu = glutCreateMenu(mMenu);
	glutAddSubMenu("Rotation", rotMenu);
	glutAddSubMenu("Scale", scalMenu);
	glutAddMenuEntry("Quit", 0);

	glutAttachMenu(GLUT_RIGHT_BUTTON);
}

int
main( int argc, char **argv )
{
    glutInit( &argc, argv );
	glutInitDisplayMode(GLUT_RGBA | GLUT_DOUBLE | GLUT_DEPTH);
    glutInitWindowSize( 1024, 1024 );
	// Here you set the OpenGL version
    glutInitContextVersion( 3, 2 );
	//Use only one of the next two lines
    //glutInitContextProfile( GLUT_CORE_PROFILE );
	glutInitContextProfile( GLUT_COMPATIBILITY_PROFILE );
    glutCreateWindow( "Particle System" );

    // Uncomment if you are using GLEW
	glewInit(); 

    init();
	createMenus();

    glutDisplayFunc( display );
    glutKeyboardFunc( keyboard );
	glutReshapeFunc( reshape );
	glutTimerFunc(rate, timerFunction, 1);

    glutMainLoop();
    return 0;
}