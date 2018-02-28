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
package io.cloudslang.content.blueocean.entities.builders;

import io.cloudslang.content.constants.BooleanValues;
import io.cloudslang.content.httpclient.HttpClientInputs;

import java.net.MalformedURLException;
import java.net.URL;

import static io.cloudslang.content.blueocean.factory.UriFactory.getUri;
import static io.cloudslang.content.blueocean.utils.InputsUtil.areBothValuesPresent;
import static java.lang.Boolean.FALSE;
import static java.lang.String.valueOf;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class HttpClientInputsBuilder {
    private HttpClientInputsBuilder() {
        // prevent instantiation
    }

    public static HttpClientInputs buildHttpClientInputs(InputsWrapper wrapper) throws MalformedURLException {
        CommonInputs commonInputs = wrapper.getCommonInputs();

        HttpClientInputs httpClientInputs = new HttpClientInputs();
        httpClientInputs.setMethod(wrapper.getMethod());
        httpClientInputs.setUsername(commonInputs.getUsername());
        httpClientInputs.setPassword(commonInputs.getPassword());
        httpClientInputs.setTrustAllRoots(valueOf(commonInputs.getTrustAllRoots()));
        httpClientInputs.setKeepAlive(valueOf(commonInputs.getKeepAlive()));
        httpClientInputs.setUseCookies(valueOf(commonInputs.getUseCookies()));

        String trustKeyStore = commonInputs.getTrustKeystore();
        if (isNotBlank(trustKeyStore)) {
            httpClientInputs.setTrustKeystore(trustKeyStore);
        }

        String trustPassword = commonInputs.getTrustPassword();
        if (isNotBlank(trustPassword)) {
            httpClientInputs.setTrustPassword(trustPassword);
        }

        String keystore = commonInputs.getKeystore();
        if (isNotBlank(keystore)) {
            httpClientInputs.setKeystore(keystore);
        }

        String keystorePassword = commonInputs.getKeystorePassword();
        if (isNotBlank(keystorePassword)) {
            httpClientInputs.setKeystorePassword(keystorePassword);
        }

        String proxyUsername = commonInputs.getProxyUsername();
        if (isNotBlank(proxyUsername)) {
            httpClientInputs.setProxyUsername(proxyUsername);
        }

        String proxyPassword = commonInputs.getProxyPassword();
        if (isNotBlank(proxyUsername) && isNotBlank(proxyPassword)) {
            httpClientInputs.setProxyPassword(proxyPassword);
        }

        httpClientInputs.setConnectTimeout(valueOf(commonInputs.getConnectTimeout()));
        httpClientInputs.setSocketTimeout(valueOf(commonInputs.getSocketTimeout()));
        httpClientInputs.setX509HostnameVerifier(commonInputs.getX509HostnameVerifier());

        if (areBothValuesPresent(commonInputs.getProxyHost(), commonInputs.getProxyPort())) {
            httpClientInputs.setProxyHost(commonInputs.getProxyHost());
            httpClientInputs.setProxyPort(commonInputs.getProxyPort());
        }

        httpClientInputs.setUrl(buildUrlString(wrapper));

        httpClientInputs.setContentType(APPLICATION_JSON.getMimeType());
        httpClientInputs.setQueryParamsAreURLEncoded(BooleanValues.FALSE);
        httpClientInputs.setRequestCharacterSet(UTF_8.toString());
        httpClientInputs.setQueryParamsAreURLEncoded(valueOf(FALSE));

        return httpClientInputs;
    }

    private static String buildUrlString(InputsWrapper wrapper) throws MalformedURLException {
        CommonInputs commonInputs = wrapper.getCommonInputs();

        String url = commonInputs.getProtocol() + "://" + commonInputs.getEndpoint() + ":" + commonInputs.getPort() + getUri(wrapper);

        return new URL(url).toString();
    }
}
