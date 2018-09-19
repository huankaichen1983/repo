package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.storage.Component;
import org.sonatype.nexus.repository.storage.ComponentDirector;

import javax.inject.Named;
import javax.inject.Singleton;

@Named("cfg")
@Singleton
public class CFGComponentDirector implements ComponentDirector {
    @Override
    public boolean allowMoveTo(final Repository destination) {
        return true;
    }

    @Override
    public boolean allowMoveTo(final Component component, final Repository destination) {
        return true;
    }

    @Override
    public boolean allowMoveFrom(final Repository source) {
        return true;
    }
}
