package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.Format;
import org.sonatype.nexus.repository.security.RepositoryFormatSecurityContributor;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class CFGFormatSecurityContributor extends RepositoryFormatSecurityContributor {
    @Inject
    public CFGFormatSecurityContributor(@Named(CFGFormat.NAME) final Format format) {
        super(format);
    }
}
