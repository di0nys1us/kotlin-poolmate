package land.eies.poolmate.service.dto

import java.io.Serializable
import java.time.LocalDate

data class SessionDTO(
        val id: Long,
        val date: LocalDate,
        val poolLength: Int,
        val calories: Int
) : Serializable
