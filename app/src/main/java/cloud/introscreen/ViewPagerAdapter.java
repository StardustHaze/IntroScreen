package cloud.introscreen;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by kunai on 1/3/17.
 */
public class ViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
