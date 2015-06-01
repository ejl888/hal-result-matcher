package nl.my888.springframework.test.web.servlet.halmatchers;

import static java.lang.String.format;
import static nl.my888.springframework.test.web.servlet.halmatchers.MatcherUtils.pathToLink;
import static nl.my888.springframework.test.web.servlet.halmatchers.ResourceMatcher.toStringFormat;
import static nl.my888.springframework.test.web.servlet.halmatchers.RootResourceMatcher.rootResource;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LinkedResourceMatcher extends JsonObjectResultMatcherTemplate<LinkedResourceMatcher> {

    public static LinkedResourceMatcher rootLinkedResource(String relType) {
        return rootResource().linkedResource(relType);
    }

    protected LinkedResourceMatcher(String relType, ResourceMatcher parent) {
        super(parent.absolutePath(pathToLink(relType)));
    }

    public LinkedResourceMatcher havingLinkTo(String href, Object... params) {
        addMatcher(jsonPath(getPath() + ".href").value(endsWith(format(toStringFormat(href), params))));
        return this;
    }

    @Override
    public LinkedResourceMatcher self() {
        return this;
    }
}
