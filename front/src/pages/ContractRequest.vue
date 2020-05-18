<template>
    <v-container class="contract_request_container">
        <v-window v-model="contractStep">
            <v-window-item :value="1" ref="firstStep" :lazy-validation=true>
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../img/step_one.png"/></v-flex>
                    <v-flex xs8>
                        <common-form :person="person"></common-form>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <v-window-item :value="2" ref="secondStep">
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../img/step_two.png"/></v-flex>
                    <v-flex xs8>
                        <v-layout row>
                            <v-flex xs4>
                                <v-select
                                        :items="whoAreYou"
                                        v-model="contractRequest.whoAreYou"
                                        label="Кто вы?"
                                        outline
                                ></v-select>
                            </v-flex>
                            <v-spacer></v-spacer>
                            <v-flex xs5>
                                <v-text-field v-model="contractRequest.job"
                                              :rules="nameRules"
                                              label="Предпочитаемая должность">
                                </v-text-field>
                            </v-flex>
                            <v-spacer></v-spacer>
                        </v-layout>
                        <v-text-field v-model="contractRequest.officialAddress"
                                      :rules="nameRules"
                                      label="Адрес прописки по паспорту">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.passportNumber"
                                      :rules="passportRules"
                                      label="Номер паспорта">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.uniqPassportNumber"
                                      :rules="nameRules"
                                      label="Идентификационный номер">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.dateOfBirth"
                                      :rules="dateRules"
                                      type="date"
                                      label="Дата рождения">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.startDateOfPassport"
                                      :rules="dateRules"
                                      type="date"
                                      label="Когда выдан паспорт">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.endDateOfPassport"
                                      :rules="dateRules"
                                      type="date"
                                      label="Когда заканчивается паспорт">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.whoIsGiven"
                                      :rules="nameRules"
                                      label="Кем выдан">
                        </v-text-field>

                        <!--                <v-text-field v-model="contractRequest.homeTel"-->
                        <!--                              :mask="homeTelMask"-->
                        <!--                              label="Домашний телефон">-->
                        <!--                </v-text-field>-->
                        <!--                <v-text-field v-model="contractRequest.factAddress"-->
                        <!--                              :rules="nameRules"-->
                        <!--                              label="Фактический адрес проживнаия">-->
                        <!--                </v-text-field>-->
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <v-window-item :value="3">
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../img/step_three.png"/></v-flex>
                    <v-flex xs8>
                        <success-send style="margin-top: 8vw;"></success-send>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <!--            <v-window-item :value="3" ref="thirdStep">-->
            <!--                <h4>Доверенные лица</h4>-->
            <!--                <template v-for="(index, person) in contractRequest.trustees">-->
            <!--                    <v-layout row>-->
            <!--                        <v-flex xs11>-->
            <!--                            <person-info :person="person"></person-info>-->
            <!--                        </v-flex>-->
            <!--                        <v-flex xs1>-->
            <!--                            <template v-if="index === contractRequest.trustees.length-1">-->
            <!--                                <v-btn color="red">-->
            <!--                                    <v-icon style="color: white;">remove</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                            <template v-else>-->
            <!--                                <v-btn color="#00bae1">-->
            <!--                                    <v-icon style="color: white">add</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                        </v-flex>-->
            <!--                    </v-layout>-->
            <!--                </template>-->
            <!--                <h4>Куратор от университета</h4>-->
            <!--                <person-info></person-info>-->
            <!--                <h4>Рекомендации</h4>-->
            <!--                <template v-for="(pers, index) in contractRequest.recommendation">-->
            <!--                    <v-layout row>-->
            <!--                        <v-flex xs11>-->
            <!--                            <person-info :person="pers"></person-info>-->
            <!--                        </v-flex>-->
            <!--                        <v-flex xs1>-->
            <!--                            <template-->
            <!--                                    v-if="index !== contractRequest.recommendation.length-1 ? (contractRequest.recommendation.length !== 1) : false">-->
            <!--                                <v-btn @click="removeRecommend(index)" color="red">-->
            <!--                                    <v-icon style="color: white;">remove</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                            <template v-else>-->
            <!--                                <v-btn @click="addRecommend" color="#00bae1">-->
            <!--                                    <v-icon style="color: white">add</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                        </v-flex>-->
            <!--                    </v-layout>-->
            <!--                </template>-->
            <!--                <h4>Социальные сети</h4>-->
            <!--                <template v-for="(socialNetwor, index) in contractRequest.socialNetworks">-->
            <!--                    <v-layout row>-->
            <!--                        <v-flex xs11>-->
            <!--                            <social-network :socialNetwork="socialNetwor"></social-network>-->
            <!--                        </v-flex>-->
            <!--                        <v-flex xs1>-->
            <!--                            <template-->
            <!--                                    v-if="index !== contractRequest.socialNetworks.length-1 ? (contractRequest.socialNetworks.length !== 1) : false">-->
            <!--                                <v-btn @click="removeSocial(index)" color="red">-->
            <!--                                    <v-icon style="color: white;">remove</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                            <template v-else>-->
            <!--                                <v-btn @click="addSocial" color="#00bae1">-->
            <!--                                    <v-icon style="color: white">add</v-icon>-->
            <!--                                </v-btn>-->
            <!--                            </template>-->
            <!--                        </v-flex>-->
            <!--                    </v-layout>-->
            <!--                </template>-->
            <!--            </v-window-item>-->
            <v-btn class="btn_step" type="submit" @click="saveRequest">Далее</v-btn>
            <!--            <v-btn @click="contractStep -=1">Назад</v-btn>-->
        </v-window>
    </v-container>
