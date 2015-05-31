package nl.my888.springframework.test.web.servlet.halmatchers;

import java.net.URI;

import static nl.ilent.rest.test.TestUtils.makePathToProfileLink;
import static nl.ilent.rest.test.TestUtils.makePathToSelfLink;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by ejl on 30/03/15.
 */
public class ResourceMatcher extends JsonObjectResultMatcherTemplate<ResourceMatcher> {

    protected ResourceMatcher(String rootPath) {
        super(rootPath);
    }

    public EmbeddedResourceMatcher embeddedResource(String relationType) {
        return addNestedMatcher(new EmbeddedResourceMatcher(relationType, this));
    }

    public LinkedResourceMatcher linkedResource(String relationType) {
        return addNestedMatcher(new LinkedResourceMatcher(relationType, this));
    }

    public ResourceMatcher havingProfile(String profileHref, Object... params) {
        addMatcher(pathBuilder()
                .push(makePathToProfileLink())
                .add("href")
                .value(endsWith(String.format(toStringFormat(profileHref), params))));
        return this;
    }

    public ResourceMatcher havingProfile(URI profileUri) {
        addMatcher(pathBuilder().push(makePathToProfileLink()).add("href")
                .value(equalTo(profileUri.toString())));
        return this;
    }

    public ResourceMatcher havingSelfLink(String selfLink, Object... params) {
        addMatcher(pathBuilder()
                .push(makePathToSelfLink())
                .add("href")
                .value(endsWith(String.format(toStringFormat(selfLink), params))));
        return this;
    }

    public ResourceMatcher havingSize(int size) {
        addMatcher(pathBuilder().path().value(hasSize(size)));
        return this;
    }

    static String toStringFormat(String templatedString) {
        return templatedString.replaceAll("\\{(?!\\?|\\&).*?\\}", "%s");
    }

    @Override
    public ResourceMatcher arrayItem(int index) {
        return addNestedMatcher(new ResourceMatcher(absolutePath(toJsonIndex(index))));
    }
}
