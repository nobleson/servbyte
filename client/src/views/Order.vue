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
                        <h2 class="display-3 text-white">Order Form</h2>
                        <p class="lead text-white">Complete your order form and proceed to checkout</p>
                         <router-link :to="{ name: 'restaurant', params: { id: restaurantDetails.id }}"  title="Restaurant page">
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
                            <p class="mt-0">All fields are reequired</p>
                            <base-input class="mt-5"
                                        alternative
                                        placeholder="First name"
                                        label="First name"
                                        v-model="form.firstName"
                                        >
                            </base-input>
                             <base-input class="mt-5"
                                        alternative
                                        placeholder="Last name"
                                        label="Last name"
                                        v-model="form.lastName"
                                        >
                            </base-input>
                             <base-input class="mt-5"
                                        alternative
                                        placeholder="Phone"
                                        label="Phone Number"
                                        v-model="form.phoneNumber"
                                        >
                            </base-input>
                            <base-input alternative
                                        placeholder="Email address"
                                        label="Email Address"
                                        v-model="form.emailAddress"
                                        >
                            </base-input>
                            <base-input class="mb-4" label="Contact Address">
                                    <textarea class="form-control form-control-alternative" name="name" rows="4"
                                              cols="80" placeholder="Contact address" v-model="form.contactAddress"></textarea>
                            </base-input>
                             <base-input alternative
                                        placeholder="City"
                                        label="City"
                                        v-model="form.city"
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
                                        v-model="restaurantDetails.name"
                                        >
                            </base-input>
                            <div>
                            <b-button variant="primary" @click="placeOrder()">Proceed to checkout</b-button>
                            </div>
                        </card>
                    </div>
                </div>
            </div>
        </section>
        </div>
    </div>
</template>

<script>
import api from '../plugins/CustomerAPI';
import orderAPI from '../plugins/OrderAPI';
import {mapGetters, mapActions,mapState,mapMutations } from 'vuex'

export default {
  name: "order",
  components: {},
      data() {
        return {
            restaurant: {},
            form: {
                firstName:'',
                lastName: '',
                emailAddress: '',
                phoneNumber: '',
                contactAddress: '',
                city: ''
            },
            order: {
                description: '',
                status: null,
                customer: '',
                orderDate: null,
                serviceProvider: '',
                totalAmount: Number
            }
        }
    },
    mounted(){
        //this.loadServiceProvider(this.serviceProvider.id)
    },
    computed: {
     // ...mapState(['serviceProvider']),
     //...mapGetters({assesors: 'assesors/getAssesors', isContentLoading: 'assesors/getLoaderStatus'}),
        ...mapGetters({serviceProvider:'getServiceProvider', city:'getCity',meal:'getMeal'}),
        restaurantDetails(){return this.serviceProvider},
        location(){return this.city},
        mealDetails(){return this.meal}
     },
    methods: {
           ...mapMutations(['saveCustomer','saveOrder']),
     async placeOrder() { 
            if(!this.form.firstName) {
                this.$bvModal.msgBoxOk('First name required.')
                return false;
            }
            else if(!this.form.lastName) {
                this.$bvModal.msgBoxOk('Last name required.')
                return false;
            }
            else if(!this.form.phoneNumber) {
                this.$bvModal.msgBoxOk('Phone number required.')
                return false;
            }
            else if(!this.form.emailAddress) {
                this.$bvModal.msgBoxOk('Email address required.')
                return false;
            }
            else if(!this.form.contactAddress) {
                this.$bvModal.msgBoxOk('Contact address required.')
                return false;
            }
            else if(!this.form.city) {
                this.$bvModal.msgBoxOk('City is required.')
                return false;
            }
            const customer = await api.createNew('/customers',this.form)
            this.saveCustomer(customer.data)
            this.order.customer = customer.data.id
            this.order.description = this.mealDetails.name
            this.order.orderDate = new Date().toISOString()
            this.order.serviceProvider = this.restaurantDetails.id
            this.order.totalAmount = this.mealDetails.price
            const customerOrder= await orderAPI.createNew('/orders',this.order)
            this.saveOrder(customerOrder.data)

           // console.log("New Customer", customer.data)
           // console.log("New Order", customerOrder.data)
           this.$router.push({ name: 'payment', params: { id: customerOrder.data.id } })
      }

    },

};
</script>
