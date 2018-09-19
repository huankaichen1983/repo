package eu.cloudifacturing.www.repo.rest.resources;

import eu.cloudifacturing.www.repo.rest.api.RepositoryXO;
import eu.cloudifacturing.www.repo.rest.resources.doc.RepositoryResourceDoc;
import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.manager.RepositoryManager;
import org.sonatype.nexus.rest.Resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Named
@Singleton
@Path(RepositoriesResource.RESOURCE_URI)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class RepositoriesResource extends ComponentSupport implements Resource, RepositoryResourceDoc {
    public static final String RESOURCE_URI = "repositories";
    private final RepositoryManager repositoryManager;
    private RepositoryManagerRESTAPIAdapter repositoryManagerRESTAPIAdapter;

    @Inject
    public RepositoriesResource(final RepositoryManager repositoryManager,
                                final RepositoryManagerRESTAPIAdapter repositoryManagerRESTAPIAdapter){
        this.repositoryManager = repositoryManager;
        this.repositoryManagerRESTAPIAdapter = repositoryManagerRESTAPIAdapter;
    }

    @GET
    public List<RepositoryXO> getRepositories() {
        return repositoryManagerRESTAPIAdapter.getRepositories()
                .stream()
                .map(RepositoryXO::fromRepository)
                .collect(toList());
    }

    @GET
    @Path("{repository_name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public RepositoryXO getRepositoryByName(@PathParam("repository_name") final String repository_name) {
        Repository repository = repositoryManagerRESTAPIAdapter.getRepository(repository_name);
        return RepositoryXO.fromRepository(repository);
    }
}
