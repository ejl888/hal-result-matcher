package nl.my888.springframework.test.web.servlet.halmatchers;

import static nl.ilent.rest.test.TestUtils.makePathToLink;
import static nl.ilent.rest.test.halmatchers.ResourceMatcher.toStringFormat;
import static nl.ilent.rest.test.halmatchers.RootResourceMatcher.rootResource;
import static org.hamcrest.Matchers.endsWith;

/**
 * Created by ejl on 30/03/15.
 */
public class LinkedResourceMatcher extends JsonObjectResultMatcherTemplate<LinkedResourceMatcher> {

    public static LinkedResourceMatcher rootLinkedResource(String relType) {
        return rootResource().linkedResource(relType);
    }

    protected LinkedResourceMatcher(String relType, ResourceMatcher parent) {
        super(parent.absolutePath(makePathToLink(relType)));
    }

    public LinkedResourceMatcher havingLinkTo(String href, Object... params) {
        addMatcher(pathBuilder().add("href").value(endsWith(String.format(toStringFormat(href), params))));
        return this;
    }

    @Override
    public LinkedResourceMatcher self() {
        return this;
    }
}
