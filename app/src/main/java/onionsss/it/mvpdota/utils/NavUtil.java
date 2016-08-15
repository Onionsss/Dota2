package onionsss.it.mvpdota.utils;

import android.content.Context;

import onionsss.it.mvpdota.R;


/**
 * Created by Xuf on 2016/4/4.
 */
public class NavUtil {
    private static final String STRATEGY_ALL = "all";
    private static final String STRATEGY_NEWER = "newer";
    private static final String STRATEGY_STEP = "step";
    private static final String STRATEGY_SKILL = "skill";

    private static final String VIDEO_ALL = "all";
    private static final String VIDEO_BEGINNER = "beginner";
    private static final String VIDEO_QUWEI = "quwei";
    private static final String VIDEO_JIESHUO = "jieshuo";
    private static final String VIDEO_CELEBRITY = "celebrity";
    private static final String VIDEO_BISAI = "bisai";
    private static final String VIDEO_ADVANCED = "advanced";

    public static final int categoryList[] = {R.string.news, R.string.video, R.string.strategy, R.string.update};

//    public static final String fragmentList[] = {NewsFragment.class.getName(), VideoContainerFragment.class.getName(),
//            StrategyContainerFragment.class.getName(), UpdateFragment.class.getName()};

    public static final String strategyTypeList[] = {STRATEGY_ALL, STRATEGY_NEWER, STRATEGY_STEP, STRATEGY_SKILL};

    public static final String videoTypeList[] = {VIDEO_ALL, VIDEO_JIESHUO, VIDEO_BISAI, VIDEO_CELEBRITY, VIDEO_QUWEI, VIDEO_BEGINNER, VIDEO_ADVANCED};

    private static String videoTypeListChinese[];
    private static String strategyTypeListChinese[];

    public static void init(Context appContext) {
        videoTypeListChinese = appContext.getResources().getStringArray(R.array.video_type);
        strategyTypeListChinese = appContext.getResources().getStringArray(R.array.strategy_type);
    }

    public static String getVideoTypeChinese(int position) {
        return videoTypeListChinese[position];
    }

    public static String getStrategyTypeChinese(int position) {
        return strategyTypeListChinese[position];
    }
}
