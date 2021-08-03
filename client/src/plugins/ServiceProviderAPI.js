import axios from 'axios'    
const $api_url = 'http://localhost:8080';  

const instance = axios.create({  
  baseURL: $api_url,  
});  
  
export default {  
  
  // (R)ead  

  getAll: (url) => instance.get(url, {  
    transformResponse: [function (data) {  
      return data? JSON.parse(data)._embedded.serviceProviderList : data;  
    }]  
  }),  
  getById: (url) => instance.get(url, {  
    transformResponse: [function (data) {  
      return data
      //return data? JSON.parse(data)._embedded.serviceProviderList : data;  
    }]  
  }),  

}