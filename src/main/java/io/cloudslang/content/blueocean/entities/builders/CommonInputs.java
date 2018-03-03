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

import io.cloudslang.content.blueocean.entities.Api;

import static io.cloudslang.content.blueocean.validators.Validators.getValidHost;
import static io.cloudslang.content.blueocean.validators.Validators.getValidOrDefaultValue;
import static io.cloudslang.content.utils.OtherUtilities.isValidIpPort;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class CommonInputs {
    private final Api api;
    private final String action;
    private final String endpoint;
    private final String port;
    private final String protocol;

    private CommonInputs(Builder builder) {
        this.api = builder.api;
        this.action = builder.action;
        this.endpoint = builder.endpoint;
        this.port = builder.port;
        this.protocol = builder.protocol;
    }

    public Api getApi() {
        return api;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getAction() {
        return action;
    }

    public String getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public static class Builder {
        private Api api;
        private String action;
        private String endpoint;
        private String port;
        private String protocol;

        public CommonInputs build() {
            return new CommonInputs(this);
        }

        public Builder withApi(Api input) {
            api = input;
            return this;
        }

        public Builder withAction(String input) {
            action = input;
            return this;
        }

        public Builder withEndpoint(String input) {
            notNull(input);
            endpoint = getValidHost(input);
            return this;
        }

        public Builder withPort(String input) {
            port = isNotBlank(input) && isValidIpPort(input) ? input : "8080";
            return this;
        }

        public Builder withProtocol(String input) {
            protocol = getValidOrDefaultValue(input, "http", new String[]{"http", "https"});
            return this;
        }
    }
}
