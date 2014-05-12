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

package de.uni_potsdam.hpi.metanome.input.csv;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

public class CsvFileFixture {

    protected static final char QUOTE_CHAR = '"';
    protected static final char SEPARATOR = ',';

    protected FileFixture fileFixture;

    public CsvFileFixture() {
        this.fileFixture = new FileFixture(getCsvFileData());
    }

    public File getTestDataPath(String fileName) throws FileNotFoundException, UnsupportedEncodingException {
        return fileFixture.getTestData(fileName);
    }

    protected String getCsvFileData() {
        StringBuilder fileDataBuilder = new StringBuilder();

        fileDataBuilder.append(Joiner.on(SEPARATOR).join(quoteStrings(expectedHeader())));
        fileDataBuilder.append(System.getProperty("line.separator"));

        fileDataBuilder.append(Joiner.on(SEPARATOR).join(quoteStrings(expectedFirstLine())));
        fileDataBuilder.append(System.getProperty("line.separator"));

        fileDataBuilder.append(Joiner.on(SEPARATOR).join(quoteStrings(expectedSecondLine())));

        return fileDataBuilder.toString();
    }

    protected List<String> quoteStrings(List<String> unquotedStrings) {
        List<String> quotedStrings = new LinkedList<>();

        for (String unquotedString : unquotedStrings) {
            quotedStrings.add(QUOTE_CHAR + unquotedString + QUOTE_CHAR);
        }

        return quotedStrings;
    }

    public ImmutableList<String> expectedHeader() {
        return ImmutableList.of("one", "two", "three");
    }

    public ImmutableList<String> expectedFirstLine() {
        return ImmutableList.of("3", "4", "5");
    }

    public ImmutableList<String> expectedSecondLine() {
        return ImmutableList.of("6", "7", "8");
    }

}
