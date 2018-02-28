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

public class Inputs {
    private Inputs() {
        // prevent instantiation
    }

    public static class StandardInputs {
        public static final String ENDPOINT = "endpoint";
        public static final String PORT = "port";
        public static final String PROTOCOL = "protocol";
        public static final String TOKEN = "token";
    }

    public static class TokenInputs {
        public static final String EXPIRY_TIME_IN_MINS = "expiryTimeInMins";
    }

    public static class UserInputs {
        public static final String USER = "user";
    }
}
