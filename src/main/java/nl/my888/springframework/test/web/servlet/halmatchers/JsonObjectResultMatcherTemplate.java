package nl.my888.springframework.test.web.servlet.halmatchers;

import java.util.ArrayDeque;
import java.util.Deque;

import nl.ilent.rest.test.JsonPathBuilder;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.Matcher;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Json object result matcher with fluent interface enabling chaining/nesting matchers.
 * Uses http://en.wikipedia.org/wiki/Curiously_recurring_template_pattern
 */
public class JsonObjectResultMatcherTemplate<T extends JsonObjectResultMatcherTemplate<T>> implements ResultMatcher {

    private final String path;

    private final Deque<ResultMatcher> resultMatchers = new ArrayDeque<>();

    private T parent = null;

    protected JsonObjectResultMatcherTemplate(String path) {
        this.path = path;
    }

    public final JsonObjectResultMatcher nestedObject(String subPath) {
        return addNestedMatcher(new JsonObjectResultMatcher(absolutePath(subPath)));
    }

    public JsonObjectResultMatcherTemplate arrayItem(int index) {
        return addNestedMatcher(new JsonObjectResultMatcher(absolutePath(toJsonIndex(index))));
    }

    public T havingProperty(final String propertyPath, Matcher<?> matcher) {
        addMatcher(jsonPath(absolutePath(propertyPath)).value(matcher));
        return self();
    }

    public final T havingArrayProperty(String propertyPath, int index, Matcher<?> matcher) {
        addMatcher(jsonPath(absolutePath(propertyPath) + toJsonIndex(index)).value(matcher));
        return self();
    }

    public final T exists() {
        final String rootPath = getPath();
        if (StringUtils.isNotBlank(rootPath)) {
            addMatcher(jsonPath(rootPath).exists());
        }
        return self();
    }

    public final T doesNotExist() {
        final String rootPath = getPath();
        if (StringUtils.isNotBlank(rootPath)) {
            addMatcher(jsonPath(rootPath).doesNotExist());
        }
        return self();
    }

    public T parent() {
        return parent;
    }

    @Override
    public final void match(MvcResult result) throws Exception {
        beforeMatch();

        final JsonObjectResultMatcherTemplate parentToMatcher = this.parent;
        if (parentToMatcher != null) {
            // break relation with parent, to prevent stack overflows
            parent = null;
            // delegate to parent first
            parentToMatcher.match(result);
        }


        while (!resultMatchers.isEmpty()) {
            final ResultMatcher matcher = resultMatchers.getFirst();
            matcher.match(result);
            resultMatchers.remove(matcher);
        }
    }

    protected void beforeMatch() {
    }

    protected final String absolutePath(String subPath) {
        return StringUtils.isBlank(path) ? subPath : path + '.' + subPath;
    }

    protected final String getPath() {
        return path;
    }

    protected final void setParent(T parent) {
        this.parent = parent;
    }

    protected final <N extends JsonObjectResultMatcherTemplate> N addNestedMatcher(N nestedMatcher) {
        addMatcher(nestedMatcher);
        nestedMatcher.setParent(this);
        return nestedMatcher;
    }

    protected final void addMatcher(ResultMatcher resultMatcher) {
        resultMatchers.add(resultMatcher);
    }

    protected final JsonPathBuilder pathBuilder() {
        return new JsonPathBuilder(path);
    }

    String toJsonIndex(int index) {
        return "[" + index + "]";
    }

    public final T having(ResultMatcher resultMatcher) {
        addMatcher(resultMatcher);
        return self();
    }

    /**
     * typed this. Sub-classes should override this method by returning this with the correct subtype.
     * @return this.
     */
    @SuppressWarnings("unchecked")
    public T self() {
        return (T) this;
    }

}
