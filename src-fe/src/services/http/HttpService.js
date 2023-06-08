import Axios from 'axios';

const instance = Axios.create();

instance.interceptors.response.use(function (response) {
    return response;
}, function (error) {
    return Promise.reject(error);
});

export default function HttpService() {
    const basicUri = '/api'
    const headers = {
        'Content-Type': 'application/json',
    }

    return {
        GET: function (url) {
            return instance.get(`${basicUri}${url}`, { headers }).then(response => {
                return response;
            }).catch(err => {
                throw err;
            });
        },
        PATCH: function (url, data) {
            return instance.patch(`${basicUri}${url}`, data, { headers }).then(response => {
                return response;
            }).catch(err => {
                throw err;
            })
        }
    }
}