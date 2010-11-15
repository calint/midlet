package midlet.blix;

import javax.microedition.lcdui.Graphics;
import midlet.canvas;

public class blix extends canvas{
	int[] bmp;
	int bmp_width,bmp_height;
	public void init(){
		midlet.sleep_ms=0;
		bmp_width=getWidth();
		bmp_height=getHeight();
		bmp=new int[bmp_width*bmp_height];
	}
	public void paint(){
		Graphics g=getGraphics();
		g.drawRGB(bmp,0,bmp_width,0,0,bmp_width,bmp_height,false);
		paint_stats();
		flushGraphics();
	}
	public void update(){
		for(int k=0;k<bmp.length;k++){
			bmp[k]=midlet.rand(0x000000,0xffffff);
		}
	}
}
