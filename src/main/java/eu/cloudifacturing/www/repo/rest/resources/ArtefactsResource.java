package eu.cloudifacturing.www.repo.rest.resources;

import eu.cloudifacturing.www.repo.rest.api.ArtefactXO;
import eu.cloudifacturing.www.repo.rest.resources.doc.ArtefactsResourceDoc;
import org.jboss.resteasy.plugins.providers.multipart.MultipartInput;
import org.sonatype.goodies.common.ComponentSupport;
import org.sonatype.nexus.rest.Page;
import org.sonatype.nexus.rest.Resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Named
@Singleton
@Path(ArtefactsResource.RESOURCE_URI)
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class ArtefactsResource extends ComponentSupport implements Resource,ArtefactsResourceDoc {
    public static final String RESOURCE_URI = "";

    @Inject
    public ArtefactsResource(){
    }

    @POST
    @Path("/repositories/{repository_name}/artefacts")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ArtefactXO uploadArtefact(@PathParam("repository_name") final String repository_name, MultipartInput multipartInput) throws IOException {
        return null;
    }

    @GET
    @Path("/repositories/{repository_name}/artefacts")
    public Page<ArtefactXO> listArtefacts(@PathParam("repository_name") final String repository_name, Map<String, String> metadata, String query) {
        return null;
    }

    @GET
    @Path("/repositories/{repository_name}/artefacts/{artefact_id}/")
    public ArtefactXO getArtefactById(@PathParam("repository_name") final String repository_name, @PathParam("artefact_id") final String artefact_id) {
        return null;
    }

    @DELETE
    @Path("/repositories/{repository_name}/artefacts/{artefact_id}/")
    public void deleteArtefact(@PathParam("repository_name") final String repository_name, @PathParam("artefact_id") final String artefact_id) {
    }
}
