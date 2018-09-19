package eu.cloudifacturing.www.repo.format.internal

import org.sonatype.nexus.repository.Format
import org.sonatype.nexus.repository.RecipeSupport
import org.sonatype.nexus.repository.Repository
import org.sonatype.nexus.repository.Type
import org.sonatype.nexus.repository.attributes.AttributesFacet
import org.sonatype.nexus.repository.http.HttpHandlers
import org.sonatype.nexus.repository.http.HttpMethods
import org.sonatype.nexus.repository.http.PartialFetchHandler
import org.sonatype.nexus.repository.search.SearchFacet
import org.sonatype.nexus.repository.security.SecurityHandler
import org.sonatype.nexus.repository.storage.SingleAssetComponentMaintenance
import org.sonatype.nexus.repository.storage.StorageFacet
import org.sonatype.nexus.repository.storage.UnitOfWorkHandler
import org.sonatype.nexus.repository.types.HostedType
import org.sonatype.nexus.repository.view.ConfigurableViewFacet
import org.sonatype.nexus.repository.view.Route
import org.sonatype.nexus.repository.view.Router
import org.sonatype.nexus.repository.view.ViewFacet
import org.sonatype.nexus.repository.view.handlers.ConditionalRequestHandler
import org.sonatype.nexus.repository.view.handlers.ContentHeadersHandler
import org.sonatype.nexus.repository.view.handlers.ExceptionHandler
import org.sonatype.nexus.repository.view.handlers.HandlerContributor
import org.sonatype.nexus.repository.view.handlers.IndexHtmlForwardHandler
import org.sonatype.nexus.repository.view.handlers.TimingHandler
import org.sonatype.nexus.repository.view.matchers.ActionMatcher
import org.sonatype.nexus.repository.view.matchers.SuffixMatcher
import org.sonatype.nexus.repository.view.matchers.token.TokenMatcher

import javax.annotation.Nonnull
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

import static org.sonatype.nexus.repository.view.matchers.logic.LogicMatchers.and

@Named(CFGHostedRecipe.NAME)
@Singleton
class CFGHostedRecipe extends RecipeSupport {
    public static final String NAME = 'cfg-hosted'

    @Inject
    Provider<CFGSecurityFacet> securityFacet

    @Inject
    Provider<ConfigurableViewFacet> viewFacet

    @Inject
    Provider<CFGContentFacetImpl> cfgContentFacet

    @Inject
    Provider<StorageFacet> storageFacet

    @Inject
    Provider<AttributesFacet> attributesFacet

    @Inject
    Provider<SingleAssetComponentMaintenance> componentMaintenance

    @Inject
    Provider<SearchFacet> searchFacet

    @Inject
    ExceptionHandler exceptionHandler

    @Inject
    TimingHandler timingHandler

    @Inject
    IndexHtmlForwardHandler indexHtmlForwardHandler

    @Inject
    SecurityHandler securityHandler

    @Inject
    PartialFetchHandler partialFetchHandler

    @Inject
    UnitOfWorkHandler unitOfWorkHandler

    @Inject
    CFGContentHandler cfgContentHandler

    @Inject
    ConditionalRequestHandler conditionalRequestHandler

    @Inject
    ContentHeadersHandler contentHeadersHandler

    @Inject
    HandlerContributor handlerContributor

    @Inject
    CFGHostedRecipe(@Named(HostedType.NAME) final Type type,
                    @Named(CFGFormat.NAME) final Format format)
    {
        super(type, format)
    }

    @Override
    void apply(@Nonnull final Repository repository) throws Exception {
        repository.attach(securityFacet.get())
        repository.attach(configure(viewFacet.get()))
        repository.attach(cfgContentFacet.get())
        repository.attach(storageFacet.get())
        repository.attach(attributesFacet.get())
        repository.attach(componentMaintenance.get())
        repository.attach(searchFacet.get())
    }

    private ViewFacet configure(final ConfigurableViewFacet facet) {
        Router.Builder builder = new Router.Builder()

        // handle GET / forwards to /index.html
        builder.route(new Route.Builder()
                .matcher(and(new ActionMatcher(HttpMethods.GET), new SuffixMatcher('/')))
                .handler(timingHandler)
                .handler(indexHtmlForwardHandler)
                .create()
        )

        builder.route(new Route.Builder()
                .matcher(new TokenMatcher('/{name:.+}'))
                .handler(timingHandler)
                .handler(securityHandler)
                .handler(exceptionHandler)
                .handler(handlerContributor)
                .handler(conditionalRequestHandler)
                .handler(partialFetchHandler)
                .handler(contentHeadersHandler)
                .handler(unitOfWorkHandler)
                .handler(cfgContentHandler)
                .create())

        builder.defaultHandlers(HttpHandlers.badRequest())

        facet.configure(builder.create())

        return facet
    }
}
