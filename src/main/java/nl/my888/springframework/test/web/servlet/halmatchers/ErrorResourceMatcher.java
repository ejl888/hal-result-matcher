package nl.my888.springframework.test.web.servlet.halmatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Error resource response matcher
 */
public class ErrorResourceMatcher extends RootResourceMatcher {

    public static ErrorResourceMatcher errorResource() {
        return new ErrorResourceMatcher();
    }

    private ErrorResourceMatcher() {
        super();
        havingContentType(HalConstants.MEDIA_TYPE_APPLICATION_VND_ERROR_JSON);
    }

    public ErrorResourceMatcher havingMessage(String message, Object... params) {
        addMatcher(jsonPath("message").value(String.format(message, params)));
        return this;
    }

    @Override
    public ErrorResourceMatcher self() {
        return this;
    }
}
