package com.example.viewswitch;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends ActionBarActivity {

	private ViewFlipper mViewFlipper;
	private GestureDetector mGestureDetector;

	int[] resources = { R.drawable.img1, R.drawable.img2, R.drawable.img3,
			R.drawable.img4, R.drawable.img5, R.drawable.img2 };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Get the ViewFlipper
		mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

		// Add all the images to the ViewFlipper
		for (int i = 0; i < resources.length; i++) {
			ImageView imageView = new ImageView(this);
			imageView.setImageResource(resources[i]);
			mViewFlipper.addView(imageView);
		}
		
		mViewFlipper.setAutoStart(true);
		mViewFlipper.setFlipInterval(2000);

		// Set in/out flipping animations
		mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
		mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

		CustomGestureDetector customGestureDetector = new CustomGestureDetector();
		mGestureDetector = new GestureDetector(this, customGestureDetector);
	}

	class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			// Swipe left (next)
			if (e1.getX() > e2.getX()) {
				mViewFlipper.showNext();
				
				mViewFlipper.setInAnimation(MainActivity.this, R.anim.left_in);
		        mViewFlipper.setOutAnimation(MainActivity.this, R.anim.left_out);
			}

			// Swipe right (previous)
			if (e1.getX() < e2.getX()) {
				mViewFlipper.showPrevious();
				
				mViewFlipper.setInAnimation(MainActivity.this, R.anim.right_in);
		        mViewFlipper.setOutAnimation(MainActivity.this, R.anim.right_out);
			}

			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGestureDetector.onTouchEvent(event);

		return super.onTouchEvent(event);
	}
	
}