import com.hazelcast.config.Config
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrderingApplication

fun main(args: Array<String>) {
    runApplication<OrderingApplication>(*args)
}

val orderingCacheConfig = Config("hello-world-cache")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(orderingCacheConfig)