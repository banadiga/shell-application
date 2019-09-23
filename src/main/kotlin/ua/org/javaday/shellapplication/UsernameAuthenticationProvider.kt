package ua.org.javaday.shellapplication

import com.github.fonimus.ssh.shell.auth.SshShellAuthenticationProvider
import org.apache.sshd.server.session.ServerSession
import org.springframework.stereotype.Component
import ua.org.ebank.clientbank.BankClient

@Component
class UsernameAuthenticationProvider(val bankClient: BankClient) : SshShellAuthenticationProvider {

    override fun authenticate(username: String, password: String, session: ServerSession): Boolean {
        bankClient.login(username, password)
        return true
    }
}
