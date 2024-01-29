package ex1;

public sealed interface Populated
        permits City, Department, Country {

}

//OOP: new requirements  -> Add method to interface or add interface with new method (Interface Segregation)
//DOP: Stop putting behaviour in interfaces, use them as marker only
//int area();
//double density();
