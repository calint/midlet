package midlet.light;

import java.io.InputStream;
import java.util.Random;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import midlet.canvas;

public class light extends canvas{
	static class rgb{
		int r,g,b;

		public rgb(int r,int g,int b){
			super();
			this.r=r;
			this.g=g;
			this.b=b;
		}
	}

	private byte[] bytes=new byte[64*1024];
	private int bytesread;
	int dotx;
	int doty;
	Font font=Font.getFont(Font.FACE_MONOSPACE,Font.STYLE_PLAIN,Font.SIZE_SMALL);
	int ddoty=font.getHeight();
	StringBuffer sb=new StringBuffer(128);
	
	public light() throws Throwable{
		InputStream is=getClass().getResourceAsStream("file.txt");
		bytesread=is.read(bytes,0,bytes.length);
		is.close();
	}

	private Random random=new Random(0);
	public void paint(){
		Graphics g=getGraphics();
		int width=g.getClipWidth();
		int height=g.getClipHeight();
		int offsetx=g.getClipX();
		int offsety=g.getClipY();
		int color_bg=random.nextInt();
		g.setColor(color_bg);
		g.fillRect(offsetx,offsety,width,height);
		g.setColor(0x000000);
		g.setFont(font);
		doty=offsety;
		for(int k=0;k<bytesread;k++){
			byte b=bytes[k];
			if(b=='\r') continue;
			if(b=='\n'){
				String line=sb.toString();
				g.drawString(line,dotx,doty,0);
				System.out.println(doty+" "+sb);
				doty+=ddoty;
				sb.setLength(0);
				continue;
			}
			sb.append((char)b);
		}
		String line=sb.toString();
		g.drawString(line,dotx,doty,0);
		doty+=ddoty;
		sb.setLength(0);
		//
		// g.setColor(x & 0xff, y & 0xff, 0xff);
		// g.fillRect(offsetx, offsety, width, height);
		// if (fire) {
		// g.setColor(0, 255, 0);
		// g.fillRect(20, 20, 40, 40);
		// }
		// g.setColor(0xffffff);
		// g.setFont(Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN,
		// Font.SIZE_SMALL));
		// g.drawString("hello midlet " + t + "  at " + x + "," + y, x, y, 0);
		// g.drawString(sb.toString(), 1, 1, 0);
		flushGraphics();
	}
	public void update(){}
	public void init(){}
}
