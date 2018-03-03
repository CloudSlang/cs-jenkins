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
package blueocean.entities.builders;

import io.cloudslang.content.blueocean.entities.builders.HttpClientInputsWrapper;
import io.cloudslang.content.httpclient.HttpClientInputs;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Boolean.parseBoolean;
import static org.apache.http.client.methods.HttpGet.METHOD_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HttpClientInputsWrapperTest {
    private HttpClientInputsWrapper httpClientInputsWrapper;

    @Before
    public void init() {
        httpClientInputsWrapper = new HttpClientInputsWrapper.Builder()
                .withUsername("me")
                .withPassword("my_pass")
                .withTrustAllRoots("")
                .withX509HostnameVerifier("")
                .withTrustKeystore("")
                .withTrustPassword("")
                .withKeystore("")
                .withKeystorePassword("")
                .withConnectTimeout("")
                .withSocketTimeout("")
                .withUseCookies("")
                .withKeepAlive("")
                .build();
    }

    @Test
    public void shouldSetExpectedValuesOnHttpClientInputs() {
        HttpClientInputs httpClientInputs = httpClientInputsWrapper
                .getHttpClientInputs(METHOD_NAME, "proxy.example.com", "8080", "", "");

        assertEquals(httpClientInputs.getMethod(), "GET");
        assertEquals(httpClientInputs.getUsername(), "me");
        assertEquals(httpClientInputs.getPassword(), "my_pass");
        assertEquals(httpClientInputs.getRequestCharacterSet(), "UTF-8");
        assertEquals(httpClientInputs.getConnectTimeout(), "0");
        assertEquals(httpClientInputs.getSocketTimeout(), "0");
        assertEquals(httpClientInputs.getX509HostnameVerifier(), "allow_all");
        assertEquals(httpClientInputs.getProxyHost(), "proxy.example.com");
        assertEquals(httpClientInputs.getProxyPort(), "8080");

        assertFalse(parseBoolean(httpClientInputs.getQueryParamsAreURLEncoded()));
        assertFalse(parseBoolean(httpClientInputs.getTrustAllRoots()));
        assertFalse(parseBoolean(httpClientInputs.getUseCookies()));
        assertTrue(parseBoolean(httpClientInputs.getKeepAlive()));

        assertNull(httpClientInputs.getProxyUsername());
        assertNull(httpClientInputs.getProxyPassword());
        assertNull(httpClientInputs.getTrustKeystore());
        assertNull(httpClientInputs.getTrustPassword());
        assertNull(httpClientInputs.getKeystore());
        assertNull(httpClientInputs.getKeystorePassword());
    }
}
