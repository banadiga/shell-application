package ua.org.javaday.shellapplication

import com.github.fonimus.ssh.shell.postprocess.PostProcessor
import org.springframework.stereotype.Component
import ua.org.ebank.clientbank.Account

@Component
class IBANPostProcessor : PostProcessor<Account> {
    override fun getName(): String = "iban"

    override fun process(result: Account, parameters: MutableList<String>?): String = result.iban.toString()
}
