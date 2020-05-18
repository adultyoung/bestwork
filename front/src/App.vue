<template>
    <v-app>
        <v-container class="header" style="padding: 0">
            <template v-if="$route.path === '/' && !$vuetify.breakpoint.smAndDown">
                <lazy-loader></lazy-loader>
                <v-layout style="height: 50px; padding-top: 7px; position: relative; z-index: 1;" align-left>
                    <v-container>
                        <i class="tiny material-icons header_icons">phone
                        </i>
                        <a class="header_links" href="tel:+375256499702">+375 (25) 649-97-02</a>
                        <i class="tiny material-icons header_icons">mail_outline</i>
                        <a class="header_links" href="mailto:info@bestwork.by">info@bestwork.by</a>
                    </v-container>
                    <v-spacer></v-spacer>
                </v-layout>
            </template>
            <div class="div_nav" style="height: 64px; position: relative; z-index: 1" v-if="$route.path !== '/parfume'">
                <v-toolbar id="sticky_header"
                           :style="$route.path !== '/' ? 'background-color: black !important; padding: 0 2%' : 'padding: 0 2%;'"
                           flat dark color="transparent"
                           :class="$route.path !== '/' && !$vuetify.breakpoint.smAndDown ? 'fixed_header header' : 'header_nav'">
                    <v-toolbar-title>
                        <a href="/">
                            <img class="logo-main scale-with-grid"
                                 src="./img/logo-bw.png"
                                 data-retina="http://bestwork.by/wp-content/uploads/2019/05/logo-bw.png"
                                 data-height="43"
                                 alt="logo-bw"
                                 style="max-height: 43px; display: block"/>
                        </a>
                    </v-toolbar-title>
                    <template v-if="!$vuetify.breakpoint.smAndDown">
                        <v-spacer></v-spacer>

                        <v-btn class="header_buttons" flat href="/">
                            Главная
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('uslugi')">
                            Услуги
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('how_does_it_works')">
                            Как это работает
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('why_are_we')">
                            Почему мы
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('our_projects')">
                            Наши проекты
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('FAQ')">
                            FAQ
                        </v-btn>
                        <v-btn class="header_buttons" flat @click="scrollTo('reviews')">
                            Отзывы
                        </v-btn>
                        <v-btn style="border-radius: 0; line-height: normal; text-transform: none; height: 2.5vw; margin-left: 2%"
                               class="header_buttons" color="#FF6600" href="tel:+375256499702">
                            Позвонить
                        </v-btn>
                        <template v-if="!profile">
                            <v-btn icon
                                   @click="dialog = !dialog"

                            >
                                <v-icon>account_circle</v-icon>
                            </v-btn>
                        </template>
                        <template v-else>
                            <v-menu offset-y>
                                <v-btn
                                        slot="activator"
                                        dark
                                        flat
                                        style="text-transform: none !important;"
                                >
                                    <div style="color: white; font-size: 0.85vw">{{profile.firstName}}</div>
                                </v-btn>
                                <v-list>
                                    <v-list-tile @click="$router.push('/cabinet/profile')">
                                        <v-list-tile-title>Профиль</v-list-tile-title>
                                    </v-list-tile>
                                    <v-list-tile @click="$router.push('/cabinet/requests')">
                                        <v-list-tile-title>Заявки</v-list-tile-title>
                                    </v-list-tile>
                                    <v-divider></v-divider>
                                    <v-list-tile href="/logout">
                                        <v-list-tile-title>Выйти</v-list-tile-title>
                                    </v-list-tile>
                                </v-list>
                            </v-menu>
                        </template>
                    </template>
                    <template v-else>
                        <v-spacer></v-spacer>
                        <v-toolbar-side-icon @click.stop="drawer = !drawer">
                            <v-icon style="color: #fe8f3e" class="header_icon" size="36">dehaze</v-icon>
                        </v-toolbar-side-icon>
                    </template>
                </v-toolbar>
            </div>
            <v-expand-transition style="" v-if="$vuetify.breakpoint.smAndDown">
                <v-list v-show="drawer" id="expand_menu">
                    <v-layout>
                        <v-btn class="header_buttons" flat href="/">
                            Главная
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('uslugi')">
                    <span>
                         Услуги
                    </span>
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('how_does_it_works')">
                    <span>
                    Как это работает
                    </span>
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('why_are_we')">
                            Почему мы
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('our_projects')">
                            Наши проекты
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('FAQ')">
                            FAQ
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn class="header_buttons" flat @click="scrollTo('reviews')">
                            Отзывы
                        </v-btn>
                    </v-layout>
                    <v-layout>
                        <v-btn style="border-radius: 0; line-height: normal; text-transform: none; height: 2.5vw; margin-left: 2%"
                               class="header_buttons pozvonit_btn" color="#FF6600" href="tel:+375256499702">
                            Позвонить
                        </v-btn>
                    </v-layout>
                    <v-divider dark></v-divider>
                    <template v-if="!profile">
                        <v-btn flat style="color: white; text-transform: none;"
                               @click="dialog = !dialog"
                        >
                            Войти
                        </v-btn>
                    </template>
                    <template v-else>
                        <div @click="drawer = !drawer">
                            <div style="color: white; font-size: 5.85vw">{{profile.firstName}}</div>
                            <v-container class="header_buttons" @click="$router.push('/cabinet/profile')">
                                Профиль
                            </v-container>
                            <v-container class="header_buttons" @click="$router.push('/cabinet/requests')">
                                Заявки
                            </v-container>
                            <v-divider dark></v-divider>
                            <a style="text-decoration: none" href="/logout">
                                <v-container class="header_buttons" href="/logout">
                                    Выйти
                                </v-container>
                            </a>
                        </div>
                    </template>
                </v-list>
            </v-expand-transition>
            <template v-if="$route.path === '/'">
                <img @load="imgTransition" src="../src/img/header_background.jpg" class="header_img">
                <img v-if="!$vuetify.breakpoint.smAndDown" class="logo_transition"
                     src="./img/index_logo_transition.png"/>
                <v-layout row wrap style="position: relative;">
                    <v-spacer v-if="$vuetify.breakpoint.smAndDown"></v-spacer>
                    <v-flex xs10 md6>
                        <v-layout row wrap class="icons_layout">
                            <v-flex xs12>
                                <p class="text_Roboto h_text_transition">
                                    Готовы узнать <br> о новых возможностях?
                                </p>
                            </v-flex>
                            <v-flex xs12>
                                <img class="icon_transition" src="../src/img/header_ico_1.png"/>
                                <p class="text_Roboto text_transition"
                                   style="color: white; font-weight: 300">
                                    Получайте работу по специальности </p>
                            </v-flex>

                            <v-flex xs12>
                                <img class="icon_transition" src="../src/img/header_ico_2.png"/>
                                <p class="text_Roboto text_transition">Уменьшайте налог для ИП и Бизнеса </p>
                            </v-flex>
                            <v-flex xs12>
                                <img class="icon_transition" src="../src/img/header_ico_3.png"/>
                                <p class="text_Roboto text_transition"
                                   style="color: white; font-weight: 300">
                                    Берите инвестиции в свой Стартап </p>
                            </v-flex>
                        </v-layout>
                    </v-flex>
                    <v-spacer v-if="$vuetify.breakpoint.smAndDown"></v-spacer>
                    <v-flex xs6 v-if="!$vuetify.breakpoint.smAndDown"><img class="woman_transition"
                                                                           src="../src/img/woman.png"/>
                    </v-flex>
                    <v-flex xs12 v-if="!$vuetify.breakpoint.smAndDown">
                        <img style="position: absolute;top: 20vw; width: 100%" src="../src/img/header_white_shadow.png">
                    </v-flex>
                </v-layout>
            </template>


            <v-content id="content">
                <v-dialog v-model="dialog" max-width="35vw">
                    <v-card class="dialog_login">
                        <v-icon class="close_dialog" @click="dialog = false">clear</v-icon>
                        <login></login>
                    </v-card>
                </v-dialog>
                <router-view :key="$route.path">
                </router-view>
            </v-content>

            <v-container class="header footer" v-if="$route.path !== '/parfume'">
                <v-container class="header tiny footer_container">
                    <v-layout row wrap>
                        <v-flex xs12 md3>
                            <v-layout row wrap>
                                <v-flex xs12>
                                    <img src="./img/bestwork_footer.png">
                                </v-flex>
                                <v-flex xs12>
                                    <p style="color: #BCC8E0">Беларусь, г. Минск<br> Партизанский пр-т 23-А,<br> пом.1Н,
                                        офис 28-А <br>Время
                                        работы: 10:00 до 19:00 пн-пт<br>
                                        Тел.: <a href="tel: +375256499702">+375 (25) 649-97-02</a></p>
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12 md3>
                            <v-layout row wrap>
                                <v-flex xs12 class="text_open_sans head_footer"> Студентам</v-flex>
                                <v-flex xs12>
                                    <v-icon>arrow_right_alt</v-icon>
                                    Анкета на распределение
                                </v-flex>
                                <v-flex xs12>
                                    <v-icon>arrow_right_alt</v-icon>
                                    Анкета на перераспределение
                                </v-flex>
                                <v-flex xs12>
                                    <v-icon>arrow_right_alt</v-icon>
                                    Анкета на практику
                                </v-flex>
                            </v-layout>
                        </v-flex>
                        <v-flex xs12 md3>
                            <v-flex xs12 class="text_open_sans head_footer">ИП и Бизнесменам</v-flex>
                            <v-flex xs12>
                                <v-icon>arrow_right_alt</v-icon>
                                Скачать документы для трудоустройства
                            </v-flex>
                            <v-flex xs12>
                                <v-icon>arrow_right_alt</v-icon>
                                Анкета на кредит
                            </v-flex>
                        </v-flex>
                        <v-flex xs12 md3>
                            <v-flex xs12 class="text_open_sans head_footer">Безработным</v-flex>
                            <v-flex xs12>
                                <v-icon>arrow_right_alt</v-icon>
                                Вакансии
                            </v-flex>
                            <v-flex xs12>
                                <v-icon>arrow_right_alt</v-icon>
                                Документы на тродоустройство
                            </v-flex>
                        </v-flex>
                    </v-layout>
                </v-container>
                <v-divider style="border-color: rgba(255,255,255,0.12);"></v-divider>
                <v-layout row style="color: white; text-align: center; padding: 1vw">
                    <v-flex xs12>
                        © 2020 BestWork.by - Продвижение и оптимизация бизнеса. Все права защищены.
                    </v-flex>
                </v-layout>
            </v-container>

        </v-container>
    </v-app>
