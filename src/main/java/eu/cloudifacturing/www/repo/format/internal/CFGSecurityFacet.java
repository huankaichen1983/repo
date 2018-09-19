package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.security.ContentPermissionChecker;
import org.sonatype.nexus.repository.security.SecurityFacetSupport;
import org.sonatype.nexus.repository.security.VariableResolverAdapter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CFGSecurityFacet extends SecurityFacetSupport {
    @Inject
    public CFGSecurityFacet(final CFGFormatSecurityContributor securityContributor,
                            @Named(CFGFormat.NAME) final VariableResolverAdapter variableResolverAdapter,
                            final ContentPermissionChecker contentPermissionChecker)
    {
        super(securityContributor, variableResolverAdapter, contentPermissionChecker);
    }
}
