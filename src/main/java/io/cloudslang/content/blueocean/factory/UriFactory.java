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

import static io.cloudslang.content.blueocean.entities.Api.TOKEN;
import static io.cloudslang.content.blueocean.entities.Api.USERS;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Api.API_BASE_URI;
import static io.cloudslang.content.blueocean.entities.constants.Constants.Errors.UNSUPPORTED_BLUE_OCEAN_API;
import static io.cloudslang.content.blueocean.factory.users.UsersSuffixUri.getUsersSuffixUri;

public class UriFactory {
    private UriFactory() {
        // prevent instantiation
    }

    public static String getUri(InputsWrapper wrapper) {
        Api api = wrapper.getCommonInputs().getApi();

        switch (api) {
            case TOKEN:
                return TOKEN.getValue();
            case USERS:
                return API_BASE_URI + USERS.getValue() + "/" + getUsersSuffixUri(wrapper);
            default:
                throw new RuntimeException(UNSUPPORTED_BLUE_OCEAN_API);
        }
    }
}
