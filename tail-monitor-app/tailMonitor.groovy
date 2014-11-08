package es.codemotion

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.file.tail.OSDelegatingFileTailingMessageProducer
import org.springframework.messaging.simp.SimpMessagingTemplate

@Slf4j
@Configuration
class FileMonitor {
    @Bean
    MessageChannel tailChannel() {
        new DirectChannel()
    }

    @Bean
    OSDelegatingFileTailingMessageProducer tailFile() {
        def tailFile = new OSDelegatingFileTailingMessageProducer()
        tailFile.file = new File('my-app.log')
        tailFile.outputChannel = tailChannel()
        tailFile
    }
}

@Slf4j
@Configuration
@MessageEndpoint
class MonitorService {
    @Autowired
    SimpMessagingTemplate template

    @ServiceActivator(inputChannel = "tailChannel")
    void processMessage(String data) {
        log.debug data
        template.convertAndSend('/notifications/log', data)
    }
}

@Configuration
@EnableWebSocketMessageBroker
@Grab("org.webjars:sockjs-client:0.3.4")
@Grab("org.webjars:stomp-websocket:2.3.1")
class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker '/notifications'
    }

    @Override
    void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint('/monitor').withSockJS()
    }
}

@Slf4j
@Controller
@Grab("org.webjars:jquery:2.1.1")
@Grab("org.codehaus.groovy:groovy-templates:2.3.7")
class HomeController {
    @RequestMapping("/")
    def home() {
        new ModelAndView('home')
    }
}
