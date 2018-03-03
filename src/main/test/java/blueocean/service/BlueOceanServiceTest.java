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
package blueocean.service;

import io.cloudslang.content.blueocean.entities.Api;
import io.cloudslang.content.blueocean.entities.builders.CommonInputs;
import io.cloudslang.content.blueocean.entities.builders.HttpClientInputsWrapper;
import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.blueocean.entities.builders.TokenInputs;
import io.cloudslang.content.blueocean.service.BlueOceanService;
import io.cloudslang.content.httpclient.CSHttpClient;
import io.cloudslang.content.httpclient.HttpClientInputs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;

import static io.cloudslang.content.blueocean.entities.Api.TOKEN;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(CSHttpClient.class)
public class BlueOceanServiceTest {
    private HttpClientInputsWrapper httpClientInputsWrapper;
    private HttpClientInputs httpClientInputs;
    private CommonInputs commonInputs;
    private TokenInputs tokenInputs;
    private InputsWrapper wrapper;

    @Mock
    private CSHttpClient csHttpClientMock;

    private BlueOceanService toTest;

    @Before
    public void init() throws Exception {
        whenNew(CSHttpClient.class).withNoArguments().thenReturn(csHttpClientMock);

        toTest = new BlueOceanService();
    }

    private void setTestWrapper(Api api, String action, String method) {
        httpClientInputsWrapper = new HttpClientInputsWrapper.Builder()
                .withUsername("me")
                .withPassword("my_pass")
                .withTrustAllRoots("false")
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

        commonInputs = new CommonInputs.Builder()
                .withProtocol("")
                .withPort("")
                .withAction(action)
                .withApi(api)
                .withEndpoint("blueocean.jenkins.com")
                .build();

        httpClientInputs = httpClientInputsWrapper.getHttpClientInputs(method, "proxy.example.com", "8080", "", "");

        tokenInputs = new TokenInputs.Builder()
                .withExpiryTimeInMins("45")
                .build();

        wrapper = new InputsWrapper.Builder()
                .withHttpClientInputs(httpClientInputs)
                .withCommonInputs(commonInputs)
                .withTokenInputs(tokenInputs)
                .build();
    }

//    @Test
    public void bla() throws MalformedURLException {
        setTestWrapper(TOKEN, "ChangeTokenExpiryTime", "GET");

        toTest.execute(wrapper);

        verify(csHttpClientMock, times(1)).execute(eq(httpClientInputs));
        verifyNoMoreInteractions(csHttpClientMock);
    }
}
