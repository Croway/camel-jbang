///usr/bin/env jbang "$0" "$@" ; exit $?
//DEPS org.apache.camel:camel-bom:3.8.0@pom
//DEPS org.apache.camel:camel-core
//DEPS org.slf4j:slf4j-simple:1.7.30

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * A basic example running as public static void main.
 */
public final class CamelBasic {

    public static void main(String[] args) throws Exception {
        // create a CamelContext
        try (CamelContext camel = new DefaultCamelContext()) {

            // add routes which can be inlined as anonymous inner class
            // (to keep all code in a single java file for this basic example)
            camel.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("timer:foo")
                            .log("Hello Camel");
                }
            });

            // start is not blocking
            camel.start();

            // so run for 10 seconds
            Thread.sleep(10_000);

            // and then stop nicely
            camel.stop();
        }
    }
}
