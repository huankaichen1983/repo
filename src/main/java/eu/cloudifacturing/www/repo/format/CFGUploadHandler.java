package eu.cloudifacturing.www.repo.format;

import com.google.common.collect.Lists;
import eu.cloudifacturing.www.repo.format.internal.CFGFormat;
import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.rest.UploadDefinitionExtension;
import org.sonatype.nexus.repository.security.ContentPermissionChecker;
import org.sonatype.nexus.repository.security.VariableResolverAdapter;
import org.sonatype.nexus.repository.storage.StorageFacet;
import org.sonatype.nexus.repository.transaction.TransactionalStoreBlob;
import org.sonatype.nexus.repository.upload.*;
import org.sonatype.nexus.repository.view.Content;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;

@Named(CFGFormat.NAME)
@Singleton
public class CFGUploadHandler extends UploadHandlerSupport {

    private static final String FILENAME = "filename";

    private static final String GROUP_ID = "groupId";

    private static final String GROUP_ID_HELP_TEXT = "The unique identification for the ISV. It can be the ISV’s domain name, e.g. www.dfg.eu.";

    private static final String ENGINE_ID = "engineId";

    private static final String ENGINE_ID_HELP_TEXT = "The unique identification for the execution engine, e.g. cloudflow, cloudbroker, flowster";

    private static final String ARTIFACT_ID = "artifactId";

    private static final String ARTIFACT_ID_HELP_TEXT = "the unique identification for the artefact";

    private static final String VERSION = "version";

    /**
     * The upload path for the artefact will be /group_id/engine_Id/artefact_id/version/filename
     * **/

    private static final String DIRECTORY = "directory";

    private static final String DIRECTORY_HELP_TEXT = "Destination for uploaded files (e.g. /path/to/files/)";

    private static final String FIELD_GROUP_NAME = "Component attributes";

    private UploadDefinition definition;

    private final ContentPermissionChecker contentPermissionChecker;

    private final VariableResolverAdapter variableResolverAdapter;

    @Inject
    public CFGUploadHandler(final ContentPermissionChecker contentPermissionChecker,
                            @Named(CFGFormat.NAME) final VariableResolverAdapter variableResolverAdapter,
                            final Set<UploadDefinitionExtension> uploadDefinitionExtensions)
    {
        super(uploadDefinitionExtensions);
        this.contentPermissionChecker = contentPermissionChecker;
        this.variableResolverAdapter = variableResolverAdapter;
    }

    @Override
    public UploadResponse handle(final Repository repository, final ComponentUpload upload) throws IOException {
        CFGContentFacet facet = repository.facet(CFGContentFacet.class);

        String basePath = upload.getFields().get(DIRECTORY).trim();

        return TransactionalStoreBlob.operation.withDb(repository.facet(StorageFacet.class).txSupplier())
                .throwing(IOException.class).call(() -> {

                    //Data holders for populating the UploadResponse
                    List<Content> responseContents = Lists.newArrayList();
                    List<String> assetPaths = Lists.newArrayList();

                    for (AssetUpload asset : upload.getAssetUploads()) {
                        String path = normalizePath(basePath + '/' + asset.getFields().get(FILENAME).trim());

                        ensurePermitted(repository.getName(), CFGFormat.NAME, path, emptyMap());

                        Content content = facet.put(path, asset.getPayload());

                        responseContents.add(content);
                        assetPaths.add(path);
                    }

                    return new UploadResponse(responseContents, assetPaths);
                });
    }

    @Override
    public UploadDefinition getDefinition() {
        if (definition == null) {
            List<UploadFieldDefinition> componentFields = Arrays.asList(
                    new UploadFieldDefinition(GROUP_ID, GROUP_ID_HELP_TEXT, false, UploadFieldDefinition.Type.STRING, FIELD_GROUP_NAME),
                    new UploadFieldDefinition(ENGINE_ID, ENGINE_ID_HELP_TEXT, false, UploadFieldDefinition.Type.STRING, FIELD_GROUP_NAME),
                    new UploadFieldDefinition(ARTIFACT_ID, ARTIFACT_ID_HELP_TEXT,false, UploadFieldDefinition.Type.STRING, FIELD_GROUP_NAME),
                    new UploadFieldDefinition(VERSION, false, UploadFieldDefinition.Type.STRING, FIELD_GROUP_NAME));
            definition = getDefinition(CFGFormat.NAME, true,
                    componentFields,
                    singletonList(new UploadFieldDefinition(FILENAME, false, UploadFieldDefinition.Type.STRING)),
                    new UploadRegexMap("(.*)", FILENAME));
        }
        return definition;
    }

    @Override
    public VariableResolverAdapter getVariableResolverAdapter() {
        return variableResolverAdapter;
    }

    @Override
    public ContentPermissionChecker contentPermissionChecker() {
        return contentPermissionChecker;
    }

    private String normalizePath(final String path) {
        String result = path.replaceAll("/+", "/");

        if (result.startsWith("/")) {
            result = result.substring(1);
        }

        if (result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }
}
