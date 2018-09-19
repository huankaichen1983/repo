package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.Format;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * CFG repository format.
 */
@Named(CFGFormat.NAME)
@Singleton
public class CFGFormat extends Format {
    public static final String NAME = "cfg";

    public CFGFormat() {
        super(NAME);
    }
}
