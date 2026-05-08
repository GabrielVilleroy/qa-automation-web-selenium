package tests;

import base.BaseTest;

import org.junit.jupiter.api.Test;

import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {

    @Test
    public void loginComSucesso() {

        LoginPage login = new LoginPage(driver);

        login.preencherUsuario("standard_user");

        login.preencherSenha("secret_sauce");

        tirarPrint("01-login-preenchido");

        login.clicarLogin();

        tirarPrint("02-usuario-logado");

        String urlAtual = driver.getCurrentUrl();

        assertTrue(
                urlAtual.contains("inventory"),
                "Usuário não logou com sucesso"
        );
    }
    @Test
    public void loginInvalido() {

        LoginPage login = new LoginPage(driver);

        login.preencherUsuario("usuario_errado");

        login.preencherSenha("senha_errada");

        tirarPrint("03-login-invalido-preenchido");


        login.clicarLogin();

        tirarPrint("04-mensagem-erro-login");

        String mensagemErro =
                login.obterMensagemErro();

        assertTrue(
                mensagemErro.contains(
                        "Username and password do not match"
                )
        );
    }

}