</template>

<script>
    import Login from "./pages/Login.vue"
    import LazyLoader from "./components/LazyLoader.vue"
    import {mapGetters} from 'vuex'

    export default {
        name: "App",
        data() {
            return {
                dialog: false,
                drawer: false,
            }
        },
        components: {
            Login,
            LazyLoader
        },
        computed: {
            ...mapGetters(['profile'])
        },
        methods: {
            scrollTo(name) {
                let element = document.getElementById(name);
                element.scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
                if (this.$vuetify.breakpoint.smAndDown) {
                    this.drawer = false;
                }
            },
            womanTransition() {
                document.getElementsByClassName("woman_transition")[0].classList.add("transition_woman");
                document.getElementsByClassName("logo_transition")[0].classList.add("transition_logo");
            },
            textTransition() {
                document.getElementsByClassName("h_text_transition")[0].style.left = 0;
                for (var i = 0; i < document.getElementsByClassName("text_transition").length; i++) {
                    document.getElementsByClassName("text_transition")[i].style.left = 0
                }
                for (var j = 0; j < document.getElementsByClassName("icon_transition").length; j++) {
                    document.getElementsByClassName("icon_transition")[j].style.left = 0
                }
            },
            imgTransition() {
                document.getElementsByClassName("header_img")[0].style.opacity = 1;
                this.womanTransition();
                this.textTransition();
            }
        }
    }
