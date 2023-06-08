import http from '../services/http/HttpService';

export default function StatisticsController() {
    const baseUri = `/statistics`

    return {
        findAll: function (currentPage, pageSize) {
            return http().GET(`${baseUri}?page=${currentPage}&size=${pageSize}&sort=dateStart,ASC`)
                .then(response => {
                    return response;
                }).catch(err => {
                    throw err;
                });
        },
        downloadAsHtml: function () {
            return http().GET(`${baseUri}/download/html`)
                .then(response => {
                    return response;
                }).catch(err => {
                    throw err;
                });
        },
        refreshStatistics: function () {
            return http().PATCH(`${baseUri}`, null)
                .then(response => {
                    return response;
                }).catch(err => {
                    throw err;
                });
        }
    }
}