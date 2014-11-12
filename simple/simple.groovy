@RestController
class ThisWillActuallyRun {

    @RequestMapping("/")
    String home() {
        'Hello Codemotion!'
    }
}
