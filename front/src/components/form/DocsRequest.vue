<template>
    <v-layout v-if="checkZakaz()">
        <v-flex xs12>Для справки о месте и продолжительности работы необходимо заполнить следующие данные:
        </v-flex>
        <v-flex xs12>
            <v-text-field v-model="adresat" label="Адресат"></v-text-field>
        </v-flex>
        <v-spacer></v-spacer>
        <v-flex xs4>
            <v-btn dark @click="saveZakaz">Продолжить</v-btn>
        </v-flex>
    </v-layout>
</template>

<script>
    import axios from 'axios'

    export default {
        props: ["selected"],
        name: "DocsRequest",
        data() {
            return {
                adresat: null,
            }
        },
        methods: {
            async checkZakaz() {
                if (this.selected.includes("Заказать справку о месте и продолжительности работы")) {
                    return true;
                } else {
                    let resp;
                    resp = await axios.get("/save-zakaz-request?docs=" + this.selected);
                    this.$store.state.docsRequests.push(resp.data);
                    this.$router.push("/cabinet/requests");
                    return false;
                }

            },
            async saveZakaz() {
                if (this.adresat !== null) {
                    this.selected.concat(this.adresat);
                    let resp;

                    await axios.get("/save-zakaz-request?docs=" + JSON.stringify(this.selected));
                    this.$store.state.docsRequests.concat(resp.data());
                    this.$router.push("/cabinet/requests");
                }
            }
        }
    }
</script>

<style scoped>

</style>