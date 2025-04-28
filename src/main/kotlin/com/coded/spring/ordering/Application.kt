package com.coded.spring.ordering

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast
import com.hazelcast.core.HazelcastInstance


@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}

val orderingConfig = Config("Ordering-config")
val serverCache: HazelcastInstance = Hazelcast.newHazelcastInstance(orderingConfig)