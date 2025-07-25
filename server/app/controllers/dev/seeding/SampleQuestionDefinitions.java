package controllers.dev.seeding;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import play.i18n.Lang;
import services.LocalizedStrings;
import services.question.PrimaryApplicantInfoTag;
import services.question.QuestionOption;
import services.question.types.AddressQuestionDefinition;
import services.question.types.CurrencyQuestionDefinition;
import services.question.types.DateQuestionDefinition;
import services.question.types.EmailQuestionDefinition;
import services.question.types.EnumeratorQuestionDefinition;
import services.question.types.FileUploadQuestionDefinition;
import services.question.types.IdQuestionDefinition;
import services.question.types.MapQuestionDefinition;
import services.question.types.MultiOptionQuestionDefinition;
import services.question.types.MultiOptionQuestionDefinition.MultiOptionQuestionType;
import services.question.types.NameQuestionDefinition;
import services.question.types.NullQuestionDefinition;
import services.question.types.NumberQuestionDefinition;
import services.question.types.PhoneQuestionDefinition;
import services.question.types.QuestionDefinition;
import services.question.types.QuestionDefinitionConfig;
import services.question.types.QuestionType;
import services.question.types.StaticContentQuestionDefinition;
import services.question.types.TextQuestionDefinition;

public final class SampleQuestionDefinitions {

