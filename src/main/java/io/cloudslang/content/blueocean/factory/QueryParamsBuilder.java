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
package io.cloudslang.content.blueocean.factory;

import io.cloudslang.content.blueocean.entities.Api;
import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.httpclient.HttpClientInputs;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Errors.UNSUPPORTED_BLUE_OCEAN_API;
import static io.cloudslang.content.blueocean.factory.token.TokenQueryParamsBuilder.setTokenQueryParams;

public class QueryParamsBuilder {
    private QueryParamsBuilder() {
        // prevent instantiation
    }

    public static void buildQueryParams(InputsWrapper wrapper, HttpClientInputs httpClientInputs) {
        Api api = wrapper.getApi();

        switch (api) {
            case TOKEN:
                setTokenQueryParams(wrapper, httpClientInputs);
                break;
            case USERS:
                break;
            default:
                throw new RuntimeException(UNSUPPORTED_BLUE_OCEAN_API);
        }
    }
}
