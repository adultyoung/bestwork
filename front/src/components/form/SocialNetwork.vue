<template>
    <v-layout>
        <v-flex xs3>
            <v-select
                    :items="socials"
                    v-model="socialNetwork.typeOfSocial"
                    label="Соцсеть"
                    outline
            ></v-select>
        </v-flex>
        <v-spacer></v-spacer>
        <v-flex xs7>
            <v-text-field v-model="socialNetwork.url"
                          maxlength="1000"
                          label="Введите ссылку на свой профиль">
            </v-text-field>
        </v-flex>
        <v-spacer></v-spacer>
    </v-layout>
</template>

<script>
    export default {
        props: ["socialNetwork"],
        name: "SocialNetwork",
        data() {
            return {
                socials: [
                    'Instagramm',
                    'Vk',
                    'Facebook',
                    'Одноклассники',
                ]
            }
        },
        // mounted() {
        //     this.socialNetwork.type = "Vk";
        // }
    }
</script>

<style scoped>

</style>