package ua.org.javaday.shellapplication

import org.jline.utils.AttributedString
import org.jline.utils.AttributedStyle.*
import org.springframework.shell.jline.PromptProvider
import org.springframework.stereotype.Component
import ua.org.ebank.clientbank.BankClient

@Component
class UsernamePromptProvider(val bankClient: BankClient) : PromptProvider {

    override fun getPrompt(): AttributedString = if (bankClient.currentUser().isBlank()) {
        AttributedString("You are not logged in:>", DEFAULT.foreground(RED))
    } else {
        AttributedString("${bankClient.currentUser()}:>", DEFAULT.foreground(GREEN))
    }
}
