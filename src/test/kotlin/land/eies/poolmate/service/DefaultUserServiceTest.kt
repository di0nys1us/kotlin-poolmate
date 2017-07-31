package land.eies.poolmate.service

import land.eies.poolmate.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class))
@Transactional
class DefaultUserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun findUser() {
        val user = userService.findUser(1L)

        println(user)
    }
}
