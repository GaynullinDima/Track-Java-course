package ru.mail.track;

/**
 * Created by k33p on 29.09.15.
 */
public class UserStore {

    private User[] Users;
    private int index;

    public UserStore() {
        this.Users = new User[100];
        this.index = 0;
    }

    public UserStore(User[] Users) {
        this.Users = Users;
        this.index = 0;
    }

    private int getIndex() {
        return index;
    }

    private void increaseIndex() {
        this.index++;
    }

    // Вам нужно выбрать, как вы будете хранить ваших пользователей, например в массиве User users[] = new User[100];

    // проверить, есть ли пользователь с таким именем
    // если есть, вернуть true
    boolean isUserExist(String name) {
        boolean flag = false;

        for (User user : this.Users) {
            if (user != null && user.getName().equals(name)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    // Добавить пользователя в хранилище
    void addUser(User user) {
        if (!this.isUserExist(user.getName())) {
            this.Users[this.getIndex()] = user;
            this.increaseIndex();
        }
    }

    // Получить пользователя по имени и паролю
    User getUser(String name, String pass) {
        if (!this.isUserExist(name)) {
            return null;
        } else {
            User tempUser = null;
            for (User user : this.Users) {
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
