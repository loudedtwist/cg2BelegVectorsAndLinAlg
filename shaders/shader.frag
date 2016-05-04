varying vec3 color;
void main(){
    gl_FragColor = vec4(color,1); // color -> vec3
    //gl_FragColor = vec4(0.5,1,1,1);
}