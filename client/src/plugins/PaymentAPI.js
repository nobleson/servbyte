import axios from 'axios'    
const $api_url = 'http://localhost:8080';  

const instance = axios.create({  
  baseURL: $api_url,  
});  
  
export default {  
  async execute(method, resource, data, config) {  
        //let accessToken = await Vue.prototype.$auth.getAccessToken()  
        return instance({  
          method:method,  
          url: resource,  
          data,  
          headers: {  
            //    Authorization: `Bearer ${accessToken}`  
          },  
          ...config  
        })  
  },  
      
 getById: (url) => instance.get(url, {  
    transformResponse: [function (data) {  
        return data
        //return data? JSON.parse(data)._embedded.serviceProviderList : data;  
    }]  
    }),  
  createNew: (url, payload) => {  
        return instance.post(url, payload)  
   },  


}