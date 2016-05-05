varying vec4 color;
varying float scaleV;
uniform float Scale;
float ambient = 0.8;
void main(void){
    scaleV=Scale;
	//color       = gl_Color.rgb;//takes colors from java code and packs it in vec3 to pass to the fragment shader
    color = vec4(gl_Color.rgb,1)*(1.0-(gl_ModelViewProjectionMatrix * gl_Vertex).z*0.5);
    //{X,Y,Z,W}
    gl_Position = vec4((gl_ModelViewProjectionMatrix * gl_Vertex).xy,0,1);
}