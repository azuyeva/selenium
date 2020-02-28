# language: de

  @Selenium
Funktionalität: Suchanfrage in der Suchmaschine
  Die Städte in Google suchen

 Szenariogrundriss: Suche "<Suchanfrage>" in <Browser> auf <Suchmaschine>

    Angenommen Ich verwende "<Browser>" Browser
    Angenommen Die Suchmaschine ist "<Suchmaschine>"
    Wenn Ich nach "<Suchanfrage>" suche
    Dann Bekomme ich valide Ergebnisse

   Beispiele: Testfallliste
   | Suchanfrage           | Browser |  Suchmaschine |
   | Frankfut am Main      | Chrome  |  Google       |