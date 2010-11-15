package midlet;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

public abstract class canvas extends GameCanvas{
	protected midlet midlet;
	public canvas(){
		super(true);
		setFullScreenMode(true);
	}
	public abstract void init();
	public abstract void paint();
	public abstract void update();
	protected void paint_stats(){
		Graphics g=getGraphics();
		g.setColor(0xff0000);
		g.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_SMALL));
		g.setColor(0x000000);
		g.drawString("frame="+midlet.frameno+"   t="+midlet.t,0,0,0);
		g.drawString("dt="+midlet.dt+"   slp="+midlet.sleeptime+"   fps="+midlet.fps,0,11,0);
	}
}