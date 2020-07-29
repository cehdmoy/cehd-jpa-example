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
        given:
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build()
        when:
        userControllerUT.createUser(userCreateRequest)
        then:
        1 * userService.createUser(userCreateRequest)

    }
}
