package ua.org.javaday.shellapplication

import org.springframework.shell.Availability
import org.springframework.shell.Availability.available
import org.springframework.shell.Availability.unavailable
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import org.springframework.shell.standard.ShellMethodAvailability
import ua.org.ebank.clientbank.Account
import ua.org.ebank.clientbank.BankClient

@ShellComponent
@ShellCommandGroup("Account commands")
class AccountCli(val bankClient: BankClient) {

    @ShellMethod("Show my accounts.")
    fun list(): List<Account> {
        return bankClient.accounts()
    }

    @ShellMethodAvailability
    fun checkAvailability(): Availability = if (bankClient.currentUser().isNotBlank()) {
        available()
    } else {
        unavailable("you are not logged in")
    }
}