</script>

<style>

    .close_dialog {
        position: absolute;
        right: 0.6vw;
        top: 0.6vw;
        color: #1d4696 !important;
    }

    .header_buttons {
        padding: 0 20px;
        flex: inherit;
        width: auto;
        font-weight: bold;
        color: #F2F2F2 !important;
        cursor: pointer;
        font-family: Roboto, Arial, Tahoma, sans-serif;
        -webkit-transition: color 0.3s;
        transition: color 0.3s;
        text-transform: none;
    }

    @media screen and (min-width: 960px) {
        .close_dialog {
            font-size: 1.5vw;
        }


        .header_contacts a {
            float: left;
            color: #00bae1;
        }

        .header_button_link {
            padding: 15px 0;
        }

        .header {
            max-width: 100% !important;
        }

        .header_img {
            width: 100%;
            height: 37.4vw;
            position: absolute;
            top: 0;
            z-index: 0;
            opacity: 0;
            -webkit-transition: all 0.5s;
            -moz-transition: all 0.5s;
            -ms-transition: all 0.5s;
            -o-transition: all 0.5s;
            transition: all 0.5s;
            transition-delay: 0.5s;
        }

        .header_buttons:hover {
            color: #01B7E1 !important;
        }

        .header_icons {
            font-size: 16px;
            filter: invert(0.8);
            vertical-align: bottom;
        }

        .v-toolbar__content {
            padding: 0 !important;
        }

        .header_links {
            color: #00bae1;
            text-decoration: none;
            font-family: Roboto, Arial, Tahoma, sans-serif;
        }

        .icons_layout {
            position: relative;
            top: 17%;
            left: 37%;
        }

        .woman_transition {
            width: 26.9vw;
            position: relative;
            right: -60rem;
            -webkit-transition: right 1s;
            transition: right 1s;
            transition-delay: 0.5s;
        }

        .transition_woman {
            right: 4vw !important;
        }

        .sticky {
            position: fixed;
            top: 0;
            width: 100%;
            z-index: 2;
            height: 66px;
            background-color: rgba(0, 0, 0, 0.8) !important;
            -webkit-transition: all 0.7s;
            -moz-transition: all 0.7s;
            -ms-transition: all 0.7s;
            -o-transition: all 0.7s;
            transition: all 0.7s;
        }

        #sticky_header {
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            -ms-transition: all 0.3s;
            -o-transition: all 0.3s;
            transition: all 0.3s;
        }

        .text_transition {
            font-size: 1.1vw !important;
            color: white;
            font-weight: 300;
            position: relative;
            left: -90%;
            -webkit-transition: all 1s;
            -moz-transition: all 1s;
            -ms-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s;
            transition-delay: 0.5s;
        }

        .icon_transition {
            width: 1.5vw;
            float: left;
            margin-right: 15px;
            position: relative;
            left: -90%;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            -ms-transition: all 0.3s;
            -o-transition: all 0.3s;
            transition: all 0.3s;
            transition-delay: 1.3s;
        }

        .h_text_transition {
            font-size: 2.2vw;
            color: white;
            position: relative;
            left: -90%;
            -webkit-transition: all 0.3s;
            -moz-transition: all 0.3s;
            -ms-transition: all 0.3s;
            -o-transition: all 0.3s;
            transition: all 0.3s;
            transition-delay: 1s;
        }

        .logo_transition {
            width: 13.3vw;
            top: 6vh;
            position: absolute;
            right: 44vw;
            opacity: 0;
            -webkit-transition: all 1s;
            -moz-transition: all 1s;
            -ms-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s;
            transition-delay: 1.5s;
        }

        .transition_logo {
            opacity: 1 !important;
            top: 125px !important;
        }


        .footer {
            background: rgb(29, 70, 150);
            background: -moz-linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            background: -webkit-linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            background: linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#1d4696", endColorstr="#1199b9", GradientType=1);
        }

        .tiny {
            max-width: 1102px !important;
            padding: 0;
        }

        .footer_container {
            font-size: 0.8vw;
            color: white;
            padding-top: 5vw !important;
            padding-bottom: 5vw !important;
        }

        .head_footer {
            font-size: 1.1vw;
            line-height: 2;
        }


    }

    @media screen and (max-width: 960px) {
        .close_dialog {
            font-size: 7.5vw;
        }

        .v-dialog {
            max-width: unset !important;
        }

        #sticky_header {
            background-color: rgba(0, 0, 0, 0.8) !important;
            position: fixed;
        }

        .div_nav {
            height: 56px !important;
        }

        .pozvonit_btn {
            height: 8.75vw !important;
            color: white !important;
        }

        .header_buttons {
            font-size: 5vw;
            line-height: 7vh;
        }

        .footer {
            padding-top: 3vh;
            background: rgb(29, 70, 150);
            background: -moz-linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            background: -webkit-linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            background: linear-gradient(28deg, rgba(29, 70, 150, 1) 53%, rgba(17, 153, 185, 1) 100%);
            filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#1d4696", endColorstr="#1199b9", GradientType=1);
        }

        .footer_container {
            font-size: 0.8vw;
            color: white;
            margin-bottom: 4vh;
            padding-top: 4vh !important;
        }

        .head_footer {
            font-size: 4.1vw;
            padding-top: 3vh;
        }

        .header_img {
            height: 245vw;
            max-height: 135vh;
        }

        .icons_layout {
            padding: 4vh 0;
        }

        .header {
            background-color: transparent !important;
        }

        div {
            font-size: 3.4vw;
        }

        .text_transition {
            color: white;
            font-weight: 300;
            font-size: 4.2vw;
        }

        .icon_transition {
            float: left;
            margin-right: 3vw;
            width: 7.5vw;
        }

        .h_text_transition {
            color: white;
            font-size: 7vw;
        }

        .logo-main {
            max-height: 11vw !important;
        }
    }

    .container {
        padding: 0 1%;
    }

    .text_Roboto {
        font-family: Roboto, Arial, Tahoma, sans-serif;
    }

    html {
        font-family: Roboto, Arial, Tahoma, sans-serif;
    }

    .text_open_sans {
        font-family: 'Open Sans', sans-serif;
    }

    .fixed_header {
        position: fixed;
        z-index: 20;
    }

    .header_img {
        position: absolute;
        top: 0;
        z-index: 0;
        opacity: 0;
        -webkit-transition: all 0.5s;
        -moz-transition: all 0.5s;
        -ms-transition: all 0.5s;
        -o-transition: all 0.5s;
        transition: all 0.5s;
        transition-delay: 0.5s;
    }


    #expand_menu {
        position: fixed;
        width: 100%;
        text-align: center;
        background: rgba(0, 0, 0, 0.8);
        z-index: 50;
    }

    .header_buttons.theme--dark.v-btn {
        color: unset;
    }

    .header_buttons.v-btn:before {
        opacity: 0 !important;
    }

    .header_buttons .v-btn__content {
        transition: unset !important;
    }
</style>