<template>
    <v-container :style="'background-image: url(' + require('../img/cabinet.png') + ')' " class="cabinet_container">
        <v-container class="cabinet_cont">
            <v-layout row wrap>
                <v-spacer></v-spacer>
                <v-flex xs7 md10>
                    <v-layout row wrap>
                        <v-flex>
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-btn @click="toProfile" block flat
                                           :style="$route.path === '/cabinet/profile' ? 'color: #fe8f3e; border-bottom: 2px solid #fe8f3e;' : 'border-bottom: 1px solid grey;'"
                                           class="p_cabinet">Профиль
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex>
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-btn flat block @click="toZayavki"
                                           :style="$route.path === '/cabinet/requests' ? 'color: #fe8f3e; border-bottom: 2px solid #fe8f3e;' : 'border-bottom: 1px solid grey;'"
                                           class="p_cabinet">Заявки
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex>
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-btn flat block @click="toUslugi"
                                           :style="$route.path === '/cabinet/services' ? 'color: #fe8f3e; border-bottom: 2px solid #fe8f3e;' : 'border-bottom: 1px solid grey;'"
                                           class="p_cabinet">Заказ документов
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex>
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <v-btn flat block @click="toPlatezhi"
                                           :style="$route.path === '/cabinet/payments' ? 'color: #fe8f3e; border-bottom: 2px solid #fe8f3e;' : 'border-bottom: 1px solid grey;'"
                                           class="p_cabinet">Платежи
                                    </v-btn>
                                </v-flex>
                            </v-layout>
                        </v-flex>

                    </v-layout>
                </v-flex>
                <v-flex xs2 md1>
                    <v-spacer></v-spacer>
                </v-flex>
            </v-layout>
            <router-view></router-view>
        </v-container>
    </v-container>
</template>

<script>
    export default {
        name: "Cabinet",
        methods: {
            toProfile() {
                this.$router.push("/cabinet/profile");
            },
            toZayavki() {
                this.$router.push("/cabinet/requests");
            },
            toUslugi() {
                this.$router.push("/cabinet/services");
            },
            toPlatezhi() {
                this.$router.push("/cabinet/payments");
            },
        }
    }
</script>

<style scoped>
    .cabinet_container {
        background-size: cover;
        max-width: 100%;
        padding: 0;
    }

    @media screen and (max-width: 960px) {

    }

    @media screen and (min-width: 960px) {
        .cabinet_cont {
            max-width: 51vw;
        }
    }

    .cabinet_cont {
        padding-top: 12vw;
        padding-bottom: 12vw;
    }

    .p_cabinet {
        background-color: transparent;
        text-transform: none;
        text-align: center;
        cursor: pointer;
        color: white;
        font-size: 1vw;
        margin-bottom: 6px !important;
        line-height: 2vw;
    }

    p {
        transition: all;
    }
</style>