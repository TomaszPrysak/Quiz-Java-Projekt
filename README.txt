Gra typu Quiz - stworzona w ramach projektu warsztat�w Back-end Developer.

Zasada dzia�ania aplikacji:

Przy u�yciu MySQL zosta�y stworzone tabale zawieraj�ce: list� u�ytkowik�w, list� pyta� oraz tabela przechowuj�ca wyniki graczy z poszczeg�lnych gier.

Brak obecno�ci gracza w bazie danych powoduje brak mo�liwo�ci zalogowania sie do gry.

Po zalogowani u si� gracza do gry dostaje on dost�p do wybor� tematyki z kt�rej chce odpowiada� na pytania. Nast�pnie wybriera ilo�� pyta�. Po dokonaniu wybor�w uruchamia Quiz poprzez klikni�cie przycisku Gramy!

Nast�pnie pojawia si� okienko z pierwszym pytaniem losowo wybranym z bazy pyta� kategorii kt�re u�ytkownik zaznaczy�. Dokonuje wyboru i przyciskiem Next przechodzi do nastepnego pytania.

Po udzieleniu odpowiedzi na wszystkie pytania wy�wietla si� okienko z wynikiem (ilo�� poprawnych odpowiedzi) aktualnej gry. Wynik ten jest zapisywany w bazie danych. W okienku z wynikami wy�wietlana te� og�lna statystyka wszystkich dotychczasowych gier danego u�ytkownika.

Gra jest skalowalna. Mo�emy dodawa� kolejne pytania oraz kolejne kategorie.