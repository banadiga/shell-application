package ua.org.javaday.shellapplication

import org.springframework.shell.Availability
import org.springframework.shell.Availability.available
import org.springframework.shell.Availability.unavailable
import org.springframework.shell.standard.*
import org.springframework.shell.table.BeanListTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.Table
import org.springframework.shell.table.TableBuilder
import ua.org.ebank.clientbank.BankClient
import ua.org.ebank.clientbank.IBAN

@ShellComponent
@ShellCommandGroup("Account commands")
class AccountCli(val bankClient: BankClient) {

    @ShellMethod("Show my accounts.")
    fun list(): Table = TableBuilder(BeanListTableModel(bankClient.accounts(), "name", "iban.countryCode", "iban.accountNumber"))
            .addFullBorder(BorderStyle.fancy_heavy)
            .build()

    @ShellMethod("Account details.")
    fun accountGet(
            @ShellOption(
                    valueProvider = AccountProvider::class,
                    help = "ID for accounts"
            )
            id: Long) = bankClient.account(id)

    @ShellMethod("Create new account.")
    fun accountCreate(name: String, iban: IBAN) = bankClient.create(name, iban)

    @ShellMethodAvailability
    fun checkAvailability(): Availability = if (bankClient.currentUser().isNotBlank()) {
        available()
    } else {
        unavailable("you are not logged in")
    }
}
