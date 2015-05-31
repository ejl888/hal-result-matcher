package nl.my888.springframework.test.web.servlet.halmatchers;

import static nl.ilent.rest.test.TestUtils.makePathToEmbedded;
import static nl.ilent.rest.test.halmatchers.RootResourceMatcher.rootResource;

/**
 * Created by ejl on 30/03/15.
 */
public class EmbeddedResourceMatcher extends ResourceMatcher {

    public static EmbeddedResourceMatcher rootEmbeddedResource(String relType) {
        return rootResource().embeddedResource(relType);
    }

    EmbeddedResourceMatcher(String relType, ResourceMatcher parent) {
        super(parent.absolutePath(makePathToEmbedded(relType)));
    }

    @Override
    public EmbeddedResourceMatcher self() {
        return this;
    }

}
