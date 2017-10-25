package org.zoltor.config;

import java.io.File;

public interface Constants {

    File IVY_SETTING_BASE_DIR = new File(System.getProperty("java.temp"));
    String IVY_RETRIEVE_ARTIFACT_PATTERN = "";// TODO Read it from the Idea settings
}
