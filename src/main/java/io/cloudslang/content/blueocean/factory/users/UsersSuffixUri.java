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
package io.cloudslang.content.blueocean.factory.users;

import io.cloudslang.content.blueocean.entities.builders.InputsWrapper;

import static io.cloudslang.content.blueocean.entities.constants.Constants.Actions.GET_USER;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class UsersSuffixUri {
    private UsersSuffixUri() {
        // prevent instantiation
    }

    public static String getUsersSuffixUri(InputsWrapper wrapper) {
        String action = wrapper.getAction();

        switch (action) {
            case GET_USER:
                return wrapper.getUserInputs().getUser();
            default:
                return EMPTY;
        }
    }
}