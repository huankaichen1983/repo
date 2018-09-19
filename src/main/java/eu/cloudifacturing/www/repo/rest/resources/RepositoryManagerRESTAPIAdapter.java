package eu.cloudifacturing.www.repo.rest.resources;

import org.sonatype.nexus.repository.Repository;

import java.util.List;

public interface RepositoryManagerRESTAPIAdapter {
    /**
     * Retrieve a repository. Will throw a {@link javax.ws.rs.WebApplicationException} with status code 422 if the
     * supplied  repository id is null, and throws a {@link javax.ws.rs.NotFoundException} if no repository with the
     * supplied id exists.
     */
    Repository getRepository(String repositoryId);
    /**
     * Retrieve all repositories that the user access to.
     */
    List<Repository> getRepositories();
}
