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
package io.cloudslang.content.blueocean.factory.token;

import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;
import io.cloudslang.content.httpclient.HttpClientInputs;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Actions.CHANGE_TOKEN_EXPIRY_TIME;
import static io.cloudslang.content.blueocean.factory.token.TokensHelper.getTokensParamsString;

public class TokenQueryParamsBuilder {
    private TokenQueryParamsBuilder() {
        // prevent instantiation
    }

    public static void setTokenQueryParams(InputsWrapper wrapper, HttpClientInputs httpClientInputs) {
        String action = wrapper.getCommonInputs().getAction();

        switch (action) {
            case CHANGE_TOKEN_EXPIRY_TIME:
                httpClientInputs.setQueryParams(getTokensParamsString(wrapper));
                break;
            default:
                break;
        }
    }
}
