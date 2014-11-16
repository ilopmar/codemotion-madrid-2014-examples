# Codemotion 2014

This is the demo project for my talk [Spring ha muerto... ¡Larga vida a Spring Boot!](http://2014.codemotion.es/en/agenda.html?recommended=#day2/spring-ha-muerto-larga-vida-a-spring-boot) from Codemotion 2014.

## Demos

The project repo has the following demos

### Simple

A very simple Spring Boot CLI app. To execute it:

```
spring run simple.groovy
```

### Tail monitor App

A Spring Boot CLI app that uses Spring Integration and Websockets.

- First run the log generator:

```
spring run logGenerator.groovy | tee my-app.log
```

- Now open another terminal and execute the monitor:

```
spring run tailMonitor.groovy
```

- And finally open a browser and go to:

```
http://localhost:8080
```

### Api-Demo

A very simple API demo app using Spring Boot and Gradle. Execute it with

```
gradle bootRun
```

The slides of the talk are available at [slideshare](http://www.slideshare.net/ilopmar/codemotion-2014-spring-ha-muerto-larga-vida-a-spring-boot).

You can contact me at lopez.ivan@gmail.com or via twitter [@ilopmar](https://twitter.com/ilopmar)