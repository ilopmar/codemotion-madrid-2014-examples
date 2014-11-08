package es.codemotion

import groovy.util.logging.Slf4j

@Slf4j
@EnableScheduling
class RandomData {

    private static List WORDS = ['Aenean', 'Curabitur', 'Donec', 'Duis', 'Etiam', 'Fusce', 'In', 'Integer', 'Lorem', 'Mauris', 'Nam', 'Pellentesque', 'Quisque', 'Sed', 'Suspendisse', 'Vestibulum', 'A', 'Ac', 'Accumsan', 'Adipiscing', 'Aliquet', 'Amet', 'Ante', 'Arcu', 'At', 'Augue', 'Bibendum', 'Commodo', 'Consectetur', 'Consequat', 'Convallis', 'Diam', 'Dictum', 'Dignissim', 'Dolor', 'Eleifend', 'Elementum', 'Erat', 'Eros', 'Est', 'Eu', 'Ex', 'Faucibus', 'Felis', 'Fermentum', 'Finibus', 'Fringilla', 'Gravida', 'Hendrerit', 'Imperdiet', 'In', 'Ipsum', 'Lacinia', 'Lacus', 'Laoreet', 'Leo', 'Libero', 'Lobortis', 'Magna', 'Massa', 'Mattis', 'Mauris', 'Maximus', 'Metus', 'Mi', 'Molestie', 'Mollis', 'Nec', 'Neque', 'Non', 'Nulla', 'Nunc', 'Orci', 'Pellentesque', 'Pharetra', 'Placerat', 'Porta', 'Purus', 'Quam', 'Quis', 'Risus', 'Rutrum', 'Sapien', 'Sed', 'Sem', 'Semper', 'Sit', 'Sollicitudin', 'Suscipit', 'Tellus', 'Tempus', 'Tincidunt', 'Turpis', 'Ullamcorper', 'Ut', 'Vel', 'Velit', 'Venenatis', 'Vestibulum', 'Vitae', 'Vulputate']
    Random rnd = new Random()

    @Scheduled(fixedRate = 2000L)
    void generateRandomLog() {
        Collections.shuffle(WORDS)
        log.info WORDS[0..rnd.nextInt(20)].join(" ")
    }
}