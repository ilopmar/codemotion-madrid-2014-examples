package es.codemotion

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

    static void main(String[] args) {
        SpringApplication.run Application, args
    }
}

@Entity
class User {

    @Id
    @GeneratedValue
    Long id

    String name
    String lastName
}

interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findByName(@Param("name") String name)
}

@RestController
@RequestMapping("/users")
class UserRestController {

    @Autowired
    UserRepository userRepository

    @RequestMapping(method = RequestMethod.GET)
    Collection<User> listAllUsers() {
        this.userRepository.findAll()
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    User getUser(@PathVariable(value = "userId") Long id) {
        id ? userRepository.findOne(id) : userRepository.findAll()
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    Collection<User> findUserByName(@RequestParam String name) {
        this.userRepository.findByName(name)
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    User saveUser(@RequestBody User user) {
        this.userRepository.save(user)
    }
}

@ConfigurationProperties(value = 'application')
class ApplicationProperties {
    String name
    String version
}

@RestController
@RequestMapping("/appinfo")
class InfoController {

    @Autowired
    ApplicationProperties applicationProperties

    @Bean
    ApplicationProperties applicationProperties() {
        new ApplicationProperties()
    }

    @RequestMapping(method = RequestMethod.GET)
    Map getInfo() {
        [
            name   : applicationProperties.name,
            version: applicationProperties.version
        ]
    }
}