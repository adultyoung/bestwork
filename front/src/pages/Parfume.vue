<template>
    <v-container
            style="max-width: 100%; padding: 0; background-image: url('../img/parfume_background.png')">
        <div style="padding-top: 14vh">
            <v-layout class="block_header_1">
                <v-spacer></v-spacer>
                <img class="left_peon" src="../img/parfume_header_left_pion.png">
                <img class="right_peon" src="../img/parfume_header_right_pion.png">
                <v-spacer></v-spacer>
            </v-layout>
            <v-layout class="block_header_2">
                <v-spacer></v-spacer>
                <img class="center_img" src="../img/parfume_header_pikcha.png">
                <v-spacer></v-spacer>
            </v-layout>
        </div>
        <v-container
                style="background-color: white; z-index: 1; position: relative; top: -9vh; max-width: 100%; padding: 0; padding-top: 8vh">
            <v-layout>
                <v-spacer></v-spacer>
                <v-flex xs1>
                    <v-btn flat>
                        Для женщин
                    </v-btn>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs1>
                    <v-btn flat>
                        Для мужчин
                    </v-btn>
                </v-flex>
                <v-spacer></v-spacer>
                <v-flex xs1>
                    <v-text-field
                            v-model="search"
                            append-icon="search"
                            label="Поиск"
                            single-line
                            hide-details
                    ></v-text-field>
                </v-flex>
                <v-spacer></v-spacer>
            </v-layout>
            <parfume-row style="padding-top: 8vh; padding-bottom: 10vh" :cosmetics="cosmetics"
                         :search="search"></parfume-row>
        </v-container>
        <v-layout row wrap style="min-height: 200px; background-color: grey">

        </v-layout>
    </v-container>
</template>

<script>
    import axios from 'axios'
    import ParfumeRow from '../components/Parfume/ParfumeRow.vue'

    export default {
        name: "Parfume",
        components: {
            ParfumeRow
        },
        mounted() {

            axios.get('/parfume-api').then(response => {
                this.cosmetics = response.data;
            });
        },
        data() {
            return {
                search: '',
                cosmetics: [],
            }
        }
    }
</script>

<style scoped>
    .left_peon {
        width: 13vw;
        object-fit: contain;
        transform: rotate(-27deg);
        margin-left: 32vw;

    }

    .right_peon {
        width: 15vw;
        object-fit: contain;
        margin-left: 8vw;
        transform: rotate(20deg);
    }

    .center_img {
        width: 26vw;
        margin-top: 2vw;
    }

    .block_header_1 {
        position: absolute;
        z-index: 0;
    }

    .block_header_2 {
        z-index: 2;
        position: relative;
    }

    @font-face {
        font-family: "Century Gothic";
        src: url("./fonts/GOTHIC_745063ed9142edcdcdfaeed69c36e6d2.ttf") format("truetype")
    }

    div {
        font-family: "Century Gothic" !important;
        color: #93939b;
    }

    button {
        text-transform: none !important;
    }
</style>