package pl.esley.esleyspringlearnmvn.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import pl.esley.esleyspringlearnmvn.model.dto.PlayerResponse
import pl.esley.esleyspringlearnmvn.service.PlayerApiService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestApiConfiguration.class)
@AutoConfigureMockMvc(addFilters = false) // addFilter - wylacza filtry ktore posiada projekt w Spring Security (klasa: SecurityConfig)
class PlayerControllerIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    PlayerApiService apiService

    def "should invoke api service for return single player"() {
        given: 'player id'
        def playerId = 0l

        and: 'api service was called'
        1 * apiService.getSinglePlayerByIdWithCars(_ as Long) >> { Long pId ->
            {
                assert pId == playerId
            }

            PlayerResponse.builder()
                    .nickname('Patryk')
                    .yearsOld(22)
                    .build()
        }


        when: 'getting single player was called'
        def result = mockMvc.perform(get(getSingePlayerUrl(String.valueOf(playerId))))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()

        then: 'there were no exceptions'
        noExceptionThrown()

        and: 'response correct'
        objectMapper.readTree(result) == objectMapper.readTree("""{"id":0,"nickname":"Patryk","yearsOld":22,"carPlates":null}""");
    }

    static url() {
        "/player"
    }

    static getSingePlayerUrl(String playerId) {
        "${url()}/cars/$playerId"
    }
}
