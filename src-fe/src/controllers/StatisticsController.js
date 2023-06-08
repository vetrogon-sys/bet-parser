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
                    const description = response.headers.get('content-disposition');
                    const fileName = description.substring(
                        description.indexOf("\"") + 1, 
                        description.lastIndexOf("\"")
                    );
                    const url = window.URL.createObjectURL(new Blob([response.data]));
                    const link = document.createElement("a");
                    link.href = url;
                    link.setAttribute(
                        "download",
                        `${fileName}`
                    );
                    document.body.appendChild(link);
                    link.click();

                    // Clean up and remove the link
                    link.parentNode.removeChild(link);
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