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

package de.metanome.backend.result_postprocessing.results;

import com.fasterxml.jackson.annotation.JsonTypeName;

import de.metanome.algorithm_integration.ColumnCombination;
import de.metanome.algorithm_integration.results.UniqueColumnCombination;

/**
 * Represents an unique column combination result with different ranking values.
 */
@JsonTypeName("UniqueColumnCombinationResult")
public class UniqueColumnCombinationResult implements RankingResult {

  protected UniqueColumnCombination result;
  protected String tableName;

  // Needed for serialization
  public UniqueColumnCombinationResult() {}

  public UniqueColumnCombinationResult(UniqueColumnCombination result) {
    this.result = result;
    if (result.getColumnCombination().getColumnIdentifiers().size() > 0) {
      this.tableName =
          result.getColumnCombination().getColumnIdentifiers().iterator().next()
              .getTableIdentifier();
    } else {
      this.tableName = "";
    }
  }

  public UniqueColumnCombination getResult() {
    return this.result;
  }

  public ColumnCombination getColumnCombination() {
    return this.result.getColumnCombination();
  }

  public String getTableName() {
    return tableName;
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UniqueColumnCombinationResult other = (UniqueColumnCombinationResult) obj;
    return this.result.equals(other.getResult());
  }

}