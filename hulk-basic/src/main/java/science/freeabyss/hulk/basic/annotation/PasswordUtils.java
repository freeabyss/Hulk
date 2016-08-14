package science.freeabyss.hulk.basic.annotation;

/**
 * Created by abyss on 3/22/16.
 */
public class PasswordUtils {
    @UseCase(id = 39, description = "Password must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @UseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }
}
