Gra typu Quiz - stworzona w ramach projektu warsztatów Back-end Developer.

Zasada dzia³ania aplikacji:

Przy u¿yciu MySQL zosta³y stworzone tabale zawieraj¹ce: listê u¿ytkowików, listê pytañ oraz tabela przechowuj¹ca wyniki graczy z poszczególnych gier.

Brak obecnoœci gracza w bazie danych powoduje brak mo¿liwoœci zalogowania sie do gry.

Po zalogowani u siê gracza do gry dostaje on dostêp do wyboró tematyki z której chce odpowiadaæ na pytania. Nastêpnie wybriera iloœæ pytañ. Po dokonaniu wyborów uruchamia Quiz poprzez klikniêcie przycisku Gramy!

Nastêpnie pojawia siê okienko z pierwszym pytaniem losowo wybranym z bazy pytañ kategorii które u¿ytkownik zaznaczy³. Dokonuje wyboru i przyciskiem Next przechodzi do nastepnego pytania.

Po udzieleniu odpowiedzi na wszystkie pytania wyœwietla siê okienko z wynikiem (iloœæ poprawnych odpowiedzi) aktualnej gry. Wynik ten jest zapisywany w bazie danych. W okienku z wynikami wyœwietlana te¿ ogólna statystyka wszystkich dotychczasowych gier danego u¿ytkownika.

Gra jest skalowalna. Mo¿emy dodawaæ kolejne pytania oraz kolejne kategorie.