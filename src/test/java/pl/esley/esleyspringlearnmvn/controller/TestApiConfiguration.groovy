package pl.esley.esleyspringlearnmvn.controller

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import pl.esley.esleyspringlearnmvn.service.PlayerApiService
import spock.mock.DetachedMockFactory

@Import([
        PlayerController
])
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration)
@EnableWebMvc
class TestApiConfiguration {

    def factory = new DetachedMockFactory()

    @Bean
    PlayerApiService playerApiService() {
        factory.Mock(PlayerApiService)
    }
}
