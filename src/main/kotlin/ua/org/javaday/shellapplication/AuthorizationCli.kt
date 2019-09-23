package ua.org.javaday.shellapplication

import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellOption
import ua.org.ebank.clientbank.BankClient

@ShellComponent
@ShellCommandGroup("Authorization commands")
class AuthorizationCli(val bankClient: BankClient) {

    @ShellMethod("Login in to the bank.", key = ["login", "open"])
    fun login(
            @ShellOption(
                    value = ["-l", "--login"],
                    help = "Username"
            )
            username: String): String {
        print("Password:")
        val password = String(System.console().readPassword())
        bankClient.login(username, password)
        return "Login successful"
    }
}
