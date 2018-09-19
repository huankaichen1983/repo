package eu.cloudifacturing.www.repo.rest.resources.doc;

import eu.cloudifacturing.www.repo.rest.api.RepositoryXO;
import io.swagger.annotations.*;

import java.util.List;

@Api(value = "cfg-repositories")
public interface RepositoryResourceDoc {
    @ApiOperation("List repositories")
    List<RepositoryXO> getRepositories();

    @ApiOperation("Get a single repository by name")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Some content in the request was invalid"),
            @ApiResponse(code = 401, message = "No access token was supplied in the request"),
            @ApiResponse(code = 403, message = "Supplied access token does not give rights to perform the request"),
            @ApiResponse(code = 404, message = "Repository not found")
    })
    RepositoryXO getRepositoryByName(@ApiParam(value = "Name of the repository to retrieve") final String repository_name);
}
