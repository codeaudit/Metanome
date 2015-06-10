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

import de.metanome.algorithm_integration.ColumnPermutation;
import de.metanome.algorithm_integration.results.InclusionDependency;

/**
 * Represents an inclusion dependency result with different ranking values.
 */
@JsonTypeName("InclusionDependencyResult")
public class InclusionDependencyResult implements RankingResult {

  // Original result
  protected InclusionDependency result;

  // Table names of the dependant/referenced columns
  protected String dependantTableName;
  protected String referencedTableName;

  // How many columns of the table are involved
  // in the dependant/referenced side of the result?
  private float dependantColumnRatio;
  private float referencedColumnRatio;

  // On how many results are the columns of the
  // dependant/referenced side involved?
  private float dependantOccurrenceRatio;
  private float referencedOccurrenceRatio;

  // How many of the columns of the dependant/referenced
  // side are (almost) unique?
  private float dependantUniquenessRatio;
  private float referencedUniquenessRatio;

  // Needed for serialization
  public InclusionDependencyResult() {}

  public InclusionDependencyResult(InclusionDependency result) {
    this.result = result;
    if (result.getDependant().getColumnIdentifiers().size() > 0) {
      this.dependantTableName =
          result.getDependant().getColumnIdentifiers().get(0).getTableIdentifier();
    } else {
      this.dependantTableName = "";
    }
    if (result.getReferenced().getColumnIdentifiers().size() > 0) {
      this.referencedTableName =
          result.getReferenced().getColumnIdentifiers().get(0).getTableIdentifier();
    } else {
      this.referencedTableName = "";
    }
  }

  public InclusionDependency getResult() {
    return this.result;
  }

  public ColumnPermutation getDependant() {
    return this.result.getDependant();
  }

  public ColumnPermutation getReferenced() {
    return this.result.getReferenced();
  }

  public String getDependantTableName() {
    return dependantTableName;
  }

  public String getReferencedTableName() {
    return referencedTableName;
  }

  public float getDependantColumnRatio() {
    return dependantColumnRatio;
  }

  public void setDependantColumnRatio(float dependantColumnRatio) {
    this.dependantColumnRatio = dependantColumnRatio;
  }

  public float getReferencedColumnRatio() {
    return referencedColumnRatio;
  }

  public void setReferencedColumnRatio(float referencedColumnRatio) {
    this.referencedColumnRatio = referencedColumnRatio;
  }

  public float getDependantOccurrenceRatio() {
    return dependantOccurrenceRatio;
  }

  public void setDependantOccurrenceRatio(float dependantOccurrenceRatio) {
    this.dependantOccurrenceRatio = dependantOccurrenceRatio;
  }

  public float getReferencedOccurrenceRatio() {
    return referencedOccurrenceRatio;
  }

  public void setReferencedOccurrenceRatio(float referencedOccurrenceRatio) {
    this.referencedOccurrenceRatio = referencedOccurrenceRatio;
  }

  public float getDependantUniquenessRatio() {
    return dependantUniquenessRatio;
  }

  public void setDependantUniquenessRatio(float dependantUniquenessRatio) {
    this.dependantUniquenessRatio = dependantUniquenessRatio;
  }

  public float getReferencedUniquenessRatio() {
    return referencedUniquenessRatio;
  }

  public void setReferencedUniquenessRatio(float referencedUniquenessRatio) {
    this.referencedUniquenessRatio = referencedUniquenessRatio;
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
    InclusionDependencyResult other = (InclusionDependencyResult) obj;
    return this.result.equals(other.result);
  }
}