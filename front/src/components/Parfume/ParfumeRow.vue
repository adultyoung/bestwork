<template>
    <v-layout>
        <v-spacer></v-spacer>
        <v-flex xs6>
            <v-data-table
                    :headers="headers"
                    :items="cosmetics"
                    :search="search"
            >
                <template slot="items" slot-scope="props">
                    <td>{{ props.item.code }}</td>
                    <td>{{ props.item.name }}</td>
                    <td>{{ props.item.price }}</td>
                </template>
            </v-data-table>
        </v-flex>
        <v-spacer></v-spacer>
    </v-layout>
</template>

<script>
    export default {
        props: ['cosmetics', 'search'],
        name: "ParfumeRow",
        data: () => ({
            headers: [
                {
                    text: 'Код',
                    align: 'left',
                    value: 'code'
                },
                {text: 'Название', value: 'name'},
                {text: 'Цена', value: 'price'},
            ],
        }),
    }
</script>

<style scoped>
</style>