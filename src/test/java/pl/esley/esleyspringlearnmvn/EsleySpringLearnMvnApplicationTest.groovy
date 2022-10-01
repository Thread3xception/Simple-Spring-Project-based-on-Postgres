package pl.esley.esleyspringlearnmvn

import spock.lang.Specification

class EsleySpringLearnMvnApplicationTest extends Specification {

    def "should add 2"() {
        given:
        def a = 18
        when:
        def response = a+2
        then:
        response == 20
    }
}
