<template>
    <v-layout row wrap>
        <template v-for="(check, index) in checks">
            <v-flex xs12>
                <v-layout row :class="check.isToDelete ? 'cancelled' : ''">
                    <v-spacer></v-spacer>
                    <v-flex md2>
                        {{check.date}}
                    </v-flex>
                    <v-flex md2>
                        {{check.sum}}
                    </v-flex>
                    <v-flex md2>
                        <v-icon v-if="check.uploaded">check_circle_outline</v-icon>
                        <v-icon v-else>panorama_fish_eye</v-icon>
                    </v-flex>
                    <v-flex md2>
                        <v-icon v-if="check.verified">check_circle_outline</v-icon>
                        <v-icon v-else>panorama_fish_eye</v-icon>
                    </v-flex>
                    <v-flex md2>
                        <v-btn style="text-transform: none" flat @click="watchPhoto()">Посмотреть</v-btn>
                    </v-flex>
                    <v-spacer></v-spacer>
                </v-layout>
            </v-flex>
            <v-divider v-if="index !== checks.length-1"></v-divider>
        </template>
    </v-layout>
</template>

<script>
    import {mapState} from 'vuex'

    export default {
        name: "Platezhi",
        data() {
            return {}
        },
        computed: {
            ...mapState(["checks"])
        },
        methods: {
            watchPhoto() {

            }
        }

    }
</script>

<style scoped>
    .cancelled {
        text-decoration: line-through;
    }
</style>