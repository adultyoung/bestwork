<template>
    <v-container class="contract_request_container">
        <v-window v-model="contractStep">
            <v-window-item :value="1" ref="firstStep" :lazy-validation=true>
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../../img/step_one.png"/></v-flex>
                    <v-flex xs8>
                        <common-form :person="person"></common-form>
                        <v-text-field v-model="contractRequest.fioRod"
                                      :rules="nameRules"
                                      label="ФИО в родительном падеже">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.fioTvor"
                                      :rules="nameRules"
                                      label="Фио в творительном падеже">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.fioDat"
                                      :rules="nameRules"
                                      label="Фио в дательном падеже">
                        </v-text-field>
                        <v-select :items="companies"
                                  item-text="company"
                                  item-value="abbr"
                                  v-model="contractRequest.companyType"
                                  label="Компания"
                        >
                        </v-select>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <v-window-item :value="2" ref="secondStep">
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../../img/step_two.png"/></v-flex>
                    <v-flex xs8>
                        <v-text-field v-model="contractRequest.job"
                                      :rules="nameRules"
                                      label="Предпочитаемая должность">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.jobTvor"
                                      :rules="nameRules"
                                      label="Должность в творительном падеже">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.jobRod"
                                      :rules="nameRules"
                                      label="Должность в родительном падеже">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.officialAddress"
                                      :rules="nameRules"
                                      label="Адрес прописки">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.passportNumber"
                                      :rules="passportRules"
                                      label="Номер паспорта">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.uniqPassportNumber"
                                      :rules="nameRules"
                                      label="Идентификационный номер">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.whoIsGiven"
                                      :rules="nameRules"
                                      label="Кем выдан паспорт">
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
                        <v-text-field v-model="contractRequest.homeTel"
                                      :mask="homeTelMask"
                                      label="Домашний телефон">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.factAddress"
                                      :rules="nameRules"
                                      label="Фактический адрес проживнаия">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.period"
                                      :rules="nameRules"
                                      type="number"
                                      label="Срок контракта">
                        </v-text-field>
                        <v-text-field v-model="contractRequest.startOfContract"
                                      :rules="dateRules"
                                      type="date"
                                      label="Дата начала контракта">
                        </v-text-field>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <v-window-item :value="3" ref="thirdStep">
                <v-layout row>
                    <v-flex xs4><img class="img_step" src="../../img/step_three.png"/></v-flex>
                    <v-flex xs8>
                        <h4>Доверенные лица</h4>
                        <template v-for="(person, index) in contractRequest.trustees">
                            <v-layout row>
                                <v-flex xs11>
                                    <person-info :person="person"></person-info>
                                </v-flex>
                                <v-flex xs1>
                                    <template v-if="index === contractRequest.trustees.length-1">
                                        <v-btn color="red">
                                            <v-icon style="color: white;">remove</v-icon>
                                        </v-btn>
                                    </template>
                                    <template v-else>
                                        <v-btn color="#00bae1">
                                            <v-icon style="color: white">add</v-icon>
                                        </v-btn>
                                    </template>
                                </v-flex>
                            </v-layout>
                        </template>
                        <h4>Куратор от университета</h4>
                        <person-info></person-info>
                        <h4>Рекомендации</h4>
                        <template v-for="(pers, index) in contractRequest.recommendation">
                            <v-layout row>
                                <v-flex xs11>
                                    <person-info :person="pers"></person-info>
                                </v-flex>
                                <v-flex xs1>
                                    <template
                                            v-if="index !== contractRequest.recommendation.length-1 ? (contractRequest.recommendation.length !== 1) : false">
                                        <v-btn @click="removeRecommend(index)" color="red">
                                            <v-icon style="color: white;">remove</v-icon>
                                        </v-btn>
                                    </template>
                                    <template v-else>
                                        <v-btn @click="addRecommend" color="#00bae1">
                                            <v-icon style="color: white">add</v-icon>
                                        </v-btn>
                                    </template>
                                </v-flex>
                            </v-layout>
                        </template>
                        <h4>Социальные сети</h4>
                        <template v-for="(socialNetwor, index) in contractRequest.socialNetworks">
                            <v-layout row>
                                <v-flex xs11>
                                    <social-network :socialNetwork="socialNetwor"></social-network>
                                </v-flex>
                                <v-flex xs1>
                                    <template
                                            v-if="index !== contractRequest.socialNetworks.length-1 ? (contractRequest.socialNetworks.length !== 1) : false">
                                        <v-btn @click="removeSocial(index)" color="red">
                                            <v-icon style="color: white;">remove</v-icon>
                                        </v-btn>
                                    </template>
                                    <template v-else>
                                        <v-btn @click="addSocial" color="#00bae1">
                                            <v-icon style="color: white">add</v-icon>
                                        </v-btn>
                                    </template>
                                </v-flex>
                            </v-layout>
                        </template>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-window-item>
            <v-btn class="btn_step" type="submit" @click="saveRequest">Далее</v-btn>
            <!--            <v-btn @click="contractStep -=1">Назад</v-btn>-->
        </v-window>
    </v-container>
</template>

<script>
    import axios from "axios"
    import SuccessSend from "../form/SuccessSend.vue"
    import SocialNetwork from "../form/SocialNetwork.vue"
    import PersonInfo from "../form/PersonInfo.vue"
    import CommonForm from "../form/CommonForm.vue"
    import {mapGetters} from 'vuex'

    export default {
        name: "RequestEdit",
        components: {
            SocialNetwork,
            CommonForm,
            PersonInfo,
            SuccessSend
        },
        data() {
            return {
                person: {
                    id: null,
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
                contractRequest: [],
                companies: [
                    {company: "Твое дело", abbr: "TD"},
                    {company: "Кэшбокс", abbr: "KB"},
                    {company: "Твои инвестиции", abbr: "TI"},
                    {company: "Факост", abbr: "FK"},
                ]
            }
        },
        computed: {
            ...mapGetters(["social", "personInfo"])
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
                    await this.$store.dispatch("updateUser", this.person);
                    if (this.contractRequest.userId !== null) {
                        this.contractStep = 2;
                    }
                } else if (this.contractStep === 3) {
                    await this.$store.dispatch("updateContractRequest", this.contractRequest);
                    if (this.contractRequest.id !== null) {
                        this.$router.push("/cabinet/requests")
                    }
                } else if (this.contractStep === 2) {
                    this.contractStep += 1;
                }
            }
        },
        async beforeMount() {
            await axios.get("/contract-request-api?id=" + this.$route.query.id).then((resp) => {
                this.contractRequest = resp.data;
                this.person.id = resp.data.userId;
            });
            await axios.get("/user-api?id=" + this.contractRequest.userId).then((response) => {
                this.person.firstName = response.data.firstName;
                this.person.lastName = response.data.lastName;
                this.person.otchestvo = response.data.otchestvo;
                this.person.tel = response.data.tel;
                this.person.email = response.data.email;
            });
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