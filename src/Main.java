//import User.User;


import DataBase.FileDataProvider;

public class Main {
    public  static void main(String[] args) {

        FileDataProvider dataProvider = new FileDataProvider("users.csv");

//        dataProvider.writeFile("user test write");

        dataProvider.readFile();

        System.out.println(dataProvider.readLine(10));


    }
}

