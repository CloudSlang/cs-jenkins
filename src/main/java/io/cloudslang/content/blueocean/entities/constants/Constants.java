/*
 * (c) Copyright 2017 Micro Focus, L.P.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Apache License v2.0 which accompany this distribution.
 *
 * The Apache License is available at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.cloudslang.content.blueocean.entities.constants;

public class Constants {
    private Constants() {
        // prevent instantiation
    }

    public static class Actions {
        public static final String GET_TOKEN = "GetToken";
        public static final String GET_USER = "GetUser";
        public static final String CHANGE_TOKEN_EXPIRY_TIME = "ChangeTokenExpiryTime";
    }

    public static class Api {
        public static final String API_BASE_URI = "/jenkins/blue/rest";
    }

    public static class Errors {
        public static final String UNSUPPORTED_BLUE_OCEAN_API = "Unsupported Blue Ocean API.";
    }

    public static class Headers {
        public static final String AUTHORIZATION_HEADER_PREFIX = "Authorization:Bearer";
        public static final String RESPONSE_HEADERS = "responseHeaders";
        public static final String X_BLUE_OCEAN_JWT = "X-BLUEOCEAN-JWT";
    }
    public static class Miscellaneous {
        public static final String AMPERSAND = "&";
        public static final String EQUAL = "=";
    }

    public static class Regex {
        public static final String HOST_REGEX = "^(([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z]|[A-Za-z][A-Za-z0-9\\-]*[A-Za-z0-9])$";
        public static final String PORT_REGEX = "^([0-9]{1,4}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
    }
}
