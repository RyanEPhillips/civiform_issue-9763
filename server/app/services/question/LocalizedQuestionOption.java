package services.question;

import com.google.auto.value.AutoValue;
import java.util.Locale;
import java.util.Optional;
import services.MessageKey;
import views.components.TextFormatter;

/**
 * Represents a single option in a {@link services.question.types.MultiOptionQuestionDefinition},
 * localized to a specific locale.
 */
@AutoValue
public abstract class LocalizedQuestionOption {

  /** Create a LocalizedQuestionOption. */
  public static LocalizedQuestionOption create(
      long id,
      long order,
      String adminName,
      String optionText,
      Optional<Boolean> displayInAnswerOptions,
      Locale locale) {
    return new AutoValue_LocalizedQuestionOption(
        id, order, adminName, optionText, displayInAnswerOptions, locale);
  }

  /** The id for this option. */
  public abstract long id();

  /** The order of the option. */
  public abstract long order();

  /** The immutable identifier for this option, used to reference it in the API and predicates. */
  public abstract String adminName();

  /** The text strings to display to the user. */
  public abstract String optionText();

  /** Whether to display the answer option to the applicant. */
  public abstract Optional<Boolean> displayInAnswerOptions();

  /** Sanitized HTML for the option that processes Markdown. */
  public String formattedOptionText(String ariaLabelForNewTabs) {
    return TextFormatter.formatTextToSanitizedHTML(optionText(), false, false, ariaLabelForNewTabs);
  }

  /** Returns the message key for yes/no question options. Only applicable to yes/no questions. */
  public String getYesNoOptionMessageKey() {
    return switch (adminName()) {
      case "yes" -> MessageKey.OPTION_YES.getKeyName();
      case "no" -> MessageKey.OPTION_NO.getKeyName();
      case "not-sure" -> MessageKey.OPTION_NOT_SURE.getKeyName();
      case "maybe" -> MessageKey.OPTION_MAYBE.getKeyName();
      default -> "";
    };
  }

  /** The locale this option is localized to. */
  public abstract Locale locale();
}
