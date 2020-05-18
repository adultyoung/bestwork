<template>
    <v-container>
        <h4> Номер вашей заявки - {{contractRequest.id}}</h4>
        Ваша заявка отправлена. Наш менеджер в скором времени с вами свяжется для уточнения деталей.
        На вашу электронную почту отправлены данные для входа в личный кабинет, где вы сможете просматривать,
        корректировать ваши заявки.
    </v-container>
</template>

<script>
    import {mapGetters} from 'vuex';

    export default {
        name: "SuccessSend",
        computed: {
            ...mapGetters(['contractRequest'])
        }
    }
</script>

<style scoped>

</style>