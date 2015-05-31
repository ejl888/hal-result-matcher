package nl.my888.springframework.test.web.servlet.halmatchers;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by ejl on 23/04/15.
 */
public class ResourceMatcherTest {


    @Test
    public void testToStringForm() throws Exception {
        assertThat(ResourceMatcher.toStringFormat("/abc/cde"), equalTo("/abc/cde"));


        assertThat(ResourceMatcher.toStringFormat("/abc/{id}/cde"), equalTo("/abc/%s/cde"));

        assertThat(ResourceMatcher.toStringFormat("/abc/{id}/cde{id-2}"), equalTo("/abc/%s/cde%s"));
    }

    @Test
    public void testToStringFormLeavesUriTemplatesAlone() throws Exception {

        // Form-style query, ampersand-separated
        assertThat(ResourceMatcher.toStringFormat("/abc/{id}/efg{?templ}"), equalTo("/abc/%s/efg{?templ}"));

        // Form-style query continuation
        assertThat(ResourceMatcher.toStringFormat("/abc/{id}/efg?x=y{&templ}"), equalTo("/abc/%s/efg?x=y{&templ}"));
    }
}
