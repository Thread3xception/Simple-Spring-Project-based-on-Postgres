package pl.esley.esleyspringlearnmvn.controller

import spock.lang.Specification

class ApplicationTest extends Specification {

    def "should add 2"() {
        given:
        def a = 18
        when:
        def response = a+2
        then:
        response == 20
    }
}
