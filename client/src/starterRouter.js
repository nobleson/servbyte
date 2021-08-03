import Vue from "vue";
import Router from "vue-router";
import Header from "./layout/starter/StarterHeader";
import Footer from "./layout/starter/StarterFooter";
import Starter from "./views/Starter.vue";
import Food from "./views/Food.vue";
import Restaurant from "./views/Restaurant.vue";
import Order from "./views/Order.vue";
import Payment from "./views/Payment.vue";

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [
    {
      path: "/",
      name: "starter",
      components: {
        header: Header,
        default: Starter,
        footer: Footer
      }
    },
    {
      path: "/foods",
      name: "foods",
      components: {
        header: Header,
        default: Food,
        footer: Footer
      }
    },
    {
      path: "/restaurant/:id",
      name: "restaurant",
      components: {
        header: Header,
        default: Restaurant,
        footer: Footer
      }
    },
    {
      path: "/order/:id",
      name: "order",
      components: {
        header: Header,
        default: Order,
        footer: Footer
      }
    },
    {
      path: "/payment/:id",
      name: "payment",
      components: {
        header: Header,
        default: Payment,
        footer: Footer
      }
    },
  ]
});