</template>

<script>
    import SuccessSend from "../components/form/SuccessSend.vue"
    import SocialNetwork from "../components/form/SocialNetwork.vue"
    import PersonInfo from "../components/form/PersonInfo.vue"
    import CommonForm from "../components/form/CommonForm.vue"
    import {mapGetters} from 'vuex'

    export default {
        name: "ContractRequest",
        components: {
            SocialNetwork,
            CommonForm,
            PersonInfo,
            SuccessSend
        },
        data() {
            return {
                whoAreYou: [
                    "ИП",
                    "Молодой специалист",
                    "Студент",
                    "Физ. лицо",
                ],
                person: {
                    firstName: null,
                    lastName: null,
                    otchestvo: null,
                    tel: null,
                    email: null
                },
                valid: false,
                contractStep: 1,
                nameRules: [
                    v => !!v || 'Это поле обязательно',
                    v => (v && v.length <= 500) || 'Поле содержит более 500 символов'
                ],
                passportRules: [
                    v => !!v || 'Это поле обязательно',
                    v => (v && v.length === 9) || 'Номер паспорта должен состоять из 9 знаков',
                    v => /[A-Z]{2}[0-9]{7}/.test(v) || 'Номер паспорта должен состоять из 2 букв латинского алфавита и 7 цифр',
                ],
                telRules: [
                    v => !!v || 'Это поле обязательно',
                    v => (v && v.length === 19) || 'Введите корректный номер телефона',
                ],
                telMask: '+375 (##) ###-##-##',
                homeTelMask: '+375 (###) ##-##-##',
                dateRules: [
                    v => !!v || "Это поле обязательно",
                ],

            }
        },
        computed: {
            ...mapGetters(["contractRequest", "social", "personInfo"])
        },
        methods: {
            addSocial() {
                this.contractRequest.socialNetworks.push(JSON.parse(JSON.stringify(this.social)))
            },
            removeSocial(index) {
                this.contractRequest.socialNetworks.splice(index + 1, 1)
            },
            addRecommend() {
                this.contractRequest.recommendation.push(JSON.parse(JSON.stringify(this.personInfo)))
            },
            removeRecommend(index) {
                this.contractRequest.recommendation.splice(index + 1, 1)
            },
            async saveRequest() {
                if (this.contractStep === 1) {
                    this.contractRequest.request.firstName = this.person.firstName;
                    this.contractRequest.request.lastName = this.person.lastName;
                    this.contractRequest.request.otchestvo = this.person.otchestvo;
                    this.contractRequest.request.tel = this.person.tel;
                    await this.$store.dispatch("saveUser", this.person);
                    if (this.contractRequest.userId !== null) {
                        this.contractStep = 2;
                    }
                } else if (this.contractStep === 2) {
                    await this.$store.dispatch("saveContractRequest", this.contractRequest);
                    if (this.contractRequest.id !== null) {
                        this.contractStep = 3;
                    }
                }
            }
        }
    }
</script>

<style scoped>
    .contract_request_container {
        max-width: 73vw;
        padding: 0;
        margin: 25vh 9vw;
    }


    @media screen and (max-width: 960px) {
        .btn_step {
            height: 7.3vw !important;
        }
    }

    .btn_step {
        float: right;
        background-color: #1d4696 !important;
        color: white;
        box-shadow: none;
        text-transform: none;
        border-radius: 0;
        width: 15vw;
        height: 2.3vw;
        font-size: 1vw;
        margin-top: 2vw;
        font-weight: 300;
    }

    .img_step {
        display: block;
        margin: auto;
        width: 19vw;
    }
</style>