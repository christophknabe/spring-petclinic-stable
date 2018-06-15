/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * PetClinic Spring Boot Application.
 *
 * @author Dave Syer
 *
 */
@SpringBootApplication
public class PetClinicApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PetClinicApplication.class, args);
    }

    /**
     * This is an example how to start a bean yourself with access to the command
     * line arguments and other injected beans.
     */
    @Bean
    @Autowired
    public CommandLineRunner startCommandLineRunner(final ApplicationContext applicationContext) {
        return args -> {
            System.out.println("\n==========Spring Application started==========\n");
            _printEnvironment();
            _displayAllBeans(applicationContext);
        };
    }

    /**Prints the names and values of all environment variables, ordered by name.*/
    private void _printEnvironment() {
        final Map<String, String> environment = System.getenv();
        final List<Map.Entry<String, String>> envVariables = new ArrayList(environment.entrySet());
        Collections.sort(envVariables, (left, right) -> left.getKey().compareToIgnoreCase(right.getKey()));
        final StringBuilder out = new StringBuilder();
        out.append("\nEnvironment Variables\n");
        out.append("=======================================================\n");
        for(final Map.Entry<String, String> envVar: envVariables){
            out.append(envVar.getKey());
            out.append("=");
            out.append(envVar.getValue());
            out.append('\n');
        }
        System.out.println(out);
    }

    /**Displays the names of all Spring beans in the given application context.*/
    private void _displayAllBeans(final ApplicationContext applicationContext) {
        final StringBuilder out = new StringBuilder();
        out.append("\n====================Application.displayAllBeans====================\n");
        final String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (final String beanName : allBeanNames) {
            out.append(beanName);
            out.append('\n');
        }
        System.out.println(out);
    }

}
