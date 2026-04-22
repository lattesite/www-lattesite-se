package site.utils;

import lattesite.css.HEXColor;
import lattesite.css.StyleBlock;

public class CSSUtil {

    public static final int BREAKPOINT_MOBILE = 800;

    public static final String PAGE_WIDTH = "1024px";



    /*
     * All colors
     */

    public static final HEXColor COLOR_0A0A0A_BLACK = new HEXColor("#0A0A0A");

    public static final HEXColor COLOR_111217_BLACK = new HEXColor("#111217");
    public static final String COLOR_111217_BLACK_ALPHA_50 = "rgba(17, 18, 23, 0.5)";

    public static final HEXColor COLOR_0075DA_BLUE_LINK = new HEXColor("#0075da");
    public static final HEXColor COLOR_FFFFFF_WHITE = new HEXColor("#FFFFFF");
    public static final HEXColor COLOR_313435_BLACK = new HEXColor("#313435");
    public static final HEXColor COLOR_999999_LIGHT_GRAY = new HEXColor("#999999");

    public static final HEXColor COLOR_30B1FF_BLUE_LIGHT = new HEXColor("#30B1FF");
    public static final String COLOR_30B1FF_BLUE_LIGHT_ALPHA_10 = "rgba(48, 177, 255, 0.1)";
    public static final HEXColor COLOR_007BC7_BLUE_DARK = new HEXColor("#007BC7");

    public static final HEXColor COLOR_E0E0E0_LIGHT_GRAY = new HEXColor("#E0E0E0");
    public static final HEXColor COLOR_888888_GRAY = new HEXColor("#888888");
    public static final HEXColor COLOR_AE640A_BROWN_POTATO = new HEXColor("#AE640A");
    public static final HEXColor COLOR_9147FF_PURPLE_LIGHT = new HEXColor("#9147FF");

    public static final String BOX_SHADOW = "rgba(149, 157, 165, 0.1) 0px 8px 24px";

    /*
     * Named colors
     */

    public static final HEXColor COLOR_PAGE_BACKGROUND = COLOR_FFFFFF_WHITE;
    public static final HEXColor COLOR_PAGE_TEXT = COLOR_313435_BLACK;

    /*
     * Animations
     */

    public static void addFadeUpAnimation(StyleBlock sb) {
        sb.setProperty("opacity", "0");
        sb.setProperty("animation", "fadeInUp 0.48s ease-out forwards");
    }

}
