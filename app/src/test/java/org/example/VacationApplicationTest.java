/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VacationApplicationTest {
    @Test void appHasAGreeting() {
        VacationApplication classUnderTest = new VacationApplication();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}