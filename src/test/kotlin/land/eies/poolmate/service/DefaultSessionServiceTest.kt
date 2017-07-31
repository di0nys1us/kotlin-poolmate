package land.eies.poolmate.service

import land.eies.poolmate.Application
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.Month

@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(Application::class))
@Transactional
class DefaultSessionServiceTest {

    @Autowired
    lateinit var sessionService: SessionService

    @Test
    fun findSessions() {
        val sessions = sessionService.findSessions(
                1L,
                LocalDate.of(2010, Month.JANUARY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31)
        )

        println(sessions)
    }
}
