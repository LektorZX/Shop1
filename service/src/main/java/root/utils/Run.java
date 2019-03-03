package root.utils;

import root.config.PersistenceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Run {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceConfig.class);
        DatabaseHelper databaseHelper = (DatabaseHelper) context.getBean("databaseHelper");
//        databaseHelper.cleanDatabase();
//        databaseHelper.prepareData();

//        databaseHelper.userTest();//++ поставить вызов исключений
//        databaseHelper.productTest();//+++++++
//        databaseHelper.TestConvertDTO();//++++++++
//        databaseHelper.testBasket();//+++++++
    }
}
