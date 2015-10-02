package ru.mail.track;


import sun.security.util.Password;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.Scanner;

public class AuthorizationService {

    private UserStore userStore;

    public AuthorizationService(UserStore userStore) {
        this.userStore = userStore;
    }


    void startAuthorization() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Do you want to login (print l) or to create a new user (print n) or quit (print q)?");
            String resp = scanner.next();
            if (resp.equals("n")) {
                try {
                    newUser();
                    System.out.println("New user was successfully created!");
                } catch (IOException e) {
                    System.out.println("User with this name already exists, try another one");
                }
            } else if (resp.equals("l")) {
                try {
                    User user = login();
                    System.out.println("Hello " + user.getName() + " !");
                } catch (NamingException e) {
                    System.out.println("Wrong login!");
                } catch (IOException e) {
                    System.out.println("Wrong password!");
                }
            } else if (resp.equals("q")) {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    User login() throws IOException, NamingException {
        Scanner scanner = new Scanner(System.in);
        // 1. Ask for name
        System.out.println("Print you login here: ");
        String name = scanner.next();
        System.out.println("> " + name);
        if (!userStore.isUserExist(name)) {
            throw new NamingException();
        }
        // 2. Ask for pass
        System.out.println("Print you password here: ");
        String pass = scanner.next();
        System.out.println("> ");
//            3. Ask UserStore for user:  userStore.getUser(name, pass)
        User user = userStore.getUser(name, pass);
        if (user == null) {
            throw new IOException();
        } else {
            return user;
        }
    }

    void newUser() throws IOException {
        Scanner scanner = new Scanner(System.in);
        // 1. Ask for name
        System.out.println("Print you login here: ");
        String name = scanner.next();
        System.out.println("> " + name);
        if (userStore.isUserExist(name)) {
            throw new IOException();
        }
        // 2. Ask for pass
        System.out.println("Print you password here: ");
        String pass = scanner.next();
        System.out.println("> ");
        // 3. Add user to UserStore: userStore.addUser(user)
        User user = new User(name, pass);
        userStore.addUser(user);
    }

}