package ru.mail.track;

/**
 * Created by k33p on 29.09.15.
 */
public class UserStore {

    private User[] Users;
    private int index;

    public UserStore() {
        this.Users = new User[100];
        index = 0;
    }

    public UserStore(User[] Users) {
        this.Users = Users;
        index = 0;
    }

    private int getIndex() {
        return index;
    }

    private void increaseIndex() {
        index++;
    }

    // Вам нужно выбрать, как вы будете хранить ваших пользователей, например в массиве User users[] = new User[100];

    // проверить, есть ли пользователь с таким именем
    // если есть, вернуть true
    boolean isUserExist(String name) {
        boolean flag = false;

        for (User user : Users) {
            if (user != null && user.getName().equals(name)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    // Добавить пользователя в хранилище
    void addUser(User user) {
        if (!isUserExist(user.getName())) {
            Users[getIndex()] = user;
            increaseIndex();
        }
    }

    // Получить пользователя по имени и паролю
    User getUser(String name, String pass) {
        if (!isUserExist(name)) {
            return null;
        } else {
            User tempUser = null;
            for (User user : Users) {
                if (user.getName().equals(name)) {
                    tempUser = user;
                    break;
                }
            }

            if (tempUser != null && tempUser.getPass().equals(pass)) {
                return tempUser;
            } else {
                return null;
            }
        }
    }
}
