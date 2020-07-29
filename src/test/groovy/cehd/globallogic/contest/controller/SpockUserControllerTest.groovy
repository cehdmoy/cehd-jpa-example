package cehd.globallogic.contest.controller

import cehd.globallogic.contest.SpockUnitTest
import cehd.globallogic.contest.dto.request.UserCreateRequest
import cehd.globallogic.contest.service.UserService
import org.junit.experimental.categories.Category
import spock.lang.Specification

@Category(SpockUnitTest.class)
class SpockUserControllerTest extends Specification {

    def userService = Mock(UserService.class)

    def userControllerUT = new UserController(userService)

    def 'check user service interactions when create a new user'() {
        given: 'a generic request to create a new user'
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build()
        when: 'execute the createUserMethod'
        userControllerUT.createUser(userCreateRequest)
        then: 'Check the user service has been called'
        1 * userService.createUser(userCreateRequest)

    }

    def 'check user service interactions when delete an  user'() {
        given: 'an user id to delete the user'
        String user_id = '124199dd-bb25-4101-bdc0-269dbfedad9b'
        when: 'execute the deleteUser'
        userControllerUT.deleteUser(user_id)
        then: 'Check the user service has been called'
        1 * userService.deleteUser(user_id)

    }

    def 'check user service interactions when retrieve an  user'() {
        given: 'an user id to retrieve the user'
        String user_id = '124199dd-bb25-4101-bdc0-269dbfedad9b'
        when: 'execute the retrieve'
        userControllerUT.retrieveUser(user_id)
        then: 'Check the user service has been called'
        1 * userService.retrieveUser(user_id)

    }

}
