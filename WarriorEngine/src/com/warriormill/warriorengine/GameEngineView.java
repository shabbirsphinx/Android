package com.warriormill.warriorengine;

import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.warriormill.warriorengine.drawable.SpriteTile;

public class GameEngineView extends View {
	SpriteTile leftBoxer;
	SpriteTile rightBoxer;
	Context context;
	GameLoop gameloop;

	private class GameLoop extends Thread {
		private volatile boolean running = true;

		public void run() {
			while (running) {
				try {
					TimeUnit.MILLISECONDS.sleep(1);
					postInvalidate();
					pause();
				} catch (InterruptedException ex) {
					running = false;
				}
			}

		}

		public void pause() {
			running = false;
		}

		public void start() {
			running = true;
			run();
		}

		public void safeStop() {
			running = false;
			interrupt();
		}

	}

	public void unload() {
		gameloop.safeStop();

	}

	public GameEngineView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);

	}

	public GameEngineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public GameEngineView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		leftBoxer = new SpriteTile(R.drawable.buster, R.xml.leftboxer, context);
		rightBoxer = new SpriteTile(R.drawable.buster, R.xml.rightboxer,
				context);

		setBackgroundColor(Color.parseColor("#6D498A"));
		gameloop = new GameLoop();
		gameloop.run();
		this.context = context;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("Width " + widthMeasureSpec);
		WindowManager mWinMgr = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		int displayWidth = mWinMgr.getDefaultDisplay().getWidth();
		int displayHeight = mWinMgr.getDefaultDisplay().getHeight();

		setMeasuredDimension(displayWidth, displayHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// super.onDraw(canvas);
		leftBoxer.setXpos(30);
		leftBoxer.setYpos(30);
		rightBoxer.setXpos(200);
		rightBoxer.setYpos(200);

		leftBoxer.draw(canvas);
		rightBoxer.draw(canvas);
		gameloop.start();
	}
}
