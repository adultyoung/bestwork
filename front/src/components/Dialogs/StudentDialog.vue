<template>
    <v-layout row wrap class="dialog_student_box">
        <v-flex xs12 md8>
            <div class="problems_text">Проблемы
                <div class="with_word"> с</div>
                <div class="problems_why">
                    распределением <br>перераспределением <br> практикой?
                </div>
            </div>
        </v-flex>
        <v-flex xs12 md4 class="card_help_box">
            <v-layout row wrap>
                <v-flex xs12 class="card_help">
                    <v-layout row wrap>
                        <v-flex xs12>
                            <div class="need_help_text">
                                Необходима помощь?
                            </div>
                            <div class="need_help_descr">
                                Отправьте нам ваш вопрос и наш менеджер свяжется с вами с ближайшее время
                            </div>
                            <v-text-field single-line label="Имя" v-model="help.name"></v-text-field>
                            <v-text-field single-line hint="В формате +375 (хх) ххх-хх-хх" label="Телефон"
                                          v-model="help.tel"></v-text-field>
                            <v-textarea label="Задайте ваш вопрос" v-model="help.question"></v-textarea>
                            <v-layout>
                                <v-spacer></v-spacer>
                                <v-btn class="help_btn" @click="sendHelp" color="grey">
                                    Отправить
                                </v-btn>
                                <v-spacer></v-spacer>
                            </v-layout>
                            <div id="messageStudent" style="text-align: center; color: grey; display: none">Ваш вопрос
                                отправлен. <br>Наш специалист в скором времени с вами свяжется.
                            </div>
                        </v-flex>
                    </v-layout>
                </v-flex>
                <v-flex xs12>
                    <v-layout>
                        <v-spacer></v-spacer>
                        <v-btn color="#00bae1" to="/contract-request" class="btn_goto">Перейти к оформлению
                            <v-icon dark>play_arrow</v-icon>
                        </v-btn>
                    </v-layout>
                </v-flex>
            </v-layout>
        </v-flex>
    </v-layout>
</template>

<script>
    import axios from 'axios'

    export default {
        props: ['dialog'],
        name: "StudentDialog",
        data() {
            return {
                help: {
                    name: null,
                    tel: null,
                    question: null
                }
            }
        },
        methods: {
            async sendHelp() {
                await axios.post("/help-controller", this.help).then(response => {
                    document.getElementById("messageStudent").style.display = "block"
                })
            }
        }
    }
</script>

<style scoped>
    @media screen and (max-width: 960px) {
        .with_word {
            font-size: 4vw;
            padding-left: 28vw;
        }

        .problems_text {
            padding: 1vh 5vw;
            font-size: 5vw;
        }

        .problems_why {
            font-size: 4vw;
            margin-bottom: 2vh;
        }

        .card_help {
            margin: 2vh 3vw;
            padding: 1vh 5vw;
        }

        .need_help_text {
            font-size: 5vw;
            padding: 2vh 0;
        }

        .need_help_descr {
            margin-bottom: 3vh;
        }

    }

    @media screen and (min-width: 960px) {
        .problems_text {
            font-size: 3vw;
            padding: 13vh 7vw;
        }

        .card_help {
            margin: 7vh 4vw;
            padding: 3vh 4vw;
        }

        .need_help_text {
            font-size: 1.5vw;
            margin-bottom: 1vw;
        }

        .need_help_descr {
            font-size: 0.7vw;
            margin-bottom: 7vh;
        }

        .help_btn {
            margin-top: 6vh;
            font-size: 1vw;

        }

        .with_word {
            padding: 4vh 16vw;
        }
    }

    .card_help_box {
        background-color: rgba(255, 255, 255, 0.5);
    }

    .dialog_student_box {
        background-image: url("https://m.1obl.ru/upload/iblock/6fb/6fb899e5db11e9ebac2be70766689ab6.jpg");
        background-repeat: no-repeat;
    }

    .problems_text {
        color: white;
        font-weight: 200;
    }

    .card_help {
        background-color: rgba(255, 255, 255, 0.9);
        border: 1px solid grey;
        border-radius: 25px;
    }

    .need_help_text {
        text-align: center;
    }

    .need_help_descr {
        text-align: center;
        color: grey;
    }

    .help_btn {
        color: white;
        text-transform: none;
    }

    .btn_goto {
        color: white;
        text-transform: none;
    }


    .problems_why {
        float: right;
        font-weight: 500;
    }
</style>