  @VisibleForTesting
  public static final AddressQuestionDefinition ADDRESS_QUESTION_DEFINITION =
      new AddressQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Address Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "What is your address?",
                          Lang.forCode("ar").toLocale(),
                          "ما هو عنوانك؟?")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  private static final QuestionDefinitionConfig CHECKBOX_QUESTION_DEFINITION_CONFIG =
      QuestionDefinitionConfig.builder()
          .setName("Sample Checkbox Question")
          .setDescription("description")
          .setQuestionText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "Which of the following kitchen instruments do you own?",
                      Lang.forCode("ar").toLocale(),
                      "أي من أدوات المطبخ التالية تملكها؟")))
          .setQuestionHelpText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "help text",
                      Lang.forCode("ar").toLocale(),
                      "نص المساعدة")))
          .build();

  private static final ImmutableList<QuestionOption> CHECKBOX_QUESTION_OPTIONS =
      ImmutableList.of(
          QuestionOption.create(
              1L,
              1L,
              "toaster",
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "Toaster",
                      Lang.forCode("ar").toLocale(),
                      "محمصة الخبز"))),
          QuestionOption.create(
              2L,
              2L,
              "pepper_grinder",
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "Pepper Grinder",
                      Lang.forCode("ar").toLocale(),
                      "مطحنة الفلفل"))),
          QuestionOption.create(
              3L,
              3L,
              "garlic_press",
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "Garlic Press",
                      Lang.forCode("ar").toLocale(),
                      "عصارة الثوم"))));

  @VisibleForTesting
  public static final MultiOptionQuestionDefinition CHECKBOX_QUESTION_DEFINITION =
      new MultiOptionQuestionDefinition(
          CHECKBOX_QUESTION_DEFINITION_CONFIG,
          CHECKBOX_QUESTION_OPTIONS,
          MultiOptionQuestionType.CHECKBOX);

  @VisibleForTesting
  public static final CurrencyQuestionDefinition CURRENCY_QUESTION_DEFINITION =
      new CurrencyQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Currency Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "How much should a scoop of ice cream cost?",
                          Lang.forCode("ar").toLocale(),
                          "كم ينبغي أن يكون سعر مغرفة الآيس كريم؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  private static final QuestionDefinitionConfig DROPDOWN_QUESTION_CONFIG =
      QuestionDefinitionConfig.builder()
          .setName("Sample Dropdown Question")
          .setDescription("select your favorite ice cream flavor")
          .setQuestionText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "Select your favorite ice cream flavor from the following",
                      Lang.forCode("ar").toLocale(),
                      "اختر نكهة الآيس كريم المفضلة لديك من التالي")))
          .setQuestionHelpText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "this is sample help text",
                      Lang.forCode("ar").toLocale(),
                      "هذه هي عينة مساعدة النص")))
          .build();
  private static final ImmutableList<QuestionOption> DROPDOWN_QUESTION_OPTIONS =
      ImmutableList.of(
          QuestionOption.create(
              1L, 1L, "chocolate", LocalizedStrings.withDefaultValue("Chocolate")),
          QuestionOption.create(
              2L, 2L, "strawberry", LocalizedStrings.withDefaultValue("Strawberry")),
          QuestionOption.create(3L, 3L, "vanilla", LocalizedStrings.withDefaultValue("Vanilla")),
          QuestionOption.create(4L, 4L, "coffee", LocalizedStrings.withDefaultValue("Coffee")));

  @VisibleForTesting
  public static final MultiOptionQuestionDefinition DROPDOWN_QUESTION_DEFINITION =
      new MultiOptionQuestionDefinition(
          DROPDOWN_QUESTION_CONFIG, DROPDOWN_QUESTION_OPTIONS, MultiOptionQuestionType.DROPDOWN);

  @VisibleForTesting
  public static final EmailQuestionDefinition EMAIL_QUESTION_DEFINITION =
      new EmailQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Email Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "What is your email?",
                          Lang.forCode("ar").toLocale(),
                          "ما هو بريدك الالكتروني؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .setUniversal(true)
              .setPrimaryApplicantInfoTags(ImmutableSet.of(PrimaryApplicantInfoTag.APPLICANT_EMAIL))
              .build());

  @VisibleForTesting
  public static final EnumeratorQuestionDefinition ENUMERATOR_QUESTION_DEFINITION =
      new EnumeratorQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Enumerator Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.withDefaultValue("List all members of your household."))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build(),
          LocalizedStrings.of(
              ImmutableMap.of(
                  Lang.forCode("en-US").toLocale(),
                  "household member",
                  Lang.forCode("ar").toLocale(),
                  "عضو في الأسرة")));

  @VisibleForTesting
  public static final FileUploadQuestionDefinition FILE_UPLOAD_QUESTION_DEFINITION =
      new FileUploadQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample File Upload Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "Upload anything from your computer",
                          Lang.forCode("ar").toLocale(),
                          "تحميل أي شيء من جهاز الكمبيوتر الخاص بك")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  @VisibleForTesting
  public static final IdQuestionDefinition ID_QUESTION_DEFINITION =
      new IdQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample ID Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "What is your driver's license ID?",
                          Lang.forCode("ar").toLocale(),
                          "ما هو رقم رخصة القيادة الخاصة بك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  @VisibleForTesting
  public static final MapQuestionDefinition MAP_QUESTION_DEFINITION =
      new MapQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Map Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "Select locations",
                          Lang.forCode("ar").toLocale(),
                          "حدد المواقع")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  @VisibleForTesting
  public static final NameQuestionDefinition NAME_QUESTION_DEFINITION =
      new NameQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Name Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "What is your name?",
                          Lang.forCode("ar").toLocale(),
                          "ما اسمك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .setUniversal(true)
              .setPrimaryApplicantInfoTags(ImmutableSet.of(PrimaryApplicantInfoTag.APPLICANT_NAME))
              .build());

  @VisibleForTesting
  public static final NumberQuestionDefinition NUMBER_QUESTION_DEFINITION =
      new NumberQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Number Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "How many pets do you have?",
                          Lang.forCode("ar").toLocale(),
                          "كم عدد الحيوانات الأليفة لديك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  private static final QuestionDefinitionConfig RADIO_BUTTON_QUESTION_CONFIG =
      QuestionDefinitionConfig.builder()
          .setName("Sample Radio Button Question")
          .setDescription("favorite season in the year")
          .setQuestionText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "What is your favorite season?",
                      Lang.forCode("ar").toLocale(),
                      "ما هو موسمك المفضل؟")))
          .setQuestionHelpText(
              LocalizedStrings.of(
                  ImmutableMap.of(
                      Lang.forCode("en-US").toLocale(),
                      "this is sample help text",
                      Lang.forCode("ar").toLocale(),
                      "هذه هي عينة مساعدة النص")))
          .build();

  private static final ImmutableList<QuestionOption> RADIO_BUTTON_QUESTION_OPTIONS =
      ImmutableList.of(
          QuestionOption.create(
              1L, 1L, "winter", LocalizedStrings.withDefaultValue("Winter (will hide next block)")),
          QuestionOption.create(2L, 2L, "spring", LocalizedStrings.withDefaultValue("Spring")),
          QuestionOption.create(3L, 3L, "summer", LocalizedStrings.withDefaultValue("Summer")),
          QuestionOption.create(
              4L, 4L, "fall", LocalizedStrings.withDefaultValue("Fall (will hide next block)")));

  @VisibleForTesting
  public static final MultiOptionQuestionDefinition RADIO_BUTTON_QUESTION_DEFINITION =
      new MultiOptionQuestionDefinition(
          RADIO_BUTTON_QUESTION_CONFIG,
          RADIO_BUTTON_QUESTION_OPTIONS,
          MultiOptionQuestionType.RADIO_BUTTON);

  @VisibleForTesting
  public static final StaticContentQuestionDefinition STATIC_CONTENT_QUESTION_DEFINITION =
      new StaticContentQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Static Content Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          """
                          Hi I'm a block of static text.
                           * Welcome to this __test program__.
                           * It contains one of every question type.

                          ## What are the eligibility requirements?
                          Please go [here](https://www.example.com) for more information""",
                          Lang.forCode("ar").toLocale(),
                          """
                           مرحبًا، أنا كتلة من النص الثابت.
                            * مرحبا بكم في هذا برنامج الاختبار
                            * يحتوي على سؤال واحد من كل نوع.

                          ## ما هي متطلبات الأهلية؟
                          يرجى الذهاب إلى [نا](https://www.example.com) لمزيد من المعلومات
                          """)))
              .setQuestionHelpText(LocalizedStrings.withDefaultValue(""))
              .build());

  @VisibleForTesting
  public static final TextQuestionDefinition TEXT_QUESTION_DEFINITION =
      new TextQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Text Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "What is your favorite color?",
                          Lang.forCode("ar").toLocale(),
                          "ما هو لونك المفضل؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  @VisibleForTesting
  public static final PhoneQuestionDefinition PHONE_QUESTION_DEFINITION =
      new PhoneQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Phone Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "what is your phone number",
                          Lang.forCode("ar").toLocale(),
                          "ما هو رقم هاتفك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .setUniversal(true)
              .setPrimaryApplicantInfoTags(ImmutableSet.of(PrimaryApplicantInfoTag.APPLICANT_PHONE))
              .build());

  @VisibleForTesting
  public static final DateQuestionDefinition DATE_QUESTION_DEFINITION =
      new DateQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Date Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "When is your birthday?",
                          Lang.forCode("ar").toLocale(),
                          "متى يحين عيد ميلادك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .setUniversal(true)
              .setPrimaryApplicantInfoTags(ImmutableSet.of(PrimaryApplicantInfoTag.APPLICANT_DOB))
              .build());

  static final DateQuestionDefinition DATE_PREDICATE_QUESTION_DEFINITION =
      new DateQuestionDefinition(
          QuestionDefinitionConfig.builder()
              .setName("Sample Predicate Date Question")
              .setDescription("description")
              .setQuestionText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "When is your birthday?",
                          Lang.forCode("ar").toLocale(),
                          "متى يحين عيد ميلادك؟")))
              .setQuestionHelpText(
                  LocalizedStrings.of(
                      ImmutableMap.of(
                          Lang.forCode("en-US").toLocale(),
                          "help text",
                          Lang.forCode("ar").toLocale(),
                          "نص المساعدة")))
              .build());

  private static final QuestionDefinitionConfig.Builder
      DATE_ENUMERATED_QUESTION_DEFINITION_BUILDER =
          QuestionDefinitionConfig.builder()
              .setName("Sample Enumerated Date Question")
              .setDescription("description")
              .setQuestionText(LocalizedStrings.withDefaultValue("When is $this's birthday?"))
              .setQuestionHelpText(
                  LocalizedStrings.withDefaultValue("help text for $this's birthday"));

  @VisibleForTesting
  public static DateQuestionDefinition dateEnumeratedQuestionDefinition(long enumeratorId) {
    return new DateQuestionDefinition(
        DATE_ENUMERATED_QUESTION_DEFINITION_BUILDER.setEnumeratorId(enumeratorId).build());
  }

  /** All members of this class that are of type {@link QuestionDefinition}. */
  public static final ImmutableList<QuestionDefinition> ALL_SAMPLE_QUESTION_DEFINITIONS =
      ImmutableList.of(
          ADDRESS_QUESTION_DEFINITION,
          CHECKBOX_QUESTION_DEFINITION,
          CURRENCY_QUESTION_DEFINITION,
          DATE_PREDICATE_QUESTION_DEFINITION,
          DATE_QUESTION_DEFINITION,
          DROPDOWN_QUESTION_DEFINITION,
          EMAIL_QUESTION_DEFINITION,
          ENUMERATOR_QUESTION_DEFINITION,
          FILE_UPLOAD_QUESTION_DEFINITION,
          ID_QUESTION_DEFINITION,
          NAME_QUESTION_DEFINITION,
          NUMBER_QUESTION_DEFINITION,
          PHONE_QUESTION_DEFINITION,
          RADIO_BUTTON_QUESTION_DEFINITION,
          STATIC_CONTENT_QUESTION_DEFINITION,
          TEXT_QUESTION_DEFINITION);

  public static QuestionDefinition getQuestionDefinitionWithTestId(QuestionType questionType) {
    return switch (questionType) {
      case ADDRESS -> ADDRESS_QUESTION_DEFINITION.withPopulatedTestId();
      case CHECKBOX -> CHECKBOX_QUESTION_DEFINITION.withPopulatedTestId();
      case CURRENCY -> CURRENCY_QUESTION_DEFINITION.withPopulatedTestId();
      case DATE -> DATE_QUESTION_DEFINITION.withPopulatedTestId();
      case DROPDOWN -> DROPDOWN_QUESTION_DEFINITION.withPopulatedTestId();
      case EMAIL -> EMAIL_QUESTION_DEFINITION.withPopulatedTestId();
      case ENUMERATOR -> ENUMERATOR_QUESTION_DEFINITION.withPopulatedTestId();
      case FILEUPLOAD -> FILE_UPLOAD_QUESTION_DEFINITION.withPopulatedTestId();
      case ID -> ID_QUESTION_DEFINITION.withPopulatedTestId();
      case MAP -> MAP_QUESTION_DEFINITION.withPopulatedTestId();
      case NAME -> NAME_QUESTION_DEFINITION.withPopulatedTestId();
      case NUMBER -> NUMBER_QUESTION_DEFINITION.withPopulatedTestId();
      case RADIO_BUTTON -> RADIO_BUTTON_QUESTION_DEFINITION.withPopulatedTestId();
      case STATIC -> STATIC_CONTENT_QUESTION_DEFINITION.withPopulatedTestId();
      case TEXT -> TEXT_QUESTION_DEFINITION.withPopulatedTestId();
      case PHONE -> PHONE_QUESTION_DEFINITION.withPopulatedTestId();
        // Fall through to Null Question for now since Yes/No is not fully implemented.
        // TODO(#10800): Create a Yes/No question instead.
      case YES_NO, NULL_QUESTION -> new NullQuestionDefinition(1);
    };
  }
}
