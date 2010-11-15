package midlet;

import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

final public class midlet extends MIDlet implements Runnable{
	public int sleep_ms=100;
	public String canvasClassStr="midlet.blix.blix";
	private Thread thread;
	private boolean on;
	long frameno;
	long t;
	long dt;
	long sleeptime;
	int fps;
	Random random=new Random(0);
	
	public midlet(){}

	public void startApp(){
		thread=new Thread(this);
		thread.start();
	}

	public void run(){
		canvas canvas;
		try{
			canvas=(canvas)Class.forName(canvasClassStr).newInstance();
		}catch(Throwable t){
			throw new Error(t.getMessage());
		}
		Display.getDisplay(this).setCurrent(canvas);
		canvas.midlet=this;
		canvas.init();
		on=true;
		long t1=System.currentTimeMillis();
		while(on){
			try{
				canvas.update();
				canvas.paint();
				frameno++;
				long t2=System.currentTimeMillis();
				dt=t2-t1;
				while(dt==0){
					Thread.sleep(1);
					t2=System.currentTimeMillis();
					dt=t2-t1;
				}
				t1=t2;
				t+=dt;
				fps=(int)(1000.0/dt);
				sleeptime=sleep_ms-dt;
				if(sleeptime<0)
					sleeptime=0;
				if(sleeptime>0)
					Thread.sleep(sleeptime);
			}catch(InterruptedException e){}
		}
	}

	protected void pauseApp(){}

	protected void destroyApp(boolean unconditional){
		on=false;
		thread.interrupt();
		try{
			thread.join();
		}catch(InterruptedException e){}
		thread=null;
		notifyDestroyed();
	}

	public long frameno(){
		return frameno;
	}
	public int fps(){
		return fps;
	}

	public int rand(int from,int to_excl){
		return (int)((to_excl-from)*random.nextDouble());
	}
}