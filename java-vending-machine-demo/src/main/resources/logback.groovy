import ch.qos.logback.classic.AsyncAppender
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

def APPENDER = 'APPENDER'

appender(APPENDER, ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern='%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n'
    }
}

appender('ASYNC', AsyncAppender) {
    appenderRef(APPENDER)

    queueSize = 16
    discardingThreshold = 0
}

root(INFO, ['ASYNC'])
