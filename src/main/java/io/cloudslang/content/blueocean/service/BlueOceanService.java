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
package io.cloudslang.content.blueocean.service;

import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.httpclient.CSHttpClient;
import io.cloudslang.content.httpclient.HttpClientInputs;

import java.net.MalformedURLException;
import java.util.Map;

import static io.cloudslang.content.blueocean.entities.builders.HttpClientInputsBuilder.buildHttpClientInputs;
import static io.cloudslang.content.blueocean.factory.HeadersBuilder.buildHeaders;
import static io.cloudslang.content.blueocean.factory.QueryParamsBuilder.buildQueryParams;

public class BlueOceanService {
    public final Map<String, String> execute(InputsWrapper wrapper) throws MalformedURLException {
        HttpClientInputs httpClientInputs = buildHttpClientInputs(wrapper);

        buildHeaders(wrapper, httpClientInputs);
        buildQueryParams(wrapper, httpClientInputs);

        return new CSHttpClient().execute(httpClientInputs);
    }
}
