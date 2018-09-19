package eu.cloudifacturing.www.repo.rest.resources;

import com.google.common.collect.Streams;
import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.manager.RepositoryManager;
import org.sonatype.nexus.repository.security.RepositoryPermissionChecker;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;
import static org.sonatype.nexus.repository.http.HttpStatus.FORBIDDEN;
import static org.sonatype.nexus.repository.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Named
public class RepositoryManagerRESTAPIAdapterImpl implements RepositoryManagerRESTAPIAdapter {
    private final RepositoryManager repositoryManager;
    private final RepositoryPermissionChecker repositoryPermissionChecker;

    @Inject
    public RepositoryManagerRESTAPIAdapterImpl(final RepositoryManager repositoryManager,
                                            final RepositoryPermissionChecker repositoryPermissionChecker)
    {
        this.repositoryManager = checkNotNull(repositoryManager);
        this.repositoryPermissionChecker = checkNotNull(repositoryPermissionChecker);
    }

    @Override
    public Repository getRepository(final String repositoryId) {
        if (repositoryId == null) {
            throw new WebApplicationException("repositoryId is required.", UNPROCESSABLE_ENTITY);
        }
        Repository repository = ofNullable(repositoryManager.get(repositoryId))
                .orElseThrow(() -> new NotFoundException("Unable to locate repository with id " + repositoryId));

        if (repositoryPermissionChecker.userCanBrowseRepository(repository)) {
            //browse implies complete access to the repository.
            return repository;
        }
        else if (repositoryPermissionChecker.userCanViewRepository(repository)) {
            //user knows the repository exists but does not have the appropriate permission to browse, return a 403
            throw new WebApplicationException(FORBIDDEN);
        }
        else {
            //User does not know the repository exists because they can not VIEW or BROWSE, return a 404
            throw new NotFoundException("Unable to locate repository with id " + repositoryId);
        }
    }

    @Override
    public List<Repository> getRepositories() {
        return Streams.stream(repositoryManager.browse())
                .filter(repositoryPermissionChecker::userCanBrowseRepository)
                .collect(Collectors.toList());
    }
}
