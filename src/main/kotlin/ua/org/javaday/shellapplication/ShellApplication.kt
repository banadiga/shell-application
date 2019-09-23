package ua.org.javaday.shellapplication

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import ua.org.ebank.clientbank.MyBankClient

@SpringBootApplication
class ShellApplication {

    @Bean
    fun bankClient() = MyBankClient()
}

fun main(args: Array<String>) {
    runApplication<ShellApplication>(*args)
}
