varying vec4 color;
varying float scaleV;
void main(){
    gl_FragColor = vec4(color.xy,scaleV,1); // color -> vec3
    //gl_FragColor = vec4(0.5,1,1,1);
}