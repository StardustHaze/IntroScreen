//StardustHaze

package cloud.introscreen;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kunai on 1/2/17.
 */

public class IntroManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    //shared pref mode
    int PRIVATE_MODE =0;
    
    //shared preferences file name
    private static final String PREF_NAME = "br0ly-welcome";
    
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    
    public IntroManager(Context context)
    {
        this._context = context;
        pref=context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setIsFirstTimeLaunch(boolean isFirstTime)
    {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public boolean isFirstTimeLaunch()
    {

        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }


    public void setFirstTimeLaunch(boolean b) {
    }
}
