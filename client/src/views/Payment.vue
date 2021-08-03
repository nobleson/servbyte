<template>
    <div>

        <div class="position-relative">
            <!-- shape Hero -->
        <section class="section section-shaped my-0 overflow-hidden">
            <div class="shape shape-style-3 bg-gradient-default shape-skew">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div class="container pt-lg pb-300">
                <div class="row text-center justify-content-center">
                    <div class="col-lg-10">
                        <h2 class="display-3 text-white">Payment Form</h2>
                        <p class="lead text-white">Review your order and checkout</p>
                         <router-link :to="{ name: 'restaurant', params: { id: restaurant.id }}"  title="Restaurant page">
                            <b-button pill variant="primary">                                
                                <icon name="fa fa-long-arrow-left"></icon>Back to product</b-button>
                         </router-link>
                    </div>
                </div>
            </div>
        </section>

        <section class="section section-lg pt-lg-0 section-contact-us">
            <div class="container">
                <div class="row justify-content-center mt--300">
                    <div class="col-lg-8">
                        <card gradient="secondary" shadow body-classes="p-lg-5">
                            <h4 class="mb-1">Personal Details</h4>
                            <base-input class="mt-5"
                                        alternative
                                        placeholder="First name"
                                        label="First name"
                                        v-model="customer.firstName"
                                        disabled
                                        >
                            </base-input>
                             <base-input class="mt-5"
                                        alternative
                                        placeholder="Last name"
                                        label="Last name"
                                        v-model="customer.lastName"
                                        disabled
                                        >
                            </base-input>
                             <base-input class="mt-5"
                                        alternative
                                        placeholder="Phone"
                                        label="Phone Number"
                                        v-model="customer.phoneNumber"
                                        disabled
                                        >
                            </base-input>
                            <base-input alternative
                                        placeholder="Email address"
                                        label="Email Address"
                                        v-model="customer.emailAddress"
                                        disabled
                                        >
                            </base-input>
                            <base-input class="mb-4" label="Contact Address">
                                    <textarea class="form-control form-control-alternative" name="name" rows="4"
                                              cols="80" placeholder="Contact address" v-model="customer.contactAddress" disabled></textarea>
                            </base-input>
                             <base-input alternative
                                        placeholder="City"
                                        label="City"
                                        v-model="customer.city"
                                        disabled
                                        >
                            </base-input>
                            <hr>
                            <h4 class="mb-1">Your Order Details</h4>
                            <base-input class="mt-5"
                                        label="Item"
                                        alternative
                                        placeholder="Item"
                                        disabled
                                        v-model="mealDetails.name">
                            </base-input>
                            <base-input class="mt-5"
                                        label="Description"
                                        disabled
                                        >
                                        <textarea class="form-control form-control-alternative" name="name" rows="4"
                                        cols="80" placeholder="Description" disabled
                                        v-model="mealDetails.description"
                                        ></textarea>
                            </base-input>
                             <base-input alternative
                                        label="Amount"
                                        placeholder="Amount"
                                        disabled
                                        v-model="mealDetails.price"
                                        >
                            </base-input>
                            <base-input alternative
                                        label="Location"
                                        placeholder="Location"
                                        disabled
                                        v-model="location"
                                        >
                            </base-input>
                             <base-input alternative
                                        label="Service Provider"
                                        placeholder="Service Provider"
                                        disabled
                                        v-model="restaurant.name"
                                        >
                            </base-input>
                            <div>
                               <b-button variant="warning" @click="cancelOrder()">Cancel</b-button>
                               <b-button variant="primary">
                                <paystack
                                    :amount="order.totalAmount * 100"
                                    :email="customer.emailAddress"
                                    :paystackkey="PUBLIC_KEY"
                                    :reference="reference"
                                    :callback="processPayment"
                                    :close="close"
                                >
                                    Make Payment
                                </paystack>
                               </b-button>
                            </div>
                        </card>
                    </div>
                </div>
            </div>
        </section>
        </div>
    </div>
</template>

<script src="https://js.paystack.co/v1/inline.js"></script> 

<script>
import paymentAPI from '../plugins/PaymentAPI';
import orderAPI from '../plugins/OrderAPI';
import {mapGetters, mapActions,mapState,mapMutations } from 'vuex'
import paystack from 'vue-paystack';

export default {
  name: "payment",
  components: {
      paystack
  },
      data() {
        return {
            PUBLIC_KEY: 'pk_test_d45ed41c4d493a9897802b3cc4d2aac42921135d',
            payment: {
                order: '',
                amount:'',
                payer:'',
                payment:'',
                transactionId: '',
                transactionStatus:'',
                serviceProvider: ''
            },
        }
    },
    mounted(){
    },
    computed: {
        ...mapGetters({serviceProvider:'getServiceProvider', city:'getCity',meal:'getMeal',orderDetails:'getOrder',customerDetails:'getCustomer'}),
        restaurant(){return this.serviceProvider},
        location(){return this.city},
        mealDetails(){return this.meal},
        customer(){return this.customerDetails},
        order(){return this.orderDetails},
        reference() {
            let text = "";
            let possible =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            for (let i = 0; i < 10; i++)
                text += possible.charAt(Math.floor(Math.random() * possible.length));
            return text;
        }
     },
    methods: {
          // ...mapMutations(['saveCustomer','saveOrder']),
    processPayment: () => {
        let message = 'Payment recieved! Order will be delivered soon: ' + response.reference;
        this.payment.order = this.order.id
        this.payment.amount = this.order.totalAmount
        this.payment.payer = this.order.customer
        this.payment.paymentDate = new Date().toISOString()
        this.payment.transactionId = this.reference
        this.payment.transactionStatus = 'SUCCESS'
        this.payment.serviceProvider = this.order.serviceProvider

        paymentAPI.createNew('/payments',this.payment)
        orderAPI.update(`/orders/${this.order.id}/complete`)
        this.$bvModal.msgBoxOk(message)
    },
    close: () => {
        this.$bvModal.msgBoxOk("You closed the checkout page, order will not be processed")
    },
    async cancelOrder(){
        let self = this
        this.$bvModal.msgBoxOk("You order will be canceled")
        await orderAPI.delete(`/orders/${self.order.id}/cancel`)
        setTimeout(function(){
         self.$router.push({ name: 'restaurant', params: { id: self.order.serviceProvider } })
        }, 2000)
    }

    },
};
</script>
