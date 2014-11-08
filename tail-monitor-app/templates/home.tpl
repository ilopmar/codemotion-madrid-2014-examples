yieldUnescaped '<!DOCTYPE html>'

html {
    head {
        title "Codemotion 2014"

        link(rel: 'stylesheet', href: '/css/app.css')

        ['webjars/sockjs-client/0.3.4/sockjs.js',
         'webjars/stomp-websocket/2.3.1/stomp.js',
         'webjars/jquery/2.1.1/jquery.min.js',
         'js/app.js']
        .each {
            yieldUnescaped "<script src='$it'></script>"
        }
    }

    body {
        div(id: 'messages') {
        }
    }
    yieldUnescaped "<script>connect()</script>"
}