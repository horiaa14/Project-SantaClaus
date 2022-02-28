# Project - Santa Claus


## ***Implementation details:***

* ## ***Design patterns that I used*** ##

    1. Singleton
    2. Abstract Object Factory
    3. Visitor
    4. Observer


* ## ***The packages and the classes used in project:*** ##

   * ***children***: To memorize all the information about children, I defined the\
    Child class. Since each child falls into a certain age category, there is \
    no need to create Child objects. That's why I created the Baby, Kid, Teen \
    classes that were derived from the Child class, which became abstract. To\
    generate Child-derived objects I have implemented the ***ChildFactory*** class \
    which allows the instantiation of these objects according to the child's age.\
    This modeling of the classes is based on the use of the ***Abstract Object\
    Factory*** design pattern.\
    The different calculation of the average score depending on the type of child\
    suggests a fundamental concept of OOP: the polymorphism. In order to separate\
    the class statement from the implementation of these algorithms and to have\
    a decoupled design, I preferred to use another design pattern, ***Visitor***.\
    The interface called AverageScoreCalculator contains the signatures of three\
    methods used to calculate the average score, their parameters being of types\
    derived from the Child class. The ***AverageScoreImpl*** class contains the \
    implementation of the three algorithms and the interface called ChildInterface\
    contains a method implemented by all classes derived from Child: the ***accept***\
    method that receives a computer of average scores and calls the correct\
    calculation method depending on the type of object that invokes the function.\
    This concept is known as ***Double Dispatch***.

    * ***database***: In this package we can find the databases that will ensure \
    the management of children and gifts: the ***ChildrenDatabase*** class and \
    the ***GiftsDatabase*** class. Only one database of each type will be needed\
    to model the application. Therefore, these classes will be ***Singleton***. \
    The ChildrenDatabase class contains a list of children and the GiftsDatabase\
    class contains a list of gifts. \
    An important aspect in the design of the application is the role of ***observer***\
    of these two classes in the implementation of the ***Observer*** design pattern.\
    Following updates at the end of the rounds, these classes will be automatically\
    notified to update. By implementing the Observer interface, they will contain\
    a method called ***update***. In the class where the children are stored, at \
    the end of the rounds we will have to do the following operations: 
        * to change the age of the children 
        * to add new children to the database 
        * to update existing information about them


    * ***simulation***: Inside this package will be the two interfaces from the description\
    of the ***Observer*** design pattern, as well as the ***Simulation***
    class
    in which you\
    will find the methods used to implement the round simulation.
    The ***Simulation***\
    class will be the subject, the end of a round involving the updating of observers\
    (databases with children and gifts)

    * ***gift***: This package contains a single class for representing gift information

    * ***utils***: The Utils class in this package contains methods that are used in the\
    process of parsing json files.

    * ***fileio***: Here are all the classes needed to read and display data. I have\
    implemented one class for each entity, so the classes have only one responsability.

    * ***main***: This package contains the Main class, representing the starting point\
    of the program.

