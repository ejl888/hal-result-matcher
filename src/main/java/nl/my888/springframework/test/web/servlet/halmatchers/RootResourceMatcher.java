package nl.my888.springframework.test.web.servlet.halmatchers;

import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Response root resource matcher.
 * By default validates for content type MEDIA_TYPE_APPLICATION_HAL_JSON
 */
public class RootResourceMatcher extends ResourceMatcher {

    private MediaType contentMediaType = HalConstants.MEDIA_TYPE_APPLICATION_HAL_JSON;

    public static RootResourceMatcher rootResource() {
        return new RootResourceMatcher();
    }

    protected RootResourceMatcher() {
        super("");
    }

    public final RootResourceMatcher havingContentType(final MediaType mediaType) {
        this.contentMediaType = mediaType;
        return this;
    }

    @Override
    public RootResourceMatcher self() {
        return this;
    }

    @Override
    protected void beforeMatch() {
        addMatcher(content().contentType(contentMediaType));
    }
}
