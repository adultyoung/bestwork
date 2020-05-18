<template>
    <v-container style="max-width: 100%; padding: 0">
        <v-layout row>
            <v-flex xs3 md4>
                <v-icon class="cabinet_person">person</v-icon>
            </v-flex>
            <v-flex xs9 md8>
                <v-form>
                    <v-layout row wrap>
                        <v-flex xs4 md3>
                            <v-text-field class="text_field_cabinet" dense color="white" background-color="transparent"
                                          solo flat dark hide-details
                                          v-model="profile.firstName"
                                          :rules="nameRules"
                                          label="Имя"
                                          :disabled="redit"
                                          required>
                            </v-text-field>
                        </v-flex>
                        <v-flex xs4 md3>
                            <v-text-field class="text_field_cabinet" hide-details dense color="white"
                                          background-color="transparent" solo flat
                                          dark v-model="profile.lastName"
                                          :rules="nameRules"
                                          label="Фамилия"
                                          :disabled="redit"
                                          required>
                            </v-text-field>
                        </v-flex>
                        <v-flex xs1>
                            <v-btn @click="redit = !redit" icon>
                                <v-icon style="color: #fe8f3e;">create</v-icon>
                            </v-btn>
                        </v-flex>
                        <v-flex xs2 md5>
                            <v-spacer></v-spacer>
                        </v-flex>
                        <v-flex xs6>
                            <v-text-field class="text_field_cabinet" hide-details dense color="white"
                                          background-color="transparent" solo flat
                                          dark v-model="profile.otchestvo"
                                          :rules="nameRules"
                                          label="Отчество"
                                          :disabled="redit"
                                          required>
                            </v-text-field>
                        </v-flex>
                    </v-layout>
                    <v-text-field class="text_field_cabinet" hide-details dense color="white"
                                  background-color="transparent" solo flat dark
                                  v-model="profile.tel"
                                  :rules="telRules"
                                  :disabled="redit"
                                  label="Телефон"
                                  required>
                    </v-text-field>
                    <v-text-field class="text_field_cabinet" hide-details dense color="white"
                                  background-color="transparent" solo flat dark
                                  v-model="profile.email"
                                  :rules="emailRules"
                                  label="Адрес электронной почты"
                                  :disabled="redit"
                                  required>
                    </v-text-field>
                    <v-layout row>
                        <v-flex xs5 md3>
                            <v-btn v-show="!redit" @click="saveProfile" style="text-transform: none" outline
                                   color="#fe8f3e" type="submit">Сохранить
                            </v-btn>
                        </v-flex>
                        <v-flex xs4>
                            <v-btn flat class="btn_cabinet">Выйти</v-btn>
                        </v-flex>
                    </v-layout>
                </v-form>
                <input type="file">
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import axios from "axios"
    import {mapGetters} from 'vuex'

    export default {
        name: "Profile",
        computed: {
            ...mapGetters(["profile"])
        },
        data() {
            return {
                telRules: [
                    v => !!v || 'Это поле обязательно',
                    v => (v && v.length === 19) || 'Введите корректный номер телефона',
                ],
                nameRules: [
                    v => !!v || 'Это поле обязательно',
                    v => (v && v.length <= 500) || 'Поле содержит более 500 символов'
                ],
                emailRules: [
                    v => !!v || 'E-mail обязателен',
                    v => /.+@.+/.test(v) || 'Введите валидный E-mail',
                ],
                redit: true,
                message: null,
            }
        },
        methods: {
            async saveProfile() {
                await axios.post("/profile-api", this.profile).then(function (response) {
                    this.message = response.data;
                })
            }
        },
    }
</script>

<style scoped>

    @media screen and (max-width: 960px) {
        .cabinet_person {
            border: 4px solid #fe8f3e;
        }
    }

    @media screen and (min-width: 960px) {
        .cabinet_person {
            border: 10px solid #fe8f3e;
        }
    }

    .cabinet_person {
        float: right;
        font-size: 7vw;
        border-radius: 10000px;
        color: #fe8f3e;
        margin-right: 2vw;
    }

    .theme--dark .v-text-field--solo .v-input__slot {
        background: transparent !important;
    }

    .text_field_cabinet {
        color: white;
        font-size: 1vw;
    }

    .btn_cabinet {
        text-transform: none;
        font-size: 1vw;
        color: white;
        float: right;
    }
</style>