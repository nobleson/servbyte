<template>
    <div>

        <div class="position-relative">
            <!-- shape Hero -->
        <section class="section section section-shaped my-0 overflow-hidden">
            <div class="shape shape-style-1 bg-gradient-warning shape-skew">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
            <div class="container py-0">
                <div class="row row-grid align-items-center">
                    <div class="col-md-6 order-lg-2 ml-lg-auto">
                        <div class="position-relative pl-md-5">
                            <img src="../assets/images/ill-2.svg" class="img-center img-fluid">
                        </div>
                    </div>
                    <div class="col-lg-6 order-lg-1">
                        <div class="d-flex px-3">
                            <div>
                                <icon name="ni ni-building" size="lg" class="bg-gradient-white" color="primary" shadow
                                      rounded></icon>
                            </div>
                            <div class="pl-4">
                                <h4 class="display-3 text-white">{{restaurant}}</h4>
                                <h5 class="display-3 text-white">{{location}}</h5>
                                <p class="text-white">Your taste is our delight</p>
                            </div>
                        </div>
                        <card shadow class="shadow-lg--hover mt-5">
                            <div class="d-flex px-3">
                                <div>
                                    <icon name="ni ni-satisfied" gradient="success" color="white" shadow
                                          rounded></icon>
                                </div>
                                <div class="pl-4">
                                    <h5 class="title text-success">About</h5>
                                    <p>A leading quality  food provider in {{location}}</p>
                                    <a href="#" class="text-success">Learn more</a>
                                </div>
                            </div>
                        </card>
                    </div>
                </div>
            </div>
        </section>
        <section class="section section-lg">
            <div class="container" id="container">
                <div class="row justify-content-center text-center mb-lg">
                    <div class="col-lg-8">
                        <h2 class="display-3">Menu</h2>
                        <p class="lead text-muted">View and buy with us</p>
                    </div>
                </div>
                <div class="row">
                        <div class="col-md-6 col-lg-3 mb-5 mb-lg-0" v-for="menu in menuList" :menu="menu" :key="menu.id">
                            <div class="px-4">

                                <img :src="getImgUrl(menu.photo)"
                                    class="img-center img-fluid shadow shadow-lg--hover"
                                    style="width: 200px;"/>
                                <div class="pt-4 text-center">
                                    <h5 class="title">
                                        <span class="d-block mb-1">{{menu.name}}</span>
                                        <small class="h6 text-muted"> {{menu.description}}</small>
                                    <br>
                                    <h6>Prepared in {{menu.duration}} mins</h6>
                                    <small class="h5"><b>&#x20A6;</b>{{menu.price}}</small>
                                    </h5>
                                    <div class="mt-3">
                                        <b-button pill variant="primary" :to="{ name: 'order', params: { id: menu.id }}" title="Order page" @click="storeMealData(menu)">Buy now</b-button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </div>
                    </div>
                </section>
