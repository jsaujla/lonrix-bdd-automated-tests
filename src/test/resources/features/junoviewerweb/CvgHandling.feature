@juno
Feature: Demonstrate the possibility to automate CVG at UI level.

  Background:
    Given I am at JunoViewer login page
    And I login JunoViewer with user: "${juno.viewer.valid.username}" and password: "${juno.viewer.valid.password}"
    #And I login JunoViewer with user: "${juno.viewer.valid.username}", password: "${juno.viewer.valid.password}" and rememberme: "false"


  Scenario: Verify content in cells for NetworkA - Displayed Cells Only
    When I goto to "Views > Strip Map View"
    And I choose location and click OK
      |Network: |SH:|RS:|Section Id:|From Km:|To Km:|
      |Network A|   |103|           |        |      |

    Then I should see expectedLwpIriCellText and expectedLwpIriCellColor
      |3.77          |4.24          |3.87          |5.86          |3.00          |4.54          |3.85          |5.75          |4.65          |3.53          |4.55          |5.43          |6.79          |6.77          |4.88          |4.93          |5.32          |3.86          |4.12          |3.18          |
      |rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|
    And I should see expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor
      |9.3             |19.1          |10.6            |11.4            |5.0             |11.3            |7.1             |8.1             |7.9             |6.5             |12.7            |8.1              |8.7             |8.5             |6.0             |10.9            |10.4            |7.3             |11.8            |12.2             |
      |rgb(255, 255, 0)|rgb(0, 128, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 165, 0)|rgb(154, 205, 50)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(154, 205, 50)|
    And I should see expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor
      |2CHIP|2CHIP|2CHIP|2CHIP|2CHIP|2CHIP|1CHIP|2CHIP|
      |white|white|white|white|white|white|white|white|
    And I should see expectedAccidentsCellText and expectedAccidentsCellColor
      |1        |1        |1        |4        |1        |1        |1        |1        |
      |LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|


  Scenario: Verify content in cells for NetworkA - All Cells
    When I goto to "Views > Strip Map View"
    And I choose location and click OK
      |Network: |SH:|RS:|Section Id:|From Km:|To Km:|
      |Network A|   |103|           |        |      |

    Then Verify presence of expectedLwpIriCellText and expectedLwpIriCellColor
      |3.77          |4.24          |3.87          |5.86          |3.00          |4.54          |3.85          |5.75          |4.65          |3.53          |4.55          |5.43          |6.79          |6.77          |4.88          |4.93          |5.32          |3.86          |4.12          |3.18          |4.13          |3.90          |4.01          |3.84          |3.36          |3.32          |6.26          |3.92          |3.87          |6.82          |5.64          |3.71          |6.95          |4.43          |6.43          |2.82          |3.57          |3.17          |3.14          |3.57          |
      |rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|rgb(0, 128, 0)|
    And Verify presence of expectedLwpMeanRutCellText and expectedLwpMeanRutCellColor
      |9.3             |19.1          |10.6            |11.4            |5.0             |11.3            |7.1             |8.1             |7.9             |6.5             |12.7            |8.1              |8.7             |8.5             |6.0             |10.9            |10.4            |7.3             |11.8            |12.2             |
      |rgb(255, 255, 0)|rgb(0, 128, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 165, 0)|rgb(154, 205, 50)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(255, 255, 0)|rgb(255, 165, 0)|rgb(255, 255, 0)|rgb(154, 205, 50)|
    And Verify presence of expectedSurfacingCodeCellText and expectedSurfacingCodeCellColor
      |2CHIP|2CHIP|2CHIP|2CHIP|2CHIP|2CHIP|1CHIP|2CHIP|
      |white|white|white|white|white|white|white|white|
    And Verify presence of expectedAccidentsCellText and expectedAccidentsCellColor
      |1        |1        |1        |4        |1        |1        |1        |1        |
      |LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|LightBlue|


  Scenario: Verify default content in Naasra for NetworkA
    When I goto to "Views > Strip Map View"
    And I choose location and click OK
      |Network: |SH:|RS:|Section Id:|From Km:|To Km:|
      |Network A|   |103|           |        |      |

    Then I should see expectedNaasraTextInRow1
      |65.0|62.0|64.0|66.0|67.0|66.0|67.0|68.0|69.0|72.0|
    And I should see expectedNaasraTextInRow2
      |42.0|42.0|43.0|47.0|46.0|45.0|45.0|45.0|46.0|47.0|