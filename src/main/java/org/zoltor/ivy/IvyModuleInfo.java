package org.zoltor.ivy;

import java.io.File;

public class IvyModuleInfo {

    private String pathToIvySettings;
    private String pathToIvyXml;
    private String outputIvyLibsDir;
    private String ivyRetrieveArtifactPattern;

    private IvyModuleInfo(final Builder builder) {
        this.pathToIvySettings = builder.pathToIvySettings;
        this.pathToIvyXml = builder.pathToIvyXml;
        this.outputIvyLibsDir = builder.outputIvyLibsDir;
        this.ivyRetrieveArtifactPattern = builder.ivyRetrieveArtifactPattern;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getPathToIvySettings() {
        return pathToIvySettings;
    }

    public String getPathToIvyXml() {
        return pathToIvyXml;
    }

    public String getOutputIvyLibsDir() {
        return outputIvyLibsDir;
    }

    public File getPathToIvySettingsAsFile() {
        return new File(getPathToIvySettings());
    }

    public File getPathToIvyXmlAsFile() {
        return new File(getPathToIvyXml());
    }

    public File getOutputIvyLibsDirAsFile() {
        return new File(getOutputIvyLibsDir());
    }

    public String getIvyRetrieveArtifactPattern()
    {
        return ivyRetrieveArtifactPattern;
    }

    public static class Builder {

        private String pathToIvySettings;
        private String pathToIvyXml;
        private String outputIvyLibsDir;
        private String ivyRetrieveArtifactPattern;

        private Builder() {

        }

        public Builder pathToIvySettings(String pathToIvySettings) {
            this.pathToIvySettings = pathToIvySettings;
            return this;
        }

        public Builder pathToIvyXml(String pathToIvyXml) {
            this.pathToIvyXml = pathToIvyXml;
            return this;
        }

        public Builder outputIvyLibsDir(String outputIvyLibsDir) {
            this.outputIvyLibsDir = outputIvyLibsDir;
            return this;
        }

        public Builder ivyRetrieveArtifactPattern(String ivyRetrieveArtifactPattern) {
            this.ivyRetrieveArtifactPattern = ivyRetrieveArtifactPattern;
            return this;
        }

        public IvyModuleInfo build() {
            return new IvyModuleInfo(this);
        }
    }
}
