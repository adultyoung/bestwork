<template>
    <v-layout row>
        <v-text-field
                v-model="person.name"
                :rules="nameRules"
                label="ФИО">
        </v-text-field>
        <v-spacer></v-spacer>
        <v-text-field
                v-model="person.tel"
                :rules="telRules"
                label="Телефон">
        </v-text-field>
        <v-spacer></v-spacer>
        <v-text-field outline
                      v-model="person.comment"
                      :rules="commRules"
                      label="Комментарий">
        </v-text-field>
        <v-spacer></v-spacer>
    </v-layout>
</template>

<script>
    import {mask} from 'vue-the-mask'

    export default {
        props: ["person"],
        directives: {
            mask,
        },
        name: "PersonInfo",
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
                commRules: [
                    v => v.length <= 500 || 'Поле содержит более 500 символов'
                ],
            }
        }
    }
</script>

<style scoped>

</style>