package ua.org.javaday.shellapplication

import org.springframework.shell.Availability
import org.springframework.shell.Availability.available
import org.springframework.shell.Availability.unavailable
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

    fun loginAvailability(): Availability = if (bankClient.currentUser().isBlank()) {
        available()
    } else {
        unavailable("you are already logged in")
    }

    @ShellMethod("Logout from the bank.")
    fun logout(): String {
        bankClient.logout()
        return "Logout successful"
    }

    fun logoutAvailability(): Availability = if (bankClient.currentUser().isNotBlank()) {
        available()
    } else {
        unavailable("you are not logged in")
    }
}
