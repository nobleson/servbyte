<template>
<div class="section-shaped my-0">
            <div class="shape shape-style-1 shape-primary">
            <span class="span-150"></span>
            <span class="span-50"></span>
            <span class="span-50"></span>
            <span class="span-75"></span>
            <span class="span-100"></span>
            <span class="span-75"></span>
            <span class="span-50"></span>
            <span class="span-100"></span>
            <span class="span-50"></span>
            <span class="span-100"></span>
        </div>
       <div class="container shape-container  align-items-center">
                <div class="row justify-content-center align-items-center">
                    <div class="col-lg-7 text-center ">
                        <p class="lead text-white mt-4 mb-5">… Safe and high quality service delivery</p>
                    </div>
                </div>
        </div>
        <div class="container  align-items-center">
             <div class="row">
                <b-card-group deck class="service-card-deck">
                            <b-card
                                title="Food"
                                img-src="img/brand/foods.jpg"
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 20rem;"
                                class="card-lift--hover shadow border-0 service-card"
                            >
                                <b-card-text>
                                Shop food from leading restaurants.
                                </b-card-text>
                                <router-link to="/foods" title="Food page">
                                    <b-button block  size="lg" variant="primary">View more</b-button>
                                </router-link>
                            </b-card>

                            <b-card
                                title="Laundry"
                                img-src="img/brand/laundry.jpg"
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 20rem;"
                                class="card-lift--hover shadow border-0 service-card"
                            >
                                <b-card-text>
                                Get high quality laundry services at your doorstep.
                                </b-card-text>
                                    <b-button block disabled >View more</b-button>
                            </b-card>
                            <b-card
                                title="Pharmacy Store"
                                img-src="img/brand/pharmacy.jpg"
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 20rem;"
                                class="card-lift--hover shadow border-0 service-card"
                            >
                                <b-card-text>
                                Buy high quality drugs for your health and family
                                </b-card-text>
                                 <b-button block disabled >View more</b-button>
                            </b-card>
                             <b-card
                                title="Grocery"
                                img-src="img/brand/grocery.jpg"
                                img-alt="Image"
                                img-top
                                tag="article"
                                style="max-width: 20rem;"
                                class="card-lift--hover shadow border-0 service-card"
                            >
                                <b-card-text>
                                Shop grocery easy as breaz.
                                </b-card-text>
                                    <b-button block disabled >View more</b-button>
                            </b-card>
                </b-card-group>
            </div>
        </div>
</div>
</template>
<script>

export default {

};
</script>
<style scoped>
    .service-card{
        margin-bottom: 50px
    }
    .service-card-deck{
        margin-bottom: 100px
    }

</style>
