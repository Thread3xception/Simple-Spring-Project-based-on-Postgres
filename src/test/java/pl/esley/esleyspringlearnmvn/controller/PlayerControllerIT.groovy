package pl.esley.esleyspringlearnmvn.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import pl.esley.esleyspringlearnmvn.exception.EntityNotFoundException
import pl.esley.esleyspringlearnmvn.model.Player
import pl.esley.esleyspringlearnmvn.model.dto.PlayerResponse
import pl.esley.esleyspringlearnmvn.service.PlayerApiService
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TestApiConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
// addFilter - wylacza filtry ktore posiada projekt w Spring Security (klasa: SecurityConfig)
class PlayerControllerIT extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    PlayerApiService apiService

    def "should invoke api service for return single player"() {
        given: 'player id'
        def playerId = 1l

        and: 'api service was called'
        1 * apiService.getSinglePlayerByIdWithCars(_ as Long) >> { Long pId ->
            {
                assert pId == playerId
            }

            PlayerResponse.builder()
                    .id(1)
                    .nickname('Patryk')
                    .yearsOld(22)
                    .build()
        }


        when: 'getting single player was called'
        def result = mockMvc.perform(get(getSingePlayerUrl(playerId)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString()

        then: 'there were no exceptions'
        noExceptionThrown()

        and: 'response correct'
        objectMapper.readTree(result) == objectMapper.readTree("""{"id":1,"nickname":"Patryk","yearsOld":22,"carPlates":null}""");
    }

    def "should throw exception when single player was not found"() {
        given: 'player id'
        def playerId = 100l

        and: 'api service was called'
        1 * apiService.getSinglePlayerByIdWithCars(playerId) >> { throw new EntityNotFoundException(Player.class, playerId) }

        when:
        apiService.getSinglePlayerByIdWithCars(playerId)

        then: 'exception message correct'
        def e = thrown(EntityNotFoundException.class)
        e.message == "Nie znaleziono obiektu Player o id: 100";
    }

    static url() {
        "/player"
    }

    static getSingePlayerUrl(Long playerId) {
        "${url()}/cars/$playerId"
    }
}
