package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.httpbridge.LegacyViewConfiguration;
import org.sonatype.nexus.repository.httpbridge.LegacyViewContributor;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.regex.Pattern;

@Named
@Singleton
public class CFGLegacyViewContributor implements LegacyViewContributor {
    @Override
    public LegacyViewConfiguration contribute() {
        return new LegacyViewConfiguration()
        {
            @Override
            public String getFormat() {
                return CFGFormat.NAME;
            }

            @Override
            public Pattern getRequestPattern() {
                return Pattern.compile("/content/sites/.*");
            }
        };
    }
}
