package org.zoltor.ivy;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.module.descriptor.ModuleDescriptor;
import org.apache.ivy.core.module.id.ModuleRevisionId;
import org.apache.ivy.core.report.ResolveReport;
import org.apache.ivy.core.retrieve.RetrieveOptions;

import java.io.IOException;
import java.text.ParseException;

public class IvyWorker {

    private final IvyModuleInfo ivyModuleInfo;
    private final IvyConfigurator configurator;
    private final Ivy ivy;

    private ResolveReport resolveReport;

    public IvyWorker(final IvyModuleInfo ivyModuleInfo) {
        this.ivyModuleInfo = ivyModuleInfo;
        this.configurator = new IvyConfigurator(ivyModuleInfo);
        this.ivy = configurator.createDefaultIvy();
    }

    public void resolve() {
        try {
            this.resolveReport = ivy.resolve(ivyModuleInfo.getPathToIvyXmlAsFile());
            processResolveReport(resolveReport);
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Unable to operate with the main ivy xml file (path: " +
                    ivyModuleInfo.getPathToIvyXml() + "). Check that file is exists and has valid XML content", e);
        }
    }

    public void retrieve() {
        if (resolveReport == null || resolveReport.hasError()) {
            throw new RuntimeException("Artifacts weren't resolved. Call org.zoltor.ivy.IvyWorker#resolve() first");
        }
        ModuleDescriptor moduleDescriptor = resolveReport.getModuleDescriptor();
        ModuleRevisionId moduleRevisionId = moduleDescriptor.getModuleRevisionId();
        RetrieveOptions retrieveOptions = configurator.createRetrieveOptions();

        try
        {
            ivy.retrieve(moduleRevisionId, retrieveOptions);
        } catch (IOException e) {
            throw new RuntimeException("Unable to retrieve dependencies", e);
        }
    }

    private void processResolveReport(ResolveReport resolveReport) {
        if (resolveReport.hasError()) {
            throw new RuntimeException("Unable to resolve dependencies. The errors: " +
                    resolveReport.getAllProblemMessages());
        }
    }
}
