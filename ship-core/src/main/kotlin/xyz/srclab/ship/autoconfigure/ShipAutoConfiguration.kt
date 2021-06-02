package xyz.srclab.ship.autoconfigure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import xyz.srclab.ship.properties.PropertiesBeanFactoryPostProcessor

@Configuration
open class ShipAutoConfiguration {

    @Bean("xyz.srclab.ship.properties.PropertiesBeanFactoryPostProcessor")
    open fun propertiesBeanFactoryPostProcessor(): PropertiesBeanFactoryPostProcessor {
        return PropertiesBeanFactoryPostProcessor()
    }
}