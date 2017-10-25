package org.zoltor.ivy;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.retrieve.RetrieveOptions;
import org.apache.ivy.core.settings.IvySettings;
import org.zoltor.config.Constants;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class IvyConfigurator {

    private final IvyModuleInfo ivyModuleInfo;

    public IvyConfigurator(IvyModuleInfo ivyModuleInfo) {
        this.ivyModuleInfo = ivyModuleInfo;
    }

    public Ivy createDefaultIvy() {
        return createIvy(createSettings(Constants.IVY_SETTING_BASE_DIR));
    }

    public IvySettings createSettings(File settingsBaseDir) {
        final IvySettings settings = new IvySettings();
        settings.setBaseDir(settingsBaseDir);
        return settings;
    }

    public Ivy createIvy(IvySettings settings) {
        try {
            Ivy ivy = Ivy.newInstance(settings);
            ivy.configure(ivyModuleInfo.getPathToIvySettingsAsFile());
            return ivy;
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Unable to operate with ivy settings xml file (path: " +
                    ivyModuleInfo.getPathToIvySettings() + "). Check that file is exists and has valid XML content");
        }
    }

    public RetrieveOptions createRetrieveOptions() {
        final RetrieveOptions retrieveOptions = new RetrieveOptions();

        // The pattern should be stored in Idea settings but can be overridden in Module settings
        final String ivyPattern = (ivyModuleInfo.getIvyRetrieveArtifactPattern() == null) ?
                Constants.IVY_RETRIEVE_ARTIFACT_PATTERN : ivyModuleInfo.getIvyRetrieveArtifactPattern();
        retrieveOptions.setDestIvyPattern(ivyModuleInfo.getOutputIvyLibsDir() + ivyPattern);
        return retrieveOptions;
    }
}
