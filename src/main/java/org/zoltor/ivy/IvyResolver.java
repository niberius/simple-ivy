package org.zoltor.ivy;

import org.apache.ivy.Ivy;
import org.apache.ivy.core.report.ResolveReport;

import java.io.IOException;
import java.text.ParseException;

public class IvyResolver {

    private final IvyModuleInfo ivyModuleInfo;
    private final Ivy ivy;

    public IvyResolver(final IvyModuleInfo ivyModuleInfo) {
        this.ivyModuleInfo = ivyModuleInfo;
        this.ivy = new IvyConfigurator(ivyModuleInfo).createDefaultIvy();
    }

    public void resolve() {
        try {
            final ResolveReport resolveReport = ivy.resolve(ivyModuleInfo.getPathToIvyXmlAsFile());
            processResolveReport(resolveReport);
        } catch (ParseException | IOException e) {
            throw new RuntimeException("Unable to operate with the main ivy  xml file (path: " +
                    ivyModuleInfo.getPathToIvyXml() + "). Check that file is exists and has valid XML content");
        }
    }

    private void processResolveReport(ResolveReport resolveReport) {
        if (resolveReport.hasError()) {
            throw new RuntimeException("Unable to resolve dependencies. The errors: " +
                    resolveReport.getAllProblemMessages());
        }
    }
}
