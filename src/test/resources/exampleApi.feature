# language: de

Funktionalität: REST Api Testen

  Grundlage:

    Angenommen ich will JsonPlaceholderApi testen

  Szenario: Users anfragen

    Wenn Ich nach Users suche
    Dann der User mit id 2 hat Name "Ervin Howell"

  Szenario: Kommentar abschicken (Post Request)
    Wenn der User mit Id 1 den Kommentar mit Titel "Mein Titel" und Inhalt "Mein Inhalt" erfolgreich postet
    Dann bekommt er im Response die Statusphrase "Created"
    Dann bekommt er im Response den KommentarId zurück


