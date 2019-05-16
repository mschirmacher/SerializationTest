package test.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
abstract class DomainEvent(val eventVersion: Int = 1) {

    val occurredOn: Long = 0L

    @Transient // Must be transient to avoid a bug which would lead `key` appear in the json twice
    abstract val key: String
}
