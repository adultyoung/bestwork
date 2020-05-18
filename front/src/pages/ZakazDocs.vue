<template>
    <v-container>
        <div v-for="(doc, index) in docs">
            <v-btn class="text-xs-center doc"
                   @click="selected.includes(doc) ? removeFromBasket(index, doc) : addToBasket(index, doc)"
                   flat
                   dark
                   block>
                {{doc}}
            </v-btn>
            <v-divider dark v-if="index !== docs.length-1"></v-divider>
        </div>
        <v-layout row>
            <v-spacer></v-spacer>
            <v-flex md5>
                <v-btn block @click="produceDocs()">
                    Далее
                </v-btn>
            </v-flex>
            <v-spacer></v-spacer>
        </v-layout>
    </v-container>
</template>

<script>
    export default {
        name: "ZakazDocs",
        data() {
            return {
                docs: [
                    "Заказать справку с места работы",
                    "Заказать справку о месте и продолжительности работы",
                    "Заказать справку о заработной плате",
                    "Заказать характеристику работника",
                    "Уволиться"
                ],
                selected: [],
            }
        },
        methods: {
            addToBasket(index, doc) {
                document.getElementsByClassName("doc")[index].classList.add("selectedDoc");
                this.selected.push(doc);
            },
            removeFromBasket(index, doc) {
                document.getElementsByClassName("doc")[index].classList.remove("selectedDoc");
                this.selected.splice(this.selected.indexOf(doc), 1);
            },
            produceDocs() {
                this.$router.push({path: "/docs-request", query: {selected: this.selected}});

            }
        }
    }
</script>

<style scoped>
    .doc {

    }

    .selectedDoc {
        background-color: white;
    }
</style>