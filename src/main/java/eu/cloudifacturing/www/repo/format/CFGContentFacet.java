package eu.cloudifacturing.www.repo.format;

import org.sonatype.nexus.common.collect.AttributesMap;
import org.sonatype.nexus.repository.Facet;
import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.cache.CacheInfo;
import org.sonatype.nexus.repository.storage.Asset;
import org.sonatype.nexus.repository.upload.ComponentUpload;
import org.sonatype.nexus.repository.view.Content;
import org.sonatype.nexus.repository.view.Payload;

import javax.annotation.Nullable;
import java.io.IOException;

@Facet.Exposed
public interface CFGContentFacet extends Facet {
    @Nullable
    Content get(String path) throws IOException;

    Content put(String path, AttributesMap attributesMap, Payload content) throws IOException;

    boolean delete(String path) throws IOException;

    /**
     * Raw proxy facet specific method: invoked when cached content (returned by {@link #get(String)} method of this
     * same facet instance) is found to be up to date after remote checks. This method applies the passed in {@link
     * CacheInfo} to the {@link Content}'s underlying asset.
     */
    void setCacheInfo(String path, Content content, CacheInfo cacheInfo) throws IOException;

    Asset getOrCreateAsset(Repository repository, String componentName, String componentGroup, String assetName);
}
