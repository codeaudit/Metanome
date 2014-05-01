/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.uni_potsdam.hpi.metanome.results_db;

import de.uni_potsdam.hpi.metanome.test_helper.EqualsAndHashCodeTester;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link de.uni_potsdam.hpi.metanome.results_db.Algorithm}
 *
 * @author Jakob Zwiener
 */
public class AlgorithmTest {

    /**
     * Test method for {@link Algorithm#store(Algorithm)} and {@link Algorithm#retrieve(String)}
     * <p/>
     * Algorithms should be storable and retrievable by id.
     */
    @Test
    public void testPersistence() throws EntityStorageException {
        // Setup
        HibernateUtil.clear();

        // Expected values
        String expectedFileName = "someFileName";
        Algorithm expectedAlgorithm = new Algorithm(expectedFileName);

        // Execute functionality
        Algorithm.store(expectedAlgorithm);
        Algorithm actualAlgorithm = Algorithm.retrieve(expectedFileName);

        // Check result
        assertEquals(expectedAlgorithm, actualAlgorithm);

        // Cleanup
        HibernateUtil.clear();
    }

    /**
     * Test method for {@link Algorithm#retrieveAll()}
     */
    @Test
    public void testRetrieveAll() throws EntityStorageException {
        // Setup
        HibernateUtil.clear();

        Algorithm expectedAlgorithm1 = new Algorithm("some file name 1");
        Algorithm.store(expectedAlgorithm1);
        Algorithm expectedAlgorithm2 = new Algorithm("some file name 2");
        Algorithm.store(expectedAlgorithm2);

        // Execute functionality
        Collection<Algorithm> actualAlgorithms = Algorithm.retrieveAll();

        // Check result
        assertThat(actualAlgorithms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedAlgorithm1, expectedAlgorithm2));

        // Cleanup
        HibernateUtil.clear();
    }

    /**
     * Test method for {@link de.uni_potsdam.hpi.metanome.results_db.Algorithm#equals(Object)} and
     * {@link de.uni_potsdam.hpi.metanome.results_db.Algorithm#hashCode()}
     */
    @Test
    public void testEqualsHashCode() {
        // Setup
        // Expected values
        String expectedFileName1 = "someFileName1";
        String expectedFileName2 = "someFileName2";
        Algorithm algorithm = new Algorithm(expectedFileName1);
        Algorithm algorithmEqual = new Algorithm(expectedFileName1);
        Algorithm algorithmNotEqual = new Algorithm(expectedFileName2);

        // Execute functionality
        // Check result
        new EqualsAndHashCodeTester<Algorithm>()
                .performBasicEqualsAndHashCodeChecks(algorithm, algorithmEqual, algorithmNotEqual);
    }
}