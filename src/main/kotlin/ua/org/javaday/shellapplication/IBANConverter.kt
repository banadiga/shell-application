package ua.org.javaday.shellapplication

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.org.ebank.clientbank.IBAN

@Component
class IBANConverter : Converter<String, IBAN> {

    override fun convert(source: String): IBAN? {
        return IBAN(
                countryCode = source.substring(0, 2),
                checkSun = source.substring(2, 4),
                bankCode = source.substring(4, 10),
                accountNumber = source.substring(10, 29)
        )
    }
}
