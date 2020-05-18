<template>
    <v-container style="width: 100%; padding: 0">
        <v-layout row wrap>
            <v-flex xs3 class="zayavka_cell expand" @click="expand = !expand">
                {{zayavka.createdAt}}
            </v-flex>
            <v-flex xs9 md5 class="zayavka_cell expand" @click="expand = !expand">{{zayavka.request.lastName + " " +
                zayavka.request.firstName + " " +
                zayavka.request.otchestvo}}
            </v-flex>
            <v-spacer></v-spacer>
            <v-flex xs7 md3 class="zayavka_cell">
                <v-btn icon @click="toEditRequest">
                    <v-icon class="action_icon" color="#fe8f3e">create</v-icon>
                </v-btn>
                <v-btn icon @click="printContract">
                    <v-icon class="action_icon" color="#fe8f3e">print</v-icon>
                </v-btn>
                <v-btn icon href="/google/googlesignin" v-if="googleAuth === false">
                    <v-icon class="action_icon" color="grey">save</v-icon>
                </v-btn>
                <v-btn icon @click="saveToCloud" v-else>
                    <v-icon class="action_icon" color="#fe8f3e">save</v-icon>
                </v-btn>
            </v-flex>
            <v-flex xs4 md1 class="zayavka_cell">
                <v-btn flat class="btn_zayavka">
                    Отмена
                </v-btn>
            </v-flex>
        </v-layout>
        <v-expand-transition>
            <v-container v-show="expand" style="width: 100%; padding: 0">
                <v-divider dark style="margin: 1vw"></v-divider>
                <v-layout :id="'expand' + zayavka.id" class="expand_layout">
                    <v-flex xs3>
                        <v-layout row wrap>
                            <v-flex xs12 class="zayavka_desc">
                                {{zayavka.request.tel}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                {{zayavka.request.email}}
                            </v-flex>
                            <v-flex xs12 style="color: red;" class="zayavka_desc">
                                Не заполнено
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex xs4>
                        <v-layout row wrap>
                            <v-flex xs12 class="zayavka_desc">
                                Предполагаемая должность
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                {{zayavka.job}}
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-flex xs5>
                        <v-layout row wrap>
                            <v-flex xs12 class="zayavka_desc">
                                Адрес прописки: {{zayavka.officialAddress}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Номер паспорта: {{zayavka.passportNumber}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Идентификационный номер:
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                {{zayavka.uniqPassportNumber}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Дата рождения: {{zayavka.dateOfBirth}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Выдан: {{zayavka.whoIsGiven}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Дата выдачи: {{zayavka.startDateOfPassport}}
                            </v-flex>
                            <v-flex xs12 class="zayavka_desc">
                                Действителен до: {{zayavka.endDateOfPassport}}
                            </v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-container>
        </v-expand-transition>
        <v-divider v-if="$vuetify.breakpoint.smAndDown"></v-divider>
    </v-container>

</template>

<script>
    import {mapGetters} from 'vuex';
    import axios from "axios";

    export default {
        props: ["zayavka", "profile"],
        name: "Zayavka",
        methods: {
            toEditRequest() {
                this.$router.push('/request-edit?id=' + this.zayavka.id);
            },
            async printContract() {
                await axios.get("/print-request-contract/" + this.zayavka.id).then(resp => {
                    let filename = resp.data;
                    window.open("http://localhost:8091/" + filename);
                })
            },
            async saveToCloud() {
                await axios.post("/google/save-request", this.zayavka)
            }
        },
        data() {
            return {
                expand: false,
            }
        },
        computed: {
            ...mapGetters(['googleAuth'])
        }
    }
</script>

<style scoped>
    @media screen and (min-width: 960px) {
        .zayavka_cell {
            font-size: 1vw;
        }

        .zayavka_desc {
            font-size: 0.8vw;
        }
    }

    @media screen and (max-width: 960px) {
        .action_icon {
            font-size: 5vw;
        }

        .zayavka_cell {
            font-size: 3vw;
        }

        .zayavka_desc {
            font-size: 2.8vw;
        }
    }

    .zayavka_cell {
        margin: auto;
        color: white;
    }

    .expand {
        cursor: pointer;
    }


    .btn_zayavka {
        color: red;
        text-transform: none;
        font-size: 1vw;

    }

    .zayavka_desc {
        color: white;
        margin: auto;
    }
</style>