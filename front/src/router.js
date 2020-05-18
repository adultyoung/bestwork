import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from './pages/Index.vue'
import ContractRequest from './pages/ContractRequest.vue'
import Cabinet from './pages/Cabinet.vue'
import Requests from "./pages/Zayavki.vue";
import Profile from "./pages/Profile.vue";
import RequestEdit from "./components/Cabinet/RequestEdit.vue";
import Mail from "./pages/Mail.vue";
import DocsRequest from "./components/form/DocsRequest.vue";
import ZakazDocs from "./pages/ZakazDocs.vue";
import Platezhi from "./components/Cabinet/Platezhi.vue";

Vue.use(VueRouter);

const routes = [
    {path: "/", component: Index},
    {path: "/contract-request", component: ContractRequest},
    {
        path: "/cabinet", component: Cabinet,
        children: [
            {path: "requests", component: Requests},
            {path: "profile", component: Profile},
            {path: "services", component: ZakazDocs},
            {path: "payments", component: Platezhi}
        ]
    },
    {path: "/docs-request*", component: DocsRequest, props: (route) => ({selected: route.query.selected})},
    {path: "/request-edit*", component: RequestEdit},
    {path: "/parfume", component: Mail},
];

const router = new VueRouter({
    mode: "history",
    routes
});

export default router;