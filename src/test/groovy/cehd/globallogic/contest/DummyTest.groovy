package cehd.globallogic.contest


import org.junit.experimental.categories.Category
import spock.lang.Specification


@Category(SpockUnitTest.class)
class DummyTest extends Specification {

    def 'Should not be run'() {
        expect: 'Should return the correct message'
        println 'Should not be run'
        'Hello World!_' == 'Hello World!'
    }
}