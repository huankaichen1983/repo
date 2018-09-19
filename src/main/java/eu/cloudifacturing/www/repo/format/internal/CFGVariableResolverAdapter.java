package eu.cloudifacturing.www.repo.format.internal;

import com.orientechnologies.orient.core.record.impl.ODocument;
import org.elasticsearch.search.lookup.SourceLookup;
import org.sonatype.nexus.repository.security.VariableResolverAdapter;
import org.sonatype.nexus.repository.security.VariableResolverAdapterSupport;
import org.sonatype.nexus.repository.storage.Asset;
import org.sonatype.nexus.repository.view.Request;
import org.sonatype.nexus.selector.VariableSourceBuilder;

import javax.inject.Named;
import java.util.Map;

@Named(CFGFormat.NAME)
public class CFGVariableResolverAdapter extends VariableResolverAdapterSupport implements VariableResolverAdapter {
    @Override
    protected void addFromRequest(VariableSourceBuilder builder, Request request) {

    }

    @Override
    protected void addFromDocument(VariableSourceBuilder builder, ODocument document) {

    }

    @Override
    protected void addFromAsset(VariableSourceBuilder builder, Asset asset) {

    }

    @Override
    protected void addFromSourceLookup(VariableSourceBuilder builder, SourceLookup sourceLookup, Map<String, Object> asset) {

    }
}
