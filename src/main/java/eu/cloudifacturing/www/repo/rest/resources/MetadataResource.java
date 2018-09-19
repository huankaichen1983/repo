package eu.cloudifacturing.www.repo.rest.resources;

import eu.cloudifacturing.www.repo.rest.api.MetadataXO;
import eu.cloudifacturing.www.repo.rest.resources.doc.MetadataResourceDoc;
import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.rest.Resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;

import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Named
@Singleton
@Path(MetadataResource.RESOURCE_URI)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class MetadataResource extends ComponentSupport implements Resource,MetadataResourceDoc {
    public static final String RESOURCE_URI = "";

    @Inject
    public MetadataResource(){
    }

    @GET
    @Path("/repositories/{repository_name}/artefacts/{artefact_id}/metadata")
    public MetadataXO listMetadata(@PathParam("repository_name") final String repository_name, @PathParam("artefact_id") final String artefact_id) {
        return null;
    }

    @PUT
    @Path("/repositories/{repository_name}/artefacts/{artefact_id}/metadata")
    public MetadataXO modifyMetadata(@PathParam("repository_name") final String repository_name, @PathParam("artefact_id") final String artefact_id, Map<String, String> metadata) {
        return null;
    }

    @DELETE
    @Path("/repositories/{repository_name}/artefacts/{artefact_id}/metadata")
    public MetadataXO deleteMetadata(@PathParam("repository_name") final String repository_name, @PathParam("artefact_id") final String artefact_id, Map<String, String> metadata) {
        return null;
    }
}
