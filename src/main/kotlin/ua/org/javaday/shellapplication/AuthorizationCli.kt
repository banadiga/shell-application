package ua.org.javaday.shellapplication

import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ua.org.ebank.clientbank.BankClient

@ShellComponent
class AuthorizationCli(val bankClient: BankClient) {

    @ShellMethod("Login in to the bank.")
    fun login(username: String, password: String) {
        bankClient.login(username, password)
    }
}
