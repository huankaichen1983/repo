package eu.cloudifacturing.www.repo.rest.resources.doc;

import eu.cloudifacturing.www.repo.rest.api.ArtefactXO;
import io.swagger.annotations.*;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import org.sonatype.nexus.rest.Page;

import java.io.IOException;
import java.util.Map;

@Api(value = "cfg-artefacts")
public interface ArtefactsResourceDoc {
    @ApiOperation("Upload a single artefact to repository")
    ArtefactXO uploadArtefact(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(hidden = true) @MultipartForm MultipartInput multipartInput) throws IOException;

    @ApiOperation("List artefacts in a repository")
    Page<ArtefactXO> listArtefacts(@ApiParam(value = "Name of the repository") final String repository_name, final Map<String,String> metadata, final String query);

    @ApiOperation("Get artefacts by id in a repository")
    ArtefactXO getArtefactById(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(value = "ID of the artefact") final String artefact_id);

    @ApiOperation(value = "Delete a single artefact from repository")
    void deleteArtefact(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(value = "ID of the artefact to delete") final String artefact_id);
}
