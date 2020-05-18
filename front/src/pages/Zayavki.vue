<template>
    <v-layout row wrap>
        <v-flex xs12 class="zayavki_header">Ваши заявки</v-flex>
        <v-flex xs12 v-if="requests.contractRequests.length > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на контракт</v-flex>
                <template v-for="(zayavka, index) in requests.contractRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== requests.contractRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="requests.raspredRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на распределение</v-flex>
                <template v-for="(zayavka, index) in requests.raspredRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== requests.raspredRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="requests.pereRaspredRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на перераспределение</v-flex>
                <template v-for="(zayavka, index) in requests.pereRaspredRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== requests.pereRaspredRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="requests.practiseRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на практику</v-flex>
                <template v-for="(zayavka, index) in requests.practiseRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== requests.practiseRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 class="zayavki_header" v-if="allRequests !== undefined">
            Общие заявки
        </v-flex>
        <v-flex xs12 v-if="allRequests.contractRequests.length > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на контракт</v-flex>
                <template v-for="(zayavka, index) in allRequests.contractRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== allRequests.contractRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="allRequests.raspredRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на распределение</v-flex>
                <template v-for="(zayavka, index) in allRequests.raspredRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== allRequests.raspredRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="allRequests.pereRaspredRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на перераспределение</v-flex>
                <template v-for="(zayavka, index) in allRequests.pereRaspredRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== allRequests.pereRaspredRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
        <v-flex xs12 v-if="allRequests.practiseRequests > 0">
            <v-layout row wrap>
                <v-flex xs12 class="zayavki_header">Заявки на практику</v-flex>
                <template v-for="(zayavka, index) in allRequests.practiseRequests">
                    <v-flex xs12>
                        <zayavka :zayavka="zayavka" :profile="profile"></zayavka>
                    </v-flex>
                    <v-divider v-if="index !== allRequests.practiseRequests.length-1"></v-divider>
                </template>
            </v-layout>
        </v-flex>
    </v-layout>
</template>

<script>
    import Zayavka from "./Zayavka.vue"
    import {mapGetters} from 'vuex'

    export default {
        name: "Zayavki",
        components: {
            Zayavka
        },
        computed: {
            ...mapGetters(["requests", "allRequests", "profile"])
        }

    }
</script>

<style scoped>
    @media screen and (min-width: 960px) {
        .zayavki_header {
            font-size: 1vw;
        }
    }

    @media screen and (max-width: 960px) {
        .zayavki_header {
            font-size: 5vw;
        }
    }

    .zayavki_header {
        color: white;
    }
</style>