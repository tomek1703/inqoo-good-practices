# Dobre Praktyki Tworzenia Oprogramowania

## Motywacja

W programie całego kursu brakowało bardzo jakiegoś kawałka pozwalającego się skupić na dobrych praktykach programistycznych.

Blok ten byłby o tyle przydatny, że wchodzi on w zakres egzaminu Professional Scrum Developer.

## Program

1. Jakośc

    * Wprowadzenie
    * Wymiary jakości 
        - wewnętrzna 
        - zewnętrzna

2. Czysty kod

    * Porównanie kodu dobrej i złej jakości - ćwiczenie
    * Poprawne nazewnictwo
    * Nazewnictwo - ćwiczenie
    * Metody dobrej jakości
    * Metody - ćwiczenie
    * Klasy
        - Solid
        - Prawo Demeter
    * Klasy - ćwiczenie
    * Komentarze
    * Formatowanie 
    * Boy Scout Rule - opis  
    * Refaktoryzacja - opis

3. Współpraca w zespole
    * Code review
    * Pair programming

4. Test Driven Development
    * Wprowadzenie
    * String Calculator Kata

5. Vending Machina Kata z wykorzystaniem TDD i Object Callisthenics
    * Wprowadzenie o Object Callisthenics

6. Zadanie domowe - rozwiązywanie Sudoku
    * Omówienie algorytmu rozwiązywania Sudoku
    * Wprowadzenie

## Instrukcja dla trenera

Przed tym warsztatem warto zadać kursantom powtórkę z repozytorium java-repetition.

Slajdy: slides\presentation.html 

Slajdy kontrakt i o mnie są do dyspozycji trenera, który będzie prowadził dane zajęcia.

Warto mieć flipchart/whiteboarda do omawiania np. SOLIDa i zadania domowego.

Jeśli chodzi o rozplanowanie w czasie to sugeruje, żeby pierwszego dnia skończyc na String Calculator Kata.
Idealnie byłoby zacząć tę Katę gdzieś w okolicach obiadu. 

Na Vending Machine proponuję cały drugi dzień, z wyjątkiem końca który jest przewidziany na omówienie zadania domowego.

### Ćwiczenia:  

#### 1. Czytelność kodu 

Kursanci dostają kawałek kodu złej (com.inqoo.quality.clean.example.bad.Calc) i dobrej (com.inqoo.quality.clean.example.good.PITCalculator) jakości.
Na analizę każdego prpoponuję po ok. 10 minut - niech to przeczytają i spróbują zrobić code review.

#### 2. Czysty kod 
 
Opisane jest na slajdach zadanie ma na celu poprawienie istniejącego kodu złej jakości na trzech poziomach:

- nazwy zmiennych, pól klas i metod
- czystość kodu (tu w ramach metod), chodzi o ekstrakcję metod z instrukcji warunkowych i zagnieżdżeń
- klas, tu chodzi głównie o przestrzeganie prawa Demeter i reguły tell don't ask

#### 3. Slajdy z przykładami (np. na SOLID)

Przy wszystkich przykładach kodu na slajdach wchodź w interakcje z kursantami - niech pomyślą co tam się dzieje

#### 4. String Calculator Kata 

Opis jest na slajdach.
 
Proponuję by każdy robił to sam na swoim branchu (lub w parach) i co jakiś czas robić zbiorowe Code Review bieżących postępów.

Bieżące zmiany jako pull requesty do mastera - wtedy dobrze widać przyrosty.

#### 5. Vending Machine Kata

Opis jest na slajdach. Kluczowe jest to, żeby jak najbardziej spełnić reguły callisthenics.

Proponuję by każdy robił to sam na swoim branchu (lub w parach) i co jakiś czas robić zbiorowe Code Review bieżących postępów.

Bieżące zmiany jako pull requesty do mastera - wtedy dobrze widać przyrosty.  

#### 6. Sudoku

Jest zadaniem domowym. Niezbędne jest wprowadzenie i omówienie sposobu rozwiązywanie sudoku. 
Warto przeanalizować rozwiązywanie, tak by kursanci rozumieli algorytm. 

Oczywiście zadanie powinno byc zrobione z zachowaniem Object callisthenics i idealnie TDD.