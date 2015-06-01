package nl.my888.springframework.test.web.servlet.halmatchers;

import org.springframework.http.MediaType;

class HalConstants {

    static final MediaType MEDIA_TYPE_APPLICATION_HAL_JSON = new MediaType("application", "hal+json");

    static final MediaType MEDIA_TYPE_APPLICATION_VND_ERROR_JSON = new MediaType("application", "vnd.error+json");

}
