varying vec3 color;
float ambient = 0.8;
void main(void){
	color       = gl_Color.rgb;//takes colors from java code and packs it in vec3 to pass to the fragment shader
    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}