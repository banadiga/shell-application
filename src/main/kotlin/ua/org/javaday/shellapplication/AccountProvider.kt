package ua.org.javaday.shellapplication

import org.springframework.core.MethodParameter
import org.springframework.shell.CompletionContext
import org.springframework.shell.CompletionProposal
import org.springframework.shell.standard.ValueProviderSupport
import org.springframework.stereotype.Component
import ua.org.ebank.clientbank.BankClient

@Component
class AccountProvider(val bankClient: BankClient) : ValueProviderSupport() {

    override fun complete(parameter: MethodParameter?, completionContext: CompletionContext?, hints: Array<out String>?):
            MutableList<CompletionProposal> = bankClient.accounts()
            .map { account ->
                CompletionProposal(account.id.toString())
                        .displayText(account.name)
                        .displayText(account.iban.toString())
                        .category("My account")
            }
            .toMutableList()

}