<!--                     <div class="col-md-6 col-lg-3 mb-5 mb-lg-0">
                        <div class="px-4">
                            <img :src="afangImage"
                                 class="img-center img-fluid shadow shadow-lg--hover"
                                 style="width: 200px;">
                            <div class="pt-4 text-center">
                                <h5 class="title">
                                    <span class="d-block mb-1">Afang</span>
                                    <small class="h6 text-muted"> Made with a combination of afang leaves, water leaves, dried fish, beef, onions, red palm oil, ground crayfish, and seasoning cubes.</small>
                                <br>
                                <h6>Prepared in 35 mins</h6>
                                <small class="h5"><b>&#x20A6;</b>3500</small>
                                </h5>
                                <div class="mt-3">
                                     <router-link to="/order" title="Order page">
                                          <b-button pill variant="primary">Buy</b-button>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 mb-5 mb-lg-0">
                        <div class="px-4">
                            <img v-lazy="'img/brand/eba.jpg'"
                                 class="img-center img-fluid shadow shadow-lg--hover"
                                 style="width: 200px;">
                            <div class="pt-4 text-center">
                                <h5 class="title">
                                    <span class="d-block mb-1">Eba</span>
                                    <small class="h6 text-muted">Staple food consisting of cassava flour combined with hot water. </small>
                                <br>
                                <h6>Prepared in 15 mins</h6>
                                <small class="h5"><b>&#x20A6;</b>1500</small>
                                </h5>
                                <div class="mt-3">
                                     <router-link to="/order" title="Order page">
                                          <b-button pill variant="primary">Buy</b-button>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 mb-5 mb-lg-0">
                        <div class="px-4">
                            <img v-lazy="'img/brand/moin-moin.jpg'"
                                 class="img-center img-fluid shadow shadow-lg--hover"
                                 style="width: 300px;">
                            <div class="pt-4 text-center">
                                <h5 class="title">
                                    <span class="d-block mb-1">Moin Moin</span>
                                    <small class="h6 text-muted">Consist of ground beans or black-eyed peas, onions, oil, and freshly ground peppers such as chil</small>
                                <br>
                                <h6>Prepared in 30 mins</h6>
                                <small class="h5"><b>&#x20A6;</b>2000</small>
                                </h5>
                                <div class="mt-3">
                                   <router-link to="/order" title="Order page">
                                          <b-button pill variant="primary">Buy</b-button>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-3 mb-5 mb-lg-0">
                        <div class="px-4">
                            <img v-lazy="'img/brand/suya.jpg'"
                                 class="img-center img-fluid shadow shadow-lg--hover"
                                 style="width: 300px;">
                            <div class="pt-4 text-center">
                                <h5 class="title">
                                    <span class="d-block mb-1">Suya</span>
                                    <small class="h6 text-muted">Thin slices of beef generously seasoned, then roasted over open charcoal grills.</small>
                                <br>
                                <h6>Prepared in 30 mins</h6>
                                <small class="h5"><b>&#x20A6;</b>2000</small>
                                </h5>
                                <div class="mt-3">
                                     <router-link to="/order" title="Order page">
                                          <b-button pill variant="primary">Buy</b-button>
                                    </router-link>
                                </div>
                            </div>
                        </div>
                    </div> -->


<!--         <section class="section section-lg pt-0">
            <div class="container">
                <card gradient="warning"
                      no-body
                      shadow-size="lg"
                      class="border-0">
                    <div class="p-5">
                        <div class="row align-items-center">
                            <div class="col-lg-8">
                                <h3 class="text-white">We made website building easier for you.</h3>
                                <p class="lead text-white mt-3">I will be the leader of a company that ends up being
                                    worth billions of dollars, because I got the answers. I understand culture.</p>
                            </div>
                            <div class="col-lg-3 ml-lg-auto">
                                <base-button tag="a" href="https://www.creative-tim.com/product/vue-argon-design-system"
                                             type="white" block size="lg">
                                    Download Vue
                                </base-button>
                            </div>
                        </div>
                    </div>
                </card>
            </div>
        </section> -->
      </div>
    </div>
</template>

<script>
import afang from "../assets/images/afang.jpg"
import jellof from "../assets/images/jellof-rice.jpg"
import api from '../plugins/MealAPI';
import {mapGetters, mapActions,mapState,mapMutations } from 'vuex'

export default {
  name: "restaurant",
  components: {},
  data(){
      return{
        menuList: [],  
        afangImage: afang,
        jellofImage: jellof,
      }
  },
  mounted(){
      this.loadMenus()
      this.scrollIntoView()
  },
  computed: {
     // ...mapState(['serviceProvider']),
     //...mapGetters({assesors: 'assesors/getAssesors', isContentLoading: 'assesors/getLoaderStatus'}),
    ...mapGetters({serviceProvider:'getServiceProvider', city:'getCity'}),
    restaurant(){return this.serviceProvider.name},
    location(){return this.city}
  },
  methods: {
    ...mapMutations(['saveServiceProvider','saveCity','saveMeal']),
    loadMenus(){
        api.getAll('/meals')  
        .then(response => {  
            console.log("Menu loaded: ", response.data) 
            if(response.data){
                this.menuList =  response.data
            }
        })  
        .catch(error => {  
            console.log(error)  
        })  
    },
    getImgUrl(pic) {
        switch(pic){
            case 'jellof-rice.jpg':
                return this.jellofImage;
                break;
            case 'afang.jpg':
                return this.afangImage

        }
    },
    storeMealData(meal){
        this.saveMeal(meal)
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
