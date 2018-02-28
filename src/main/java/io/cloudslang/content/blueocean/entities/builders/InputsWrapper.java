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

public class InputsWrapper {
    private final CommonInputs commonInputs;
    private final TokenInputs tokenInputs;
    private final UserInputs userInputs;

    private final Api api;

    private final String method;
    private final String action;
    private final String token;

    private InputsWrapper(Builder builder) {
        this.commonInputs = builder.commonInputs;
        this.tokenInputs = builder.tokenInputs;
        this.userInputs = builder.userInputs;

        this.api = builder.api;

        this.method = builder.method;
        this.action = builder.action;
        this.token = builder.token;
    }

    public CommonInputs getCommonInputs() {
        return commonInputs;
    }

    public TokenInputs getTokenInputs() {
        return tokenInputs;
    }

    public UserInputs getUserInputs() {
        return userInputs;
    }

    public Api getApi() {
        return api;
    }

    public String getMethod() {
        return method;
    }

    public String getAction() {
        return action;
    }

    public String getToken() {
        return token;
    }

    public static class Builder {
        private CommonInputs commonInputs;
        private TokenInputs tokenInputs;
        private UserInputs userInputs;

        private Api api;

        private String method;
        private String action;
        private String token;

        public InputsWrapper build() {
            return new InputsWrapper(this);
        }

        public Builder withCommonInputs(CommonInputs commonInputs) {
            this.commonInputs = commonInputs;
            return this;
        }

        public Builder withTokenInputs(TokenInputs tokenInputs) {
            this.tokenInputs = tokenInputs;
            return this;
        }

        public Builder withUserInputs(UserInputs userInputs) {
            this.userInputs = userInputs;
            return this;
        }

        public Builder withApi(Api input) {
            api = input;
            return this;
        }

        public Builder withMethod(String input) {
            method = input;
            return this;
        }

        public Builder withAction(String input) {
            action = input;
            return this;
        }

        public Builder withToken(String input) {
            token = input;
            return this;
        }
    }
}
