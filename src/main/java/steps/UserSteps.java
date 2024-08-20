package steps;

import config.RestConfig;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.User;

import static config.Constants.TOKEN;
import static config.EndPoints.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserSteps extends RestConfig {
    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user) {
        return given()
                .body(user)
                .post(CREATE_USER)
                .then();
    }

    @Step("Авторизация пользователя")
    public ValidatableResponse login(User user) {
        return given()
                .body(user)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("Получение токена")
    public String getToken(User user) {
        ValidatableResponse loginResponse = given()
                .body(user)
                .when()
                .post(LOGIN)
                .then()
                .assertThat()
                .statusCode(SC_OK);

        String token = loginResponse.extract().path(TOKEN);
        if (token == null) {
            throw new IllegalArgumentException("Token is null");
        }
        return token;
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(User user) {
        return given()
                .header(TOKEN, user.getAccessToken())
                .when()
                .delete(USER_DELETE)
                .then();
    }
}
