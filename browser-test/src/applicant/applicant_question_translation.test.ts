import {test, expect} from '../support/civiform_fixtures'
import {
  disableFeatureFlag,
  loginAsAdmin,
  logout,
  selectApplicantLanguage,
  validateScreenshot,
} from '../support'

test.describe('Admin can manage translations', () => {
  test.beforeEach(async ({page}) => {
    await disableFeatureFlag(page, 'north_star_applicant_ui')
  })

  test('Expect single-answer question is translated for applicant', async ({
    page,
    adminPrograms,
    adminQuestions,
    adminTranslations,
    applicantQuestions,
  }) => {
    await loginAsAdmin(page)

    // Add a new question to be translated
    const questionName = 'name-translated'
    await adminQuestions.addNameQuestion({questionName})

    // Go to the question translation page and add a translation for Spanish
    await adminQuestions.goToQuestionTranslationPage(questionName)
    await adminTranslations.selectLanguage('Spanish')
    await adminTranslations.editQuestionTranslations(
      'ingrese\n1.nombre\n2.segundo nombre\n3.apellido',
      'Spanish help text',
    )

    // Add the question to a program and publish
    const programName = 'Spanish question program'
    await adminPrograms.addProgram(
      programName,
      'program description',
      'short program description',
      'http://seattle.gov',
    )
    await adminPrograms.editProgramBlock(programName, 'block', [questionName])
    await adminPrograms.publishProgram(programName)
    await logout(page)

    // View the translated program details on index without an account
    await selectApplicantLanguage(page, 'Español')
    await applicantQuestions.validateHeader('es-US')

    // Expect program details link to contain 'Detalles del programa' with link to 'http://seattle.gov'
    expect(
      await page.innerText('.cf-application-card a[href="http://seattle.gov"]'),
    ).toContain('Detalles del programa')

    await applicantQuestions.applyProgram(programName)
    // Set the language for this particular guest applicant
    await selectApplicantLanguage(page, 'Español')
    await applicantQuestions.validateHeader('es-US')

    expect(await page.innerText('.cf-applicant-question-text')).toContain(
      'ingrese\n1.nombre\n2.segundo nombre\n3.apellido',
    )
    expect(await page.innerText('.cf-applicant-question-help-text')).toContain(
      'Spanish help text',
    )
  })

  test('Expect single-answer question is translated with markdown for applicant', async ({
    page,
    adminPrograms,
    adminQuestions,
    adminTranslations,
    applicantQuestions,
  }) => {
    await loginAsAdmin(page)

    // Add a new question to be translated
    const questionName = 'name-translated'
    await adminQuestions.addNameQuestion({questionName})

    // Go to the question translation page and add a translation for Spanish
    await adminQuestions.goToQuestionTranslationPage(questionName)
    await adminTranslations.selectLanguage('Spanish')
    await adminTranslations.editQuestionTranslations(
      '# Enter name  \n * Your first name \n *  Your middle name \n * Your last name',
      '## It will identify you',
    )
    // Add the question to a program and publish
    const programName = 'Spanish question program with markdown'
    await adminPrograms.addProgram(
      programName,
      'program description',
      'short program description',
      'http://seattle.gov',
    )
    await adminPrograms.editProgramBlock(programName, 'block', [questionName])
    await adminPrograms.publishProgram(programName)
    await logout(page)

    // Log in as an applicant and view the translated question
    await applicantQuestions.applyProgram(programName)
    await selectApplicantLanguage(page, 'Español')
    await applicantQuestions.validateHeader('es-US')

    await validateScreenshot(
      page.locator('.cf-applicant-question-text'),
      'question-translation-with-markdown',
      /* fullPage= */ false,
    )
  })

  test('Expect multi-option question is translated for applicant', async ({
    page,
    adminPrograms,
    adminQuestions,
    adminTranslations,
    applicantQuestions,
  }) => {
    await loginAsAdmin(page)

    // Add a new question to be translated
    const questionName = 'multi-option-translated'
    await adminQuestions.addRadioButtonQuestion({
      questionName,
      options: [
        {adminName: 'one_admin', text: 'one'},
        {adminName: 'two_admin', text: 'two'},
        {adminName: 'three_admin', text: 'three'},
      ],
    })

    // Go to the question translation page and add a translation for Spanish
    await adminQuestions.goToQuestionTranslationPage(questionName)
    await adminTranslations.selectLanguage('Spanish')
    await adminTranslations.editQuestionTranslations('hola', 'mundo', [
      'uno',
      'dos',
      'tres',
    ])

    // Add the question to a program and publish
    const programName = 'Spanish question multi option program'
    await adminPrograms.addProgram(programName)
    await adminPrograms.editProgramBlock(programName, 'block', [questionName])
    await adminPrograms.publishProgram(programName)
    await logout(page)

    // Log in as an applicant and view the translated question
    await applicantQuestions.applyProgram(programName)
    await selectApplicantLanguage(page, 'Español')

    expect(await page.innerText('main form')).toContain('uno')
    expect(await page.innerText('main form')).toContain('dos')
    expect(await page.innerText('main form')).toContain('tres')
  })

  test('Expect enumerator question is translated for applicant', async ({
    page,
    adminPrograms,
    adminQuestions,
    adminTranslations,
    applicantQuestions,
  }) => {
    await loginAsAdmin(page)

    // Add a new question to be translated
    const questionName = 'enumerator-translated'
    await adminQuestions.addEnumeratorQuestion({questionName})

    // Go to the question translation page and add a translation for Spanish
    await adminQuestions.goToQuestionTranslationPage(questionName)
    await adminTranslations.selectLanguage('Spanish')
    await adminTranslations.editQuestionTranslations('test', 'enumerator', [
      'miembro de la familia',
    ])

    // Add the question to a program and publish
    const programName = 'Spanish question enumerator program'
    await adminPrograms.addProgram(programName)
    await adminPrograms.editProgramBlock(programName, 'block', [questionName])
    await adminPrograms.publishProgram(programName)
    await logout(page)

    // Log in as an applicant and view the translated question
    await applicantQuestions.applyProgram(programName)
    await selectApplicantLanguage(page, 'Español')

    expect(await page.innerText('main form')).toContain('miembro de la familia')
  })
})
