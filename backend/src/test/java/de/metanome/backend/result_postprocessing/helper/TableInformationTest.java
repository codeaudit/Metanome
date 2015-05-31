/*
 * Copyright 2015 by the Metanome project
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

package de.metanome.backend.result_postprocessing.helper;

import de.metanome.algorithm_integration.input.InputGenerationException;
import de.metanome.algorithm_integration.input.InputIterationException;
import de.metanome.algorithm_integration.input.RelationalInput;
import de.metanome.algorithm_integration.input.RelationalInputGenerator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TableInformationTest {

  FileFixture fileFixture = new FileFixture();
  RelationalInputGenerator relationalInputGenerator = new RelationalInputGenerator() {
    @Override
    public RelationalInput generateNewCopy() throws InputGenerationException {
      try {
        return fileFixture.getTestData();
      } catch (InputIterationException e) {
        return null;
      }
    }
  };

  @Test
  public void testSetUpWithoutDataDependentStatistics()
      throws InputIterationException, InputGenerationException {
    // Execute Functionality
    TableInformation tableInformation = new TableInformation(relationalInputGenerator, false);

    // Check
    assertEquals(4, tableInformation.getColumnCount());
    assertEquals("some_file", tableInformation.getTableName());
    assertEquals(4, tableInformation.getColumnInformationList().size());
    assertNotNull(tableInformation.getRelationalInputGenerator());
    assertEquals(0L, tableInformation.getRowCount());
    assertEquals(0L, tableInformation.getInformationContent());
  }

  @Test
  public void testSetUpWithDataDependentStatistics()
      throws InputIterationException, InputGenerationException {
    // Execute Functionality
    TableInformation tableInformation = new TableInformation(relationalInputGenerator, true);

    // Check
    assertEquals(4, tableInformation.getColumnCount());
    assertEquals("some_file", tableInformation.getTableName());
    assertEquals(4, tableInformation.getColumnInformationList().size());
    assertNotNull(tableInformation.getRelationalInputGenerator());
    assertEquals(11, tableInformation.getRowCount());
    assertEquals(190, tableInformation.getInformationContent());
  }

}