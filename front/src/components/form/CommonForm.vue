<template>
    <div>
        <v-text-field v-model="person.firstName"
                      :rules="nameRules"
                      label="Имя"
                      required>
        </v-text-field>
        <v-text-field v-model="person.lastName"
                      :rules="nameRules"
                      label="Фамилия"
                      required>
        </v-text-field>
        <v-text-field v-model="person.otchestvo"
                      :rules="nameRules"
                      label="Отчество"
                      required>
        </v-text-field>
        <v-text-field v-model="person.tel"
                      :rules="telRules"
                      :mask="telMask"
                      label="Телефон"
                      required>
        </v-text-field>
        <v-text-field v-model="person.email"
                      :rules="emailRules"
                      label="Адрес электронной почты"
                      required>
        </v-text-field>
    </div>
</template>

<script>
    export default {
        props: ["person"],

        name: "CommonForm",
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
            }
        },
        created() {
            this.telMask = '+375 (##) ###-##-##'
        }
    }
</script>

<style scoped>

</style>