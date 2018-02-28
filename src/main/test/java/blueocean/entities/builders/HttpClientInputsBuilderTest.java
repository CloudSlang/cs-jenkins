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

import io.cloudslang.content.blueocean.entities.Api;
import io.cloudslang.content.blueocean.entities.builders.CommonInputs;
import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.blueocean.entities.builders.UserInputs;
import io.cloudslang.content.httpclient.HttpClientInputs;
import org.junit.Test;

import java.net.MalformedURLException;

import static io.cloudslang.content.blueocean.entities.builders.HttpClientInputsBuilder.buildHttpClientInputs;
import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class HttpClientInputsBuilderTest {
    @Test
    public void testBuilder() throws MalformedURLException {
        HttpClientInputs httpClientInputs = buildHttpClientInputs(getWrapper());

        assertEquals(httpClientInputs.getUrl(), "http://domain.example.com:8080/jenkins/blue/rest/users/testUser");
        assertEquals(httpClientInputs.getMethod(), "GET");
        assertEquals(httpClientInputs.getUsername(), "me");
        assertEquals(httpClientInputs.getPassword(), "my_pass");
        assertEquals(httpClientInputs.getContentType(), "application/json");
        assertEquals(httpClientInputs.getRequestCharacterSet(), "UTF-8");
        assertEquals(httpClientInputs.getConnectTimeout(), "0");
        assertEquals(httpClientInputs.getSocketTimeout(), "0");
        assertEquals(httpClientInputs.getX509HostnameVerifier(), "allow_all");

        assertFalse(parseBoolean(httpClientInputs.getQueryParamsAreURLEncoded()));
        assertFalse(parseBoolean(httpClientInputs.getTrustAllRoots()));
        assertTrue(parseBoolean(httpClientInputs.getUseCookies()));
        assertTrue(parseBoolean(httpClientInputs.getKeepAlive()));

        assertNull(httpClientInputs.getProxyHost());
        assertNull(httpClientInputs.getProxyPort());
        assertNull(httpClientInputs.getProxyUsername());
        assertNull(httpClientInputs.getProxyPassword());
        assertNull(httpClientInputs.getTrustKeystore());
        assertNull(httpClientInputs.getTrustPassword());
        assertNull(httpClientInputs.getKeystore());
        assertNull(httpClientInputs.getKeystorePassword());
    }

    private CommonInputs getCommonInputs() {
        return new CommonInputs.Builder()
                .withProtocol("")
                .withEndpoint("domain.example.com")
                .withPort("")
                .withUsername("me")
                .withPassword("my_pass")
                .withProxyHost("")
                .withProxyPort("")
                .withProxyUsername("")
                .withProxyPassword("")
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

    private InputsWrapper getWrapper() {
        return new InputsWrapper.Builder()
                .withCommonInputs(getCommonInputs())
                .withUserInputs(getUserInputs())
                .withApi(Api.USERS)
                .withMethod("GET")
                .withAction("GetUser")
                .withToken("myToken")
                .build();
    }

    private UserInputs getUserInputs() {
        return new UserInputs.Builder().withUser("testUser").build();
    }
}
