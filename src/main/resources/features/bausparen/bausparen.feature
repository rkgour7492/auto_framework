@bausparen
Feature: As a user
  I want to able to use Bausparen and its products


  Background: launch website
    Given i navigate to the website Bausparen portal

  Scenario Outline: Verify error message on entering invalid email i
    When i enter invalid value "<invalid>" in the field
    And i click on Absenden
    Then verify error message "Bitte geben Sie eine korrekte E-Mail Adresse an."

    Examples:
      | invalid      |
      | abcdef434    |
      | ram@.com     |
      | ramgmail.com |

  Scenario: Verify Search function
    When verify search box input field is present
    And I search "Kontakt" in search box and verify search results


  Scenario:Verify Dropdown Menu items and flyout on hovering over them
    When verify below menu items are present
      | Bausparen | Finanzieren | Rechner | Services | Meine Wohnwelt | Kontakt |
    Then verify flyout should open on hovering over each of them


  Scenario: Verify logo in the page is present and not broken
    When logo is present
    And I perform GET request for "Logo Raiffeisen Bausparkasse"
    Then verify image is not broken with 200 status code


  Scenario: Verify all hyperlinks and navigations
    When hyperlink for gewintballen is present
    And i click on gewintballen hyperlink
    Then verify page is navigated with "Gewinntabellen" header


  Scenario: Verify products and slick buttons
    When i click slick arrow button and verify its action
    And i click slick dot buttons and verify it actions
    Then verify below products are available
      | KLASSISCHES BAUSPAREN | JUGEND BAUSPAREN | MIXZINS BAUSPAREN | RELAX BAUSPAREN |


  Scenario:  Verify video on web page is available and playable
    When video is present
    And i click on play video
    Then verify video is playable


  Scenario: Verify information on GUT ZU WISSEN can be expanded and collapsed
    When I am able to expand Weitere infos
    Then verify that section is expanded
    And I am able to collapse schliessen
    Then verify section is collapsed


  Scenario: Verify Download functionality of Produktinfos
    When download produktubfos is present
    Then I perform GET request for "Download Produktinfos"
    And verify PDF file is available for download with response 200


  Scenario: Verify email is succesfully sent and received after click on Absenden
    When I enter valid email "abcdef@gmail.com"
    And i click on Absenden
    Then verify email sent success message as "Vielen Dank. Sie erhalten in Kürze eine E-Mail mit einem Link um Ihre Anmeldung zu den WOHNWELT News zu bestätigen."


