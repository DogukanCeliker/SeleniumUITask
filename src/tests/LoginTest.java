package tests;

import org.testng.annotations.Test;
import pages.Login;

public class LoginTest extends MainTest {

    //String variables for error message
    String emptyPasswordError = "Lütfen şifre giriniz.";
    String emptyUsernameError = "Lütfen email adresinizi giriniz.";
    String wrongUsernameAndPasswordError = "Hatalı E-Posta / Şifre. Tekrar Deneyin.";


    //String variables for input username and password
    String correctMail = "doguceliker@gmail.com";
    String correctPassword = "Erbergama8";
    String incorrectMail = "dodo@gmail.com";
    String incorrectPassword = "12345678";

    //Testcase for inputting correct username and password
    @Test(priority = 0)
    public void correctLogin() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, correctMail);
        login.typeCredentials(login.passwordLabel, correctPassword);
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.loginContainer);
    }
    //Testcase for inputting correct username and incorrect password
    @Test(priority = 1)
    public void incorrectLoginWithPassword() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, correctMail);
        login.typeCredentials(login.passwordLabel, incorrectPassword);
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.errorBox);
        login.checkErrorMessageMatching(login.errorBox,wrongUsernameAndPasswordError);
    }
    //Testcase for inputting incorrect username and  correct password
    @Test(priority = 2)
    public void incorrectLoginWithUsername() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, incorrectMail);
        login.typeCredentials(login.passwordLabel, correctPassword);
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.errorBox);
        login.checkErrorMessageMatching(login.errorBox,wrongUsernameAndPasswordError);
    }
    //Testcase for inputting incorrect username and password.This case will be failed because it is expected to see container after login
    @Test(priority = 5)
    public void incorrectLoginWithBothLabels() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, incorrectMail);
        login.typeCredentials(login.passwordLabel, incorrectPassword);
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.loginContainer);
    }
    //Testcase for inputting empty password
    @Test(priority = 3)
    public void emptyPasswordLogin() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, correctMail);
        login.typeCredentials(login.passwordLabel, "");
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.errorBox);
        login.checkErrorMessageMatching(login.errorBox,emptyPasswordError);

    }
    //Testcase for inputting empty username
    @Test(priority = 4)
    public void emptyUsernameLogin() {
        Login login = new Login(driver, wait);
        login.LoginMethod(login);
        login.typeCredentials(login.emailLabel, "");
        login.typeCredentials(login.passwordLabel, correctPassword);
        login.click(login.submitBtn);
        login.checkElementDisplayed(login.errorBox);
        login.checkErrorMessageMatching(login.errorBox,emptyUsernameError);
    }

}
