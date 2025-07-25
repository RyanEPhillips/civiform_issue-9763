package views.style;

import views.components.ButtonStyles;

/** Styles for admin pages. */
public final class AdminStyles {

  public static final String LINK_SELECTED =
      StyleUtils.joinStyles("m-2", "border-blue-400", "border-b-2");

  public static final String LINK_NOT_SELECTED = StyleUtils.joinStyles("m-2");

  public static final String LANGUAGE_LINK_SELECTED =
      StyleUtils.joinStyles(ReferenceClasses.ADMIN_LANGUAGE_LINK, LINK_SELECTED);

  public static final String LANGUAGE_LINK_NOT_SELECTED =
      StyleUtils.joinStyles(ReferenceClasses.ADMIN_LANGUAGE_LINK, LINK_NOT_SELECTED);

  public static final String BODY_GRADIENT_STYLE =
      StyleUtils.joinStyles("bg-gradient-to-r", "from-gray-100", "via-white", "to-gray-100");

  public static final String MOVE_BLOCK_BUTTON =
      StyleUtils.joinStyles(
          "bg-transparent",
          "p-0",
          "w-6",
          "text-center",
          "text-gray-500",
          StyleUtils.hover("bg-gray-200", "text-gray-900"));

  public static final String DELETE_ICON_BUTTON =
      StyleUtils.joinStyles(
          "bg-transparent",
          "p-0",
          "w-6",
          "text-center",
          "text-blue-900",
          StyleUtils.hover("bg-gray-200", "text-gray-900"));

  public static final String BODY =
      StyleUtils.joinStyles(BODY_GRADIENT_STYLE, "box-border", "flex", "min-h-screen", "mt-5");

  public static final String MAIN_CENTERED =
      StyleUtils.joinStyles("px-2", "max-w-screen-2xl", "mx-auto");

  public static final String MAIN_FULL = StyleUtils.joinStyles("flex", "flex-row");

  public static final String MAIN =
      StyleUtils.joinStyles(
          "bg-white",
          "border",
          "border-gray-200",
          "mt-12",
          "shadow-lg",
          "w-screen",
          "admin-responsive-main-padding");

  public static final String HEADER_BUTTON_STYLES =
      StyleUtils.joinStyles(ButtonStyles.OUTLINED_WHITE_WITH_ICON, "my-5", "mr-2");

  /**
   * For use with section headers, such as on the questions list view for universal vs. other
   * questions
   */
  public static final String SEMIBOLD_HEADER = StyleUtils.joinStyles("mt-8", "font-semibold");
}
