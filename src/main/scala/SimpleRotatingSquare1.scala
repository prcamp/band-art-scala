//https://www.openprocessing.org/sketch/225008
class SimpleRotatingSquare1 {

}
//
//void setup() {
//  size(300, 300);
//}
//int w =250, N=13, r=5;
//float t=0, k=0;
//void draw() {
//  background(#FF0533);
//  float dw = 250/N;
//  translate(width/2, height/2);
//  noStroke();
//  rectMode(CENTER);
//  for (int i=0; i<N; i++) {
//    rotate(radians(t*(N-i)));
//    if (i%2==0)fill(-1);
//    else fill(#FF0533);
//    rect(0, 0, w-i*dw, w-i*dw, r*(N-i), r*(N-i), r*(N-i), r*(N-i) );
//  }
//  t = sin(radians(k));
//  k++;
//}