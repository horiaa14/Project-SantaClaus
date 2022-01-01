# Proiect - Etapa 1

## Nume: Banciu Horia - Adrian

## Grupa: 322CD

---

## ***Coding style:***

* Liniile de cod din cadrul aplicației conțin cel mult 80 de caractere.


* Metodele nu depășesc lungimea unui ecran când linia de comandă a IDE-ului \
este deschisă. Altfel spus, acestea conțin maxim 24 de linii de cod (pentru a \
fi ușor de urmărit).


* Am adăugat comentarii doar înaintea secțiunilor pentru care a fost nevoie. \
Numele variabilelor și metodelor sunt sugestive, comportamentul unei funcții \
putând fi dedus cu ușurință. 


* Fiecare metodă conține un Javadoc care descrie complet rolul acesteia.

---

## ***Descrierea implementării:***

* ## ***Design pattern-uri utilizate*** ##

    1. Singleton
    2. Abstract Object Factory
    3. Visitor
    4. Observer



* ## ***Pachetele și clasele folosite în modelarea aplicației*** ##

    * Pachetul ***children***: Pentru memorarea tuturor informațiilor despre copii\
    am definit clasa Child. Întrucât fiecare copil se încadrează la o anumită  \
    categorie în funcție de vârstă, putem observa că nu este nevoie să creăm \
    obiecte de tip Child. De aceea, am creat clasele Baby, Kid și Teen care au \
    fost derivate din clasa Child, aceasta devenind abstractă. Nu am definit și\
    clasa Young Adult, deoarece obiectele de acest tip nu ar fi fost implicate \
    în prelucrare. \
    Pentru generarea obiectelor de tip derivat din Child, prin upcast la clasa \
    de bază, am implementat clasa ***ChildFactory*** care permite instanțierea \
    acestor obiecte în funcție de vârsta copilului. Această modelare a claselor\
    se bazează pe folosirea design pattern-ului ***Abstract Object Factory***. \
    În continuare, calcularea diferită a average score-ului în funcție de tipul\
    copilului ne sugerează un concept fundamental OOP: ***polimorfismul***. \
    Pentru a separa declarația claselor de implementarea acestor algoritmi și\
    pentru a avea astfel un design decuplat, am preferat folosirea unui alt \
    design pattern, ***Visitor***. \
    În locul denumirilor clasice pentru interfețele care apar în implementarea\
    acestui design pattern, și anume ***Visitor*** și ***Visitable***, am ales 
    alte denumiri\
    mai sugestive în contextul acestei aplicații. \
    Astfel, interfața numită AverageScoreCalculator conține semnăturile a trei\
    metode folosite la calcularea average score-ului, parametrii acestora fiind\
    de tipuri derivate din clasa ***Child*** (supraîncărcare de metode). \
    Clasa ***AverageScoreImpl*** conține implementarea celor trei algoritmi, iar\
    interfața numită ***ChildInterface*** conține o metodă implementată de toate\
    clasele derivate din Child: metoda ***accept*** care primește un calculator
    de \
    average score-uri și apelează metoda corectă de calcul în funcție de tipul \
    obiectului care invocă funcția. Acest concept este cunoscut sub denumirea \
    de ***Double Dispatch***.
    
    * Pachetul ***database***: În cadrul acestui pachet putem regăsi bazele de
    date\
    care vor asigura gestiunea copiilor și a cadourilor: clasa ChildrenDatabase\
    și GiftsDatabase. Pentru modelarea aplicației va fi nevoie de o singură 
    bază\
    de date din fiecare tip. De aceea, aceste clase vor fi ***Singleton***.\
    Clasa ChildrenDatabase conține o listă în care vor fi introduși copiii prin\
    upcast la clasa de bază (listă de tip Child), iar clasa GiftsDatabase
    conține\
    o listă de cadouri.\
    Un aspect ***deosebit de important*** în design-ul aplicației îl constituie
    rolul\
    de ***observator*** al acestor două clase în implementarea design
    pattern-ului\
    ***Observer***. În urma actualizărilor care se realizează la finalul
    rundelor, aceste\
    clase vor fi notificate automat pentru a se actualiza. Implementând
    interfața\
    Observer, acestea vor conține o metodă numită ***update***. În clasa în care
    sunt \
    memorați copiii, la finalul rundelor va trebui să facem următoarele operații:
        * să modificăm vârsta copiilor (schimbând tipul obiectului dacă noua\
        vârstă se încadrează la o altă categorie - prin folosirea Abstract
        Object\
        Factory);

        * să adăugăm noi copii în baza de date;
        
        * să actualizăm informațiile existente despre aceștia.

        În clasa care conține cadourile lui Moș Crăciun metoda update va adăuga
        \
        și alte cadouri în baza de date.
  
    * Pachetul ***simulation***: În interiorul acestui pachet se vor afla cele
    două\
    interfețe din descrierea design pattern-ului ***Observer*** (Subject și
    Observer)\
    precum și clasa ***Simulation***, în care se vor regăsi metodele folosite la
    \
    implementarea simulării rundelor.\
    Clasa ***Simulation*** va deține rolul de ***subiect***, încheierea unei 
    runde implicând\
    actualizarea observatorilor (bazele de date cu copii și cadouri). \
    Toate metodele care aparțin procesului de simulare au fost implementate în\
    această clasă. Dimensiunea acestei clase este mai mare în raport cu
    celelalte,\
    deoarece au fost adăugate Javadoc-uri detaliate pentru fiecare metodă. 

    * Pachetul ***gift***: Acest pachet conține o singură clasă pentru
    reprezentarea \
    informațiilor despre cadouri.

    * Pachetul ***utils***: Clasa Utils din acest pachet conține metode care
    sunt folosite \
    în procesul de parsare a fișierelor de tip json.

    * Pachetul ***fileio***: Aici se află toate clasele necesare pentru citirea
    și afișarea\
    datelor. Am implementat câte o clasă pentru fiecare entitate citită, clasele
    \
    având astfel o singură responsabilitate. Toate informațiile necesare pentru\
    implementarea procesului de simulare se vor afla în câmpurile unui obiect\
    de tip Input. Metoda readData() din clasa InputLoader va parsa fișierele de\
    tip json și va construi un obiect de tip Input pe care îl va returna la
    final.\
    Pentru afișarea rezultatelor în urma simulării vom folosi clasa Writer.\
    Aceste clase au fost folosite doar la citire / scriere. Pentru celelalte
    operații\
    am preferat implementarea altor clase.

    * Pachetul ***main***: Acest pachet conține clasa Main, aceasta reprezentând
    \
    punctul de început al programului. 

---

## ***Observații generale:***
    
* Pentru modelarea acestei aplicații am exploatat cât mai multe dintre noțiunile
de\
OOP studiate: încapsulare, compunere, moștenire, abstractizare, polimorfism, \
interfețe, șabloane de proiectare (design patterns) + Java Collections și
stream-uri.


* Fiecare clasă are o singură responsabilitate, principiul singurei
responsabilități fiind\
respectat în întregul program.


* Aplicația poate fi structurată în diverse feluri, fiind foarte bogată din 
punctul de\
 vedere al utilizării design pattern-urilor. 









    







    




