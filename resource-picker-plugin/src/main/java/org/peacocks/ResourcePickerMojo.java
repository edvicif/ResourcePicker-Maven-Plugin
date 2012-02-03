package org.peacocks;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Goal which touches a timestamp file.
 *
 * @goal touch
 *
 * @phase process-sources
 */
public class ResourcePickerMojo
        extends AbstractMojo {

    /**
     * Location of the file. 
     * @parameter expression="${project.build.sourceDirectory}/com/mycompany" @required
     */
    private File outputDirectory;
    /**
     * @parameter expression="${basedir}/src/main/resources" 
     * @required
     */
    private File scanDirectory;

    public void execute()
            throws MojoExecutionException {
        ensureOutputDirectoryExists();
        System.out.println(scanDirectory);
        File enumFile = new File(outputDirectory, "R.java");
        writeEnum(enumFile, listFilesInScanDirectory());
    }

    private void ensureOutputDirectoryExists() {
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }
    }

    private String escapeFilename(File file) {
        return file.getName().toUpperCase().replaceAll("\\.", "_");
    }

    private void writeEnum(File touch, List<File> listFilesInScanDirectory) throws MojoExecutionException {
        FileWriter w = null;


        try {
            w = new FileWriter(touch);

            w.write(enumContent(listFilesInScanDirectory));
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }

    private String enumContent(List<File> listFilesInScanDirectory) {
        return "package com.mycompany;\n"
                + "public enum R {\n\n"
                + generateEnumStringFromFileList(listFilesInScanDirectory) + "\n\n"
                + constructor() + "\n\n"
                + "}";
    }

    private List<File> listFilesInScanDirectory() {
        if (scanDirectory.exists()) {
            return Arrays.asList(scanDirectory.listFiles());
        }
        return new ArrayList<File>();
    }

    private String generateEnumStringFromFileList(List<File> listFilesInScanDirectory) {
        StringBuilder result = new StringBuilder();
        for (File file : listFilesInScanDirectory) {
            result.append(escapeFilename(file))
                    .append("(\"").append(file.getName()).append("\")")
                    .append(",");
        }
        result.append(";");
        return result.toString();
    }

    private String constructor() {
        return "private R(String fileName) {\n"
                + "}";
    }
}
