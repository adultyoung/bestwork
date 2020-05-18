import Vue from 'vue'
import Vuex from 'vuex'
import axios from "axios";


Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        ...frontendData,
    },
    getters: {
        googleAuth: state => state.googleAuth,
        contractRequest: state => state.contractRequest,
        personInfo: state => state.personInfo,
        social: state => state.social,
        allRequests: state => state.allRequests,
        requests: state => state.requests,
        profile: state => state.profile,
        cosmetics: state => state.cosmetics,

    },
    mutations: {
        updateContractRequest(state, contractRequest) {
            state.contractRequest = contractRequest;
        },
        updateContractRequestUserId(state, user) {
            state.contractRequest.userId = user.id;
        }
    },
    actions: {
        getCosmetics({state}) {
            axios.get('/parfume-api').then(function (response) {
                state.cosmetics = response.data;
            })
        },
        async saveContractRequest({commit}, contractRequest) {
            await axios.post("/contract-request-api", contractRequest).then(function (response) {
                contractRequest = response.data;
                if (contractRequest) {
                    commit("updateContractRequest", contractRequest);
                    return true;
                } else {
                    return false;
                }
            })
        },
        async saveUser({commit}, person) {
            await axios.post("/request-user", person).then(function (response) {
                let user = response.data;
                if (user) {
                    commit("updateContractRequestUserId", user);
                    return true;
                }
                return false;
            })
        },
        async updateContractRequest({state, commit}, contractRequest) {
            await axios.put("contract-request-api", contractRequest);
        },
        async updateUser({commit}, person) {
            await axios.put("request-user/" + person.id, person);

        },

    }
})