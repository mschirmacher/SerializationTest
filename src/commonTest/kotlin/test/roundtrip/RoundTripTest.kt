package test.roundtrip

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import test.model.DomainEvent
import kotlin.test.Test
import kotlin.test.assertEquals

class RoundTripTest {

    private val json = Json(configuration = JsonConfiguration.Stable.copy(strictMode = false))
    private val serializer = TestableDomainEvent.serializer()

    @Test
    fun testExchangeRoundTrip() {
        val domainEvent = TestableDomainEvent(1, "something")
        val serializedDomainEvent = json.stringify(serializer, domainEvent)

        val deserializedDomainEvent = json.parse(serializer, serializedDomainEvent)


        assertEquals(domainEvent, deserializedDomainEvent)
    }
}


@Serializable
data class TestableDomainEvent(val id: Long, val name: String, override val key: String = "TestableDomainEvent") :
        DomainEvent()
