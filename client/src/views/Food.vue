<template>
    <div>

        <div class="position-relative">
            <!-- shape Hero -->
            <section class="section-shaped my-0">
                <div class="shape shape-style-1 shape-default shape-skew">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
                <div class="container shape-container d-flex" style="top:0">
                    <div class="col px-0">
                        <div class="row">
                            <div class="col-lg-6">
                                <h1 class="display-3  text-white">Shop food from high standard restaurants
                                    <span>trusted with quality</span>
                                </h1>
                                <p class="lead  text-white">Search for restaurant by city</p>
                                <div class="btn-wrapper">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div>
                                            <Select2 v-model="myValue" :options="myOptions"  @change="myChangeEvent($event)" />
                                        </div>
                                    </div>
                                </div>
                                    <base-button tag="a"
                                                 href="#"
                                                 class="mb-3 mb-sm-0"
                                                 type="info"
                                                 icon="fa fa-search">
                                        Search
                                    </base-button>
                                </div>
                            </div>
                             <div class="col-md-6 order-lg-2 ml-lg-auto">
                                <div class="position-relative pl-md-5">
                                    <img src="img/ill/ill-2.svg" class="img-center img-fluid">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- 1st Hero Variation -->
        </div>
        <section class="section section-lg pt-lg-0 mt--200" >
            <div class="container" ref="shoping">
                 <div v-if="isCitySelected & isRestaurantAvailable == false" class="row justify-content-center">
                    <card class="border-0" hover shadow body-classes="py-5">
                            <icon name="fa  fa-search-minus" type="primary" rounded class="mb-4">
                            </icon>
                            <h6 class="text-primary text-uppercase">No result found: {{myValue}}</h6>
                            <p class="description mt-3">Oops! we can't find restaurant from this city.
                            </p>
                    </card>
                 </div>
                <div v-else-if="isCitySelected & isRestaurantAvailable" class="row justify-content-center">
                    <div class="col-lg-12">
                        <div class="row row-grid">
                            <div class="col-lg-4" v-for="restaurant in restaurantsList" :restaurant="restaurant" :key="restaurant.id">
                                <card class="border-0" hover shadow body-classes="py-5">
                                    <icon name="ni ni-check-bold" type="primary" rounded class="mb-4">
                                    </icon>
                                    <h6 class="text-primary text-uppercase">{{restaurant.name}}</h6>
                                    <p class="description mt-3">Food service</p>
                                    <div>
                                        <badge type="primary" rounded>{{restaurant.phoneNumber}}</badge>
                                        <badge type="primary" rounded>{{restaurant.email}}</badge>
                                    </div>
                                    <b-button type="primary" class="mt-4" :to="{ name: 'restaurant', params: { id: restaurant.id }}" title="Restaurant page" @click="storeData(restaurant)">
                                        Visit Restaurant
                                    </b-button>

                                </card>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="container" style="height: 200px; overflow-y: scroll">
    </div>
    </div>
</template>

<script>
import api from '../plugins/ServiceProviderAPI';
import cityAPI from '../plugins/CityAPI';
import {mapGetters, mapActions,mapState,mapMutations } from 'vuex'

export default {
  name: "home",
    components: {},
    data() {
        return {
            restaurantsList: [],
            isRestaurantAvailable: true,
            isCitySelected: false,
            myValue: '',
            myOptions: [],
        }
    },
    mounted(){
        this.loadCities()
        this.loadServiceProviders()
    },
    computed: {
     // ...mapState(['serviceProvider']),
     //...mapGetters({assesors: 'assesors/getAssesors', isContentLoading: 'assesors/getLoaderStatus'}),
        //...mapGetters({serviceProvider:'getServiceProvider'}),
        //restaurant(){return this.serviceProvider}
    },
    methods: {
        ...mapMutations(['saveServiceProvider','saveCity']),

        loadServiceProviders(){
            api.getAll('/service-providers')  
            .then(response => {  
                if(response.data){
                    this.restaurantsList =  response.data
                }
            })  
            .catch(error => {  
                console.log(error)  
            })  
        },

        loadCities(){
            cityAPI.getAll('/city')  
            .then(response => {  
                if(response.data){
                    for(var i = 0; i < response.data.length; i++){
                        this.myOptions.push(response.data[i].name)
                    }
                }
            })  
            .catch(error => {  
                console.log(error)  
            })  
        },
        myChangeEvent(val){
            this.isCitySelected = true;
            this.saveCity(val)
            this.scrollIntoView()
        },
        mySelectEvent({id, text}){
            console.log({id, text})
        },
        storeData(data){
            this.saveServiceProvider(data)
        },
        scrollIntoView() {
            const el = this.$el.querySelector("#container");
            if (el) {
                el.scrollIntoView();
            }
        },

    } 

};
</script>
