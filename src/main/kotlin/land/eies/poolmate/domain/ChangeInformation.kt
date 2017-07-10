package land.eies.poolmate.domain

import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Embeddable

@Embeddable
data class ChangeInformation(
        var createdBy: String,
        var createdAt: LocalDateTime,
        var modifiedBy: String,
        var modifiedAt: LocalDateTime
) : Serializable
