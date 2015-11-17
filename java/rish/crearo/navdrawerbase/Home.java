package rish.crearo.navdrawerbase;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home extends AppCompatActivity {

    @Bind(R.id.home_image_layout)
    FrameLayout currentImageLayout;

    @Bind(R.id.home_image_image)
    ImageView currentImage;

    @Bind(R.id.home_image_popup)
    ImageView currentImagePopup;

    float x, y, dx, dy;

    FrameLayout.LayoutParams params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);

        Display display = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);

        currentImagePopup.setAlpha(0);
        final int centerY = size.y / 2 - (currentImageLayout.getHeight() / 2);
        final int centerX = size.x / 2 - (currentImageLayout.getWidth() / 2);


        params = (FrameLayout.LayoutParams) currentImageLayout.getLayoutParams();

        params.topMargin = centerY;
        params.leftMargin = centerX;
        currentImageLayout.setLayoutParams(params);

        currentImageLayout.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(final View view, MotionEvent event) {

                        switch (event.getAction()) {
                            case MotionEvent.ACTION_MOVE:
                                params.topMargin = (int) event.getRawY() - (view.getHeight() / 2);
                                params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
                                view.setLayoutParams(params);

                                int alpha = (int) (250 * (Math.abs(size.x / 2 - event.getRawX()) / (size.x / 3)));
                                alpha = (alpha <= 255) ? alpha : 255;
                                currentImagePopup.setAlpha(alpha);
                                System.out.println(alpha);

                                if (event.getRawX() < size.x / 4 || event.getRawX() > 3 * size.x / 4)
                                    currentImagePopup.setImageResource(R.drawable.fab_background);
                                else
                                    currentImagePopup.setImageResource(R.drawable.fab_background);

//                                Matrix matrix = new Matrix();
//                                matrix.postRotate((event.getRawX() - size.x / 2) / 10, currentImage.getWidth() / 2, currentImage.getHeight() / 2);
//                                currentImage.setImageMatrix(matrix);

                                break;

                            case MotionEvent.ACTION_UP:

                                currentImagePopup.setAlpha(0);

                                if (event.getRawX() < size.x / 4 || event.getRawX() > 3 * size.x / 4) {
                                    params.topMargin = (int) event.getRawY() - (view.getHeight() / 2);
                                    params.leftMargin = (int) event.getRawX() - (view.getWidth() / 2);
                                    view.setLayoutParams(params);
                                } else {
                                    // bring back to the center with animation

                                    int fromY = ((FrameLayout.LayoutParams) view.getLayoutParams()).topMargin;
                                    int fromX = ((FrameLayout.LayoutParams) view.getLayoutParams()).leftMargin;

                                    final int toY = size.y / 2 - (view.getHeight() / 2);
                                    final int toX = size.x / 2 - (view.getWidth() / 2);

                                    TranslateAnimation animation = new TranslateAnimation(0, toX - fromX, 0, toY - fromY);
                                    animation.setDuration(400);
                                    animation.setFillAfter(true);
                                    animation.setInterpolator(new AnticipateOvershootInterpolator());

                                    animation.setAnimationListener(new Animation.AnimationListener() {
                                        @Override
                                        public void onAnimationStart(Animation animation) {

                                        }

                                        @Override
                                        public void onAnimationEnd(Animation animation) {
                                            params.topMargin = toY;
                                            params.leftMargin = toX;
                                            view.setLayoutParams(params);
                                        }

                                        @Override
                                        public void onAnimationRepeat(Animation animation) {

                                        }
                                    });

//                                    currentImageLayout.setAnimation(animation);
//                                    animation.start();

                                    params.topMargin = toY;
                                    params.leftMargin = toX;
                                    view.setLayoutParams(params);
                                }
                                break;

                            case MotionEvent.ACTION_DOWN:
                                view.setLayoutParams(params);
                                break;
                        }

                        return true;
                    }
                });
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
