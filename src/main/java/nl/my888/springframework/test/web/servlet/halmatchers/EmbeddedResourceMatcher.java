package nl.my888.springframework.test.web.servlet.halmatchers;

import static nl.my888.springframework.test.web.servlet.halmatchers.MatcherUtils.pathToEmbedded;
import static nl.my888.springframework.test.web.servlet.halmatchers.RootResourceMatcher.rootResource;

/**
 * Embedded Resource Matcher
 */
public class EmbeddedResourceMatcher extends ResourceMatcher {

    public static EmbeddedResourceMatcher rootEmbeddedResource(String rel) {
        return rootResource().embeddedResource(rel);
    }

    EmbeddedResourceMatcher(String rel, ResourceMatcher parent) {
        super(parent.absolutePath(pathToEmbedded(rel)));
    }

    @Override
    public EmbeddedResourceMatcher self() {
        return this;
    }

}
