<template>
    <v-card-text class="card_text_login">
        <v-container grid-list-md>
            <v-layout wrap>
                <v-flex xs12 class="login_title">Вход в личный кабинет</v-flex>
                <v-flex xs12 class="login_desc">
                    <v-form action="/login" method="post" v-model="valid" id="form" lazy-validation>
                        <v-text-field prepend-inner-icon="mail_outline"
                                      name="username"
                                      :rules="emailRules"
                                      label="Email"
                                      required
                        ></v-text-field>
                        <v-flex xs12>
                            <v-text-field type="password"
                                          name="password"
                                          prepend-inner-icon="lock"
                                          :rules="passwordRules"
                                          label="Пароль"
                                          required
                            ></v-text-field>
                        </v-flex>
                    </v-form>
                </v-flex>
            </v-layout>
        </v-container>
        <v-layout>
            <v-spacer></v-spacer>
            <v-btn class="btn_login"
                   type="submit"
                   form="form"
                   :disabled="!valid"
            >
                Войти
            </v-btn>
            <v-spacer></v-spacer>
        </v-layout>
    </v-card-text>
</template>

<script>

    export default {
        name: "Login",
        data: () => ({
            valid: true,
            passwordRules: [
                v => !!v || 'Пароль обязателен',
                v => (v && v.length <= 50) || 'Пароль должен быть менее 50 знаков'
            ],
            emailRules: [
                v => !!v || 'E-mail обязателен',
                v => /.+@.+/.test(v) || 'Введите валидный E-mail',
            ],
            dialogi: true,
        }),
        methods: {
            clear() {
                this.$refs.form.reset()
            }
        }

    }
</script>

<style scoped>
    @media screen and (min-width: 960px) {
        .login_title {
            font-size: 1.3vw;
        }

        .card_text_login {
            padding: 3.3vw 6vw !important;
        }
    }

    @media screen and (max-width: 960px) {
        .login_title {
            font-size: 4.3vw;
        }

        .card_text_login {
            padding: 13.3vw 6vw !important;
        }
    }

    .login_title {
        text-align: center;
        font-weight: 500;
        color: #1d4696;
        padding-bottom: 2.5vw !important;
    }

    .login_desc {
        margin-bottom: 1vw;
    }

    .btn_login {
        width: 14vw;
        border-radius: 0;
        color: white;
        background-color: #1d4696 !important;
        text-transform: none;
        font-size: 1vw;
        font-weight: 300;
    }

</style>