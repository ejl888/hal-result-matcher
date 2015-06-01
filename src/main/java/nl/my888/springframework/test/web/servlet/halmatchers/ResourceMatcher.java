package nl.my888.springframework.test.web.servlet.halmatchers;

import java.net.URI;

import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import static java.lang.String.format;
import static nl.my888.springframework.test.web.servlet.halmatchers.MatcherUtils.pathToProfileLink;
import static nl.my888.springframework.test.web.servlet.halmatchers.MatcherUtils.pathToSelfLink;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
        addMatcher(jsonPathLinkHref(pathToProfileLink()).value(endsWith(format(toStringFormat(profileHref), params))));
        return this;
    }


    public ResourceMatcher havingProfile(URI profileUri) {
        addMatcher(jsonPathLinkHref(pathToProfileLink()).value(equalTo(profileUri.toString())));
        return this;
    }

    public ResourceMatcher havingSelfLink(String selfLink, Object... params) {
        addMatcher(jsonPathLinkHref(pathToSelfLink()).value(endsWith(format(toStringFormat(selfLink), params))));
        return this;
    }

    public ResourceMatcher havingSize(int size) {
        addMatcher(jsonPath(getPath()).value(hasSize(size)));
        return this;
    }

    @Override
    public ResourceMatcher arrayItem(int index) {
        return addNestedMatcher(new ResourceMatcher(absolutePath(toJsonIndex(index))));
    }

    static String toStringFormat(String templatedString) {
        return templatedString.replaceAll("\\{(?!\\?|\\&).*?\\}", "%s");
    }

    private JsonPathResultMatchers jsonPathLinkHref(String link) {
        return jsonPath(getPath() + "." + link + ".href");
    }
}
