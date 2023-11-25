package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User User1 = new User("User1", "Lastname1", "user1@mail.com");
      User1.setCar(new Car("Firebird", 1));
      userService.add(User1);
      User User2 = new User("User2", "Lastname2", "user2@mail.com");
      User2.setCar(new Car("Coyote", 2));
      userService.add(User2);
      User User3 = new User("User3", "Lastname3", "user3@mail.com");
      User3.setCar(new Car("Mustang", 3));
      userService.add(User3);
      User User4 = new User("User4", "Lastname4", "user4@mail.com");
      User4.setCar(new Car("Camaro", 4));
      userService.add(User4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         try {
            System.out.println("Car model = " + user.getCar().getModel());
            System.out.println("Car series = " + user.getCar().getSeries());
         } catch (NullPointerException e) {
            System.out.println("Не владеет авто");
         }
         System.out.println();
      }

      List<User> usersWithCar = userService.getUserByCar("Camaro", 4);
      for (User user : usersWithCar) {
         System.out.println("Имеет машину Camaro, 4:");
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }
      context.close();
   }
}