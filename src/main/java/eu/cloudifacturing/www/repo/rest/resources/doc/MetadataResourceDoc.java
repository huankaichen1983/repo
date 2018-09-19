package eu.cloudifacturing.www.repo.rest.resources.doc;

import eu.cloudifacturing.www.repo.rest.api.MetadataXO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Map;

@Api(value = "cfg-metadata")
public interface MetadataResourceDoc {
    @ApiOperation("List metadata from a single artefact")
    MetadataXO listMetadata(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(value = "ID of the artefact") final String artefact_id);

    @ApiOperation("Create/update one or list of metadata for a single artefact")
    MetadataXO modifyMetadata(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(value = "ID of the artefact") final String artefact_id, final Map<String, String> metadata);

    @ApiOperation("Delete one or list of metadata from a single artefact")
    MetadataXO deleteMetadata(@ApiParam(value = "Name of the repository") final String repository_name, @ApiParam(value = "ID of the artefact") final String artefact_id, final Map<String, String> metadata);
}
