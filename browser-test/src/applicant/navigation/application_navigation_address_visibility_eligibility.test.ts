import {test, expect} from '../../support/civiform_fixtures'
import {
  disableFeatureFlag,
  enableFeatureFlag,
  isLocalDevEnvironment,
  loginAsAdmin,
  logout,
} from '../../support'
import {Eligibility} from '../../support/admin_programs'

test.describe('Applicant navigation flow', () => {
  test.beforeEach(async ({page}) => {
    await disableFeatureFlag(page, 'north_star_applicant_ui')
  })
  if (isLocalDevEnvironment()) {
    test.describe('using address as visibility condition', () => {
      const programName = 'Test program for address as visibility condition'
      const questionAddress = 'address-test-q'
      const questionText1 = 'text-test-q-one'
      const questionText2 = 'text-test-q-two'
      const screen1 = 'Screen 1'
      const screen2 = 'Screen 2'
      const screen3 = 'Screen 3'

      test.beforeEach(
        async ({page, adminQuestions, adminPrograms, adminPredicates}) => {
          await loginAsAdmin(page)
          await enableFeatureFlag(page, 'esri_address_correction_enabled')
          await enableFeatureFlag(
            page,
            'esri_address_service_area_validation_enabled',
          )

          // Create Questions
          await adminQuestions.addAddressQuestion({
            questionName: questionAddress,
            questionText: questionAddress,
          })

          await adminQuestions.addTextQuestion({
            questionName: questionText1,
            questionText: questionText1,
          })

          await adminQuestions.addTextQuestion({
            questionName: questionText2,
            questionText: questionText2,
          })

          // Create Program
          await adminPrograms.addProgram(programName)

          // Attach questions to program
          await adminPrograms.editProgramBlock(programName, screen1, [
            questionAddress,
          ])

          await adminPrograms.addProgramBlock(programName, screen2, [
            questionText1,
          ])

          await adminPrograms.addProgramBlock(programName, screen3, [
            questionText2,
          ])

          await adminPrograms.goToBlockInProgram(programName, screen1)

          await adminPrograms.clickAddressCorrectionToggleByName(
            questionAddress,
          )

          const addressCorrectionInput =
            adminPrograms.getAddressCorrectionToggleByName(questionAddress)

          await expect(addressCorrectionInput).toHaveValue('true')

          await adminPrograms.setProgramEligibility(
            programName,
            Eligibility.IS_NOT_GATING,
          )

          // Add address eligibility predicate
          await adminPrograms.goToEditBlockEligibilityPredicatePage(
            programName,
            screen1,
          )

          await adminPredicates.addPredicates({
            questionName: questionAddress,
            scalar: 'service area',
            operator: 'in service area',
            value: 'Seattle',
          })

          // Add the address visibility predicate
          await adminPrograms.goToBlockInProgram(programName, screen2)

          await adminPrograms.goToEditBlockVisibilityPredicatePage(
            programName,
            screen2,
          )

          await adminPredicates.addPredicates({
            questionName: questionAddress,
            action: 'shown if',
            scalar: 'service area',
            operator: 'in service area',
            value: 'Seattle',
          })

          // Publish Program
          await adminPrograms.gotoAdminProgramsPage()
          await adminPrograms.publishProgram(programName)

          await logout(page)
        },
      )

      test('when address is eligible show hidden screen', async ({
        page,
        applicantQuestions,
      }) => {
        await test.step('Fill out application with an eligible address', async () => {
          await applicantQuestions.applyProgram(programName)
          await applicantQuestions.answerAddressQuestion(
            'Legit Address',
            '',
            'Seattle',
            'WA',
            '98109',
            0,
          )
          await applicantQuestions.clickNext()
          await applicantQuestions.expectVerifyAddressPage(true)
          await applicantQuestions.clickConfirmAddress()
        })

        await test.step('See eligible alert and page only shown when have valid address', async () => {
          await applicantQuestions.expectMayBeEligibileAlertToBeVisible()
          await applicantQuestions.validateQuestionIsOnPage(questionText1)
        })

        await test.step('Fill out the remainder of the application and submit', async () => {
          await applicantQuestions.answerTextQuestion('answer 1')
          await applicantQuestions.clickNext()
          await applicantQuestions.answerTextQuestion('answer 2')
          await applicantQuestions.clickNext()

          await applicantQuestions.expectQuestionAnsweredOnReviewPage(
            questionAddress,
            'Address In Area',
          )

          await applicantQuestions.clickSubmit()
          await logout(page)
        })
      })

      test('when address is not eligible do not show hidden screen', async ({
        page,
        applicantQuestions,
      }) => {
        await test.step('Fill out application with an eligible address', async () => {
          await applicantQuestions.applyProgram(programName)
          await applicantQuestions.answerAddressQuestion(
            'Nonlegit Address',
            '',
            'Seattle',
            'WA',
            '98109',
            0,
          )
          await applicantQuestions.clickNext()
          await applicantQuestions.expectVerifyAddressPage(false)
          await applicantQuestions.clickConfirmAddress()
        })

        await test.step('Eligible alert is hidden and page page with visibility condition is skipped', async () => {
          await applicantQuestions.expectMayBeEligibileAlertToBeHidden()
          await applicantQuestions.validateQuestionIsOnPage(questionText2)
        })

        await test.step('Fill out the remainder of the application and submit', async () => {
          await applicantQuestions.answerTextQuestion('answer 2')
          await applicantQuestions.clickNext()
          await applicantQuestions.expectQuestionAnsweredOnReviewPage(
            questionAddress,
            'Nonlegit Address',
          )

          await applicantQuestions.clickSubmit()
          await logout(page)
        })
      })

      test('hide screen when address changes from eligible to not eligible', async ({
        page,
        applicantQuestions,
      }) => {
        await test.step('Fill out application with an eligible address', async () => {
          await applicantQuestions.applyProgram(programName)
          await applicantQuestions.answerAddressQuestion(
            'Legit Address',
            '',
            'Seattle',
            'WA',
            '98109',
            0,
          )
          await applicantQuestions.clickNext()
          await applicantQuestions.expectVerifyAddressPage(true)
          await applicantQuestions.clickConfirmAddress()
        })

        await test.step('See eligible alert and page only shown when have valid address', async () => {
          await applicantQuestions.expectMayBeEligibileAlertToBeVisible()
          await applicantQuestions.validateQuestionIsOnPage(questionText1)
        })

        await test.step('Edit address to be ineligible', async () => {
          await applicantQuestions.clickPrevious()
          await applicantQuestions.answerAddressQuestion(
            'Nonlegit Address',
            '',
            'Seattle',
            'WA',
            '98109',
            0,
          )
          await applicantQuestions.clickNext()
          await applicantQuestions.expectVerifyAddressPage(false)
          await applicantQuestions.clickConfirmAddress()
        })

        await test.step('Skip page that is hidden when have invalid address', async () => {
          await applicantQuestions.expectMayBeEligibileAlertToBeHidden()
          await applicantQuestions.validateQuestionIsOnPage(questionText2)
        })

        await test.step('Application submits successfully', async () => {
          await applicantQuestions.answerTextQuestion('answer 2')
          await applicantQuestions.clickNext()
          await applicantQuestions.expectQuestionAnsweredOnReviewPage(
            questionAddress,
            'Nonlegit Address',
          )

          await applicantQuestions.clickSubmit()
          await logout(page)
        })
      })
    })
  }
})
